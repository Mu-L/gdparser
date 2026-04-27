package dev.superice.gdparser.infra.treesitter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Locale;

/// Ensures tree-sitter-ng prefers the managed native directory before temp extraction.
final class GdTreeSitterRuntimeBootstrap {

    static final String PROP_TREE_SITTER_LIB = "tree-sitter-lib";

    private static final String TREE_SITTER_RESOURCE_DIR = "lib";
    private static final String TREE_SITTER_BASE_NAME = "tree-sitter";

    private GdTreeSitterRuntimeBootstrap() {
    }

    static void initialize() {
        var configured = System.getProperty(PROP_TREE_SITTER_LIB);
        if (configured != null && !configured.isBlank()) {
            return;
        }

        var managedDir = GdLanguageLoader.resolveConfiguredResourceDir();
        if (tryInitializeIn(managedDir)) {
            return;
        }

        var tempDir = createTempRuntimeDir();
        System.setProperty(PROP_TREE_SITTER_LIB, tempDir.toString());
        extractRuntimeLibraryIfMissing(tempDir);
    }

    private static boolean tryInitializeIn(Path managedDir) {
        System.setProperty(PROP_TREE_SITTER_LIB, managedDir.toString());
        try {
            extractRuntimeLibraryIfMissing(managedDir);
            return true;
        } catch (RuntimeException _) {
            return false;
        }
    }

    private static Path createTempRuntimeDir() {
        try {
            var tempDir = Files.createTempDirectory("gdparser-tree-sitter-");
            tempDir.toFile().deleteOnExit();
            return tempDir;
        } catch (IOException exception) {
            throw new IllegalStateException("Failed to create tree-sitter-ng runtime temp directory", exception);
        }
    }

    private static void extractRuntimeLibraryIfMissing(Path managedDir) {
        var relativePath = runtimeLibraryRelativePath();
        var targetPath = managedDir.resolve(relativePath);
        if (Files.exists(targetPath)) {
            return;
        }

        try (var stream = GdTreeSitterRuntimeBootstrap.class.getClassLoader().getResourceAsStream(relativePath)) {
            if (stream == null) {
                return;
            }
            var parent = targetPath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            Files.copy(stream, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException exception) {
            throw new IllegalStateException("Failed to extract tree-sitter-ng runtime to: " + targetPath, exception);
        }
    }

    static Path runtimeLibraryPath(Path managedDir) {
        return managedDir.resolve(runtimeLibraryRelativePath());
    }

    private static String runtimeLibraryRelativePath() {
        return TREE_SITTER_RESOURCE_DIR
                + "/"
                + normalizedRuntimeArch()
                + "-"
                + normalizedRuntimeOs()
                + "-"
                + TREE_SITTER_BASE_NAME
                + "."
                + runtimeLibraryExtension();
    }

    private static String runtimeLibraryExtension() {
        return switch (normalizedRuntimeOs()) {
            case "windows" -> "dll";
            case "linux-gnu" -> "so";
            case "macos" -> "dylib";
            default -> throw new IllegalStateException("Unsupported OS for tree-sitter-ng runtime");
        };
    }

    private static String normalizedRuntimeOs() {
        var osName = System.getProperty("os.name", "unknown").toLowerCase(Locale.ROOT);
        if (osName.contains("win")) {
            return "windows";
        }
        if (osName.contains("nux") || osName.contains("nix")) {
            return "linux-gnu";
        }
        if (osName.contains("mac") || osName.contains("darwin")) {
            return "macos";
        }
        throw new IllegalStateException("Unsupported OS for tree-sitter-ng runtime: " + osName);
    }

    private static String normalizedRuntimeArch() {
        var arch = System.getProperty("os.arch", "unknown").toLowerCase(Locale.ROOT);
        return switch (arch) {
            case "x86_64", "amd64" -> "x86_64";
            case "aarch64", "arm64" -> "aarch64";
            default -> throw new IllegalStateException("Unsupported arch for tree-sitter-ng runtime: " + arch);
        };
    }
}
