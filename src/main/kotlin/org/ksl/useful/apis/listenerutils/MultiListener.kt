package org.ksl.useful.apis.listenerutils

import org.ksl.useful.extensions.KotlinListener
import org.ksl.useful.ktlibs.each

class MultiListener(vararg listeners: KotlinListener) : KotlinListener {

	private val listeners = HashSet<KotlinListener>()

	init {
		addListeners(*listeners)
	}

	override fun register() {
		listeners.each { register() }
	}

	override fun unRegister() {
		listeners.each { unRegister() }
	}

	fun addListeners(vararg newListeners: KotlinListener) {
		for (listener in newListeners) if (listener is MultiListener) listeners.addAll(listener.listeners) else listeners.add(listener)
	}

	fun removeListeners(vararg newListeners: KotlinListener) {
		for (listener in newListeners) if (listener is MultiListener) listeners.removeAll(listener.listeners) else listeners.remove(listener)
	}

	operator fun contains(listener: KotlinListener) = listener in listeners

	operator fun minus(listener: KotlinListener) = MultiListener().apply { this@MultiListener.listeners.each { if (this != listener) addListeners(this) } }

	operator fun minusAssign(listener: KotlinListener) {
		removeListeners(listener)
	}

	operator fun plusAssign(listener: KotlinListener) {
		addListeners(listener)
	}
}

fun KotlinListener.join(vararg listeners: KotlinListener) = MultiListener(this, *listeners)

operator fun KotlinListener.plus(l: KotlinListener) = MultiListener(this, l)

//TODO: antixray - Timings