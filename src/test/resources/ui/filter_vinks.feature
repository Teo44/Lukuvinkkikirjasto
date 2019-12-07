Feature: As a user i want to be able to search with filtering and have the search term highlighted

    Scenario: user can add a vink
        Given command new is selected
        When  headline "filterTesti" and type "filterTestiTyyppi" and tags "FilTestitagi1" and comment "FilTesti komeentti" and link "https://Fillinkki.com" is selected
        Then  system will respond with "Vink created!"


    Scenario: user can filter vinks by anything
        Given command filter is selected
        When  filter word "filterTesti" is selected
        Then  system will give a filtered list "filterTesti" , "filterTestiTyyppi" , "FilTestitagi1" , "FilTesti komeentti" , "https://Fillinkki.com"