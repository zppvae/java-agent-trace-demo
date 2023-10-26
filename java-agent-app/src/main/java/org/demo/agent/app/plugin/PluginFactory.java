package org.demo.agent.app.plugin;


import org.demo.agent.app.plugin.impl.jvm.JvmPlugin;
import org.demo.agent.app.plugin.impl.link.LinkPlugin;

import java.util.ArrayList;
import java.util.List;

public class PluginFactory {

    public static List<PluginService> pluginGroup = new ArrayList<>();

    static {
        // 链路监控
        pluginGroup.add(new LinkPlugin());
        // Jvm监控
//        pluginGroup.add(new JvmPlugin());
    }

}
