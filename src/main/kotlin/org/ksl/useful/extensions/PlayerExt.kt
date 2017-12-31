@file:Suppress("unused", "DEPRECATION")

package org.ksl.useful.extensions

import org.bukkit.Location
import org.bukkit.Material.AIR
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.block.BlockFace.EAST
import org.bukkit.block.BlockFace.NORTH
import org.bukkit.block.BlockFace.SOUTH
import org.bukkit.block.BlockFace.WEST
import org.bukkit.entity.Player
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.util.Vector
import org.ksl.useful.global.functions.forEachOnlinePlayer
import org.ksl.useful.ktlibs.abs
import org.ksl.useful.ktlibs.each
import java.lang.Math.abs
import java.util.*

/**
 * Created by LordCarlosMP on 28/06/2017.
 */

fun Player.getOfflineUUID() = name.getOfflineUUID()

fun Player.teleportRelative(x: Number, y: Number, z: Number) = teleport(location.clone().add(x.toDouble(), y.toDouble(), z.toDouble()))

fun Player.clearPotionEffects() = activePotionEffects.each { removePotionEffect(this.type) }

fun Player.kill() {
	player.health = 0.0
}

fun Player.getStandingBlocks() = arrayOfNulls<Block>(10)

fun Location.cornerLoc() = Location(world, x.toInt().toDouble(), y.toInt().toDouble(), z.toInt().toDouble())

fun Location.centerOfBlock(): Location = cornerLoc().add(0.5, 0.5, 0.5)

fun Location.topCenterOfBlock(): Location = cornerLoc().add(0.5, 1.0, 0.5)

fun Location.nearestCornerLoc() = Location(world, Math.round(x - 0.5).toDouble(), Math.round(y - 0.5).toDouble(), Math.round(z - 0.5).toDouble())

inline fun Location.forEachNearbyPlayer(maxX: Int, maxY: Int, maxZ: Int, f: (Player) -> Unit) = forEachOnlinePlayer { if (isNearOf(it.location, maxX, maxY, maxZ)) f(it) }

fun Location.isNearOf(otherLocation: Location, maxX: Int, maxY: Int, maxZ: Int): Boolean {
	if ((otherLocation.x - x).abs() > maxX) return false
	if ((otherLocation.z - z).abs() > maxZ) return false
	if ((otherLocation.y - y).abs() > maxY) return false
	return otherLocation.world == world
}

fun Player.increaseHealth(amount: Double) {
	if (health + amount >= 20) {
		health = 20.0
		return
	}
	health += amount

}

fun Player.getPlayerInSight(maxDistance: Int): Player? {
	val playerDirection = Vector3D(location.direction)
	val start = Vector3D(location)
	val end = start.add(playerDirection.multiply(maxDistance))
	location.forEachNearbyPlayer(maxDistance, maxDistance, maxDistance) {
		val nearbyLoc = Vector3D(it.location)
		val min = nearbyLoc.subtract(0.5, 1.6, 0.5)
		val max = nearbyLoc.add(0.5, 0.3, 0.5)
		if (hasIntersection(start, end, min, max)) return it
	}
	return null
}

fun firstOrNullNearbyPlayer(loc: Location, x: Int, y: Int, z: Int): Player? {
	forEachOnlinePlayer { if (loc.isNearOf(it.location, x, y, z)) return it }
	return null
}

fun Player.getStandignBlocks(): ArrayList<Block> {

	val list = ArrayList<Block>(4)
	fun add(block: Block) = list.add(block)
	val xv = location.x - location.blockX
	val zv = location.z - location.blockZ
	val first = location.world.getBlockAt(location.blockX, (location.y - 1).toInt(), location.blockZ)
	add(first)
	fun addFace(face: BlockFace) {
		for (e in list) add(e.getRelative(face))
	}

	fun addSouthOrNorth() {
		if (zv < 0.3) addFace(NORTH)
		else if (zv > 0.7) addFace(SOUTH)
	}

	fun addFrom(second: Block) {
		list.add(second)
		addSouthOrNorth()
	}
	when {
		xv < 0.3 -> addFrom(first.getRelative(WEST))
		xv > 0.7 -> addFrom(first.getRelative(EAST))
		else -> addSouthOrNorth()
	}
	return list
}

fun Player.clearTitle() {
	sendTitle("", "")
}

internal class Vector3D(val x: Double, val y: Double, val z: Double) {
	internal constructor(vector: Vector) : this(vector.x, vector.y, vector.z)

	internal constructor(location: Location) : this(location.x, location.y, location.z)

