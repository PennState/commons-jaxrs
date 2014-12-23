package edu.psu.enumeration;
/*
 * the GI is expecting 
 * 	"Commuter", "1" 
 *  "Off Campus", "2" 
 *  "On Campus", "3"
 */
public enum Housing {

	ON_CAMPUS("On Campus"),
	OFF_CAMPUS("Off Campus"),
	COMMUTER("Commuter");

	private String displayName_;
	
	Housing(String displayName) {
		displayName_ = displayName;
	}

	public String getDisplayName() {
		return displayName_;
	}

	public static Housing fromDisplayName(String displayName) {
		for (Housing housing : values()) {
			if (housing.getDisplayName().equals(displayName)) {
				return housing;
			}
		}
		return null;
	}
	
}
