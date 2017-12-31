package org.ksl.useful

import org.bukkit.scheduler.BukkitTask
import java.util.*

/**
 * Created by LordCarlosMP on 04/04/2017.
 */
open class BlockingList<in E> {

	private val bloqueando = HashMap<E, Long>()
	private val tasks = HashMap<E, BukkitTask>()

	private fun millis() = System.currentTimeMillis()

	fun isBloqueando(key: E) = bloqueando.containsKey(key)

	fun addBloqueando(key: E, segundos: Int = 10) {
		bloqueando.put(key, millis() + segundos * 1000)
	}

	fun remove(key: E) {
		bloqueando.remove(key)
		tasks[key]?.cancel()
		tasks.remove(key)
	}

	fun getRemainingMilisFor(key: E): Long {
		val l = bloqueando[key] ?: return 0
		return l - System.currentTimeMillis()
	}

//	@Suppress("unused")
//	fun getRemainingFor(key: E) = getRemainingMilisFor(key) / 1000

	fun wasFree(key: E, segundos: Int = 10): Boolean {
		if (isBloqueando(key)) {
			return false
		}
		addBloqueando(key, segundos)
		return true
	}

}
