Feature: As a user I want to be able to browse my vinks to find things to read.

    Scenario: user can add a vink
        Given command new is selected
        When  headline "ListaTesti" and type "testiTyyppi" and tags "testitagi1" and comment "testi komeentti" and link "https://testilinkki.com" is selected
        Then  system will respond with "Vink created!"


    Scenario: user can list all the vinks
        Given command list is selected
        Then  system will respond with list "ListaTesti" , "testiTyyppi" , "testitagi1" , "testi komeentti" , "https://testilinkki.com"

