## Advice参数
@Advice.Origin("#t") - 注解提供了方法的类名
@Advice.Origin("#m") - 注解提供了方法的方法名称
@Advice.Origin("#r") - 注释提供了方法返回的数据类型
@Advice.Argument     - 注解提供了方法的参数值
 - @Advice.Argument(0)，表示方法的第一个参数值，参数类型需要与方法实际的参数类型一致 
   
@Advice.AllArguments Object[] params - 以数组的格式返回方法的所有参数的值

### 将数据从OnMethodEnter Advice传递到Exit Advice
```java
@Advice.OnMethodEnter()
public static String enter() {
    String sharedData = "share data";
    // 返回共享数据
    return sharedData;
}

@Advice.OnMethodExit(onThrowable = Throwable.class)
public static void exit(@Advice.Enter String shareData) {
    System.out.println("OnMethodEnter 传给 OnMethodExit 的共享数据：" + shareData);
}
```