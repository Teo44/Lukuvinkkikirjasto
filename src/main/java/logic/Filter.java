package logic;

import domain.Vink;
import java.util.ArrayList;

public class Filter {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001b[32;1m";

    public ArrayList<Vink> getFilteredColorisedList(ArrayList<Vink> vinks, String merkkijono) {
        ArrayList<Vink> filteredList = new ArrayList<>();

        for (int i = 0; i < vinks.size(); i++) {
            boolean modified = false;

            if (vinks.get(i).getHeadline().contains(merkkijono)) {
                String colorHeadline = vinks.get(i).getHeadline().replaceAll(merkkijono, ANSI_GREEN + merkkijono + ANSI_RESET);
                vinks.get(i).setHeadline(colorHeadline);
                modified = true;
            }
            
            if (vinks.get(i).getAuthor().contains(merkkijono))  {
                String colorAuthor = vinks.get(i).getAuthor().replaceAll(merkkijono, ANSI_GREEN + merkkijono + ANSI_RESET);
                vinks.get(i).setAuthor(colorAuthor);
                modified = true;
            }

            if (vinks.get(i).getType().contains(merkkijono)) {
                String colorType = vinks.get(i).getType().replaceAll(merkkijono, ANSI_GREEN + merkkijono + ANSI_RESET);
                vinks.get(i).setType(colorType);
                modified = true;
            }

            if (vinks.get(i).getComment().contains(merkkijono)) {
                String colorComment = vinks.get(i).getComment().replaceAll(merkkijono, ANSI_GREEN + merkkijono + ANSI_RESET);
                vinks.get(i).setComment(colorComment);
                modified = true;
            }

            if (vinks.get(i).getLink().contains(merkkijono)) {
                String colorLink = vinks.get(i).getLink().replaceAll(merkkijono, ANSI_GREEN + merkkijono + ANSI_RESET);
                vinks.get(i).setLink(colorLink);
                modified = true;
            }
            
            ArrayList<String> tags = vinks.get(i).getTags();

            for (int j = 0; j < tags.size(); j++) {
                if (tags.get(j).contains(merkkijono)) {
                    String colorTag = tags.get(j).replaceAll(merkkijono, ANSI_GREEN + merkkijono + ANSI_RESET);
                    tags.set(j, colorTag);
                    modified = true;
                }
            }
            
            if (modified) {
                filteredList.add(vinks.get(i));
            }

        }

        return filteredList;
    }

}