	fun add(other: Vector3D) = Vector3D(x + other.x, y + other.y, z + other.z)

	fun add(x: Double, y: Double, z: Double) = Vector3D(this.x + x, this.y + y, this.z + z)

	internal fun subtract(other: Vector3D) = Vector3D(x - other.x, y - other.y, z - other.z)

	internal fun subtract(x: Double, y: Double, z: Double) = Vector3D(this.x - x, this.y - y, this.z - z)

	internal fun multiply(factor: Int) = Vector3D(x * factor, y * factor, z * factor)

	internal fun multiply(factor: Double) = Vector3D(x * factor, y * factor, z * factor)

	internal fun abs() = Vector3D(abs(x), abs(y), abs(z))

	override fun toString() = "[x: $x, y: $y, z: $z]"

}

private fun hasIntersection(start: Vector3D, end: Vector3D, min: Vector3D, max: Vector3D): Boolean {
	val d = end.subtract(start).multiply(0.5)
	val e = max.subtract(min).multiply(0.5)
	val c = start.add(d).subtract(min.add(max).multiply(0.5))
	val ad = d.abs()
	//Cleanest Code Ever   !!!!
	return abs(c.x) <= e.x + ad.x && abs(c.y) <= e.y + ad.y && abs(c.z) <= e.x + ad.z && abs(d.y * c.z - d.z * c.y) <= e.y * ad.z + e.z * ad.y + 9.999999747378752E-5 && abs(d.z * c.x - d.x * c.z) <= e.z * ad.x + e.x * ad.x + 9.999999747378752E-5 && abs(d.x * c.y - d.y * c.x) <= e.x * ad.y + e.y * ad.x + 9.999999747378752E-5
}

//fun Player.send(s: String) = AnnounceBar.sendTo(this, s)
//fun Location.nearBlockOf(material: Material): Block? { could do, but too specific to incude in this API
//	val loc = nearestCornerLoc()
//	return if (loc.block.typeId == 0) loc.block else
//		BlockFace.values()
//				.map { loc.block.getRelative(it) }
//				.firstOrNull { it.typeId == 0 }
//}

fun Location.getHitedSolidBlock(): Block? {
	val other = nearestCornerLoc()
	if (other.block.typeId != 0) return other.block
	return BlockFace.values()
			.map { other.block.getRelative(it) }
			.firstOrNull { it.type != AIR }
}

fun Player.clearInventory() = inventory.clear() //TODO. Probar que se actualiza

var Player.boots: ItemStack
	get() = inventory.boots
	set(boots) {
		inventory.boots = boots
	}

var Player.leggings: ItemStack
	get() = inventory.leggings
	set(leggings) {
		inventory.leggings = leggings
	}
var Player.chestplate: ItemStack
	get() = inventory.chestplate
	set(chestplate) {
		inventory.chestplate = chestplate
	}
var Player.helmet: ItemStack
	get() = inventory.helmet
	set(helmet) {
		inventory.helmet = helmet
	}

var Player.inventoryContents: Array<out ItemStack>
	get() = inventory.contents
	set(newContents) {
		inventory.contents = newContents
	}
var Player.armorContents: Array<out ItemStack>
	get() = inventory.armorContents
	set(newContents) {
		inventory.armorContents = newContents
	}

fun Player.getHead() = name.headWithThatName()

//TODO: pasar pl
fun Player.captureNextChatMessage(plugin: JavaPlugin, timeoutInTicks: Int, msg: AsyncPlayerChatEvent.() -> Unit) {
	var listener: DefaultKslListener? = null


	listener = plugin.addAsyncPlayerChatListener {
		if (player == this@captureNextChatMessage) {
			msg()
			listener?.unRegister()
		}
	}
	plugin.later(timeoutInTicks) {
		listener?.unRegister()
	}
}

//TODO: no va, tire ClassNotFoundError
@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
//fun Player.sendTitle(title: String, subTitle: String) {
//	Title(title, subTitle).send(this)
//}
//TODO: no va, tire ClassNotFoundError
//fun Player.sendTitle(title: String) {
//	Title(title,"").send(this)
//}

fun Player.sendTitle(title: String) {
	sendTitle(title, "")
}

fun Player.clearArmorContents() {
	helmet = ItemStack(AIR)
	chestplate = ItemStack(AIR)
	leggings = ItemStack(AIR)
	boots = ItemStack(AIR)
}

fun Player.foodToTopLevel() {
	foodLevel = 20
	saturation = 20.0F
}

fun Player.clean() {
	foodToTopLevel()
	clearInventory()
	clearArmorContents()
	clearPotionEffects()
}