Feature: user can open vinks

    Scenario: user can add a vink
        Given command new is selected
        When  headline "OpenTesti" and type "testiTyyppi" and tags "testitagi1" and comment "testi komeentti" and link "https://testilinkki.com" is selected
        Then  system will respond with "Vink created!"


    Scenario: user can open a vink
        Given command open is selected
        When  headline "OpenTesti" is selected
        Then  system will respond with "Vink opened correctly" or "Something went wrong when trying to connect to desktop, try again!"


    Scenario: user cannot open a vink that doesn't exist
        Given command open is selected
        When  headline "Olematon" is selected
        Then  system will respond with "Something went wrong when trying to open a vink with title: Olematon, try again!"


    Scenario: user can add a vink
        Given command new is selected
        When  headline "OpenTesti2" and type "testiTyyppi" and tags "testitagi1" and comment "testi komeentti" and link "" is selected
        Then  system will respond with "Vink created!"


    Scenario: user cannot open a link that doesn't exist
        Given command open is selected
        When  headline "OpenTesti2" is selected
        Then  system will respond with "Something went wrong when trying to open a vink with no path, try again!"
