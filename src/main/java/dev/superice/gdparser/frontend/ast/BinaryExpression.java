package dev.superice.gdparser.frontend.ast;

/// binary operation expression.
public record BinaryExpression(String operator, Expression left, Expression right, Range range) implements Expression {

    @Override
    public java.util.List<Node> getChildren() {
        return java.util.List.of(left, right);
    }
}
