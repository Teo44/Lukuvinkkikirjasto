package logic;

import domain.Vink;
import database.VinkDAO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Logic {
    private VinkDAO vinkDao;
    
    public Logic(VinkDAO vinkDao) {
        this.vinkDao = vinkDao;
        vinkDao.createTablesIfNotExist();
    }
    
    public boolean saveVink(String headline, String type, ArrayList<String> tags, String comment) {
        Vink vink = new Vink(headline, type, tags, comment);
        
        vinkDao.addVink(vink);
        return true;                        //returns true if the saving was successfull
                                            //logic to be implemented later
    }
    
    public ArrayList<Vink> getAllVinks() {
        return vinkDao.getAllVinks();
    }
    

    public boolean deleteVinkByTitle(String title) {
        ArrayList<Vink> vinkList = getAllVinks();
        for (int i = 0; i < vinkList.size(); i++) {
            if (vinkList.get(i).getHeadline() == title) {
                vinkDao.deleteVink(vinkList.get(i).getDatabaseID());
            }
        }
        return true;
    }
    
    private ArrayList<String> deleteDuplicates(ArrayList<String> list) {
        ArrayList<String> newList = list;
        Set<String> set = new HashSet<>(newList);
        newList.clear();
        newList.addAll(set);
        return newList;
    }
    
    
}
