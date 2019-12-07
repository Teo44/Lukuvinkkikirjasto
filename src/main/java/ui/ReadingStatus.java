package ui;

import domain.Vink;
import io.IO;
import logic.Logic;

public class ReadingStatus extends Command {

    public ReadingStatus(IO io, Logic logic) {
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

        if (vink.getReadingStatus().equals(1)) {
            io.print("Vink " + vink.getHeadline() + " is unread.");
        } else if (vink.getReadingStatus().equals(2)) {
            io.print("Vink " + vink.getHeadline() + " reading is in progress.");
        } else {
            io.print("Vink " + vink.getHeadline() + " is read.");
        }
        
        io.print("");

        while (true) {
            io.print("Type a number 1-3 to change the reading status");
            String input = io.readLine("[1 = unread, 2 = in progress, 3 = read completed]");

            if (input.equals("1")) {
                logic.updateVinkReadingStatus(title, 1);
                break;
            } else if (input.equals("2")) {
                logic.updateVinkReadingStatus(title, 2);
                break;
            } else if (input.equals("3")) {
                logic.updateVinkReadingStatus(title, 3);
                break;
            } else {
                io.print("Invalid input!");
                io.print("");
            }

        }
        
        io.print("The reading status of vink " + title + " has been updated!");

    }

}
