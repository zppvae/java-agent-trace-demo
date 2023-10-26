package org.demo.agent.trace.threadlocal.test;

/**
 * 使用threadlocal链路跟踪
 *
 * jvm参数：
 * -javaagent:F:\maven\mavenProject\java-agent-trace-demo\java-agent-trace-threadlocal\target\java-agent-trace-threadlocal-1.0-SNAPSHOT.jar=trace
 *
 */
public class ApiTraceTest {

    public static void main(String[] args) {
        new Thread(() -> new ApiTraceTest().http_lt1()).start();
    }


    public void http_lt1() {
        System.out.println(Thread.currentThread().getName() + ", 测试结果：hi1");
        http_lt2();
    }

    public void http_lt2() {
        System.out.println(Thread.currentThread().getName() + ", 测试结果：hi2");
        http_lt3();
    }

    public void http_lt3() {
        System.out.println(Thread.currentThread().getName() + ", 测试结果：hi3");
    }


}
