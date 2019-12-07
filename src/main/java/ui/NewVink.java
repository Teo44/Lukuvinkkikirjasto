package ui;

import io.IO;
import io.Network;
import java.util.ArrayList;
import logic.Logic;

public class NewVink extends Command {

    public NewVink(IO io, Logic logic, Network networkCon) {
        super(io, logic, networkCon);
    }

    @Override
    public void handleCommand() {
        boolean vinkCreatedSuccesfully = false;
        boolean byIsbin = io.askUser("Add manually or by isbn [man, isbn]").equalsIgnoreCase("isbn");
        io.print("");
        
        if (byIsbin) {
            String isbn = io.askUser("Enter the isbn-code");
            vinkCreatedSuccesfully = networkCon.fetchAndSaveBookByISBN(isbn);
        } else {
            String title = io.askUser("Title");
            String type = io.askUser("Type");
            String author = io.askUser("Author");
            ArrayList<String> tags = askForTags();
            String comment = io.askUser("Comment");
            String link = io.askUser("Link");
            vinkCreatedSuccesfully = logic.saveVink(title, type, tags, comment, link, author);
        }
        
        if (vinkCreatedSuccesfully) {
            io.print("");
            io.print("Vink created!");
        } else {
            printError("add new vink");
        }
    }
    
}
