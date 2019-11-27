package ui;

import java.util.ArrayList;
import java.util.Scanner;
import logic.Logic;

public class Textual {
    
    private Scanner scanner;
    private Logic logic;
    
    public Textual(Scanner scanner, Logic logic) {
        this.scanner = scanner;
        this.logic = logic;
    }
    
    public void run() {
        System.out.println("Welcome to Lukuvinkkikirjasto.");
        System.out.println("give an command, quit exits");
        System.out.println("------------------------------");
        
        String command = null;
        
        while (true) {
            System.out.println("");
            
            command = askUser("give command, recognized commands: [new, list, delete, quit]");
            command = command.toLowerCase();
            
            System.out.println("new = add new vink");
            System.out.println("");
            
            if (command.equals("new")) {
                String title = askUser("Title");
                String type = askUser("Type");
                ArrayList<String> tags = askForTags();
                String comment = askUser("Comment");
                
                boolean vinkCreatedSuccesfully = logic.saveVink(title, type, tags, comment);
                
                if (vinkCreatedSuccesfully) {
                    System.out.println("Vink created!");
                } else {
                    System.out.println("Something went wrong, try again");
                }
                
            } else if (command.equals("list")) {
                System.out.println("");
                System.out.println("All vinks:");
                System.out.println("----------");
                
                logic.getAllVinks().forEach(v -> {
                    System.out.println("");
                    System.out.println("Headline: " + v.getHeadline());
                    System.out.println("Type: " + v.getType());
                    System.out.println("Tags: ");
                    v.getTags().forEach(t -> System.out.println(" " + t));
                    System.out.println("Comment: " + v.getComment());
                });
            } else if (command.equals("delete")) {
                System.out.println("");
                System.out.println("Deleting vink by Title");
                String title = askUser("Title of vink to delete");
                if (!title.trim().isEmpty()) {
                    boolean vinkDeletedSuccesfully = logic.deleteVinkByTitle(title);
                    if (vinkDeletedSuccesfully) {
                        System.out.println("Vink deleted");
                    } else {
                        System.out.println("Soemthing went wrong when trying to delete vink");
                    }
                }            
            } else if (command.equals("quit")) {
                break;
            } else {
                System.out.println("Unrecognized Command");
            }
        }
        
        System.out.println("");
        System.out.println("Exiting!..");
    }
    
    private ArrayList<String> askForTags() {
        ArrayList<String> tags = new ArrayList<>();
        String tag = "";
        
        System.out.println("Enter tags, one per line. empty string ends.");
        while (true) {
            tag = askUser(" Give tag");
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
        return scanner.nextLine().trim();
    }
    
}
