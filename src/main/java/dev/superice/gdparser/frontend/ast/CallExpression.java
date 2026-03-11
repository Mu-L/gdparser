package dev.superice.gdparser.frontend.ast;

import java.util.List;

/// call expression.
public record CallExpression(Expression callee, List<Expression> arguments, Range range) implements Expression {

    @Override
    public java.util.List<Node> getChildren() {
        return NodeChildren.builder()
                .add(callee)
                .addAll(arguments)
                .toList();
    }
}
