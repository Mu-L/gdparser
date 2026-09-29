package dev.superice.gdparser.frontend.ast;

import java.util.List;

/// subscript call step in attribute chain.
public record AttributeSubscriptStep(String name, List<Expression> arguments, Range range) implements AttributeStep {

    public AttributeSubscriptStep {
        arguments = List.copyOf(arguments);
    }

    @Override
    public java.util.List<Node> getChildren() {
        return java.util.List.copyOf(arguments);
    }
}
