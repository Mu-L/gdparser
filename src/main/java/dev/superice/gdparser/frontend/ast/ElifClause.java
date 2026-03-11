package dev.superice.gdparser.frontend.ast;

/// elif branch of an if statement.
public record ElifClause(Expression condition, Block body, Range range) implements Node {

    @Override
    public java.util.List<Node> getChildren() {
        return java.util.List.of(condition, body);
    }
}
