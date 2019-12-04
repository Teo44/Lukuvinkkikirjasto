package ui;

import database.VinkDAO;
import database.VinkDAOSqlite;
import java.io.IOException;
import logic.Logic;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class FancyTerminal {
    
    private Terminal terminal;
    private LineReader lineReader;
    private VinkDAO vinkDao;
    private Logic logic;
    
    public FancyTerminal() throws IOException{
        this.vinkDao = new VinkDAOSqlite("_vinkDatabase.db");
        this.logic = new Logic(vinkDao);
        this.terminal = TerminalBuilder.terminal();
        //this.terminal = TerminalBuilder.builder().system(true).build();
        this.lineReader = LineReaderBuilder.builder()
                .terminal(terminal)
                .completer(logic.getHeadlineCompleter())
                .build();
    }
    
    public void run() {
        String prompt = "";
        while (true) {
            String line = null;
            line = lineReader.readLine();
            System.out.println("saatiin " + line);
        }
    }
    
    
}
