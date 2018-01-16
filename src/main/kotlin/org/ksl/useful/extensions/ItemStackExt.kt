@file:Suppress("unused", "FunctionName")

package org.ksl.useful.extensions

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.enchantments.EnchantmentTarget.ALL
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.ksl.plugin.KslPlugin

/**
 * Created by LordCarlosMP on 28/06/2017.
 */

fun ItemStack.setDisplayNameAndLore(name: String, vararg lore: String) = setDisplayNameAndLore(name, lore.toList())

fun ItemStack.setDisplayNameAndLore(name: String, lore: List<String>) {
	val im = itemMeta
	im.displayName = name
	im.lore = lore
	itemMeta = im
	Bukkit.getScheduler().runTask(KslPlugin) {

	}
}

var ItemStack.material: Material
	get() = type
	set(nv) {
		type = nv
	}

var ItemStack.name: String
	get() = itemMeta.displayName
	set(newName) {
		val im = itemMeta
		im.displayName = newName
		itemMeta = im
	}

fun ItemStack.setLore(vararg newLore: String) {
	lore = newLore.asList()
}

fun ItemStack.addToLore(vararg newLines: String) {
	val meta = itemMeta
	val lore = meta.lore ?: ArrayList<String>()
	lore.addAll(newLines)
	meta.lore = lore
	itemMeta = meta
}

var ItemStack.lore: List<String>
	get() = itemMeta.lore
	set(newLore) {
		val im = itemMeta
		im.lore = newLore
		itemMeta = im
	}

//Avoid conflicts
private object GhostEnchantment : Enchantment(13109) {

	init {
		Enchantment.registerEnchantment(this)
	}

	override fun getItemTarget() = ALL

	override fun getName() = "GhostEnchantment"

	override fun getMaxLevel() = 0

	override fun getStartLevel() = 0

	override fun conflictsWith(other: Enchantment?) = false

	override fun canEnchantItem(item: ItemStack?) = true

}

fun ItemStack.addEnchantAura() {
	val im = itemMeta
	im.addItemFlags(ItemFlag.HIDE_ENCHANTS)
	itemMeta = im
	addUnsafeEnchantment(GhostEnchantment, 0)
}

fun ItemStack.addItemFlags(vararg flags: ItemFlag) {
	itemMeta.addItemFlags(*flags)
	itemMeta = itemMeta
}

val ItemStack.itemFlags: Set<ItemFlag> get() = itemMeta.itemFlags

fun ItemStack.hasItemFlag(flags: ItemFlag) = itemMeta.hasItemFlag(flags)

fun ItemStack.removeItemFlags(vararg flags: ItemFlag) {
	val meta = itemMeta
	meta.removeItemFlags(*flags)
	itemMeta = meta
}

val ItemStack.isEnchanted get() = itemMeta.hasEnchants()

fun ItemStack.clearEnchantments() {
	val meta = itemMeta
	for ((e) in enchantments) meta.removeEnchant(e)
	itemMeta = meta
}

fun ItemStack.addAllItemFlags() {
	addItemFlags(*ItemFlag.values())
}

/**
 * If you are using named arguments, go with this ones
 */
fun Material.itemStack(amount: Int = 1, data: Int = 0, name: String? = null, lore: List<String>? = null) = ItemStack(this, amount, data, name, lore)

fun ItemStack(material: Material, amount: Int = 1, data: Int = 0, name: String? = null, lore: List<String>? = null)
		= ItemStack(material, amount, data.toShort()).apply {
	name?.let { this@apply.name = it }
	lore?.let { this@apply.lore = it }
}

/**
 * These methods avoid the necessity of using named arguments for "most" typical cases
 * which are not included in Bukkit's ItemStack contructors, remember that when the
 * data is 0, you can use Material.ItemStack(itemStack.()->Unit) method.
 */
fun ItemStack(material: Material, amount: Int, data: Int) = ItemStack(material, amount, data.toShort())

fun ItemStack(material: Material, name: String, vararg lore: String) = material.itemStack(
		name = name,
		lore = lore.toList()
)
