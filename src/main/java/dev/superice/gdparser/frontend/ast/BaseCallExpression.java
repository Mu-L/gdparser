package dev.superice.gdparser.frontend.ast;

import java.util.List;

/// Implicit base call such as `.foo(...)`.
public record BaseCallExpression(String name, List<Expression> arguments, Range range) implements Expression {

    @Override
    public java.util.List<Node> getChildren() {
        return java.util.List.copyOf(arguments);
    }
}
