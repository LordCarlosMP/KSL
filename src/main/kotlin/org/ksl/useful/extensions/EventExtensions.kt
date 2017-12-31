package org.ksl.useful.extensions

import org.bukkit.entity.Player
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.event.inventory.InventoryClickEvent

/**
 * Created by LordCarlosMP on 02/07/2017.
 */
val ProjectileHitEvent.hitedBlock get() = entity.location.getHitedSolidBlock()

val InventoryClickEvent.castedPlayer get() = whoClicked as Player