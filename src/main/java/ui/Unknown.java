package ui;

import io.IO;
import io.Network;
import logic.Logic;

public class Unknown extends Command {

    public Unknown(IO io, Logic logic, Network networkCon) {
        super(io, logic, networkCon);
    }

    @Override
    public void handleCommand() {
        io.print("Unrecognized command");
    }
    
}
