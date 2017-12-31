package org.ksl.useful.extensions

import org.bukkit.*

fun Location(world: World, x: Int, y: Int, z: Int) = Location(world, x.toDouble(), y.toDouble(), z.toDouble())