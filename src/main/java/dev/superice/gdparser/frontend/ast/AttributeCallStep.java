package dev.superice.gdparser.frontend.ast;

import java.util.List;

/// method call step in attribute chain.
public record AttributeCallStep(String name, List<Expression> arguments, Range range) implements AttributeStep {

    @Override
    public java.util.List<Node> getChildren() {
        return java.util.List.copyOf(arguments);
    }
}
