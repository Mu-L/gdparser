package dev.superice.gdparser.frontend.ast;

import org.jetbrains.annotations.Nullable;

/// class_name declaration.
/// In GDScript 4.x, editor icons are represented by a separate `@icon` annotation.
public record ClassNameStatement(
        String name,
        @Nullable String extendsTarget,
        Range range
) implements Statement {
}
