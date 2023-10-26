package org.demo.agent.trace.threadlocal;


import java.util.Stack;

public class TrackManager {

    private static final ThreadLocal<Stack<String>> track = new ThreadLocal<Stack<String>>();

    private static String createSpan() {
        Stack<String> stack = track.get();
        if (stack == null) {
            stack = new Stack<>();
            track.set(stack);
        }
        String spanId;
        if (stack.isEmpty()) {
            spanId = TrackContext.getSpanId();
            if (spanId == null) {
                spanId = "nvl";
                TrackContext.setSpanId(spanId);
            }
        } else {
            spanId = stack.peek();
            TrackContext.setSpanId(spanId);
        }
        return spanId;
    }

    public static String createEntrySpan() {
        String span = createSpan();
        Stack<String> stack = track.get();
        stack.push(span);
        return span;
    }


    public static String getExitSpan() {
        Stack<String> stack = track.get();
        if (stack == null || stack.isEmpty()) {
            TrackContext.clear();
            return null;
        }
        // 返回栈底元素，同时将栈顶元素出栈
        return stack.pop();
    }

    public static String getCurrentSpan() {
        Stack<String> stack = track.get();
        if (stack == null || stack.isEmpty()) {
            return null;
        }
        // 返回栈顶元素
        return stack.peek();
    }


}
