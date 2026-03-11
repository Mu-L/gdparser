package dev.superice.gdparser.frontend.ast;

import java.util.List;

/// attribute chain expression.
public record AttributeExpression(Expression base, List<AttributeStep> steps, Range range) implements Expression {

    @Override
    public java.util.List<Node> getChildren() {
        return NodeChildren.builder()
                .add(base)
                .addAll(steps)
                .toList();
    }
}
