package dev.superice.gdparser.frontend.ast;

/// dictionary key/value pair.
public record DictEntry(Expression key, Expression value, Range range) implements Node {

    @Override
    public java.util.List<Node> getChildren() {
        return java.util.List.of(key, value);
    }
}
