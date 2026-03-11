package dev.superice.gdparser.frontend.ast;

import org.jetbrains.annotations.Nullable;

/// `assert(...)` lowered as a dedicated statement.
public record AssertStatement(Expression condition, @Nullable Expression message, Range range) implements Statement {

    @Override
    public java.util.List<Node> getChildren() {
        return NodeChildren.builder()
                .add(condition)
                .add(message)
                .toList();
    }
}
