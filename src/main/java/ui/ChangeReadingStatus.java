package ui;

import domain.Vink;
import io.IO;
import logic.Logic;

public class ChangeReadingStatus extends Command {

    public ChangeReadingStatus(IO io, Logic logic) {
        super(io, logic);
    }

    @Override
    public void handleCommand() {
        Vink vink;
        String title;
        
        title = io.readLine("Give a title of a vink to modify the reading status:");
        vink = logic.getVinkByTitle(title);
        if (vink == null) {
            io.print("Invalid title!");
            io.print("");
            return;
        }
        
        io.print("");

        switch (vink.getReadingStatus()) {
            case 1:
                io.print("Vink " + vink.getHeadline() + " is unread.");
                break;
            case 2:
                io.print("Vink " + vink.getHeadline() + " reading is in progress.");
                break;
            default:
                io.print("Vink " + vink.getHeadline() + " is read.");
                break;
        }
        
        io.print("");

        OUTER:
        while (true) {
            io.print("Type a number 1-3 to change the reading status");
            String input = io.readLine("[1 = unread, 2 = in progress, 3 = read completed]");
            switch (input) {
                case "1":
                    logic.updateVinkReadingStatus(title, 1);
                    break OUTER;
                case "2":
                    logic.updateVinkReadingStatus(title, 2);
                    break OUTER;
                case "3":
                    logic.updateVinkReadingStatus(title, 3);
                    break OUTER;
                default:
                    io.print("Invalid input!");
                    io.print("");
                    break;
            }
        }
        
        io.print("The reading status of vink " + title + " has been updated!");

    }

}
