package org.example._4_eventbus.monitor;

import java.io.IOException;

public interface TargetMonitor {

    void startMonitor() throws IOException;

    void stopMonitor() throws IOException;
}
