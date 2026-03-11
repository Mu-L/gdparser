package dev.superice.gdparser.frontend.ast;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/// Helper for assembling immutable child lists while filtering out absent optional nodes.
final class NodeChildren {

    private final ArrayList<Node> children = new ArrayList<>();

    private NodeChildren() {
    }

    static NodeChildren builder() {
        return new NodeChildren();
    }

    NodeChildren add(@Nullable Node child) {
        if (child != null) {
            children.add(child);
        }
        return this;
    }

    NodeChildren addAll(List<? extends Node> directChildren) {
        children.addAll(directChildren);
        return this;
    }

    List<Node> toList() {
        return List.copyOf(children);
    }
}
