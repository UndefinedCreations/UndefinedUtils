package com.undefined.api.event

import com.undefined.api.UndefinedUtils
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener

inline fun <reified T : Event> event(
    priority: EventPriority = EventPriority.NORMAL,
    ignoreCancelled: Boolean = false,
    crossinline callback: T.() -> Unit
): Listener = UndefinedListener().apply {
    UndefinedUtils.plugin.server.pluginManager.registerEvent(
        T::class.java,
        this,
        priority,
        { _, event ->
            if (event is T) callback(event)
        },
        UndefinedUtils.plugin,
        ignoreCancelled
    )
}

class UndefinedListener : Listener {
    fun unregister() = HandlerList.unregisterAll(this)
}