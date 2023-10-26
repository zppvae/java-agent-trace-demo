package org.demo.agent.app.test;

/**
 * 应用级监控
 *
 * jvm 参数：
 *
 *
 */
public class ApiAppTest {

    public static void main(String[] args) {

        //线程一
        new Thread(() -> new ApiAppTest().http_lt1("APP1")).start();

        //线程二
        new Thread(() -> {
            new ApiAppTest().http_lt2("APP2");
        }).start();

    }

    public String http_lt1(String name) {
        try {
            Thread.sleep((long) (Math.random() * 500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // int a = 1 / 0;
        System.out.println("测试结果：hi1 " + name);
        http_lt2(name);
        return "http_lt1";
    }

    public void http_lt2(String name) {
        try {
            Thread.sleep((long) (Math.random() * 500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("测试结果：hi2 " + name);
        http_lt3(name);
    }

    public void http_lt3(String name) {
        try {
            Thread.sleep((long) (Math.random() * 500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("测试结果：hi3 " + name);
    }
}
