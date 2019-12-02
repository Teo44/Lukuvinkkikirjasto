
package ui;

import database.VinkDAOSqlite;
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
    VinkDAOSqlite dao;
    Logic logic;
    StubIO io;
    List<String> inputLines;
    
    @Before
    public void setup() {
        dao = new VinkDAOSqlite("test.db");
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
    
    @Given("^command modify is selected")
    public void commandModifyIsSelected() {
        inputLines.add("modify");
    }
    
    @Given("^command nonexistant is selected")
    public void commandNonexistantIsSelected() {
        inputLines.add("nonexistant");
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
    
    @When("headline {string} and new tags {string} and {string} are selected")
    public void newTagsAreSelected(String headline, String tagi1, String tagi2) {
        inputLines.add(headline);
        inputLines.add("");
        inputLines.add("");
        inputLines.add("");
        inputLines.add(tagi1);
        inputLines.add(tagi2);
        inputLines.add("");
        inputLines.add("");
        inputLines.add("");
        inputLines.add("list");
        inputLines.add("quit");
        
        io = new StubIO(inputLines);
        text = new Textual(logic, io);
        text.run();
    }
   
    
    @Then("system will respond with {string}")
    public void systemWillRespondWith(String expectedOutput) {
        assertTrue(io.getPrints().contains(expectedOutput));
        
    }
    
    @Then("system will respond with list {string} , {string} , {string} , {string} , {string}")
    public void systemWillRespondWithList(String e1, String e2, String e3, String e4, String e5) {
        ArrayList vink = new ArrayList<>();
        vink.add("Headline: " + e1);
        vink.add("Type: " + e2);
        vink.add("Tags: " + e3);
        vink.add("Comment: " + e4);
        vink.add("Link: " + e5);
        assertTrue(io.getPrints().containsAll(vink));
    }
    
}
