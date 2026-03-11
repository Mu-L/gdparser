package dev.superice.gdparser.frontend.ast;

/// expression statement.
public record ExpressionStatement(Expression expression, Range range) implements Statement {

    @Override
    public java.util.List<Node> getChildren() {
        return java.util.List.of(expression);
    }
}
