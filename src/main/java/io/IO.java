package io;

public interface IO {
    void print(String toPrint);
    public String askUser(String question, String... suggestions);
    public String readLine(String prompt);
}
