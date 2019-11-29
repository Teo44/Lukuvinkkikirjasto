package logic;

import domain.Vink;
import java.util.ArrayList;


public class Filter {
    
    public ArrayList<Vink> filterByString(ArrayList<Vink> vinks, String string) {
        ArrayList<Vink> filteredList = new ArrayList<>();
        
        for (int i = 0; i < vinks.size(); i++) {
            if (vinks.get(i).getHeadline().contains(string) || vinks.get(i).getType().contains(string)
                    || vinks.get(i).getComment().contains(string) || vinks.get(i).getLink().contains(string)) {
                
                filteredList.add(vinks.get(i));
                continue;
            }
            
            for (int j = 0; j < vinks.get(i).getTags().size(); j++) {
                if (vinks.get(i).getTags().contains(string)) {
                    filteredList.add(vinks.get(i));
                    break;
                }
            }
            
        }
        
        return filteredList;
    }
}
