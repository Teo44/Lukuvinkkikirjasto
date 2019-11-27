
package ui;

import database.VinkDAO;
import domain.Vink;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.ArrayList;
import java.util.List;
import logic.Logic;
import static org.junit.Assert.*;

public class Stepdefs {  
    Vink vink;
    VinkDAO dao;
    Logic logic;
    Textual ui;
    List<String> inputLines;
    
    @Before
    public void setup() {
        dao = new VinkDAO("testcucumber.cb");
        logic = new Logic(dao);
        inputLines = new ArrayList<>();
    }
            
    @Given("command new is selected")
    public void commandNewIsSelected() {
        inputLines.add("new");
    }

    @When("headline {string} and type {string} and tags {string} and comment {string} is selected")
    public void newVinkInfoEntered(String headline, String type, String tags, String comment) {
        inputLines.add(headline);
        inputLines.add(type);
        inputLines.add(tags);
        inputLines.add("");
        inputLines.add(comment);
    }
    
}
