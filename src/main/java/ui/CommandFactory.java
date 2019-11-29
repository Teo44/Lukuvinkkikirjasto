package ui;

import io.IO;
import java.util.HashMap;
import logic.Logic;

public class CommandFactory {
    
    private HashMap<String, Command> supportedCommands;
    private Command unknown;

    public CommandFactory(IO io, Logic logic) {
        this.supportedCommands = new HashMap<>();
        this.supportedCommands.put("new", new NewVink(io, logic));
        this.supportedCommands.put("list", new ListAllVinks(io, logic));
        this.supportedCommands.put("delete", new DeleteVink(io, logic));
        this.supportedCommands.put("modify", new ModifyVink(io, logic));
        this.unknown = new Unknown(io, logic);
    }
    
    public Command getCommand(String command) {
        return supportedCommands.getOrDefault(command, unknown);
    }
    
    public Command getDefaultObject() {
        return getCommand(null);
    }
    
}
