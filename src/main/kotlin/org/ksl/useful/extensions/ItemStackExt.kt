@file:Suppress("unused")

package org.ksl.useful.extensions

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.enchantments.EnchantmentTarget.ALL
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

/**
 * Created by LordCarlosMP on 28/06/2017.
 */

fun ItemStack.setDisplayNameAndLore(name: String, vararg lore: String) {
	val im = itemMeta
	im.displayName = name
	im.lore = lore.asList()
	itemMeta = im
}

var ItemStack.displayName: String
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
	val meta = itemMeta
	meta.addItemFlags(*flags)
	itemMeta = meta
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

fun ItemStack(material: Material, amount: Int = 1, data: Int) = ItemStack(material, amount, data.toShort())

fun ItemStack(material: Material, amount: Int = 1, data: Int = 0, name: String, vararg lore: String)
		= ItemStack(material, amount, data.toShort()).apply { setDisplayNameAndLore(name, *lore) }

fun ItemStack(material: Material, name: String, vararg lore: String) = ItemStack(material, 1, 0, name, *lore)

