package org.demo.agent.monitor.test;

/**
 * 监控方法执行耗时
 *
 * jvm参数：
 * -javaagent:F:\maven\mavenProject\java-agent-trace-demo\java-agent-monitor-time\target\java-agent-monitor-time-1.0-SNAPSHOT.jar=testmonitor
 *
 */
public class ApiMonitorTest {

    public static void main(String[] args) {
        ApiMonitorTest apiTest = new ApiMonitorTest();
        apiTest.echoHi();
    }

    private void echoHi(){
        System.out.println("test monitor hi");
    }

}
