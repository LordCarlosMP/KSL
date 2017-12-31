package org.ksl.useful.extensions

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

fun Material.itemStack(f: ItemStack.()-> Unit = {}) = ItemStack(this).apply(f)
