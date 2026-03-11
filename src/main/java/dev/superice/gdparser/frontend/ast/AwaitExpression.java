package dev.superice.gdparser.frontend.ast;

/// `await <expr>` expression.
public record AwaitExpression(Expression value, Range range) implements Expression {

    @Override
    public java.util.List<Node> getChildren() {
        return java.util.List.of(value);
    }
}
