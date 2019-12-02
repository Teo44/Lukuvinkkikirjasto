package logic;

import domain.Vink;
import java.util.ArrayList;

public class Filter {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";

    public ArrayList<Vink> getFilteredColorisedList(ArrayList<Vink> vinks, String string) {
        ArrayList<Vink> filteredList = new ArrayList<>();

        for (int i = 0; i < vinks.size(); i++) {
            Vink originalVink = vinks.get(i);
            boolean modified = false;

            if (vinks.get(i).getHeadline().contains(string)) {
                String colorHeadline = vinks.get(i).getHeadline().replaceAll(string, ANSI_GREEN + string + ANSI_RESET);
                vinks.get(i).setHeadline(colorHeadline);
                modified = true;
            }

            if (vinks.get(i).getType().contains(string)) {
                String colorType = vinks.get(i).getType().replaceAll(string, ANSI_GREEN + string + ANSI_RESET);
                vinks.get(i).setHeadline(colorType);
                modified = true;
            }

            if (vinks.get(i).getComment().contains(string)) {
                String colorComment = vinks.get(i).getHeadline().replaceAll(string, ANSI_GREEN + string + ANSI_RESET);
                vinks.get(i).setComment(colorComment);
                modified = true;
            }

            if (vinks.get(i).getLink().contains(string)) {
                String colorLink = vinks.get(i).getLink().replaceAll(string, ANSI_GREEN + string + ANSI_RESET);
                vinks.get(i).setHeadline(colorLink);
                modified = true;
            }
            
            ArrayList<String> tags = vinks.get(i).getTags();
            ArrayList<String> coloredTags = new ArrayList<>();

            for (int j = 0; j < tags.size(); j++) {
                if (tags.get(j).contains(string)) {
                    String colorTag = tags.get(i).replaceAll(string, ANSI_GREEN + string + ANSI_RESET);
                    coloredTags.add(colorTag);
                    modified = true;
                }
            }
            
            if (!coloredTags.isEmpty()) {
                vinks.get(i).setTags(coloredTags);
            }
            
            if (modified) {
                filteredList.add(vinks.get(i));
            }

        }

        return filteredList;
    }

}
