Feature: As a user I want to be able to delete the vinks if I decide not to read them.

    Scenario: user can add a vink
        Given command new is selected
        When  headline "DeleteTesti" and type "testiTyyppi" and tags "testitagi1" and comment "testi komeentti" and link "https://testilinkki.com" is selected
        Then  system will respond with "Vink created!"


    Scenario: user can delete a vink
        Given command delete is selected
        When  headline "DeleteTesti" is selected
        Then  system will respond with "Vink deleted"


    Scenario: user cannot delete a vink that doesn,t exist
        Given command delete is selected
        When  headline "DeleteTesti" is selected
        Then  system will respond with "Something went wrong when trying to delete vink, try again!"
