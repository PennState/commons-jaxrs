package edu.psu.enumeration;


public enum Residency {

	PA("Pennsylvania"),
	NON_PA("Non-Pennsylvania"),
	INTERNATIONAL("International");

	private String displayName_;
	
	Residency(String displayName) {
		displayName_ = displayName;
	}

	public String getDisplayName() {
		return displayName_;
	}

	public static Residency fromDisplayName(String displayName) {
		for (Residency residency : values()) {
			if (residency.getDisplayName().equals(displayName)) {
				return residency;
			}
		}
		return null;
	}
}
