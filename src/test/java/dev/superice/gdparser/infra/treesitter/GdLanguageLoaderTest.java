package dev.superice.gdparser.infra.treesitter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GdLanguageLoaderTest {

    @AfterEach
    void tearDown() {
        System.clearProperty(GdLanguageLoader.PROP_RESOURCE_DIR);
    }

    @Test
    void defaultResourceDirIsBesideLoaderCodeSource() throws URISyntaxException {
        System.clearProperty(GdLanguageLoader.PROP_RESOURCE_DIR);

        assertEquals(codeSourceDir().resolve("native"), GdLanguageLoader.resolveConfiguredResourceDir());
    }

    @Test
    void configuredResourceDirOverridesCodeSourceDefault() {
        var configured = Path.of("tmp", "configured-native").toAbsolutePath().normalize();

        System.setProperty(GdLanguageLoader.PROP_RESOURCE_DIR, configured.toString());

        assertEquals(configured, GdLanguageLoader.resolveConfiguredResourceDir());
    }

    private static Path codeSourceDir() throws URISyntaxException {
        var location = Path.of(GdLanguageLoader.class.getProtectionDomain().getCodeSource().getLocation().toURI())
                .toAbsolutePath()
                .normalize();
        return Files.isRegularFile(location) ? location.getParent() : location;
    }
}
