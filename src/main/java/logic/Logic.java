package logic;

import domain.Vink;
import database.VinkDAO;

import java.util.ArrayList;

public class Logic {
    private VinkDAO vinkDao;
    
    public Logic() {
        vinkDao = new VinkDAO("vinkDatabase.db");
        vinkDao.createTablesIfNotExist();
    }
    
    public void saveVink(String headline, String type, ArrayList<String> tags, String comment) {
        Vink vink = new Vink(headline, type, tags, comment);
        vinkDao.addVink(vink);
    }
    
    public ArrayList<Vink> getAllVinks() {
        return vinkDao.getAllVinks();
    }
    
    
}
