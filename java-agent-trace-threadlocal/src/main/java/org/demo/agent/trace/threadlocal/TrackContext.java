package org.demo.agent.trace.threadlocal;


public class TrackContext {

    private static final ThreadLocal<String> trackLocal = new ThreadLocal<String>();

    public static void clear(){
        trackLocal.remove();
    }

    public static String getSpanId(){
        return trackLocal.get();
    }

    public static void setSpanId(String spanId){
        trackLocal.set(spanId);
    }

}
