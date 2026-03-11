package dev.superice.gdparser.frontend.ast;

import java.util.List;

/// signal declaration.
public record SignalStatement(String name, List<Parameter> parameters, Range range) implements Statement {

    @Override
    public java.util.List<Node> getChildren() {
        return java.util.List.copyOf(parameters);
    }
}
