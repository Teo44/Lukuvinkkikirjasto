Feature: As a user I want to be able to set ’luettu’, ‘alettu lukemaan’ attributes to vinks

    Scenario: user can add a book
        Given command new is selected
        When  headline "Harry Potter" and type "Book" and author "J.K. Rowlings" and tags "Magic" and comment "Magical book about magical school" is selected
        Then  system will respond with "Vink created!"


    Scenario: user can check reading status
        Given command read is selected
        When  reading status "1" is selected
        Then  system will respond with reading progress "[                              ] 0%" and headline "Harry Potter"


    Scenario: user can set book as reading
        Given command mark is selected
        When  headline "Harry Potter" and status "2" are selected
        Then  system will respond with "The reading status of vink Harry Potter has been updated!"

    Scenario: user can check reading status
        Given command read is selected
        When  reading status "2" is selected
        Then  system will respond with reading progress "[===============               ] 50%" and headline "Harry Potter"


    Scenario: user can check reading status
        Given command read is selected
        When  reading status "1" is selected
        Then  system will respond with reading progress "[                              ] 0%" and headline "Harry Potter"
