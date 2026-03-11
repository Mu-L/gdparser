package dev.superice.gdparser.frontend.ast;

/// while statement.
public record WhileStatement(Expression condition, Block body, Range range) implements Statement {

    @Override
    public java.util.List<Node> getChildren() {
        return java.util.List.of(condition, body);
    }
}
