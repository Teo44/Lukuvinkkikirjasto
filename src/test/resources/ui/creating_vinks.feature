Feature: User can create vinks

    Scenario: user can add a vink
        Given command new is selected
        When  headline "Testi" and type "testiTyyppi" and tags "testitagi1" and comment "testi komeentti" and link "https://testilinkki.com" is selected
        Then  system will respond with "Vink created!"

    Scenario: user can list all the vinks
        Given command list is selected
        Then  system will respond with "Link: https://testilinkki.com"


    Scenario: user can delete a vink
        Given command delete is selected
        When  headline "Testi" is selected
        Then  system will respond with "Vink deleted"



