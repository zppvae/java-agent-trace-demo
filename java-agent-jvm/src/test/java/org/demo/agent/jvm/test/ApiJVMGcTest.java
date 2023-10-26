package org.demo.agent.jvm.test;


import java.util.LinkedList;
import java.util.List;

/**
 *
 * 监控jvm gc参数信息
 *
 * jvm参数：
 * -javaagent:F:\maven\mavenProject\java-agent-trace-demo\java-agent-jvm\target\java-agent-jvm-1.0-SNAPSHOT.jar=test
 *
 */
public class ApiJVMGcTest {

    public static void main(String[] args) {
        while (true) {
            List<Object> list = new LinkedList<>();
            list.add("javaagent gc jvm");
        }
    }

}
