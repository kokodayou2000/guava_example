EventBus 

内存中的消息中间件

使用`@Subscribe` 注解来监听

```java
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        // 注册
        eventBus.register(new SimpleListener());
        // 发送事件
        eventBus.post("simple event");
    }
```

- 创建事件总线
  - 注册 subscriber
  - post 事件
- subscriber
  - 订阅 event
  - 继承 listener
  - 继承 event
- 异常处理
  - ExceptionEventBusExample
  - 能通过重写SubscriberExceptionHandler 来自定义异常处理
- DeadEvent
  - DeadEventEventBusException 
  - 处理未被订阅的消息，可以记录日志、发送警报或采取其他补救措施
- unregister
  - 移除注册的监听器
- 使用 EventBus 连接两个服务
  - ComEachOtherEventBusExample
  - 类似本地的RPC通信