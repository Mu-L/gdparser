package dev.superice.gdparser.frontend.ast;

import org.jetbrains.annotations.Nullable;

/// One enum entry.
public record EnumMember(String name, @Nullable Expression value, Range range) implements Node {

    @Override
    public java.util.List<Node> getChildren() {
        return NodeChildren.builder()
                .add(value)
                .toList();
    }
}
