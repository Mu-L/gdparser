package dev.superice.gdparser.frontend.ast;

import dev.superice.gdparser.frontend.cst.CstIssueKind;

/// Statement placeholder for a CST `ERROR`/`MISSING` node that could not be lowered faithfully.
/// Keeps the structural issue kind, the original CST node type, and the raw source fragment so
/// downstream passes can reason about broken regions without guessing.
public record ErrorStatement(CstIssueKind kind, String nodeType, String sourceText, Range range) implements Statement {
}
