package logic;

import domain.Vink;
import database.VinkDAO;

import java.util.ArrayList;

public class Logic {
    private Vink vink;
    private VinkDAO vinkDao;
    
    public Logic() {
        
    }
    
    public void saveVink(String headline, String type, ArrayList<String> tags, String comment) {
        Vink vink = new Vink(headline, type, tags, comment);
        vinkDao.addVink(vink);
    }
    
    public Vink fetchVink() {
        return null;
    }
    
    public String getVink() {
        return ("");
    }
    
    public ArrayList<Vink> getAllVinks() {
        return null;
    }
    
    
}
