package ui;

import domain.Vink;
import io.IO;
import java.util.ArrayList;
import logic.Logic;

public class ModifyVink extends Command {

    public ModifyVink(IO io, Logic logic) {
        super(io, logic);
    }

    @Override
    public void handleCommand() {
        String title = io.askUser("Title of vink to modify");
        Vink v = logic.getVinkByTitle(title);
        
        if (v == null) {
            printError("modify vink with title: " + title);
            return;
        }
        
        io.print("");
        io.print("Modifying vink, you can either enter a modified value for eatch field or keep the old one with an empty line");

        Integer id = v.getDatabaseID();
        title = v.getHeadline();
        String type = v.getType();
        ArrayList<String> tags = v.getTags();
        String comment = v.getComment();
        String link = v.getLink();
        
        io.print("Old title: " + title);
        String updatedTitle = io.askUser(" New title");
        io.print("Old type: " + type);
        String updatedType = io.askUser(" Updated type");
        io.print("Old comment: " + comment);
        String updatedComment = io.askUser(" Updated comment");
        
        ArrayList<String> updatedTags = modifyTags(tags);
        
        io.print("Old link: " + link);
        String updatedLink = io.askUser(" Updated link");
        
        title = updatedTitle.isEmpty() ? title : updatedTitle;
        type = updatedType.isEmpty() ? type : updatedType;
        comment = updatedComment.isEmpty() ? comment : updatedComment;
        link = updatedLink.isEmpty() ? link : updatedLink;
        
        boolean updatedSuccesfully = logic.updateVink(id, title, type, updatedTags, comment, link);
        
        if (updatedSuccesfully) {
            io.print("Vink updated succesfully!");
        } else {
            printError("modify vink with title :" + title);
        }
        
    }
    
    private ArrayList<String> modifyTags(ArrayList<String> tags) {
        ArrayList<String> updatedTags = new ArrayList<>();
        String updatedTag = null;
        
        for (String tag : tags) {
            io.print(" Old tag: " + tag);
            updatedTag = io.askUser(" Updated tag");
            if (updatedTag.isEmpty()) {
                updatedTags.add(tag);
            } else {
                updatedTags.add(updatedTag);
            }
        }
        
        io.print("");
        io.print("Add new tags to vink");
        io.print("--------------------");
        
        updatedTags.addAll(askForTags());
        
        return updatedTags;
    }
    
}
