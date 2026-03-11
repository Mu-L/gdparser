package dev.superice.gdparser.frontend.ast;

/// `preload(...)` lowered as a dedicated expression.
public record PreloadExpression(Expression path, Range range) implements Expression {

    @Override
    public java.util.List<Node> getChildren() {
        return java.util.List.of(path);
    }
}
