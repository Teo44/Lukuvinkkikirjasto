package ui;

import java.util.ArrayList;
import logic.Logic;
import io.IO;

public class Textual {
    
    private IO io;
    private CommandFactory commandFactory;
    
    public Textual(Logic logic, IO io) {
        this.commandFactory = new CommandFactory(io, logic);
        this.io = io;
    }
    
    public void run() {
        io.print("Welcome to Lukuvinkkikirjasto.");
        io.print("------------------------------");
        
        String command = null;
        
        while (true) {
            io.print("");
            
            commandFactory.getDefaultObject().printSupportedCommands();
            
            command = io.askUser("Give command");
            command = command.toLowerCase();
            
            io.print("");
            
            if (command.equals("quit")) {
                break;
            }
            
            commandFactory.getCommand(command).handleCommand();
        }
        
        io.print("");
        io.print("Exiting!..");
    }
    
}
