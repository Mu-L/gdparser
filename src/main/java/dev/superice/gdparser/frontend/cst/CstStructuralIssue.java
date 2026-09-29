package dev.superice.gdparser.frontend.cst;

/// A detected structural issue in a CST node.
/// `node` retains the originating view so recovery passes can coordinate diagnostics by identity.
public record CstStructuralIssue(CstIssueKind kind, String nodeType, CstRange range, CstNodeView node) {
}
