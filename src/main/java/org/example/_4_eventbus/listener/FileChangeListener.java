package org.example._4_eventbus.listener;

import com.google.common.eventbus.Subscribe;
import org.example._4_eventbus.monitor.FileChangeEvent;

public class FileChangeListener {

    @Subscribe
    public void onChange(FileChangeEvent event){
        System.out.println("FileChangeListener onChange " + event);
    }
}
