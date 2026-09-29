package dev.superice.gdparser.frontend.ast;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/// AST records defensively freeze their collection components so a published AST object graph
/// stays immutable even when callers keep mutating the lists they passed in.
class AstCollectionFreezeTest {

    private static final Range RANGE = new Range(0, 0, new Point(0, 0), new Point(0, 0));

    @Test
    void sourceFileShouldFreezeStatements() {
        var statements = new ArrayList<Statement>();
        statements.add(new PassStatement(RANGE));
        var sourceFile = new SourceFile(statements, RANGE);

        statements.add(new BreakStatement(RANGE));
        assertEquals(1, sourceFile.statements().size());
        assertThrows(UnsupportedOperationException.class, () -> sourceFile.statements().add(new PassStatement(RANGE)));
    }

    @Test
    void attributeExpressionShouldFreezeSteps() {
        var steps = new ArrayList<AttributeStep>();
        steps.add(new AttributePropertyStep("a", RANGE));
        var expression = new AttributeExpression(new IdentifierExpression("base", RANGE), steps, RANGE);

        steps.add(new AttributePropertyStep("b", RANGE));
        assertEquals(1, expression.steps().size());
        assertThrows(UnsupportedOperationException.class, () -> expression.steps().clear());
    }

    @Test
    void callExpressionShouldFreezeArguments() {
        var arguments = new ArrayList<Expression>();
        arguments.add(new IdentifierExpression("x", RANGE));
        var call = new CallExpression(new IdentifierExpression("f", RANGE), arguments, RANGE);

        arguments.add(new IdentifierExpression("y", RANGE));
        assertEquals(1, call.arguments().size());
        assertThrows(UnsupportedOperationException.class, () -> call.arguments().removeFirst());
    }

    @Test
    void ifStatementShouldFreezeElifClauses() {
        var elifClauses = new ArrayList<ElifClause>();
        elifClauses.add(new ElifClause(new IdentifierExpression("c", RANGE), new Block(List.of(), RANGE), RANGE));
        var ifStatement = new IfStatement(
                new IdentifierExpression("cond", RANGE),
                new Block(List.of(), RANGE),
                elifClauses,
                null,
                RANGE
        );

        elifClauses.clear();
        assertEquals(1, ifStatement.elifClauses().size());
        assertThrows(UnsupportedOperationException.class, () -> ifStatement.elifClauses().add(null));
    }

    @Test
    void constructorDeclarationShouldFreezeParameters() {
        var parameters = new ArrayList<Parameter>();
        parameters.add(new Parameter("x", null, null, false, RANGE));
        var constructor = new ConstructorDeclaration(parameters, null, new Block(List.of(), RANGE), RANGE);

        parameters.clear();
        assertEquals(1, constructor.parameters().size());
        assertThrows(UnsupportedOperationException.class, () -> constructor.parameters().add(new Parameter("y", null, null, false, RANGE)));
    }

    @Test
    void dictionaryExpressionShouldFreezeEntries() {
        var entries = new ArrayList<DictEntry>();
        entries.add(new DictEntry(new IdentifierExpression("k", RANGE), new IdentifierExpression("v", RANGE), RANGE));
        var dictionary = new DictionaryExpression(entries, false, RANGE);

        entries.clear();
        assertEquals(1, dictionary.entries().size());
        assertThrows(UnsupportedOperationException.class, () -> dictionary.entries().clear());
    }
}
