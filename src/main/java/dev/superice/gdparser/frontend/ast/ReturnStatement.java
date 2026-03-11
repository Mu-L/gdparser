package dev.superice.gdparser.frontend.ast;

import org.jetbrains.annotations.Nullable;

/// return statement.
public record ReturnStatement(@Nullable Expression value, Range range) implements Statement {

    @Override
    public java.util.List<Node> getChildren() {
        return NodeChildren.builder()
                .add(value)
                .toList();
    }
}
