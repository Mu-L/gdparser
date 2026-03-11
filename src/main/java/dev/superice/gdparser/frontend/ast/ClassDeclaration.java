package dev.superice.gdparser.frontend.ast;

import org.jetbrains.annotations.Nullable;

/// Inner or top-level `class` declaration.
public record ClassDeclaration(String name, @Nullable String extendsTarget, Block body,
                               Range range) implements Statement {

    @Override
    public java.util.List<Node> getChildren() {
        return java.util.List.of(body);
    }
}
