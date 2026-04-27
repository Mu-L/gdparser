package dev.superice.gdparser.infra.treesitter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GdTreeSitterRuntimeBootstrapTest {

    @TempDir
    Path tempDir;

    @AfterEach
    void tearDown() {
        System.clearProperty(GdLanguageLoader.PROP_RESOURCE_DIR);
        System.clearProperty(GdTreeSitterRuntimeBootstrap.PROP_TREE_SITTER_LIB);
    }

    @Test
    void initializeFallsBackToTempDirWhenManagedResourceDirCannotBeCreated() throws IOException {
        var blockedResourceDir = Files.createFile(tempDir.resolve("native"));
        System.setProperty(GdLanguageLoader.PROP_RESOURCE_DIR, blockedResourceDir.toString());

        GdTreeSitterRuntimeBootstrap.initialize();

        var runtimeDir = Path.of(System.getProperty(GdTreeSitterRuntimeBootstrap.PROP_TREE_SITTER_LIB));
        assertNotEquals(blockedResourceDir, runtimeDir);
        assertTrue(Files.exists(GdTreeSitterRuntimeBootstrap.runtimeLibraryPath(runtimeDir)));
    }
}
