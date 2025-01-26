package com.undefined.api

import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger

object UndefinedUtils {

    lateinit var plugin: JavaPlugin
    lateinit var logger: Logger

    fun initialize(plugin: JavaPlugin, logger: Logger = this.plugin.logger) {
        this.plugin = plugin
        this.logger = logger
    }

}