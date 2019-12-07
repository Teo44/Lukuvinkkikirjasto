package ui;

import domain.Vink;
import io.IO;
import io.Network;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import logic.Logic;

public class OpenVink extends Command {
    
    public OpenVink(IO io, Logic logic, Network networkCon) {
        super(io, logic, networkCon);
    }

    @Override
    public void handleCommand() {
        String title = io.askUser("Title of vink to open");
        Vink v = logic.getVinkByTitle(title);

        if (v == null) {
            printError("open a vink with title: " + title);
            return;
        }
        if (v.getLink().equals("")) {
            printError("open a vink with no path");
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
        } else if (link.contains("https://")) {
            desktop.browse(java.net.URI.create(link));
            io.print("Vink opened correctly");
        } else {
            printError("find a path: " + link);
        }
    }
}
