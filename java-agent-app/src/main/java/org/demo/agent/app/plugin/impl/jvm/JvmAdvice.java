package org.demo.agent.app.plugin.impl.jvm;


import net.bytebuddy.asm.Advice;

public class JvmAdvice {

    /**
     * 方法退出后执行
     */
    @Advice.OnMethodExit()
    public static void exit() {
        JvmStack.printMemoryInfo();
        JvmStack.printGCInfo();
    }

}
