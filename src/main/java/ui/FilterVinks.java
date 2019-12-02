package ui;

import io.IO;
import logic.Logic;

public class FilterVinks extends Command {

    public FilterVinks(IO io, Logic logic) {
        super(io, logic);
    }

    @Override
    public void handleCommand() {
        String keyword = io.askUser("Filter by keyword");

        io.print("Filtered vinks::");

        logic.filterByString(keyword).forEach(v -> {
            io.print("------------------------------");
            io.print("Headline: " + v.getHeadline());
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
