package edu.psu.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;
import javax.faces.el.PropertyNotFoundException;

/*
	This class contains some experimentaly code that jsg160 was working with in an attempt to dynamically...
		1. Find the server.log - DONE
		2. Open the server.log file and parse it's contents - DONE
		3. Identify and classify ERRORs and stack traces - NOT DONE
		4. Inventory the issues observed on the production machines and prioritize by count - NOT DONE

	It could be useful for other stuff too.  :)

	CONCERNS:
	- You could only see the errors on the server assigned to you by the load balancer.
	- Needs to be secured to protect log data from the nosey ones.
*/

public class EnvironmentUtilities {

	// Attempts to look up the name of the server the code is running on by:
	//		1. Looking up known system properties (JBoss specific so far).
	//		2. Looking up known environment variables (Linux/Apache, Windows).
	//		3. Doing a reverse lookup on the default IP Address.
	//		4. Making a system call to `hostname` as a last resort. (Linux, OSX, Windows)
	public static String getHostname() {
		String hostname = null;

		List<String> propNames = Arrays.asList( "jboss.qualified.host.name","jboss.server.name","jboss.host.name","jboss.node.name" );
		for ( String propName : propNames ) {
			hostname = System.getProperty( propName );
			if( hostname != null && ! hostname.isEmpty() ) {
				return hostname;
			}
		}
		List<String> envNames = Arrays.asList( "HOSTNAME","COMPUTERNAME" );
		for ( String envName : envNames ) {
			hostname = System.getenv(envName );
			if( hostname != null && ! hostname.isEmpty() ) {
				return hostname;
			}
		}
		try {
			InetAddress addr;
			addr = InetAddress.getLocalHost();
			hostname = addr.getHostName();
			if( hostname != null && ! hostname.isEmpty() ) {
				return hostname;
			}
		} catch (UnknownHostException ex) {}
		if( hostname == null || hostname.isEmpty() ) {
			int rv;
			Runtime rt = Runtime.getRuntime();
			try {
				Process pr = rt.exec( "hostname" );
				BufferedReader ibr = new BufferedReader( new InputStreamReader( pr.getInputStream() ) );
				rv = pr.waitFor();
				while( ibr.ready() ) {
					hostname = ibr.readLine();
				}
			} catch (IOException | InterruptedException e) {}
		}
		
		if( hostname == null || hostname.isEmpty() ) {
			return "UNKNOWN HOST, SORRY";
		}
		return hostname;
	}

	
	// MORE USEFUL FOR EXPLORING THAN USE IN A PRODUCTION SYSTEM.
	public static String getAllSystemProperties() {
		StringBuilder output = new StringBuilder();
		List<String> sortedList = new ArrayList();
		
		Properties p = System.getProperties();
		Enumeration pNames = p.propertyNames();
		while( pNames.hasMoreElements() ) {
			sortedList.add( (String) pNames.nextElement() );
		}
		Collections.sort( sortedList );
		for( String key : sortedList ) {
			if( Pattern.matches("^.*(credential|password).*$", key) ) {
				output.append( key+" = ********\n" );
			} else {
				output.append( key+" = "+p.getProperty(key)+"\n" );
			}
		}
		return output.toString();
	}

	
	// YES, THIS IS OBVIOUSLY TRIVIAL, BUT I WANTED TO INCLUDE IT FOR COMPLETENESS.
	public static String getSystemProperty( String name ) {
		return System.getProperty( name );
	}

	
	// WORKS OK, BUT I MIGHT BE MISSING A USE CASE OR TWO.
	// NO CACHING, BUT ALSO NEVER OUT OF DATE.
	public static String getFileProperty( String filename, String property ) throws PropertyNotFoundException, IOException {
		String logFilename = filename.replaceFirst( "^file:", "" ); // or use URL instead of FILENAME?
		File file = new File( logFilename );
		if( file.exists() && file.isFile() && file.canRead() ) {
			Reader fr =  new FileReader( file );
			Properties prop = new Properties();
			prop.load( fr );
			String rv = prop.getProperty( property );
			if( rv != null ) {
				return rv;
			} else {
				System.err.println( "getFileProperty( "+logFilename+", "+property+" ) - Property not found in file" );
			}
		} else {
			System.err.println( "getFileProperty( "+logFilename+", "+property+" ) - File not found" );
		}
		throw new PropertyNotFoundException( "Property '"+property+"' was not found in file '"+logFilename+"'" );
	}
	

}
