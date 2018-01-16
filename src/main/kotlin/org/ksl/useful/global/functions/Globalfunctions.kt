@file:Suppress("unused")

package org.ksl.useful.global.functions

import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.ksl.useful.extensions.Title
import org.ksl.useful.extensions.playerWithThatUUID
import org.ksl.useful.extensions.runCommandAsConsole
import org.ksl.useful.global.vals.InventorySize
import org.ksl.useful.global.vals.onlinePlayers
import java.util.*

/**
 * Created by LordCarlosMP on 29/06/2017.
 */

fun createInventory(size: Int, owner: InventoryHolder? = null): Inventory = Bukkit.createInventory(owner, size)

fun createInventory(size: InventorySize, owner: InventoryHolder? = null): Inventory = Bukkit.createInventory(owner, size.getInventorySize())

fun createInventory(size: Int, title: String = "", owner: InventoryHolder? = null): Inventory = Bukkit.createInventory(owner, size, title)

fun createInventory(size: InventorySize, title: String = "", owner: InventoryHolder? = null): Inventory = Bukkit.createInventory(owner, size.getInventorySize(), title)

fun createInventory(type: InventoryType, owner: InventoryHolder? = null): Inventory = Bukkit.createInventory(owner, type)

fun createInventory(type: InventoryType, title: String = "", owner: InventoryHolder? = null): Inventory = Bukkit.createInventory(owner, type, title)

inline fun forEachOnlinePlayer(f: (Player) -> Unit) {
	for (p in onlinePlayers) f.invoke(p)
}

inline fun inEachOnlinePlayer(f: Player.() -> Unit) {
	for (p in onlinePlayers) p.f()
}

fun CommandSender.execute(cmd: String) = Bukkit.dispatchCommand(this, cmd)
fun executeCommand(cmd: String) = cmd.runCommandAsConsole()


//TODO: val?
fun isPrimaryThread(): Boolean = Bukkit.isPrimaryThread()

inline fun Iterable<UUID>.inEachOnlinePlayer(f: Player.() -> Unit) {
	for (id in this) id.playerWithThatUUID()?.f()
}

fun printInColor(msg: String) = Bukkit.getConsoleSender().sendMessage(msg)

fun broadcastInTitle(title: String, subTitle: String) = Title(title, subTitle).broadcast()
