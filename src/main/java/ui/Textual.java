package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Textual {
    
    private Scanner scanner;
    
    public Textual(Scanner scanner) {
        this.scanner = scanner;
    }
    
    public void run() {
        System.out.println("Welcome to Lukuvinkkikirjasto.");
        System.out.println("give an command, quit exits");
        System.out.println("");
        
        String command = null;
        
        while (true) {
            command = askUser("give command, recognized commands: [new, quit]");
            System.out.println("new = add new vink");
            
            System.out.println("command: " + command);
            
            if (command.equals("new")) {
                String title = askUser("Title");
                String type = askUser("Type");
                List<String> tags = askForTags();
                String comment = askUser("Comment");
                
                System.out.println("got:");
                System.out.println("title: " + title);
                System.out.println("type: " + type);
                System.out.println("tags:" + tags);
                tags.forEach(t -> System.out.println(t));
                System.out.println("comment:" + comment);
                //save new vink
            } else if (command.equals("quit")) {
                break;
            }
        }
    }
    
    private List<String> askForTags() {
        List<String> tags = new ArrayList<>();
        String tag = "";
        
        System.out.println("enter tags, one per line. empty string ends.");
        while (true) {
            tag = askUser(" give tag:");
            if (tag.isEmpty()) break;
            tags.add(tag);
        }
        
        return tags;
    }
    
    /**
     * Prints the given string parameter to the console
     * and appends " :" to the end. Returns user
     * input string.
     * @param question
     * @return user input
     */
    private String askUser(String question) {
        System.out.print(question + ": ");
        return scanner.nextLine().trim().toLowerCase();
    }
    
}
