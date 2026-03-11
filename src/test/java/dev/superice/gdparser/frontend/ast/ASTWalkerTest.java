package dev.superice.gdparser.frontend.ast;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/// Verifies traversal order, dispatch, pruning, and defensive checks for ASTWalker.
class ASTWalkerTest {

    private static final Range RANGE = new Range(0, 1, new Point(0, 0), new Point(0, 1));

    @Test
    void walkShouldVisitNodesInPreOrderAndUseTypeSpecificHandlers() {
        var visited = new ArrayList<String>();
        var walker = new ASTWalker(new ASTNodeHandler() {
            @Override
            public FrontendASTTraversalDirective handleNode(Node node) {
                visited.add("generic:" + node.getClass().getSimpleName());
                return FrontendASTTraversalDirective.CONTINUE;
            }

            @Override
            public FrontendASTTraversalDirective handleSourceFile(SourceFile node) {
                visited.add("specific:SourceFile");
                return FrontendASTTraversalDirective.CONTINUE;
            }

            @Override
            public FrontendASTTraversalDirective handleFunctionDeclaration(FunctionDeclaration node) {
                visited.add("specific:FunctionDeclaration");
                return FrontendASTTraversalDirective.CONTINUE;
            }

            @Override
            public FrontendASTTraversalDirective handleCallExpression(CallExpression node) {
                visited.add("specific:CallExpression");
                return FrontendASTTraversalDirective.CONTINUE;
            }
        });

        walker.walk(sampleTree());

        assertEquals(List.of(
                "specific:SourceFile",
                "specific:FunctionDeclaration",
                "generic:Parameter",
                "generic:TypeRef",
                "generic:TypeRef",
                "generic:Block",
                "generic:VariableDeclaration",
                "specific:CallExpression",
                "generic:IdentifierExpression",
                "generic:LiteralExpression",
                "generic:ReturnStatement",
                "generic:IdentifierExpression",
                "generic:PassStatement"
        ), visited);
    }

    @Test
    void walkShouldSkipChildrenOfTheCurrentNodeOnly() {
        var visited = new ArrayList<String>();
        var walker = new ASTWalker(new ASTNodeHandler() {
            @Override
            public FrontendASTTraversalDirective handleNode(Node node) {
                visited.add(node.getClass().getSimpleName());
                return FrontendASTTraversalDirective.CONTINUE;
            }

            @Override
            public FrontendASTTraversalDirective handleFunctionDeclaration(FunctionDeclaration node) {
                visited.add(node.getClass().getSimpleName());
                return FrontendASTTraversalDirective.SKIP_CHILDREN;
            }
        });

        walker.walk(sampleTree());

        assertEquals(List.of("SourceFile", "FunctionDeclaration", "PassStatement"), visited);
    }

    @Test
    void walkShouldRejectNullRootsAndNullDirectives() {
        var walker = new ASTWalker(new ASTNodeHandler() {
            @Override
            public FrontendASTTraversalDirective handlePassStatement(PassStatement node) {
                return null;
            }
        });

        assertThrows(NullPointerException.class, () -> new ASTWalker(new ASTNodeHandler() {
        }).walk(null));
        assertThrows(NullPointerException.class, () -> walker.walk(new PassStatement(RANGE)));
    }

    private static SourceFile sampleTree() {
        var parameterType = new TypeRef("int", RANGE);
        var returnType = new TypeRef("void", RANGE);
        var function = new FunctionDeclaration(
                "run",
                List.of(new Parameter("value", parameterType, null, false, RANGE)),
                returnType,
                false,
                new Block(List.of(
                        new VariableDeclaration(
                                DeclarationKind.VAR,
                                "result",
                                null,
                                new CallExpression(
                                        new IdentifierExpression("compute", RANGE),
                                        List.of(new LiteralExpression("number", "1", RANGE)),
                                        RANGE
                                ),
                                false,
                                "variable_statement",
                                RANGE
                        ),
                        new ReturnStatement(new IdentifierExpression("result", RANGE), RANGE)
                ), RANGE),
                RANGE
        );

        return new SourceFile(List.of(function, new PassStatement(RANGE)), RANGE);
    }
}
