package com.undefined.api

import org.bukkit.plugin.java.JavaPlugin

class UndefinedUtils(javaPlugin: JavaPlugin) {

    init {
        plugin = javaPlugin
    }

    companion object {
        lateinit var plugin: JavaPlugin
    }

}