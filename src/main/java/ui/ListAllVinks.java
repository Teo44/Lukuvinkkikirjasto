package ui;

import io.IO;
import java.util.ArrayList;
import logic.Logic;

public class ListAllVinks extends Command {

    public ListAllVinks(IO io, Logic logic) {
        super(io, logic);
    }

    @Override
    public void handleCommand() {
        io.print("All vinks:");

        logic.getAllVinks().forEach(v -> {
            io.print("------------------------------");
            io.print("Headline: " + v.getHeadline());
            if (!(v.getAuthor().equals("")))    {
                io.print("Author: " + v.getAuthor());
            }
            io.print("Type: " + v.getType());
            String tagString = new String();
            for (int i = 0; i < v.getTags().size(); i++) {
                tagString = tagString + v.getTags().get(i);
                if (i+1 != v.getTags().size()) {
                    tagString = tagString + ", ";
                }
            }
            io.print("Tags: " + tagString);
            io.print("Comment: " + v.getComment());
            if (!(v.getLink().equals("")))   {
                io.print("Link: " + v.getLink());
            }
        });
    }
    
}
