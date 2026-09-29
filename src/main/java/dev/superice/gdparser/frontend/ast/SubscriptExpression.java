package dev.superice.gdparser.frontend.ast;

import java.util.List;

/// subscript expression.
public record SubscriptExpression(Expression base, List<Expression> arguments, Range range) implements Expression {

    public SubscriptExpression {
        arguments = List.copyOf(arguments);
    }

    @Override
    public java.util.List<Node> getChildren() {
        return NodeChildren.builder()
                .add(base)
                .addAll(arguments)
                .toList();
    }
}
