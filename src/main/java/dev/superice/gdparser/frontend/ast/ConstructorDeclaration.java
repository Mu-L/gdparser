package dev.superice.gdparser.frontend.ast;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/// `_init` constructor declaration lowered from `constructor_definition`.
/// Legacy 3.x parent-constructor chaining syntax is rejected during lowering.
public record ConstructorDeclaration(
        List<Parameter> parameters,
        @Nullable TypeRef returnType,
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
