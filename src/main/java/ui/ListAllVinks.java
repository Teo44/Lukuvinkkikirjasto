package ui;

import io.IO;
import java.util.ArrayList;
import logic.Logic;

public class ListAllVinks extends Command {
    ProgressBar progressBar = new ProgressBar();
    
    public ListAllVinks(IO io, Logic logic) {
        super(io, logic);
    }

    @Override
    public void handleCommand() {
        io.print("All vinks:");

        logic.getAllVinks().forEach(v -> {
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
