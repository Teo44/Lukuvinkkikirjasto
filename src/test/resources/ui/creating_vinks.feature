Feature: User can create vinks

    Scenario: user can add a vink
	Given command new is selected
	When  headline "Testi" and type "testiTyyppi" and tags "TestiTagi1;TestiTagi2" and comment "testi kommentti" is selected
	Then  system will respond with getting all the correct gets
