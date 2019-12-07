package logic;

import domain.Vink;
import java.util.ArrayList;

public class Filter {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001b[32;1m";

    public ArrayList<Vink> getFilteredColorisedList(ArrayList<Vink> vinks, String string) {
        ArrayList<Vink> filteredList = new ArrayList<>();

        for (int i = 0; i < vinks.size(); i++) {
            boolean modified = false;

            if (vinks.get(i).getHeadline().contains(string)) {
                String colorHeadline = vinks.get(i).getHeadline().replaceAll(string, ANSI_GREEN + string + ANSI_RESET);
                vinks.get(i).setHeadline(colorHeadline);
                modified = true;
            }
            
            if (vinks.get(i).getAuthor().contains(string))  {
                String colorAuthor = vinks.get(i).getAuthor().replaceAll(string, ANSI_GREEN + string + ANSI_RESET);
                vinks.get(i).setAuthor(colorAuthor);
                modified = true;
            }

            if (vinks.get(i).getType().contains(string)) {
                String colorType = vinks.get(i).getType().replaceAll(string, ANSI_GREEN + string + ANSI_RESET);
                vinks.get(i).setType(colorType);
                modified = true;
            }

            if (vinks.get(i).getComment().contains(string)) {
                String colorComment = vinks.get(i).getComment().replaceAll(string, ANSI_GREEN + string + ANSI_RESET);
                vinks.get(i).setComment(colorComment);
                modified = true;
            }

            if (vinks.get(i).getLink().contains(string)) {
                String colorLink = vinks.get(i).getLink().replaceAll(string, ANSI_GREEN + string + ANSI_RESET);
                vinks.get(i).setLink(colorLink);
                modified = true;
            }
            
            ArrayList<String> tags = vinks.get(i).getTags();

            for (int j = 0; j < tags.size(); j++) {
                if (tags.get(j).contains(string)) {
                    String colorTag = tags.get(j).replaceAll(string, ANSI_GREEN + string + ANSI_RESET);
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
