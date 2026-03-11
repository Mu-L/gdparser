package dev.superice.gdparser.frontend.ast;

/// Controls how ASTWalker descends after a node-specific handler method runs.
public enum FrontendASTTraversalDirective {
    CONTINUE,
    SKIP_CHILDREN
}
