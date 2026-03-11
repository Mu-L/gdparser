package dev.superice.gdparser.frontend.ast;

import org.jetbrains.annotations.Nullable;

/// function/lambda/signal parameter.
public record Parameter(
        String name,
        @Nullable TypeRef type,
        @Nullable Expression defaultValue,
        boolean variadic,
        Range range
) implements Node {

    @Override
    public java.util.List<Node> getChildren() {
        return NodeChildren.builder()
                .add(type)
                .add(defaultValue)
                .toList();
    }
}
