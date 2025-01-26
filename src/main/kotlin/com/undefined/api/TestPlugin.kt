package com.undefined.api

import com.undefined.api.event.event
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin() {

    override fun onEnable() {
        UndefinedUtils(this)
        event<PlayerJoinEvent> {
            player.showDemoScreen()
        }
    }

}