package ui;

import io.IO;
import logic.Logic;

public class ListAllVinks extends Command {

    public ListAllVinks(IO io, Logic logic) {
        super(io, logic);
    }

    @Override
    public void handleCommand() {
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
    }
    
}
