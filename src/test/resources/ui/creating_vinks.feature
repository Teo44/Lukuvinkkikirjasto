Feature: User can create, set and get vinks and their values.

    Scenario: user can create a vink
	Given command create vink is selected
	When  headline "Testi" and type "testiTyyppi" and tags "TestiTagi1, "TestiTagi2" and comment "testi kommentti" is selected
	Then  system will respond with getting all the correct gets
