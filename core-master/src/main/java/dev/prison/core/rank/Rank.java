package dev.prison.core.rank;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Rank {

    private final UUID rankId;
    private final String name;
    private final List<String> permissions = new ArrayList<>();

    private String prefix, suffix, color;

    public Rank(UUID rankId, String name) {
        this.rankId = rankId;
        this.name = name;
    }
}