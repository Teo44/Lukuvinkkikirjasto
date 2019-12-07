package ui;

import domain.Vink;
import io.IO;
import java.util.ArrayList;
import logic.Logic;

public class SearchByReadingStatus extends Command {

    public SearchByReadingStatus(IO io, Logic logic) {
        super(io, logic);
    }

    @Override
    public void handleCommand() {
        io.print("Type a number 1-3 to search vinks by their reading status");
        String input = io.readLine("[1 = unread, 2 = in progress, 3 = read completed]");
        ArrayList<Vink> vinks = new ArrayList<>();

        while (true) {
            if (input.equals("1")) {
                vinks = logic.getVinksByReadingStatus(1);
                break;
            } else if (input.equals("2")) {
                vinks = logic.getVinksByReadingStatus(2);
                break;
            } else if (input.equals("3")) {
                vinks = logic.getVinksByReadingStatus(3);
                break;
            } else {
                io.print("Invalid input!");
            }

        }
        
        if (vinks.isEmpty()) {
            io.print("No matches found!");
            io.print("");
            return;
        }

        vinks.forEach(v -> {
            io.print("------------------------------");
            io.print("Headline: " + v.getHeadline());
            io.print("Type: " + v.getType());
            String tagString = new String();
            for (int i = 0; i < v.getTags().size(); i++) {
                tagString = tagString + v.getTags().get(i);
                if (i + 1 != v.getTags().size()) {
                    tagString = tagString + ", ";
                }
            }
            io.print("Tags: " + tagString);
            io.print("Comment: " + v.getComment());
            if (!(v.getLink().equals(""))) {
                io.print("Link: " + v.getLink());
            }
        });

    }

}
