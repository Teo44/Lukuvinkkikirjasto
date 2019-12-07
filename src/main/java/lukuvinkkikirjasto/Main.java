package lukuvinkkikirjasto;

import logic.Logic;
import ui.Textual;
import database.VinkDAOSqlite;
import io.ConsoleIO;
import io.IO;
import io.Network;


public class Main {

    public static void main(String[] args) {
        IO io = new ConsoleIO();
        Network networkCon = new Network();
        VinkDAOSqlite vinkDao = new VinkDAOSqlite("vinkDatabase.db"); 
        Logic logic = new Logic(vinkDao, networkCon);
        
        new Textual(logic, io).run();
    }
}
