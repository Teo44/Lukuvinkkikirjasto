package io;

import java.io.IOException;
import java.util.Scanner;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class ConsoleIO implements IO {
    
    private Scanner scanner = new Scanner(System.in);
    private Terminal terminal;
    private LineReader lineReader;

    public ConsoleIO() throws IOException {
        this.terminal = TerminalBuilder.builder()
                .streams(System.in, System.out)
                .system(true)
                .jansi(true)
                .build();
        //this.lineReader = LineReaderBuilder.builder()
        //        .terminal(terminal)
        //        .completer(new StringsCompleter("new", "list", "modify", "quit"))
        //        .build();
    }
    
    
    @Override
    public void print(String toPrint) {
        System.out.println(toPrint);
    }

    public int readInt(String prompt) {
        System.out.println(prompt);
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public String readLine(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    @Override
    public String askUser(String question, String... suggestions) {
        //System.out.print(question + ": ");
        //return scanner.nextLine().trim();
        if (suggestions != null) {
            this.lineReader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .completer(new StringsCompleter(suggestions))
                    .build();
            this.lineReader.setVariable(LineReader.DISABLE_COMPLETION, false);
        }
        
        String userInput = this.lineReader.readLine(question + ": ").trim();
        this.lineReader.setVariable(LineReader.DISABLE_COMPLETION, true);
        return userInput;
    }
    
}
