package org.demo.agent.trace.threadlocal;

import net.bytebuddy.asm.Advice;

import java.util.UUID;

public class MyAdvice {

    @Advice.OnMethodEnter()
    public static void enter(@Advice.Origin("#t") String className, @Advice.Origin("#m") String methodName) {
        String spanId = TrackManager.getCurrentSpan();
        if (null == spanId) {
            spanId = UUID.randomUUID().toString();
            TrackContext.setSpanId(spanId);
        }
        String entrySpan = TrackManager.createEntrySpan();
        System.out.println("链路追踪：" + entrySpan + " " + className + "." + methodName);

    }

    @Advice.OnMethodExit()
    public static void exit(@Advice.Origin("#t") String className, @Advice.Origin("#m") String methodName) {
        TrackManager.getExitSpan();
    }

}
