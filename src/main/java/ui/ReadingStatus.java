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
        while (true) {
            title = io.readLine("Give a title of a vink to modify the reading status:");
            vink = logic.getVinkByTitle(title);
            if (vink == null) {
                io.print("Invalid title!");
                io.print("");
            } else {
                break;
            }
        }

        if (vink.getReadingStatus().equals(1)) {
            io.print("Vink " + vink.getHeadline() + " is unread.");
        } else if (vink.getReadingStatus().equals(2)) {
            io.print("Vink " + vink.getHeadline() + " reading is in progress.");
        } else {
            io.print("Vink " + vink.getHeadline() + " is read.");
        }

        while (true) {
            io.print("Type a number 1-3 to change the reading status");
            String input = io.readLine("[0 = keep current value, 1 = unread, 2 = in progress, 3 = read completed]");
            try {
                Integer value = Integer.parseInt(input);
            } catch (Exception e) {
                io.print("Invalid value");
            }

        }

    }

}
