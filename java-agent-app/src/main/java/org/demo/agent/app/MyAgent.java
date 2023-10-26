package org.demo.agent.app;


import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.utility.JavaModule;
import org.demo.agent.app.plugin.InterceptPoint;
import org.demo.agent.app.plugin.PluginFactory;
import org.demo.agent.app.plugin.PluginService;

import java.lang.instrument.Instrumentation;
import java.util.List;

public class MyAgent {

    //JVM 首先尝试在代理类上调用以下方法
    public static void premain(String agentArgs, Instrumentation inst) {

        System.out.println("javaagent链路追踪");
        System.out.println("==========================================================\r\n");
        AgentBuilder agentBuilder = new AgentBuilder.Default();

        List<PluginService> pluginGroup = PluginFactory.pluginGroup;
        for (PluginService plugin : pluginGroup) {
            InterceptPoint[] interceptPoints = plugin.buildInterceptPoint();
            for (InterceptPoint point : interceptPoints) {

                AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, javaModule) -> {
                    builder = builder.visit(Advice.to(plugin.adviceClass()).on(point.buildMethodsMatcher()));
                    return builder;
                };
                agentBuilder = agentBuilder.type(point.buildTypesMatcher()).transform(transformer).asDecorator();
            }
        }

        //监听
        AgentBuilder.Listener listener = new AgentBuilder.Listener() {
            @Override
            public void onDiscovery(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b, DynamicType dynamicType) {
                System.out.println("onTransformation：" + typeDescription);
            }

            @Override
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

            @Override
            public void onError(String s, ClassLoader classLoader, JavaModule javaModule, boolean b, Throwable throwable) {

            }

            @Override
            public void onComplete(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

        };

        agentBuilder.with(listener).installOn(inst);

    }

}
