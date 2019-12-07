package ui;

import domain.Vink;
import io.IO;
import io.Network;
import java.util.ArrayList;
import logic.Logic;

public class FilterVinks extends Command {
    ProgressBar progressBar = new ProgressBar();

    public FilterVinks(IO io, Logic logic, Network networkCon) {
        super(io, logic, networkCon);
    }

    @Override
    public void handleCommand() {
        String keyword = "";

        while (true) {
            keyword = io.askUser("Filter by keyword");

            if (keyword.trim().isEmpty()) {
                io.print("Keyword must not be empty or contain only spaces!");
                io.print("");
            } else {
                break;
            }
        }

        ArrayList<Vink> filteredList = logic.filterByString(keyword);

        if (!filteredList.isEmpty()) {
            io.print("Found " + filteredList.size() + " vink(s) including keyword " + keyword + ":");

            filteredList.forEach(v -> {
                io.print("------------------------------");

                if (v.getReadingStatus().equals(1)) {
                    io.print(progressBar.getProgressBar(1));
                } else if (v.getReadingStatus().equals(2)) {
                    io.print(progressBar.getProgressBar(2));
                } else if (v.getReadingStatus().equals(3)) {
                    io.print(progressBar.getProgressBar(3));
                }

                io.print("Headline: " + v.getHeadline());
                io.print("Type: " + v.getType());
                if (!(v.getAuthor().equals(""))) {
                    io.print("Author: " + v.getAuthor());
                }
                String tagString = new String();

                for (int i = 0; i < v.getTags().size(); i++) {
                    tagString = tagString + v.getTags().get(i);
                    if (i + 1 != v.getTags().size()) {
                        tagString = tagString + ", ";
                    }
                }

                io.print("Tags: " + tagString);
                io.print("Comment: " + v.getComment());

                if (!(v.getLink().equals(""))) {
                    io.print("Link: " + v.getLink());
                }
            });

        } else {
            io.print("No matches found with keyword " + keyword);
        }

    }

}
