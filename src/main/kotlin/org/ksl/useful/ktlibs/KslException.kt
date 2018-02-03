package org.ksl.useful.ktlibs

import org.bukkit.plugin.java.JavaPlugin
import org.ksl.useful.extensions.printStackTrace

class KslException(val plugin: JavaPlugin, message: String, cause: Throwable) : Exception(message, cause) {
	override fun printStackTrace() {
		val c = cause
		val m = message
		if (c != null && m != null) {
			plugin.printStackTrace(c, m)
		}else super.printStackTrace()
	}
}