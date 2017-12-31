package org.ksl.useful.extensions

import org.bukkit.*
import org.bukkit.entity.*
import java.util.*

fun UUID.playerWithThatUUID(): Player? = Bukkit.getPlayer(this)

val UUID.offlinePlayer: OfflinePlayer? get() = Bukkit.getOfflinePlayer(this)