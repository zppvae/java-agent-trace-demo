package org.demo.agent.test;

/**
 * jvm参数：
 * -javaagent:F:\maven\mavenProject\java-agent-trace-demo\java-agent-01\target\java-agent-01-1.0-SNAPSHOT.jar=testargs
 *
 */
public class ApiTest {

    public static void main(String[] args) {
        System.out.println("java agent 测试");
    }

}
