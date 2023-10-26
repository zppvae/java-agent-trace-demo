package org.demo.agent.bytebuddy.test;

/**
 * 使用字节码操作工具ByteBuddy实现更完善的监控程序
 *
 * jvm参数：
 * -javaagent:F:\maven\mavenProject\java-agent-trace-demo\java-agent-bytebuddy\target\java-agent-bytebuddy-1.0-SNAPSHOT.jar=testbytedubby
 *
 */
public class ApiByteBuddyTest {

    public static void main(String[] args) throws InterruptedException {
        ApiByteBuddyTest apiTest = new ApiByteBuddyTest();
        apiTest.echoHi();
    }

    private void echoHi() throws InterruptedException {
        System.out.println("hi bytebuddy agent");
        Thread.sleep((long) (Math.random() * 500));
    }

}
