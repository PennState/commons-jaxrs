/**
 * 
 */
package edu.psu.enumeration;

/**
 * @author svk3
 * 
 */
public enum Degree {
	ASC("Associate"), 
	BAC("Baccalaureate"), 
	MAS("Master's"), 
	DOC("Doctorate"), 
	LAW("Law"), 
	MED("Medical");

	private String displayName_;

	Degree(String displayName) {
		displayName_ = displayName;
	}

	public String getDisplayName() {
		return displayName_;
	}

	public static Degree fromDisplayName(String displayName) {
		for (Degree degree : values()) {
			if (degree.getDisplayName().equals(displayName)) {
				return degree;
			}
		}
		return null;
	}
}
