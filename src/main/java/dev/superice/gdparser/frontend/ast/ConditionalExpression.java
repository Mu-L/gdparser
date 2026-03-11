package dev.superice.gdparser.frontend.ast;

/// ternary-like conditional expression.
public record ConditionalExpression(
        Expression condition,
        Expression left,
        Expression right,
        Range range
) implements Expression {

    @Override
    public java.util.List<Node> getChildren() {
        return java.util.List.of(condition, left, right);
    }
}
