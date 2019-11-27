package lukuvinkkikirjasto;

import java.util.Scanner;
import logic.Logic;
import ui.Textual;
import database.VinkDAO;

public class Main {

    public static void main(String[] args) {
        VinkDAO vinkDao = new VinkDAO("vinkDatabase.db"); 
        new Textual(new Scanner(System.in), new Logic(vinkDao)).run();
    }
}
