package dev.superice.gdparser.frontend.ast;

/// unary operation expression.
public record UnaryExpression(String operator, Expression operand, Range range) implements Expression {

    @Override
    public java.util.List<Node> getChildren() {
        return java.util.List.of(operand);
    }
}
