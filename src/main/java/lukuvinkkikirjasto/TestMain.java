package lukuvinkkikirjasto;

import database.VinkDAO;

public class TestMain {

    public static void main(String[] args) throws Exception{
        System.out.println("Hello database");
        
        VinkDAO vink = new VinkDAO();
        vink.createTablesIfNotExist();
        vink.addVink();
    }
}
