package dev.superice.gdparser.frontend.ast;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/// one pattern section in match statement.
public record MatchSection(
        List<Expression> patterns,
        @Nullable Expression guard,
        Block body,
        Range range
) implements Node {

    public MatchSection {
        patterns = List.copyOf(patterns);
    }

    @Override
    public java.util.List<Node> getChildren() {
        return NodeChildren.builder()
                .addAll(patterns)
                .add(guard)
                .add(body)
                .toList();
    }
}
