package dev.superice.gdparser.frontend.ast;

import java.util.List;

/// array expression.
public record ArrayExpression(List<Expression> elements, boolean openEnded, Range range) implements Expression {

    @Override
    public java.util.List<Node> getChildren() {
        return java.util.List.copyOf(elements);
    }
}
