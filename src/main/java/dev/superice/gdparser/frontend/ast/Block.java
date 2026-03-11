package dev.superice.gdparser.frontend.ast;

import java.util.List;

/// Statement block body.
public record Block(List<Statement> statements, Range range) implements Statement {

    @Override
    public java.util.List<Node> getChildren() {
        return java.util.List.copyOf(statements);
    }
}
