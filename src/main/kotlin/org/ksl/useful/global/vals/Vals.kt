@file:Suppress("unused")

package org.ksl.useful.global.vals

import org.bukkit.Bukkit
import org.bukkit.Server
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFactory

/**
 * Created by LordCarlosMP on 30/05/2017.
 */

val consoleSender: ConsoleCommandSender = Bukkit.getConsoleSender()
val server: Server = Bukkit.getServer()
val itemFactory: ItemFactory = Bukkit.getItemFactory()
val onlinePlayers: Collection<Player> = Bukkit.getOnlinePlayers()

enum class InventorySize {ONEROW, TWOROWS, THREEROWS, FOURROWS, FIVEROWS, SIXROWS;

	fun getInventorySize() = 9 * (ordinal + 1)

	companion object {
		fun withTHisNumberOfColumns(size: Int) = values()[size]
		fun fitSize(size: Int) = values()[size / 9]
	}

}

fun Int.fitInventorySize() = InventorySize.fitSize(this + this % 9)

fun adsas() {

}