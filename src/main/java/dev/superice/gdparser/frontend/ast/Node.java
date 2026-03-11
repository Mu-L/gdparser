package dev.superice.gdparser.frontend.ast;

import java.util.List;

/// Base contract for all AST nodes.
public sealed interface Node permits SourceFile, Statement, Expression, Parameter, TypeRef, MatchSection, DictEntry, AttributeStep, EnumMember, ElifClause {
    Range range();

    /// Returns the direct AST children in source order.
    ///
    /// Scalar metadata such as identifiers and flags are intentionally omitted, and `null`
    /// slots are not materialized as placeholder entries.
    default List<Node> getChildren() {
        return List.of();
    }
}
