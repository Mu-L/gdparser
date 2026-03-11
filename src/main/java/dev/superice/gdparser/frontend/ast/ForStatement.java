package dev.superice.gdparser.frontend.ast;

import org.jetbrains.annotations.Nullable;

/// for statement.
public record ForStatement(
        String iterator,
        @Nullable TypeRef iteratorType,
        Expression iterable,
        Block body,
        Range range
) implements Statement {

    @Override
    public java.util.List<Node> getChildren() {
        return NodeChildren.builder()
                .add(iteratorType)
                .add(iterable)
                .add(body)
                .toList();
    }
}
