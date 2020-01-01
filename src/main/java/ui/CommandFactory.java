package ui;

import io.IO;
import java.util.HashMap;
import logic.Logic;

public class CommandFactory {
    
    private HashMap<String, Command> supportedCommands;
    private Command unknown;
    private String[] supportedCommandsArray;

    public CommandFactory(IO io, Logic logic) {
        this.supportedCommands = new HashMap<>();
        this.supportedCommands.put("new", new NewVink(io, logic));
        this.supportedCommands.put("list", new ListAllVinks(io, logic));
        this.supportedCommands.put("delete", new DeleteVink(io, logic));
        this.supportedCommands.put("modify", new ModifyVink(io, logic));
        this.supportedCommands.put("filter", new FilterVinks(io, logic));
        this.supportedCommands.put("mark", new ChangeReadingStatus(io, logic));
        this.supportedCommands.put("read", new SearchByReadingStatus(io, logic));
        this.supportedCommands.put("open", new OpenVink(io, logic));
        this.unknown = new Unknown(io, logic);
        
        initializeSupportedCommandsArray();
    }
    
    public String[] getSupportedCommands() {
        return supportedCommandsArray;
    }
    
    public Command getCommand(String command) {
        return supportedCommands.getOrDefault(command.toLowerCase(), unknown);
    }
    
    public Command getDefaultObject() {
        return getCommand("faultyinvalidcommand");
    }
    
    private void initializeSupportedCommandsArray() {
        this.supportedCommandsArray = new String[this.supportedCommands.keySet().size() + 1];
        this.supportedCommands.keySet().toArray(supportedCommandsArray);
        this.supportedCommandsArray[supportedCommandsArray.length - 1] = "quit";
    }
    
}
