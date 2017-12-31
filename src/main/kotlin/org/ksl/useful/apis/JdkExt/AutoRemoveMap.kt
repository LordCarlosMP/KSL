package org.ksl.useful.apis.JdkExt

import org.ksl.plugin.*
import org.ksl.useful.extensions.*

class AutoRemoveMap<K, V> : HashMap<K, V>() {
	fun putIfAbsent(key: K, value: V, ticks: Int): V? {
		KslPlugin.later(ticks) {
			remove(key, value)
		}
		return super.putIfAbsent(key, value)
	}

	fun put(key: K, value: V, ticks: Int): V? {
		KslPlugin.later(ticks) {
			remove(key, value)
		}
		return super.put(key, value)
	}
}