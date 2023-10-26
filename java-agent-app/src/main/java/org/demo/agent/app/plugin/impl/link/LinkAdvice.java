package org.demo.agent.app.plugin.impl.link;


import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import org.demo.agent.app.trace.Span;
import org.demo.agent.app.trace.TrackContext;
import org.demo.agent.app.trace.TrackManager;

import java.util.UUID;

public class LinkAdvice {

    /**
     * 方法进入前执行，设置唯一的请求id
     *
     * @param className
     * @param methodName
     */
    @Advice.OnMethodEnter()
    public static String enter(@Advice.Origin("#t") String className,
                             @Advice.Origin("#m") String methodName,
                             @Advice.Origin("#r") String methodReturnType,
                             @Advice.AllArguments Object[] params) {
        Span currentSpan = TrackManager.getCurrentSpan();
        String linkId = null;
        if (null == currentSpan) {
            linkId = UUID.randomUUID().toString();
            TrackContext.setLinkId(linkId);
        } else {
            linkId = currentSpan.getLinkId();
        }
        TrackManager.createEntrySpan();
        System.out.println("链路追踪(MQ)：" + linkId + " " + className + "." + methodName +
                ", 方法参数："+params[0]);

        String sharedData = "share data";
        // 返回共享数据
        return sharedData;
    }

    /**
     *
     * @param className
     * @param methodName
     * @param methodReturnType     方法返回类型
     * @param returnObject         返回结果
     */
    @Advice.OnMethodExit(onThrowable = Throwable.class)
    public static void exit(@Advice.Origin("#t") String className,
                            @Advice.Origin("#m") String methodName,
                            @Advice.Origin("#r") String methodReturnType,
                            @Advice.Return(typing= Assigner.Typing.DYNAMIC) Object returnObject,
                            @Advice.Thrown(readOnly=false) Throwable excp,
                            @Advice.Enter String shareData) {
        if (excp == null) {
            System.out.println("没有异常发生");
        } else {
            System.out.println("异常信息：" + excp.getMessage());
        }
        Span exitSpan = TrackManager.getExitSpan();
        if (null == exitSpan) return;
        System.out.println("链路追踪(MQ)：" + exitSpan.getLinkId() + " " + className + "." + methodName +
                ", 方法返回类型："+methodReturnType+", " +
                ", 返回结果：" + (returnObject != null ? returnObject.toString() : null) + ", " +
                "耗时：" + (System.currentTimeMillis() - exitSpan.getEnterTime().getTime()) + "ms");

        System.out.println("OnMethodEnter 传给 OnMethodExit 的共享数据：" + shareData);
    }

}
