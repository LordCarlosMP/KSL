package org.ksl.useful.extensions

import org.bukkit.*
import org.bukkit.command.*
import org.bukkit.entity.*
import org.bukkit.inventory.meta.SkullMeta
import org.ksl.useful.global.functions.*
import org.ksl.useful.global.vals.*
import java.util.*

/**
 * Created by LordCarlosMP on 28/06/2017.
 */
fun String.getOfflineUUID(): UUID = UUID.nameUUIDFromBytes(("OfflinePlayer:" + this).toByteArray())

fun String.playerWithThatName(): Player? = Bukkit.getPlayer(this)

fun String.alternateColorCodes(char: Char = '&') = ChatColor.translateAlternateColorCodes(char, this)!!

fun String.setExecutor(f: (sender: CommandSender, cmd: Command, aliasUsed: String, args: Array<out String>) -> Unit) {
	Bukkit.getPluginCommand(this).executor = CommandExecutor { sender, cmd, alias, args ->
		f(sender, cmd, alias, args)
		return@CommandExecutor true
	}
}

fun String.sendAsMsgTo(sender: CommandSender) = sender.sendMessage(this)

fun String.runCommandAsConsole() = consoleSender.execute(this)

fun String.broadcast(permission: String? = null) = if (permission == null) Bukkit.broadcastMessage(this) else Bukkit.broadcast(this, permission)

fun String.printInColor() = printInColor(this)
fun String.headWithThatName() = org.bukkit.inventory.ItemStack(Material.SKULL_ITEM, 1, 3.toShort()).apply {
	val im = itemMeta as SkullMeta
	im.owner = this@headWithThatName
	itemMeta = im
}