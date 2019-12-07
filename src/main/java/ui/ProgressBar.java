package ui;

public class ProgressBar {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001b[31;1m";
    private static final String ANSI_YELLOW = "\u001b[33;1m";
    private static final String ANSI_GREEN = "\u001b[32;1m";

    public String getProgressBar(Integer status) {
        if (status.equals(1)) {
            return("Reading progress: " + ANSI_RED + "[                              ] 0%" + ANSI_RESET);
        } else if (status.equals(2)) {
            return("Reading progress: " + ANSI_YELLOW + "[===============               ] 50%" + ANSI_RESET);
        } else if (status.equals(3)) {
            return("Reading progress: " + ANSI_GREEN + "[==============================] 100%" + ANSI_RESET);
        }
        return "";
    }

}
