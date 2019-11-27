package ui;

import java.util.ArrayList;
import java.util.Scanner;
import logic.Logic;
import io.IO;

public class Textual {
    
    private IO io;
    private Logic logic;
    
    public Textual(Logic logic, IO io) {
        this.logic = logic;
        this.io = io;
    }
    
    public void run() {
        io.print("Welcome to Lukuvinkkikirjasto.");
        io.print("give an command, quit exits");
        io.print("------------------------------");
        
        String command = null;
        
        while (true) {
            io.print("");
            
            command = io.askUser("give command, recognized commands: [new, list, delete, quit]");
            command = command.toLowerCase();
            
            io.print("new = add new vink");
            io.print("");
            
            if (command.equals("new")) {
                String title = io.askUser("Title");
                String type = io.askUser("Type");
                ArrayList<String> tags = askForTags();
                String comment = io.askUser("Comment");
                
                boolean vinkCreatedSuccesfully = logic.saveVink(title, type, tags, comment);
                
                if (vinkCreatedSuccesfully) {
                    io.print("Vink created!");
                } else {
                    io.print("Something went wrong, try again");
                }
                
            } else if (command.equals("list")) {
                io.print("");
                io.print("All vinks:");
                io.print("----------");
                
                logic.getAllVinks().forEach(v -> {
                    io.print("");
                    io.print("Headline: " + v.getHeadline());
                    io.print("Type: " + v.getType());
                    io.print("Tags: ");
                    v.getTags().forEach(t -> io.print(" " + t));
                    io.print("Comment: " + v.getComment());
                });
            } else if (command.equals("delete")) {
                io.print("");
                io.print("Deleting vink by Title");
                String title = io.askUser("Title of vink to delete");
                if (!title.trim().isEmpty()) {
                    boolean vinkDeletedSuccesfully = logic.deleteVinkByTitle(title);
                    if (vinkDeletedSuccesfully) {
                        io.print("Vink deleted");
                    } else {
                        io.print("Soemthing went wrong when trying to delete vink");
                    }
                }            
            } else if (command.equals("quit")) {
                break;
            } else {
                io.print("Unrecognized Command");
            }
        }
        
        io.print("");
        io.print("Exiting!..");
    }
    
    private ArrayList<String> askForTags() {
        ArrayList<String> tags = new ArrayList<>();
        String tag = "";
        
        io.print("Enter tags, one per line. empty string ends.");
        while (true) {
            tag = io.askUser(" Give tag");
            if (tag.isEmpty()) break;
            tags.add(tag);
        }
        
        return tags;
    }
    
}
