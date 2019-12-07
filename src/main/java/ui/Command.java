package ui;

import io.IO;
import io.Network;
import java.util.ArrayList;
import logic.Logic;

public abstract class Command {
    
    protected IO io;
    protected Logic logic;
    protected Network networkCon;

    public Command(IO io, Logic logic, Network networkCon) {
        this.io = io;
        this.logic = logic;
        this.networkCon = networkCon;
    }
    
    public abstract void handleCommand();
    
    protected void printSupportedCommands() {
        io.print("------------------------------");
        io.print("");
        io.print("Supported commands: [new, list, delete, modify, filter, read, mark, open, quit]");
    }
    
    protected void printError(String error) {
        io.print("Something went wrong when trying to " + error + ", try again!");
    }
    
    protected ArrayList<String> askForTags() {
        ArrayList<String> tags = new ArrayList<>();
        String tag = "";
        
        io.print("Enter tags, one per line. Empty string ends.");
        
        while (true) {
            tag = io.askUser("  Give tag");
            if (tag.isEmpty()) {
                break;
            }
            
            tags.add(tag);
        }
        
        return tags;
    }
    
}
