Feature: As a user I want to be able to add books using only their ISBN

    Scenario: user can add a book with ISBN
        Given command new with isbn is selected
        When  isbn "97834UE0348" is selected
        Then  system will respond with "Vink created!"


    Scenario: user can see the newly added book
        Given command list is selected
        Then  system will respond with book "fifty shades of grey" , "book" , "E. L. James"


    Scenario: user cannot add a book with invalid ISBN
        Given command new with isbn is selected
        When  isbn "010101010101" is selected
        Then  system will respond with "Something went wrong when trying to add new vink, try again!"