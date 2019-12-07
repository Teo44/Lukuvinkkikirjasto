Feature: user can delete vinks

    Scenario: user can delete a vink
        Given command delete is selected
        When  headline "Testi" is selected
        Then  system will respond with "Vink deleted"


    Scenario: user cannot delete a vink that doesn,t exist
        Given command delete is selected
        When  headline "Olematon" is selected
        Then  system will respond with "Something went wrong when trying to delete vink, try again!"
