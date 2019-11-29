package ui;

import io.IO;
import logic.Logic;

public class Unknown extends Command {

    public Unknown(IO io, Logic logic) {
        super(io, logic);
    }

    @Override
    public void handleCommand() {
        io.print("Unrecognized Command");
        printSupportedCommands();
    }
    
}
