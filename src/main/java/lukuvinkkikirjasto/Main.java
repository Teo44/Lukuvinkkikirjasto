package lukuvinkkikirjasto;

import logic.Logic;
import ui.Textual;
import database.VinkDAO;
import io.ConsoleIO;
import io.IO;

public class Main {

    public static void main(String[] args) {
        IO io = new ConsoleIO();
        VinkDAO vinkDao = new VinkDAO("vinkDatabase.db"); 
        Logic logic = new Logic(vinkDao);
        
        new Textual(logic, io).run();
    }
}
