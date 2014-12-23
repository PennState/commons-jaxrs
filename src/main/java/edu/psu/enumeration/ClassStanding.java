package edu.psu.enumeration;

public enum ClassStanding {

	FR("Freshman", "0 - 27"), 
	SO("Sophomore", "27.1 - 59"), 
	JR("Junior", "59.1 - 91"),
	SR("Senior", "91.1 or More"),
	FirstYear("First Year"), 
	SecondYear("Second Year"), 
	ThirdYear("Third Year"), 
	FourthYear("Fourth Year");

	@Override
	public String toString() {
		return this.label;
	}

	private String label;
	private String creditRange;

	public String getLabel() {
		return label;
	}

	public String getCreditRange() {
		return creditRange;
	}
	
	private ClassStanding(String label) {
		this.label = label;
	}
	
	private ClassStanding(String label, String creditRange) {
		this.label = label;
		this.creditRange = creditRange;
	}

}
