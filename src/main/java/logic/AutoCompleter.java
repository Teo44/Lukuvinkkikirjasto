package logic;

import domain.Vink;
import java.util.ArrayList;
import org.jline.reader.Completer;
import org.jline.reader.impl.completer.StringsCompleter;

public class AutoCompleter {
    
    public Completer getHeadlineCompleter(ArrayList<Vink> vinks) {
        ArrayList<String> headlines = new ArrayList<>();
        for (Vink v : vinks)    {
            headlines.add(v.getHeadline());
        }
        Completer completer = new StringsCompleter(headlines);
        return completer;
    }
    
}
