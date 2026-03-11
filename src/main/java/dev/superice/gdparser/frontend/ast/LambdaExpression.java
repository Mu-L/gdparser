package dev.superice.gdparser.frontend.ast;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/// lambda expression.
public record LambdaExpression(
        @Nullable String name,
        List<Parameter> parameters,
        @Nullable TypeRef returnType,
        Block body,
        Range range
) implements Expression {

    @Override
    public java.util.List<Node> getChildren() {
        return NodeChildren.builder()
                .addAll(parameters)
                .add(returnType)
                .add(body)
                .toList();
    }
}
