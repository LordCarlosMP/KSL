package org.ksl.useful.ktlibs

import java.util.*

/**
 * Created by LordCarlosMP on 04/06/2017.
 */
fun <E> MutableCollection<E>.addAll(vararg elements: E) = addAll(elements)

private val random = Random()
fun randomBetween(min: Int, max: Int) = min + random.nextInt(max - min)

inline fun <T> Iterable<T>.each(action: T.() -> Unit) {
	for (e in this) action(e)
}

inline fun <T> Iterable<T>.eachIndexed(action: T.(Int) -> Unit) {
	var i = 0
	for (e in this) action(e, i++)
}

inline fun <T> Array<out T>.each(action: T.() -> Unit) {
	for (element in this) action(element)
}

inline fun <T> Array<out T>.eachIndexed(action: T.(Int) -> Unit) {
	var i = 0
	for (element in this) action(element, i++)
}

inline fun <T> inEach(vararg all: T, f: T.() -> Unit) {
	for (one in all) one.f()
}

fun <T> Set<T>.unmodifiable(): Set<T> = Collections.unmodifiableSet(this)
fun <K, V> Map<K, V>.unmodifiable(): Map<K, V> = Collections.unmodifiableMap(this)

fun Int.abs() = if (this >= 0) this else -this
fun Double.abs() = if (this >= 0) this else -this
fun Float.abs() = if (this >= 0) this else -this
fun Short.abs() = if (this >= 0) this else -this
fun Byte.abs() = if (this >= 0) this else -this
fun Long.abs() = if (this >= 0) this else -this

inline fun Boolean.ifThen(f: () -> Unit): Boolean {
	if (this) f()
	return this
}
inline fun Boolean.ifNotThen(f: () -> Unit): Boolean {
	if (!this) f()
	return this
}