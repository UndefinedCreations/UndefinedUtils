package com.undefined.api

import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger

object UndefinedUtils {

    lateinit var plugin: JavaPlugin
    lateinit var logger: Logger
    var miniMessage = MiniMessage.miniMessage()
        private set

    fun initialize(plugin: JavaPlugin, logger: Logger = this.plugin.logger) {
        this.plugin = plugin
        this.logger = logger
    }

    fun setMiniMessage(miniMessage: MiniMessage): UndefinedUtils = apply { this.miniMessage = miniMessage }

}