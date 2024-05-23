package org.example._4_eventbus.monitor;

import lombok.Data;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

@Data
public class FileChangeEvent {

    private final Path path;
    private final WatchEvent.Kind<?> kind;

    public FileChangeEvent(final Path path, final WatchEvent.Kind<?> kind) {
        this.path = path;
        this.kind = kind;
    }


}
