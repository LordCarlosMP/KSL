@file:Suppress("unused")

package org.ksl.useful.extensions

import org.bukkit.Bukkit
import org.bukkit.ChatColor.RED
import org.bukkit.event.Event
import java.io.PrintWriter

fun Throwable.inConsole() = Bukkit.getConsoleSender().sendMessage("$RED${this.desc()}")

fun Throwable.desc(): kotlin.String {
	val errors = PrintWriter("")
	printStackTrace(errors)
	return errors.toString()
}

inline fun inCatch(w: () -> Unit) = try {//a pensar
	w()
} catch (e: Throwable) {
	e.inConsole()
}

fun Event.call() = Bukkit.getPluginManager().callEvent(this)