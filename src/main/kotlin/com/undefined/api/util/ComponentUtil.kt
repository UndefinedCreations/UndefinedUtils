package com.undefined.api.util

import com.undefined.api.UndefinedUtils
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer

fun Component.legacyString(): String = LegacyComponentSerializer.legacyAmpersand().serialize(this)
fun String.miniMessage(): Component = UndefinedUtils.miniMessage.deserialize(this)
fun String.component(): Component = Component.text(this)