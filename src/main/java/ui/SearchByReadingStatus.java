package ui;

import domain.Vink;
import io.IO;
import io.Network;
import java.util.ArrayList;
import logic.Logic;

public class SearchByReadingStatus extends Command {
    ProgressBar progressBar = new ProgressBar();
    
    public SearchByReadingStatus(IO io, Logic logic, Network networkCon) {
        super(io, logic, networkCon);
    }

    @Override
    public void handleCommand() {
        ArrayList<Vink> vinks = new ArrayList<>();

        io.print("Type a number 1-3 to search vinks by their reading status");
        String input = io.readLine("[1 = unread, 2 = in progress, 3 = read completed]");
        
        if (input.equals("1")) {
            vinks = logic.getVinksByReadingStatus(1);
        } else if (input.equals("2")) {
            vinks = logic.getVinksByReadingStatus(2);
        } else if (input.equals("3")) {
            vinks = logic.getVinksByReadingStatus(3);
        } else {
            io.print("Invalid input!");
            io.print("");
            return;
        }

        if (vinks.isEmpty()) {
            io.print("No matches found!");
            io.print("");
            return;
        }

        vinks.forEach(v -> {
            io.print("------------------------------");
            if (v.getReadingStatus().equals(1)) {
                io.print(progressBar.getProgressBar(1));
            } else if (v.getReadingStatus().equals(2)) {
                io.print(progressBar.getProgressBar(2));
            } else if (v.getReadingStatus().equals(3)) {
                io.print(progressBar.getProgressBar(3));
            }
            
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
