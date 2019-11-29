package ui;

import io.IO;
import java.util.ArrayList;
import logic.Logic;

public class NewVink extends Command {

    public NewVink(IO io, Logic logic) {
        super(io, logic);
    }

    @Override
    public void handleCommand() {
        String title = io.askUser("Title");
        String type = io.askUser("Type");
        ArrayList<String> tags = askForTags();
        String comment = io.askUser("Comment");
        String link = io.askUser("Link");

        boolean vinkCreatedSuccesfully = logic.saveVink(title, type, tags, comment, link);

        if (vinkCreatedSuccesfully) {
            io.print("Vink created!");
        } else {
            printError("add new vink");
        }
    }
    
    private ArrayList<String> askForTags() {
        ArrayList<String> tags = new ArrayList<>();
        String tag = "";
        
        io.print("Enter tags, one per line. Empty string ends.");
        
        while (true) {
            tag = io.askUser(" Give tag");
            if (tag.isEmpty()) {
                break;
            }
            
            tags.add(tag);
        }
        
        return tags;
    }
    
}
