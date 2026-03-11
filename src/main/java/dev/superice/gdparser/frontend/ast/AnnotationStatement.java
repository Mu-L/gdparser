package dev.superice.gdparser.frontend.ast;

import java.util.List;

/// Standalone annotation lowered from `annotation`/`annotations` CST nodes.
public record AnnotationStatement(String name, List<Expression> arguments, Range range) implements Statement {

    @Override
    public java.util.List<Node> getChildren() {
        return java.util.List.copyOf(arguments);
    }
}
