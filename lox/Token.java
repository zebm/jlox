package jlox.lox;

class Token {
    final TokenType type;
    final String lexeme;
    final Object literal;
    final int line;
    // Bellow fields are for more precise error handling
    final int column;
    final int length;

    Token(TokenType type, String lexeme, Object literal, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
        this.column = 0; // TODO: implement column tracking
        this.length = 1; // TODO: implement length tracking
    }

    public String toString() {
        return type + " " + lexeme + " " + literal;
    }
}