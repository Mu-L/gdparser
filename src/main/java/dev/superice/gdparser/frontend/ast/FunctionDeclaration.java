package dev.superice.gdparser.frontend.ast;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/// func declaration.
public record FunctionDeclaration(
        String name,
        List<Parameter> parameters,
        @Nullable TypeRef returnType,
        boolean isStatic,
        Block body,
        Range range
) implements Statement {

    @Override
    public java.util.List<Node> getChildren() {
        return NodeChildren.builder()
                .addAll(parameters)
                .add(returnType)
                .add(body)
                .toList();
    }
}
