package edu.psu.enumeration;

public enum CollegeCode {

	AB("Abington College"),
	AL("Altoona College"),
	BC("Behrend College"),
	BL("Berks College"),
	CA("Capital College"),
	AG("College of Agricultural Sciences"),
	AA("College of Arts and Architecture"),
	CM("College of Communications"),
	EM("College of Earth and Mineral Sciences"),
	ED("College of Education"),
	EN("College of Engineering"),
	HH("College of Health and Human Development"),
	IS("College of Information Sciences and Technology"),
	NR("College of Nursing"),
	LA("College of The Liberal Arts"),
	DU("Division of Undergraduate Studies"),
	SC("Eberly College of Science"),
	IC("Intercollege Undergraduate Programs"),
	IA("International Affairs"),
	BA("Smeal College of Business"),
	CC("University College"),

	MD("College Of Medicine"),
	LW("Dickinson School of Law"),
	ID("Intercollege Graduate Programs"),
	GV("School Of Graduate Professional Studies"),
	
	GN("Graduate Non-Degree"),
	HP("Health, Physical Education & Recreation"),
	HD("Human Development"),
	MS("Rotc"),
	UN("Undergraduate Non-Degree"),
	XX("This Code Does Not Represent A Valid College But");
	
	private String collegeName_;

	private CollegeCode(String collegeName) {
		collegeName_ = collegeName;
	}

	public String getCollegeName() {
		return collegeName_;
	}

}
