package lukuvinkkikirjasto;

import java.util.Scanner;
import logic.Logic;
import ui.Textual;

public class Main {

    public static void main(String[] args) {
        new Textual(new Scanner(System.in), new Logic()).run();
    }
}
