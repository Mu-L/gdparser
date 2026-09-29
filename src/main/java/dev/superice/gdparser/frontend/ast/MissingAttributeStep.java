package dev.superice.gdparser.frontend.ast;

/// Explicit marker for the missing final step of an attribute chain such as `receiver.`.
/// The range is the zero-width span where the member name was expected, right after the dot.
public record MissingAttributeStep(Range range) implements AttributeStep {
}
