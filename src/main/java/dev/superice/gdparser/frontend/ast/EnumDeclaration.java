package dev.superice.gdparser.frontend.ast;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/// `enum` declaration.
public record EnumDeclaration(@Nullable String name, List<EnumMember> members, Range range) implements Statement {

    @Override
    public java.util.List<Node> getChildren() {
        return java.util.List.copyOf(members);
    }
}
