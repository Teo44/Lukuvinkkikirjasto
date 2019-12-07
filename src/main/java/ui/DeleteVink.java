package ui;

import io.IO;
import io.Network;
import logic.Logic;

public class DeleteVink extends Command {

    public DeleteVink(IO io, Logic logic, Network networkCon) {
        super(io, logic, networkCon);
    }

    @Override
    public void handleCommand() {
        io.print("Deleting vink by Title");
        
        String title = io.askUser("Enter title of the vink you wish to delete");
        
        if (!title.isEmpty()) {
            boolean vinkDeletedSuccesfully = logic.deleteVinkByTitle(title);
            if (vinkDeletedSuccesfully) {
                io.print("Vink deleted");
            } else {
                printError("delete vink");
            }
        }
    }
    
}
