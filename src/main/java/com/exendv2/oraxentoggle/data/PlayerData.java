package com.exendv2.oraxentoggle.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class PlayerData {
    private final UUID playerUUID;
    private boolean oraxenEnabled = true;
    private long lastUpdate = System.currentTimeMillis();
}
