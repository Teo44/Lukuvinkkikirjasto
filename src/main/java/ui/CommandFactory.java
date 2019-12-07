package ui;

import io.IO;
import io.Network;
import java.util.HashMap;
import logic.Logic;

public class CommandFactory {
    
    private HashMap<String, Command> supportedCommands;
    private Command unknown;

    public CommandFactory(IO io, Logic logic, Network networkCon) {
        this.supportedCommands = new HashMap<>();
        this.supportedCommands.put("new", new NewVink(io, logic, networkCon));
        this.supportedCommands.put("list", new ListAllVinks(io, logic, networkCon));
        this.supportedCommands.put("delete", new DeleteVink(io, logic, networkCon));
        this.supportedCommands.put("modify", new ModifyVink(io, logic, networkCon));
        this.supportedCommands.put("filter", new FilterVinks(io, logic, networkCon));
        this.supportedCommands.put("mark", new ChangeReadingStatus(io, logic, networkCon));
        this.supportedCommands.put("read", new SearchByReadingStatus(io, logic, networkCon));
        this.supportedCommands.put("open", new OpenVink(io, logic, networkCon));
        this.unknown = new Unknown(io, logic, networkCon);
    }
    
    public Command getCommand(String command) {
        return supportedCommands.getOrDefault(command.toLowerCase(), unknown);
    }
    
    public Command getDefaultObject() {
        return getCommand("faultyinvalidcommand");
    }
    
}
