package org.example._4_eventbus.monitor;

import com.google.common.eventbus.EventBus;

import java.io.IOException;
import java.nio.file.*;

public class DirectoryTargetMonitor implements TargetMonitor{

    private WatchService watchService;

    private final EventBus eventBus;

    private final Path path;

    private volatile boolean start = false;

    public DirectoryTargetMonitor(final EventBus eventBus,final String targetPath){
        this(eventBus,targetPath,"");
    }

    public DirectoryTargetMonitor(final EventBus eventBus,final String targetPath,final String... morePath){
        this.eventBus = eventBus;
        this.path = Paths.get(targetPath,morePath);
    }

    @Override
    public void startMonitor() throws IOException {
        this.watchService = FileSystems.getDefault().newWatchService();
        // 当修改,创建,删除文件的时候，
        this.path.register(watchService,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_CREATE
        );

        System.out.println("The directory target monitor has been started");
        this.start = true;
        while (start){

            WatchKey watchKey = null;

            try {
                // block take
                watchKey = watchService.take();
                // 获取事件列表
                watchKey.pollEvents().forEach((event)->{
                    // 事件类型
                    WatchEvent.Kind<?> kind = event.kind();
                    // 事件的内容
                    Path path  = (Path) event.context();
                    // 获取相对子路径
                    Path child = DirectoryTargetMonitor.this.path.resolve(path);

                    eventBus.post(new FileChangeEvent(child,kind));
                });
            } catch (InterruptedException e) {
                this.start = false;
            }finally {
                if (watchKey != null){
                    watchKey.reset();
                }
            }

        }

    }

    @Override
    public void stopMonitor() throws IOException {
        System.out.println("The directory target monitor has been stopped");
        // 让StartMonitor捕获异常
        Thread.currentThread().interrupt();
        this.start = false;
        this.watchService.close();
    }
}
