@file:Suppress("unused")

package org.ksl.useful.extensions

import org.bukkit.*
import org.bukkit.entity.*
import org.bukkit.event.*
import org.bukkit.event.inventory.*
import org.bukkit.inventory.*
import org.bukkit.util.io.*
import org.ksl.plugin.*
import org.ksl.useful.ktlibs.*
import org.yaml.snakeyaml.external.biz.base64Coder.*
import java.io.*
import java.util.*

/**
 * Created by LordCarlosMP on 30/06/2017.
 */
internal typealias ClickListener = InventoryClickEvent.() -> Unit

class MenuListener(val inventory: Inventory, val f: InventoryClickEvent.() -> Unit) {
	var registered = true
		set(value) {
			if (value == field) return
			if (value) register() else unRegister()
		}

	fun unRegister() {
		InventoryListener.unRegisterListener(inventory, f)
		registered = false
	}

	fun register() {
		InventoryListener.registerListener(inventory, f)
		registered = true
	}
}

object InventoryListener : Listener {

	private var registered = false

	private val map = WeakHashMap<Inventory, ArrayList<InventoryClickEvent.() -> Unit>>()

	@EventHandler
	fun onClick(event: InventoryClickEvent) {
		map[event.inventory]?.forEach { it(event) }
	}

	private fun registerInternalListener() {
		if (registered) return
		registered = true
		KslPlugin.register(this)
	}

	private fun unRegisterInternalListener() {
		if (!registered) return
		if (map.isNotEmpty()) return
		HandlerList.unregisterAll(InventoryListener)
		registered = false
	}

	fun registerListener(inv: Inventory, listener: InventoryClickEvent.() -> Unit): MenuListener {
		registerInternalListener()
		val arr = map[inv]
		if (arr == null) {
			map.put(inv, ArrayList<InventoryClickEvent.() -> Unit>().apply { add(listener) })
		} else arr.add(listener)
		return MenuListener(inv, listener)
	}

	fun unRegisterListener(inv: Inventory, listener: InventoryClickEvent.() -> Unit) {
		map[inv]?.run {
			remove(listener)
			if (size == 0) map.remove(inv)
		}
		unRegisterInternalListener()
	}
}

fun Inventory.addMenuListener(f: ClickListener) = InventoryListener.registerListener(this, f)

fun Inventory.setItem(position: Int, material: Material, amount: Int = 1, data: Short = 0) = setItem(position, ItemStack(material, amount, data))

//fun Inventory.setItem(position: Int, material: Material, amount: Int = 1, data: Short = 0, f: clickListener) = setItem(position, ItemStack(material, amount, data), f)

//fun Inventory.setItem(position: Int, stack: ItemStack, f: clickListener) {
//	setItem(position, stack)
//	addMenuListener { if (slot == position) f(this) }
//}

fun Inventory.setItem(row: Int, column: Int, material: Material, amount: Int = 1, data: Short = 0) = setItem(row * 9 + column, material,amount,data)

//fun Inventory.setItem(row: Int, column: Int, material: Material, amount: Int = 1, data: Short = 0, f: clickListener) = setItem(row * 9 + column, material, amount, data, f)

//fun Inventory.setItem(row: Int, column: Int, stack: ItemStack, f: clickListener) {
//	setItem(row * 9 + column, stack)
//	addMenuListener { if (slot == row * 9 + column) f(this) }
//}

//Locks

val lockingListener: ClickListener = { isCancelled = true }

//fun Inventory.lock() {
//	addMenuListener(lockingListener)
//}

//fun Inventory.unLock() = InventoryListener.unRegisterListener(this, lockingListener)

fun Inventory.close() = viewers.toList().each { closeInventory() }

fun Inventory.open(vararg players: HumanEntity) = players.each { openInventory(this@open) }

fun Inventory.update() = viewers.filterIsInstance<Player>().each { updateInventory() }

fun Inventory.addItem(material: Material): HashMap<Int, ItemStack> = addItem(material.itemStack())
fun Inventory.addItem(vararg materials: Material): HashMap<Int, ItemStack> = addItem(*materials.map { it.itemStack() }.toTypedArray())

fun Collection<ItemStack>.toBase64() = toTypedArray().toBase64()

fun Array<ItemStack>.toBase64(): String {
	try {
		val outputStream = ByteArrayOutputStream()
		val dataOutput = BukkitObjectOutputStream(outputStream)
		dataOutput.writeInt(size)
		for (i in indices) dataOutput.writeObject(this[i])
		dataOutput.close()
		return Base64Coder.encodeLines(outputStream.toByteArray())
	} catch (e: Exception) {
		throw IllegalStateException("Unable to save item stacks.", e)
	}

}

fun String.fromBase64(): Array<ItemStack> {
	try {
		val inputStream = ByteArrayInputStream(Base64Coder.decodeLines(this))
		val dataInput = BukkitObjectInputStream(inputStream)
		val stacks = Array(dataInput.readInt()) { dataInput.readObject() as ItemStack }
		dataInput.close()
		return stacks
	} catch (e: ClassNotFoundException) {
		throw IOException("Unable to decode class type.", e)
	}
}

fun Inventory.removeAt(i: Int) {
	setItem(i, Material.AIR.itemStack())
}