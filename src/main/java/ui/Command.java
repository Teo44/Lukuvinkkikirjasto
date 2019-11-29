package ui;

import io.IO;
import java.util.ArrayList;
import logic.Logic;

public abstract class Command {
    
    protected IO io;
    protected Logic logic;

    public Command(IO io, Logic logic) {
        this.io = io;
        this.logic = logic;
    }
    
    public abstract void handleCommand();
    
    protected void printSupportedCommands() {
        io.print("Supported commands: [new, list, delete, quit]");
    }
    
    protected void printError(String error) {
        io.print("Something went wrong when trying to " + error + ", try again!");
    }
    
}
