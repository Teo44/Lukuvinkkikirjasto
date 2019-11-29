
package ui;

import database.VinkDAO;
import domain.Vink;
import io.StubIO;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import logic.Logic;
import static org.junit.Assert.*;

public class Stepdefs {  
    Textual text;
    VinkDAO dao;
    Logic logic;
    StubIO io;
    List<String> inputLines;
    
    @Before
    public void setup() {
        dao = new VinkDAO("test.db");
        logic = new Logic(dao);
        inputLines = new ArrayList<>();
    }
            
    @Given("^command new is selected")
    public void commandNewIsSelected() {
        inputLines.add("new");
    }
    
    @Given("^command delete is selected")
    public void commandDeleteIsSelected() {
        inputLines.add("delete");
    }
    
    @Given("^command list is selected")
    public void commandListIsSelected() {
        inputLines.add("list");
        inputLines.add("quit");
        
        io = new StubIO(inputLines);
        text = new Textual(logic, io);
        text.run();
    }

    @When("headline {string} and type {string} and tags {string} and comment {string} and link {string} is selected")
    public void newVinkInfoEntered(String headline, String type, String tags, String comment, String link) {
        inputLines.add(headline);
        inputLines.add(type);
        inputLines.add(tags);
        inputLines.add("");
        inputLines.add(comment);
        inputLines.add(link);
        inputLines.add("quit");
        
        io = new StubIO(inputLines);
        text = new Textual(logic, io);
        text.run();
    }
    
    @When("headline {string} is selected")
    public void selecAVink(String headline) {
        inputLines.add(headline);
        inputLines.add("quit");
        
        io = new StubIO(inputLines);
        text = new Textual(logic, io);
        text.run();
    }
   
    
    @Then("system will respond with {string}")
    public void systemWillRespondWith(String expectedOutput) {
        assertTrue(io.getPrints().contains(expectedOutput));
        
    }
    
}
