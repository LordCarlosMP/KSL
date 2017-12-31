package org.ksl.useful.apis.menu

import org.bukkit.Material
import org.bukkit.Material.WOOL
import org.bukkit.entity.HumanEntity
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import org.ksl.useful.extensions.ClickListener
import org.ksl.useful.extensions.ItemStack
import org.ksl.useful.extensions.addAllItemFlags
import org.ksl.useful.extensions.addMenuListener
import org.ksl.useful.extensions.close
import org.ksl.useful.extensions.itemStack
import org.ksl.useful.extensions.open
import org.ksl.useful.extensions.removeAt
import org.ksl.useful.extensions.setDisplayNameAndLore
import org.ksl.useful.extensions.update
import org.ksl.useful.global.functions.createInventory
import org.ksl.useful.global.vals.InventorySize
import org.ksl.useful.ktlibs.each

/**
 * Created by LordCarlosMP on 01/07/2017.
 */
fun HumanEntity.askInMenu(title: String, yesName: String, noName: String, onAccept: (Boolean) -> Unit) {
	Menu(InventorySize.THREEROWS, title).apply {
		addItem(11, ItemStack(WOOL, 1, 13), yesName)
		{
			isCancelled = true
			close()
			onAccept(true)
		}
		addItem(15, ItemStack(WOOL, 1, 14), noName) {
			isCancelled = true
			close()
			onAccept(false)
		}
		open(this@askInMenu)
	}
}

/**
 * This uses the functions Inventory.addMenuListener(InventoryClickEvent.() -> Unit)
 * for registering the listeners.
 */

class Menu(size: Int, name: String, f: Menu.() -> Unit = {}) {

	constructor(size: InventorySize, name: String, f: Menu.() -> Unit = {}) : this(size.getInventorySize(), name, f)

	var backMenu: Menu?

	init {
		backMenu = null
	}

	internal constructor(menu: Menu, size: InventorySize, name: String, f: Menu.() -> Unit = {}) : this(size, name, f) {
		backMenu = menu
	}

	private val inventory = createInventory(size, name)

	private val map = HashMap<Int, ClickListener>()

	init {
		inventory.addMenuListener {
			isCancelled = true
			map[slot]?.invoke(this)
		}
		f()
	}

	private fun goBack(vararg players: HumanEntity) {
		backMenu?.let {
			players.toList().each {
				closeInventory()
				it.open(this)
			}
		}
	}

	fun allGoBack() = goBack(*inventory.viewers.toTypedArray())

	fun InventoryClickEvent.goBack() {
		goBack(whoClicked)
	}

	fun removeItem(position: Int) {
		inventory.removeAt(position)
		map.remove(position)
		inventory.update()
	}

	fun removeItem(f: ClickListener) {
		map.forEach {
			if (it.value == f) {
				removeItem(it.key)
				return@forEach
			}
		}
	}

	fun addGoBackButtonOnBottomRight(material: Material, name: String, vararg lore: String) {
		addItem(inventory.size - 1, material, name, *lore) {
			goBack()
		}
	}

	fun addGoBackButtonOnBottomRight(name: String, vararg lore: String, material: ItemStack = ItemStack(WOOL, 1, 14)) {
		addItem(inventory.size - 1, material, name, *lore) {
			goBack()
		}
	}

	fun addItem(position: Int, stack: ItemStack, f: ClickListener) {
		inventory.setItem(position, stack)
		map.put(position, f)
		inventory.update()
	}

	fun addItem(position: Int, stack: ItemStack, name: String, vararg lore: String, f: ClickListener) {
		stack.apply {
			setDisplayNameAndLore(name, *lore)
			addAllItemFlags()
		}
		addItem(position, stack, f)
	}

	fun addItem(position: Int, material: Material, name: String, vararg lore: String, f: ClickListener) = addItem(position, material.itemStack(), name, *lore, f = f)

	fun open(vararg players: HumanEntity) = inventory.open(*players)

	fun close() = inventory.close()

	fun subMenu(position: Int, menuName: String, menuSize: InventorySize, stack: ItemStack, f: Menu.() -> Unit) {
		val menu = Menu(this@Menu, menuSize, menuName, f)
		addItem(position, stack) { menu.open(whoClicked) }
	}

	fun subMenu(position: Int, menuName: String, menuSize: InventorySize, stack: ItemStack, name: String, vararg lore: String, f: Menu.() -> Unit) {
		val menu = Menu(this@Menu, menuSize, menuName, f)
		addItem(position, stack, name, *lore) { menu.open(whoClicked) }
	}

	fun subMenu(position: Int, menuName: String, menuSize: InventorySize, material: Material, name: String, vararg lore: String, f: Menu.() -> Unit) {
		val menu = Menu(this@Menu, menuSize, menuName, f)
		addItem(position, material, name, *lore) { menu.open(whoClicked) }
	}

}

