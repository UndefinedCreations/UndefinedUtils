package com.undefined.api.builder

import org.bukkit.persistence.PersistentDataType

data class PDCInfo<P, C : Any>(val type: PersistentDataType<P, C>, val value: C)