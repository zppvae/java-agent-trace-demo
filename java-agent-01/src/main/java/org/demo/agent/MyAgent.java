package org.demo.agent;

import java.lang.instrument.Instrumentation;

/**
 * 通过 javaagent 这种机制(Instrumentation)可以在加载class文件之前修改方法的字节码(此时字节码尚未加入JVM)，
 * 动态更改类方法实现AOP，提供监控服务如；方法调用时长、可用率、内存等
 */
public class MyAgent {

    // JVM首先尝试在代理类上调用以下方法
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("this is JavaAgent  " + agentArgs);
    }

    // 如果代理类没有实现上面的方法，那么 JVM 将尝试调用该方法
    public static void premain(String agentArgs) {
    }

}
