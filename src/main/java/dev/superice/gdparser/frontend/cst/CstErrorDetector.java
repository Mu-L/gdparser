package dev.superice.gdparser.frontend.cst;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/// Detects `ERROR`/`MISSING` nodes from a CST view tree.
///
/// The bundled grammar reports missing symbols in two ways: real tree-sitter `MISSING` nodes
/// (`isMissing`) and zero-width named placeholder nodes whose subtree carries an error cost
/// (`hasError` with an empty range), e.g. the absent member name in `receiver. + 1`. Both forms
/// surface here as `CstIssueKind.MISSING` issues whose node type names the expected symbol.
public final class CstErrorDetector {

    private CstErrorDetector() {
    }

    public static @NotNull List<CstStructuralIssue> collect(CstNodeView root) {
        var issues = new ArrayList<CstStructuralIssue>();
        var stack = new ArrayDeque<CstNodeView>();
        stack.push(root);

        while (!stack.isEmpty()) {
            var node = stack.pop();
            if (node.isError()) {
                issues.add(new CstStructuralIssue(CstIssueKind.ERROR, node.type(), node.range(), node));
            }
            if (isMissingPlaceholder(node)) {
                issues.add(new CstStructuralIssue(CstIssueKind.MISSING, node.type(), node.range(), node));
            }
            for (var child : node.children()) {
                stack.push(child);
            }
        }

        return List.copyOf(issues);
    }

    public static boolean hasIssues(CstNodeView root) {
        return !collect(root).isEmpty();
    }

    /// True for nodes that stand for a symbol the grammar expected but never matched in source:
    /// tree-sitter `MISSING` nodes and zero-width error-carrying placeholder nodes.
    public static boolean isMissingPlaceholder(CstNodeView node) {
        return node.isMissing() || (node.hasError() && node.range().startByte() >= node.range().endByte());
    }
}
