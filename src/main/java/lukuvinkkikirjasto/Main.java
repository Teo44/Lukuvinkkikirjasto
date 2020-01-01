package lukuvinkkikirjasto;

import logic.Logic;
import ui.Textual;
import database.VinkDAOSqlite;
import io.ConsoleIO;
import io.IO;
import io.Network;


public class Main {

    public static void main(String[] args) {
        IO io = null;
        
        try {
            io = new ConsoleIO();
        } catch (Exception e) {
            System.out.println("Could not initialize IO: " + e.getMessage());
            System.out.println("exiting...");
            System.exit(1); // exit with status 1
        }
        
        Network networkCon = new Network();
        VinkDAOSqlite vinkDao = new VinkDAOSqlite("vinkDatabase.db"); 
        Logic logic = new Logic(vinkDao, networkCon);
        
        new Textual(logic, io).run();
    }
}
