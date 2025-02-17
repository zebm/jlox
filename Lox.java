package com.craftinginterpreters.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Lox {
    static boolean hadError = false;

    public static void main(String[] args) {
        if (args.length > 1) {
            System.out.println("Usage: lox [script]");
            System.exit(64);
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {
            runPrompt();
        }

    }

    /**
     * Runs the interpreter given a source file
     * @param path to input file
      * @throws 
     */
    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));

        if (hadError) System.exit(65);
     }
    

    /**
     *  Interactive prompt function, runs uuser input until EOF
     */
    private static void runPrompt() {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for (;;) {
            System.out.print("> ");
            String line = reader.readLine();
            if (line == null) break;
            run(line);
            hadError = false;
        }
    }

    /**
     * Runs the interpreter given a string of source code
      * @param source code to be interpreted
      */
    private static void run(String source) {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        // For now just print tokens
        for (Token token : tokens){
            System.out.println(token);
         }
    }

    static void error(int line, String message) {
        report(line, "", message);
    }

    // TODO: improve error reporting to be character precise see chapter 4
    static void report(int line, String where, String message) {
        System.err.println("[line" + line + "] Error" + where + ":" + message);
        hadError = true;
    }
}