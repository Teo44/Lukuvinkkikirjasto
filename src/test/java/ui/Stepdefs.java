
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
import io.Network;
import static org.junit.Assert.*;

public class Stepdefs {  
    Textual text;
    VinkDAOSqlite dao;
    Logic logic;
    StubIO io;
    Network network;
    List<String> inputLines;
    
    @Before
    public void setup() {
        network = new Network();
        dao = new VinkDAOSqlite("test.db");
        logic = new Logic(dao, network);
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
    
    @Given("^command open is selected")
    public void commandOpenIsSelected() {
        inputLines.add("open");
    }
    
    @Given("^command read is selected")
    public void commandReadIsSelected() {
        inputLines.add("read");
    }
    
    @Given("^command mark is selected")
    public void commandMarkIsSelected() {
        inputLines.add("mark");
    }
    
    @Given("^command new with isbn is selected")
    public void commandNewWithISBNIsSelected() {
        inputLines.add("new");
        inputLines.add("isbn");
    }
    
    @Given("^command filter is selected")
    public void commandFilterIsSelected() {
        inputLines.add("filter");
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
        inputLines.add("man");
        inputLines.add(headline);
        inputLines.add(type);
        inputLines.add("");
        inputLines.add(tags);
        inputLines.add("");
        inputLines.add(comment);
        inputLines.add(link);
        inputLines.add("quit");
        
        io = new StubIO(inputLines);
        text = new Textual(logic, io);
        text.run();
    }
    
    @When("headline {string} and type {string} and author {string} and tags {string} and comment {string} is selected")
    public void newBookIsAdded(String headline, String type, String author, String tag, String comment) {
        inputLines.add("man");
        inputLines.add(headline);
        inputLines.add(type);
        inputLines.add(author);
        inputLines.add(tag);
        inputLines.add("");
        inputLines.add(comment);
        inputLines.add("");
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
    
    @When("isbn {string} is selected")
    public void slecetAISBN(String isbn) {
        inputLines.add(isbn);
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
   
    @When("filter word {string} is selected")
    public void filterWordIsSelected(String word) {
        inputLines.add(word);
        inputLines.add("quit");
        
        io = new StubIO(inputLines);
        text = new Textual(logic, io);
        text.run();
    }
    
    @When("reading status {string} is selected")
    public void readingStatusIsSelected(String status) {
        inputLines.add(status);
        inputLines.add("quit");
        
        io = new StubIO(inputLines);
        text = new Textual(logic, io);
        text.run();
    }
            
    @When("headline {string} and status {string} are selected")  
    public void markingVinkIsChanging(String headline, String status) {
        inputLines.add(headline);
        inputLines.add(status);
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
    
    @Then("system will give a filtered list {string} , {string} , {string} , {string} , {string}")
    public void systemWillRespondWithFilteredList(String e1, String e2, String e3, String e4, String e5) {
        ArrayList vink = new ArrayList<>();
        vink.add("Headline: " + "\u001b[32;1m" + e1 + "\u001B[0m");
        vink.add("Type: " + "\u001b[32;1m" + "filterTesti" + "\u001B[0m" + "Tyyppi");
        vink.add("Tags: " + e3);
        vink.add("Comment: " + e4);
        vink.add("Link: " + e5);
        
        assertTrue(io.getPrints().containsAll(vink));
    }
    
    @Then("system will respond with book {string} , {string} , {string}")
    public void systemWillRespondWithBook(String title, String type, String author) {
        ArrayList book = new ArrayList<>();
        book.add("Headline: " + title);
        book.add("Type: " + type);
        book.add("Author: " + author);
        assertTrue(io.getPrints().containsAll(book));
    }
    
    @Then("system will respond with reading progress {string} and headline {string}")
    public void readingStatusCheckForBook(String status, String headline) {
        ArrayList rStatus = new ArrayList<>();
        if (inputLines.contains("1")) {
            rStatus.add("Reading progress: \u001b[31;1m" + status+"\u001B[0m");
        } else if (inputLines.contains("2")) {
            rStatus.add("Reading progress: \u001b[33;1m" + status+"\u001B[0m");
        } else {
            rStatus.add("Reading progress: \u001b[32;1m" + status+"\u001B[0m");
        }
        rStatus.add("Headline: " + headline);
        assertTrue(io.getPrints().containsAll(rStatus));
    }
    
    @Then("system will respond with {string} or {string}")
    public void systemWillRespondWithOr(String expected1, String expected2) {
        assertTrue(io.getPrints().contains(expected1) || io.getPrints().contains(expected2));
    }
}
