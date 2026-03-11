package dev.superice.gdparser.frontend.ast;

import java.util.List;

/// match statement.
public record MatchStatement(Expression value, List<MatchSection> sections, Range range) implements Statement {

    @Override
    public java.util.List<Node> getChildren() {
        return NodeChildren.builder()
                .add(value)
                .addAll(sections)
                .toList();
    }
}
