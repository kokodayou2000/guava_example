package org.example._4_eventbus.events;

import com.google.common.eventbus.EventBus;
import org.example._4_eventbus.listener.FileChangeListener;
import org.example._4_eventbus.monitor.DirectoryTargetMonitor;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * tail
 * Apache Flume 1.7 Spooling
 * .position
 * 使用 workService的优势是能够记录 position
 *
 */
public class MonitorClient {

    public static void main(String[] args) throws IOException {
        final EventBus eventBus = new EventBus();
        eventBus.register(new FileChangeListener());

        DirectoryTargetMonitor directoryTargetMonitor = new DirectoryTargetMonitor(eventBus, "D:\\workspace\\java\\framework\\guava_example\\src\\main\\resources\\monitor");
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(()->{
            try{
                directoryTargetMonitor.stopMonitor();
            }catch (
                   Exception e
            ){
                e.printStackTrace();

            }finally {
                executorService.shutdown();
            }
        },3, TimeUnit.SECONDS);

        // 开始监控
        directoryTargetMonitor.startMonitor();
    }


}
