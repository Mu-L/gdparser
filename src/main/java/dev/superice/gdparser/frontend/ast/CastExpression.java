package dev.superice.gdparser.frontend.ast;

/// `value as Type` expression.
public record CastExpression(Expression value, TypeRef targetType, Range range) implements Expression {

    @Override
    public java.util.List<Node> getChildren() {
        return java.util.List.of(value, targetType);
    }
}
