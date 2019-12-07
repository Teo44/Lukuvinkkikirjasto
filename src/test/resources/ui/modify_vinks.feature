Feature: As a user I want to be able to modify the vinks

    Scenario: user can add a vink
        Given command new is selected
        When  headline "Testi" and type "testiTyyppi" and tags "testitagi1" and comment "testi komeentti" and link "https://testilinkki.com" is selected
        Then  system will respond with "Vink created!"


    Scenario: user can list all the vinks
        Given command list is selected
        Then  system will respond with list "Testi" , "testiTyyppi" , "testitagi1" , "testi komeentti" , "https://testilinkki.com"


    Scenario: user can modify a vink
        Given command modify is selected
        When  headline "Testi" and new tags "uusitagi1" and "uusitagi2" are selected
        Then  system will respond with list "Testi" , "testiTyyppi" , "testitagi1, uusitagi1, uusitagi2" , "testi komeentti" , "https://testilinkki.com"
