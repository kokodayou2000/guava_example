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
- DeadEvent
