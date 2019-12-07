package logic;

import domain.Vink;
import database.VinkDAO;
import io.Network;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Logic {
    private VinkDAO vinkDao;
    private Filter filter;
    private Network networkCon;
    
    public Logic(VinkDAO vinkDao, Network networkCon) {
        this.vinkDao = vinkDao;
        this.networkCon = networkCon;
        vinkDao.createTablesIfNotExist();
        filter = new Filter();
    }    
    
    public boolean saveVinkByISBN(String isbn) {
        String[] book = this.networkCon.fetchBookDetailsByISBN(isbn);
        
        if (book == null || book.length != 2) {
            return false;
        }
        
        return saveVink(book[0], "book", new ArrayList<>(), "", "", book[1]);
    }
    
    public boolean saveVink(String headline,
                            String type,
                            ArrayList<String> tags,
                            String comment,
                            String link,
                            String author) {
        ArrayList<String> uniqueTags = deleteDuplicates(tags);
        Vink vink = new Vink(headline, type, uniqueTags, comment, link, author);
        
        vinkDao.addVink(vink);
        return true;
    }
    
    public Vink getVinkByTitle(String title) {
        ArrayList<Vink> vinkList = getAllVinks();
        for (int i = 0; i < vinkList.size(); i++) {
            if (vinkList.get(i).getHeadline().equals(title)) {
                return vinkList.get(i);
            }
        }
        return null;
    }
    
    public ArrayList<Vink> getVinksByReadingStatus(Integer readingStatus) {
        ArrayList<Vink> vinkList = getAllVinks();
        ArrayList<Vink> newList = new ArrayList<>();
        
        for (int i = 0; i < vinkList.size(); i++) {
            if (vinkList.get(i).getReadingStatus().equals(readingStatus)) {
                newList.add(vinkList.get(i));
            }
        }
        return newList;
    }
    
    public boolean updateVinkReadingStatus(String headline, Integer newValue) {
        Vink vink = getVinkByTitle(headline);
        Vink newVink = new Vink(
                headline,
                vink.getType(),
                vink.getTags(),
                vink.getComment(),
                vink.getLink(),
                newValue,
                vink.getAuthor(),
                vink.getDatabaseID());
        
        return vinkDao.updateVink(newVink);
    }
    
    public boolean updateVink(Integer id,
                              String headline,
                              String type,
                              ArrayList<String> tags,
                              String comment,
                              String link,
                              String author,
                              Integer readingStatus) {
        Vink vink = new Vink(
                headline,
                type,
                tags,
                comment,
                link,
                readingStatus,
                author,
                id);
        
        return vinkDao.updateVink(vink);
    }
    

    public boolean deleteVinkByTitle(String title) {
        ArrayList<Vink> vinkList = getAllVinks();
        for (int i = 0; i < vinkList.size(); i++) {
            if (vinkList.get(i).getHeadline().equals(title)) {
                return vinkDao.deleteVink(vinkList.get(i).getDatabaseID());
            }
        }
        return false;
    }
    
    public ArrayList<Vink> filterByString(String string) {
        ArrayList<Vink> vinkList = getAllVinks();
        return filter.getFilteredColorisedList(vinkList, string);
    }
    
    private ArrayList<String> deleteDuplicates(ArrayList<String> list) {
        ArrayList<String> newList = list;
        Set<String> set = new HashSet<>(newList);
        newList.clear();
        newList.addAll(set);
        return newList;
    }
    
    public ArrayList<Vink> getAllVinks() {
        return vinkDao.getAllVinks();
    }
    
}
