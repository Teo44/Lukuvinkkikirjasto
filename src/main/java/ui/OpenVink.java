package ui;

import domain.Vink;
import io.IO;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import logic.Logic;

public class OpenVink extends Command {
    
    
    
    public OpenVink(IO io, Logic logic) {
        super(io, logic);
    }

    @Override
    public void handleCommand() {
        String title = io.askUser("Title of vink to open");
        Vink v = logic.getVinkByTitle(title);

        if (v == null) {
            printError("open vink with title: " + title);
            return;
        }
        
        String path = v.getLink();
        try {
            open(path);
        } catch (IOException ex) {
            printError("open a vink");
        }
        
        
    }

    private void open(String link) throws IOException {
        File file = new File(link);
        
        if (!Desktop.isDesktopSupported()) {
            printError("connect to desktop");
            return;
        }
        
        Desktop desktop = Desktop.getDesktop();
        if (file.exists()) { 
            desktop.open(file);
            io.print("Vink opened correctly");
        } else {
            printError("find a path: " + link);
        }
    }
}
