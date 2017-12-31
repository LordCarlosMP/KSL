@file:Suppress("unused")

package org.ksl.useful.extensions

import com.zaxxer.hikari.HikariConfig
import org.bukkit.Bukkit
import org.bukkit.ChatColor.RED
import org.bukkit.ChatColor.YELLOW
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockBurnEvent
import org.bukkit.event.block.BlockCanBuildEvent
import org.bukkit.event.block.BlockDamageEvent
import org.bukkit.event.block.BlockDispenseEvent
import org.bukkit.event.block.BlockEvent
import org.bukkit.event.block.BlockExpEvent
import org.bukkit.event.block.BlockFadeEvent
import org.bukkit.event.block.BlockFormEvent
import org.bukkit.event.block.BlockFromToEvent
import org.bukkit.event.block.BlockGrowEvent
import org.bukkit.event.block.BlockIgniteEvent
import org.bukkit.event.block.BlockMultiPlaceEvent
import org.bukkit.event.block.BlockPhysicsEvent
import org.bukkit.event.block.BlockPistonEvent
import org.bukkit.event.block.BlockPistonExtendEvent
import org.bukkit.event.block.BlockPistonRetractEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.block.BlockRedstoneEvent
import org.bukkit.event.block.BlockSpreadEvent
import org.bukkit.event.block.EntityBlockFormEvent
import org.bukkit.event.block.LeavesDecayEvent
import org.bukkit.event.block.NotePlayEvent
import org.bukkit.event.block.SignChangeEvent
import org.bukkit.event.enchantment.EnchantItemEvent
import org.bukkit.event.enchantment.PrepareItemEnchantEvent
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.event.entity.CreeperPowerEvent
import org.bukkit.event.entity.EntityBreakDoorEvent
import org.bukkit.event.entity.EntityChangeBlockEvent
import org.bukkit.event.entity.EntityCombustByBlockEvent
import org.bukkit.event.entity.EntityCombustByEntityEvent
import org.bukkit.event.entity.EntityCombustEvent
import org.bukkit.event.entity.EntityCreatePortalEvent
import org.bukkit.event.entity.EntityDamageByBlockEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.entity.EntityEvent
import org.bukkit.event.entity.EntityExplodeEvent
import org.bukkit.event.entity.EntityInteractEvent
import org.bukkit.event.entity.EntityPortalEnterEvent
import org.bukkit.event.entity.EntityPortalEvent
import org.bukkit.event.entity.EntityPortalExitEvent
import org.bukkit.event.entity.EntityRegainHealthEvent
import org.bukkit.event.entity.EntityShootBowEvent
import org.bukkit.event.entity.EntityTameEvent
import org.bukkit.event.entity.EntityTargetEvent
import org.bukkit.event.entity.EntityTargetLivingEntityEvent
import org.bukkit.event.entity.EntityTeleportEvent
import org.bukkit.event.entity.EntityUnleashEvent
import org.bukkit.event.entity.ExpBottleEvent
import org.bukkit.event.entity.ExplosionPrimeEvent
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.entity.HorseJumpEvent
import org.bukkit.event.entity.ItemDespawnEvent
import org.bukkit.event.entity.ItemSpawnEvent
import org.bukkit.event.entity.PigZapEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.entity.PlayerLeashEntityEvent
import org.bukkit.event.entity.PotionSplashEvent
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.event.entity.ProjectileLaunchEvent
import org.bukkit.event.entity.SheepDyeWoolEvent
import org.bukkit.event.entity.SheepRegrowWoolEvent
import org.bukkit.event.entity.SlimeSplitEvent
import org.bukkit.event.hanging.HangingBreakByEntityEvent
import org.bukkit.event.hanging.HangingBreakEvent
import org.bukkit.event.hanging.HangingEvent
import org.bukkit.event.hanging.HangingPlaceEvent
import org.bukkit.event.inventory.BrewEvent
import org.bukkit.event.inventory.CraftItemEvent
import org.bukkit.event.inventory.FurnaceBurnEvent
import org.bukkit.event.inventory.FurnaceExtractEvent
import org.bukkit.event.inventory.FurnaceSmeltEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryCreativeEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.event.inventory.InventoryEvent
import org.bukkit.event.inventory.InventoryInteractEvent
import org.bukkit.event.inventory.InventoryMoveItemEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.event.inventory.InventoryPickupItemEvent
import org.bukkit.event.inventory.PrepareItemCraftEvent
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import org.bukkit.event.player.PlayerAchievementAwardedEvent
import org.bukkit.event.player.PlayerAnimationEvent
import org.bukkit.event.player.PlayerBedEnterEvent
import org.bukkit.event.player.PlayerBedLeaveEvent
import org.bukkit.event.player.PlayerBucketEmptyEvent
import org.bukkit.event.player.PlayerBucketEvent
import org.bukkit.event.player.PlayerBucketFillEvent
import org.bukkit.event.player.PlayerChangedWorldEvent
import org.bukkit.event.player.PlayerChannelEvent
import org.bukkit.event.player.PlayerChatTabCompleteEvent
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerEditBookEvent
import org.bukkit.event.player.PlayerEggThrowEvent
import org.bukkit.event.player.PlayerEvent
import org.bukkit.event.player.PlayerExpChangeEvent
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.event.player.PlayerGameModeChangeEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerItemBreakEvent
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.event.player.PlayerItemHeldEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerKickEvent
import org.bukkit.event.player.PlayerLevelChangeEvent
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerPickupItemEvent
import org.bukkit.event.player.PlayerPortalEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.player.PlayerRegisterChannelEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.event.player.PlayerShearEntityEvent
import org.bukkit.event.player.PlayerStatisticIncrementEvent
import org.bukkit.event.player.PlayerTeleportEvent
import org.bukkit.event.player.PlayerToggleFlightEvent
import org.bukkit.event.player.PlayerToggleSneakEvent
import org.bukkit.event.player.PlayerToggleSprintEvent
import org.bukkit.event.player.PlayerUnleashEntityEvent
import org.bukkit.event.player.PlayerUnregisterChannelEvent
import org.bukkit.event.player.PlayerVelocityEvent
import org.bukkit.event.server.MapInitializeEvent
import org.bukkit.event.server.PluginDisableEvent
import org.bukkit.event.server.PluginEnableEvent
import org.bukkit.event.server.PluginEvent
import org.bukkit.event.server.RemoteServerCommandEvent
import org.bukkit.event.server.ServerCommandEvent
import org.bukkit.event.server.ServerEvent
import org.bukkit.event.server.ServerListPingEvent
import org.bukkit.event.server.ServiceEvent
import org.bukkit.event.server.ServiceRegisterEvent
import org.bukkit.event.server.ServiceUnregisterEvent
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent
import org.bukkit.event.vehicle.VehicleCollisionEvent
import org.bukkit.event.vehicle.VehicleCreateEvent
import org.bukkit.event.vehicle.VehicleDamageEvent
import org.bukkit.event.vehicle.VehicleDestroyEvent
import org.bukkit.event.vehicle.VehicleEnterEvent
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent
import org.bukkit.event.vehicle.VehicleEvent
import org.bukkit.event.vehicle.VehicleExitEvent
import org.bukkit.event.vehicle.VehicleMoveEvent
import org.bukkit.event.vehicle.VehicleUpdateEvent
import org.bukkit.event.weather.LightningStrikeEvent
import org.bukkit.event.weather.ThunderChangeEvent
import org.bukkit.event.weather.WeatherChangeEvent
import org.bukkit.event.weather.WeatherEvent
import org.bukkit.event.world.ChunkEvent
import org.bukkit.event.world.ChunkLoadEvent
import org.bukkit.event.world.ChunkPopulateEvent
import org.bukkit.event.world.ChunkUnloadEvent
import org.bukkit.event.world.PortalCreateEvent
import org.bukkit.event.world.SpawnChangeEvent
import org.bukkit.event.world.StructureGrowEvent
import org.bukkit.event.world.WorldEvent
import org.bukkit.event.world.WorldInitEvent
import org.bukkit.event.world.WorldLoadEvent
import org.bukkit.event.world.WorldSaveEvent
import org.bukkit.event.world.WorldUnloadEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask
import org.ksl.useful.apis.databaselibs.KslConnectionPool
import org.ksl.useful.apis.scoreboard.KslScoreboardV3
import java.io.PrintWriter
import java.io.StringWriter

/**
 * Created by LordCarlosMP on 12/05/2017.
 */

@Suppress("LeakingThis")
fun JavaPlugin.sync(f: () -> Unit) {
	if (Bukkit.isPrimaryThread()) {
		f()
		return
	}
	Bukkit.getScheduler().runTask(this, f)
}

fun JavaPlugin.async(f: () -> Unit) {
	if (!Bukkit.isPrimaryThread()) {
		f()
		return
	}
	Bukkit.getScheduler().runTaskAsynchronously(this, f)
}

fun JavaPlugin.repeatWhile(interval: Number, shouldContinue: () -> Boolean, f: () -> Unit) {
	fun loop() {
		later(interval) {
			if (shouldContinue()) {
				f()
				loop()
			}
		}
	}
	loop()
}

fun JavaPlugin.repeat(interval: Number, times: Number, f: (Int) -> Unit) {
	var loops = 0
	fun loop() {
		if (loops++ < times.toInt()) {
			later(interval) {
				f(loops - 1)
				loop()
			}
		}
	}
	loop()
}

fun JavaPlugin.later(ticks: Number, f: () -> Unit): BukkitTask = Bukkit.getScheduler().runTaskLater(this, f, ticks.toLong())

fun JavaPlugin.laterAsync(ticks: Number, f: () -> Unit): BukkitTask = Bukkit.getScheduler().runTaskLaterAsynchronously(this, f, ticks.toLong())

/**
 * Is this helpfull?
 */
fun JavaPlugin.executorOf(commandName: String, f: (sender: CommandSender, cmd: Command, aliasUsed: String, args: Array<out String>) -> Unit) {
	getCommand(commandName).executor = CommandExecutor { sender, cmd, alias, args ->
		f(sender, cmd, alias, args)
		return@CommandExecutor true
	}
}

fun JavaPlugin.newConnectionPoolFromHickaryConfig(hikaryConfig: HikariConfig): KslConnectionPool = KslConnectionPool(this, hikaryConfig)
fun JavaPlugin.newMySQLConnectionPool(host: String, port: Int, database: String, user: String, pass: String) = KslConnectionPool(this, "jdbc:mysql://$host:$port/$database", user, pass)
fun JavaPlugin.newPostgreSQLConnectionPool(host: String, port: Int, database: String, user: String, pass: String) = KslConnectionPool(this, "jdbc:postgresql://$host:$port/$database", user, pass)

fun JavaPlugin.register(l: Listener) = Bukkit.getPluginManager().registerEvents(l, this)

fun JavaPlugin.print(msg: String) = Bukkit.getConsoleSender().sendMessage("[$name]$msg")

fun JavaPlugin.printInRed(msg: String) = print("[$name]$RED$msg")

fun JavaPlugin.printStackTrace(e: Throwable, vararg info: String) {
	printInRed("An error ocurred in " + name)
	for (s in info) printInRed(s)
	val errors = StringWriter()
	e.printStackTrace(PrintWriter(errors))
	Bukkit.getConsoleSender().sendMessage("$RED$errors")
}

fun JavaPlugin.test(msg: String) = print("$YELLOW$msg")

fun JavaPlugin.inCatch(f: () -> Unit) {

	try {
		f()
	} catch (e: Throwable) {
		printStackTrace(e)
	}
}

fun JavaPlugin.newScoreboard() = KslScoreboardV3()

interface KotlinListener {
	fun register()
	fun unRegister()
}

interface SingleKotlinListener : KotlinListener {
	var registered: Boolean
}

class DefaultKslListener(val plugin: JavaPlugin, val listener: Listener) : SingleKotlinListener {

	init {
		register()
	}

	private fun registerInternalListener() = plugin.register(listener)

	private fun unRegisterInternalListener() = HandlerList.unregisterAll(listener)

	override fun register() {
		registered = true
	}

	override fun unRegister() {
		registered = false
	}

	override var registered = false
		set(reg) {
			if (reg == registered) return
			field = reg
			if (reg) registerInternalListener() else unRegisterInternalListener()
		}

}


/**
 * Difficult to explain function, easy to code
 * Imagine you want to call a funtion that need to be called async
 * for retriving some object, ej: a database query, and you want to use that
 * object back in the main thread, this function just does it.
 */

fun <T> JavaPlugin.runSyncWhenFinished(fAsync: () -> T, fSync: (T) -> Unit) {
	async {
		val v = fAsync()
		sync {
			fSync(v)
		}
	}
}

fun JavaPlugin.addAsyncPlayerChatListener(event: AsyncPlayerChatEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslAsyncPlayerChatEvent(bukkitEvent: AsyncPlayerChatEvent) = bukkitEvent.event()
})

fun JavaPlugin.addAsyncPlayerPreLoginListener(event: AsyncPlayerPreLoginEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslAsyncPlayerPreLoginEvent(bukkitEvent: AsyncPlayerPreLoginEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerAchievementAwardedListener(event: PlayerAchievementAwardedEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerAchievementAwardedEvent(bukkitEvent: PlayerAchievementAwardedEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerAnimationListener(event: PlayerAnimationEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerAnimationEvent(bukkitEvent: PlayerAnimationEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerBedEnterListener(event: PlayerBedEnterEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerBedEnterEvent(bukkitEvent: PlayerBedEnterEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerBedLeaveListener(event: PlayerBedLeaveEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerBedLeaveEvent(bukkitEvent: PlayerBedLeaveEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerBucketEmptyListener(event: PlayerBucketEmptyEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerBucketEmptyEvent(bukkitEvent: PlayerBucketEmptyEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerBucketListener(event: PlayerBucketEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerBucketEvent(bukkitEvent: PlayerBucketEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerBucketFillListener(event: PlayerBucketFillEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerBucketFillEvent(bukkitEvent: PlayerBucketFillEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerChangedWorldListener(event: PlayerChangedWorldEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerChangedWorldEvent(bukkitEvent: PlayerChangedWorldEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerChannelListener(event: PlayerChannelEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerChannelEvent(bukkitEvent: PlayerChannelEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerChatTabCompleteListener(event: PlayerChatTabCompleteEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerChatTabCompleteEvent(bukkitEvent: PlayerChatTabCompleteEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerCommandPreprocessListener(event: PlayerCommandPreprocessEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerCommandPreprocessEvent(bukkitEvent: PlayerCommandPreprocessEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerDropItemListener(event: PlayerDropItemEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerDropItemEvent(bukkitEvent: PlayerDropItemEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerEditBookListener(event: PlayerEditBookEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerEditBookEvent(bukkitEvent: PlayerEditBookEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerEggThrowListener(event: PlayerEggThrowEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerEggThrowEvent(bukkitEvent: PlayerEggThrowEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerListener(event: PlayerEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerEvent(bukkitEvent: PlayerEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerExpChangeListener(event: PlayerExpChangeEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerExpChangeEvent(bukkitEvent: PlayerExpChangeEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerFishListener(event: PlayerFishEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerFishEvent(bukkitEvent: PlayerFishEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerGameModeChangeListener(event: PlayerGameModeChangeEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerGameModeChangeEvent(bukkitEvent: PlayerGameModeChangeEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerInteractEntityListener(event: PlayerInteractEntityEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerInteractEntityEvent(bukkitEvent: PlayerInteractEntityEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerInteractListener(event: PlayerInteractEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerInteractEvent(bukkitEvent: PlayerInteractEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerItemBreakListener(event: PlayerItemBreakEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerItemBreakEvent(bukkitEvent: PlayerItemBreakEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerItemConsumeListener(event: PlayerItemConsumeEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerItemConsumeEvent(bukkitEvent: PlayerItemConsumeEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerItemHeldListener(event: PlayerItemHeldEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerItemHeldEvent(bukkitEvent: PlayerItemHeldEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerJoinListener(event: PlayerJoinEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerJoinEvent(bukkitEvent: PlayerJoinEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerKickListener(event: PlayerKickEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerKickEvent(bukkitEvent: PlayerKickEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerLevelChangeListener(event: PlayerLevelChangeEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerLevelChangeEvent(bukkitEvent: PlayerLevelChangeEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerLoginListener(event: PlayerLoginEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerLoginEvent(bukkitEvent: PlayerLoginEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerMoveListener(event: PlayerMoveEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerMoveEvent(bukkitEvent: PlayerMoveEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerPickupItemListener(event: PlayerPickupItemEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerPickupItemEvent(bukkitEvent: PlayerPickupItemEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerPortalListener(event: PlayerPortalEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerPortalEvent(bukkitEvent: PlayerPortalEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerQuitListener(event: PlayerQuitEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerQuitEvent(bukkitEvent: PlayerQuitEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerRegisterChannelListener(event: PlayerRegisterChannelEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerRegisterChannelEvent(bukkitEvent: PlayerRegisterChannelEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerRespawnListener(event: PlayerRespawnEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerRespawnEvent(bukkitEvent: PlayerRespawnEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerShearEntityListener(event: PlayerShearEntityEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerShearEntityEvent(bukkitEvent: PlayerShearEntityEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerStatisticIncrementListener(event: PlayerStatisticIncrementEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerStatisticIncrementEvent(bukkitEvent: PlayerStatisticIncrementEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerTeleportListener(event: PlayerTeleportEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerTeleportEvent(bukkitEvent: PlayerTeleportEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerToggleFlightListener(event: PlayerToggleFlightEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerToggleFlightEvent(bukkitEvent: PlayerToggleFlightEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerToggleSneakListener(event: PlayerToggleSneakEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerToggleSneakEvent(bukkitEvent: PlayerToggleSneakEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerToggleSprintListener(event: PlayerToggleSprintEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerToggleSprintEvent(bukkitEvent: PlayerToggleSprintEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerUnleashEntityListener(event: PlayerUnleashEntityEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerUnleashEntityEvent(bukkitEvent: PlayerUnleashEntityEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerUnregisterChannelListener(event: PlayerUnregisterChannelEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerUnregisterChannelEvent(bukkitEvent: PlayerUnregisterChannelEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerVelocityListener(event: PlayerVelocityEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerVelocityEvent(bukkitEvent: PlayerVelocityEvent) = bukkitEvent.event()
})

fun JavaPlugin.addBlockBreakListener(event: BlockBreakEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslBlockBreakEvent(bukkitEvent: BlockBreakEvent) = bukkitEvent.event()
})

fun JavaPlugin.addBlockBurnListener(event: BlockBurnEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslBlockBurnEvent(bukkitEvent: BlockBurnEvent) = bukkitEvent.event()
})

fun JavaPlugin.addBlockCanBuildListener(event: BlockCanBuildEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslBlockCanBuildEvent(bukkitEvent: BlockCanBuildEvent) = bukkitEvent.event()
})

fun JavaPlugin.addBlockDamageListener(event: BlockDamageEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslBlockDamageEvent(bukkitEvent: BlockDamageEvent) = bukkitEvent.event()
})

fun JavaPlugin.addBlockDispenseListener(event: BlockDispenseEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslBlockDispenseEvent(bukkitEvent: BlockDispenseEvent) = bukkitEvent.event()
})

fun JavaPlugin.addBlockListener(event: BlockEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslBlockEvent(bukkitEvent: BlockEvent) = bukkitEvent.event()
})

fun JavaPlugin.addBlockExpListener(event: BlockExpEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslBlockExpEvent(bukkitEvent: BlockExpEvent) = bukkitEvent.event()
})

fun JavaPlugin.addBlockFadeListener(event: BlockFadeEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslBlockFadeEvent(bukkitEvent: BlockFadeEvent) = bukkitEvent.event()
})

fun JavaPlugin.addBlockFormListener(event: BlockFormEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslBlockFormEvent(bukkitEvent: BlockFormEvent) = bukkitEvent.event()
})

fun JavaPlugin.addBlockFromToListener(event: BlockFromToEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslBlockFromToEvent(bukkitEvent: BlockFromToEvent) = bukkitEvent.event()
})

fun JavaPlugin.addBlockGrowListener(event: BlockGrowEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslBlockGrowEvent(bukkitEvent: BlockGrowEvent) = bukkitEvent.event()
})

fun JavaPlugin.addBlockIgniteListener(event: BlockIgniteEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslBlockIgniteEvent(bukkitEvent: BlockIgniteEvent) = bukkitEvent.event()
})

fun JavaPlugin.addBlockMultiPlaceListener(event: BlockMultiPlaceEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslBlockMultiPlaceEvent(bukkitEvent: BlockMultiPlaceEvent) = bukkitEvent.event()
})

fun JavaPlugin.addBlockPhysicsListener(event: BlockPhysicsEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslBlockPhysicsEvent(bukkitEvent: BlockPhysicsEvent) = bukkitEvent.event()
})

fun JavaPlugin.addBlockPistonListener(event: BlockPistonEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslBlockPistonEvent(bukkitEvent: BlockPistonEvent) = bukkitEvent.event()
})

fun JavaPlugin.addBlockPistonExtendListener(event: BlockPistonExtendEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslBlockPistonExtendEvent(bukkitEvent: BlockPistonExtendEvent) = bukkitEvent.event()
})

fun JavaPlugin.addBlockPistonRetractListener(event: BlockPistonRetractEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslBlockPistonRetractEvent(bukkitEvent: BlockPistonRetractEvent) = bukkitEvent.event()
})

fun JavaPlugin.addBlockPlaceListener(event: BlockPlaceEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslBlockPlaceEvent(bukkitEvent: BlockPlaceEvent) = bukkitEvent.event()
})

fun JavaPlugin.addBlockRedstoneListener(event: BlockRedstoneEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslBlockRedstoneEvent(bukkitEvent: BlockRedstoneEvent) = bukkitEvent.event()
})

fun JavaPlugin.addBlockSpreadListener(event: BlockSpreadEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslBlockSpreadEvent(bukkitEvent: BlockSpreadEvent) = bukkitEvent.event()
})

fun JavaPlugin.addEntityBlockFormListener(event: EntityBlockFormEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslEntityBlockFormEvent(bukkitEvent: EntityBlockFormEvent) = bukkitEvent.event()
})

fun JavaPlugin.addLeavesDecayListener(event: LeavesDecayEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslLeavesDecayEvent(bukkitEvent: LeavesDecayEvent) = bukkitEvent.event()
})

fun JavaPlugin.addNotePlayListener(event: NotePlayEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslNotePlayEvent(bukkitEvent: NotePlayEvent) = bukkitEvent.event()
})

fun JavaPlugin.addSignChangeListener(event: SignChangeEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslSignChangeEvent(bukkitEvent: SignChangeEvent) = bukkitEvent.event()
})

fun JavaPlugin.addEnchantItemListener(event: EnchantItemEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslEnchantItemEvent(bukkitEvent: EnchantItemEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPrepareItemEnchantListener(event: PrepareItemEnchantEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPrepareItemEnchantEvent(bukkitEvent: PrepareItemEnchantEvent) = bukkitEvent.event()
})

fun JavaPlugin.addCreatureSpawnListener(event: CreatureSpawnEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslCreatureSpawnEvent(bukkitEvent: CreatureSpawnEvent) = bukkitEvent.event()
})

fun JavaPlugin.addCreeperPowerListener(event: CreeperPowerEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslCreeperPowerEvent(bukkitEvent: CreeperPowerEvent) = bukkitEvent.event()
})

fun JavaPlugin.addEntityBreakDoorListener(event: EntityBreakDoorEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslEntityBreakDoorEvent(bukkitEvent: EntityBreakDoorEvent) = bukkitEvent.event()
})

fun JavaPlugin.addEntityChangeBlockListener(event: EntityChangeBlockEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslEntityChangeBlockEvent(bukkitEvent: EntityChangeBlockEvent) = bukkitEvent.event()
})

fun JavaPlugin.addEntityCombustByBlockListener(event: EntityCombustByBlockEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslEntityCombustByBlockEvent(bukkitEvent: EntityCombustByBlockEvent) = bukkitEvent.event()
})

fun JavaPlugin.addEntityCombustByEntityListener(event: EntityCombustByEntityEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslEntityCombustByEntityEvent(bukkitEvent: EntityCombustByEntityEvent) = bukkitEvent.event()
})

fun JavaPlugin.addEntityCombustListener(event: EntityCombustEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslEntityCombustEvent(bukkitEvent: EntityCombustEvent) = bukkitEvent.event()
})

fun JavaPlugin.addEntityCreatePortalListener(event: EntityCreatePortalEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslEntityCreatePortalEvent(bukkitEvent: EntityCreatePortalEvent) = bukkitEvent.event()
})

fun JavaPlugin.addEntityDamageByBlockListener(event: EntityDamageByBlockEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslEntityDamageByBlockEvent(bukkitEvent: EntityDamageByBlockEvent) = bukkitEvent.event()
})

fun JavaPlugin.addEntityDamageByEntityListener(event: EntityDamageByEntityEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslEntityDamageByEntityEvent(bukkitEvent: EntityDamageByEntityEvent) = bukkitEvent.event()
})

fun JavaPlugin.addEntityDamageListener(event: EntityDamageEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslEntityDamageEvent(bukkitEvent: EntityDamageEvent) = bukkitEvent.event()
})

fun JavaPlugin.addEntityDeathListener(event: EntityDeathEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslEntityDeathEvent(bukkitEvent: EntityDeathEvent) = bukkitEvent.event()
})

fun JavaPlugin.addEntityListener(event: EntityEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslEntityEvent(bukkitEvent: EntityEvent) = bukkitEvent.event()
})

fun JavaPlugin.addEntityExplodeListener(event: EntityExplodeEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslEntityExplodeEvent(bukkitEvent: EntityExplodeEvent) = bukkitEvent.event()
})

fun JavaPlugin.addEntityInteractListener(event: EntityInteractEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslEntityInteractEvent(bukkitEvent: EntityInteractEvent) = bukkitEvent.event()
})

fun JavaPlugin.addEntityPortalEnterListener(event: EntityPortalEnterEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslEntityPortalEnterEvent(bukkitEvent: EntityPortalEnterEvent) = bukkitEvent.event()
})

fun JavaPlugin.addEntityPortalListener(event: EntityPortalEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslEntityPortalEvent(bukkitEvent: EntityPortalEvent) = bukkitEvent.event()
})

fun JavaPlugin.addEntityPortalExitListener(event: EntityPortalExitEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslEntityPortalExitEvent(bukkitEvent: EntityPortalExitEvent) = bukkitEvent.event()
})

fun JavaPlugin.addEntityRegainHealthListener(event: EntityRegainHealthEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslEntityRegainHealthEvent(bukkitEvent: EntityRegainHealthEvent) = bukkitEvent.event()
})

fun JavaPlugin.addEntityShootBowListener(event: EntityShootBowEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslEntityShootBowEvent(bukkitEvent: EntityShootBowEvent) = bukkitEvent.event()
})

fun JavaPlugin.addEntityTameListener(event: EntityTameEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslEntityTameEvent(bukkitEvent: EntityTameEvent) = bukkitEvent.event()
})

fun JavaPlugin.addEntityTargetListener(event: EntityTargetEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslEntityTargetEvent(bukkitEvent: EntityTargetEvent) = bukkitEvent.event()
})

fun JavaPlugin.addEntityTargetLivingEntityListener(event: EntityTargetLivingEntityEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslEntityTargetLivingEntityEvent(bukkitEvent: EntityTargetLivingEntityEvent) = bukkitEvent.event()
})

fun JavaPlugin.addEntityTeleportListener(event: EntityTeleportEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslEntityTeleportEvent(bukkitEvent: EntityTeleportEvent) = bukkitEvent.event()
})

fun JavaPlugin.addEntityUnleashListener(event: EntityUnleashEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslEntityUnleashEvent(bukkitEvent: EntityUnleashEvent) = bukkitEvent.event()
})

fun JavaPlugin.addExpBottleListener(event: ExpBottleEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslExpBottleEvent(bukkitEvent: ExpBottleEvent) = bukkitEvent.event()
})

fun JavaPlugin.addExplosionPrimeListener(event: ExplosionPrimeEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslExplosionPrimeEvent(bukkitEvent: ExplosionPrimeEvent) = bukkitEvent.event()
})

fun JavaPlugin.addFoodLevelChangeListener(event: FoodLevelChangeEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslFoodLevelChangeEvent(bukkitEvent: FoodLevelChangeEvent) = bukkitEvent.event()
})

fun JavaPlugin.addHorseJumpListener(event: HorseJumpEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslHorseJumpEvent(bukkitEvent: HorseJumpEvent) = bukkitEvent.event()
})

fun JavaPlugin.addItemDespawnListener(event: ItemDespawnEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslItemDespawnEvent(bukkitEvent: ItemDespawnEvent) = bukkitEvent.event()
})

fun JavaPlugin.addItemSpawnListener(event: ItemSpawnEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslItemSpawnEvent(bukkitEvent: ItemSpawnEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPigZapListener(event: PigZapEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPigZapEvent(bukkitEvent: PigZapEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerDeathListener(event: PlayerDeathEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerDeathEvent(bukkitEvent: PlayerDeathEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPlayerLeashEntityListener(event: PlayerLeashEntityEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPlayerLeashEntityEvent(bukkitEvent: PlayerLeashEntityEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPotionSplashListener(event: PotionSplashEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPotionSplashEvent(bukkitEvent: PotionSplashEvent) = bukkitEvent.event()
})

fun JavaPlugin.addProjectileHitListener(event: ProjectileHitEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslProjectileHitEvent(bukkitEvent: ProjectileHitEvent) = bukkitEvent.event()
})

fun JavaPlugin.addProjectileLaunchListener(event: ProjectileLaunchEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslProjectileLaunchEvent(bukkitEvent: ProjectileLaunchEvent) = bukkitEvent.event()
})

fun JavaPlugin.addSheepDyeWoolListener(event: SheepDyeWoolEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslSheepDyeWoolEvent(bukkitEvent: SheepDyeWoolEvent) = bukkitEvent.event()
})

fun JavaPlugin.addSheepRegrowWoolListener(event: SheepRegrowWoolEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslSheepRegrowWoolEvent(bukkitEvent: SheepRegrowWoolEvent) = bukkitEvent.event()
})

fun JavaPlugin.addSlimeSplitListener(event: SlimeSplitEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslSlimeSplitEvent(bukkitEvent: SlimeSplitEvent) = bukkitEvent.event()
})

fun JavaPlugin.addHangingBreakByEntityListener(event: HangingBreakByEntityEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslHangingBreakByEntityEvent(bukkitEvent: HangingBreakByEntityEvent) = bukkitEvent.event()
})

fun JavaPlugin.addHangingBreakListener(event: HangingBreakEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslHangingBreakEvent(bukkitEvent: HangingBreakEvent) = bukkitEvent.event()
})

fun JavaPlugin.addHangingListener(event: HangingEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslHangingEvent(bukkitEvent: HangingEvent) = bukkitEvent.event()
})

fun JavaPlugin.addHangingPlaceListener(event: HangingPlaceEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslHangingPlaceEvent(bukkitEvent: HangingPlaceEvent) = bukkitEvent.event()
})

fun JavaPlugin.addBrewListener(event: BrewEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslBrewEvent(bukkitEvent: BrewEvent) = bukkitEvent.event()
})

fun JavaPlugin.addCraftItemListener(event: CraftItemEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslCraftItemEvent(bukkitEvent: CraftItemEvent) = bukkitEvent.event()
})

fun JavaPlugin.addFurnaceBurnListener(event: FurnaceBurnEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslFurnaceBurnEvent(bukkitEvent: FurnaceBurnEvent) = bukkitEvent.event()
})

fun JavaPlugin.addFurnaceExtractListener(event: FurnaceExtractEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslFurnaceExtractEvent(bukkitEvent: FurnaceExtractEvent) = bukkitEvent.event()
})

fun JavaPlugin.addFurnaceSmeltListener(event: FurnaceSmeltEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslFurnaceSmeltEvent(bukkitEvent: FurnaceSmeltEvent) = bukkitEvent.event()
})

fun JavaPlugin.addInventoryClickListener(event: InventoryClickEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslInventoryClickEvent(bukkitEvent: InventoryClickEvent) = bukkitEvent.event()
})

fun JavaPlugin.addInventoryCloseListener(event: InventoryCloseEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslInventoryCloseEvent(bukkitEvent: InventoryCloseEvent) = bukkitEvent.event()
})

fun JavaPlugin.addInventoryCreativeListener(event: InventoryCreativeEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslInventoryCreativeEvent(bukkitEvent: InventoryCreativeEvent) = bukkitEvent.event()
})

fun JavaPlugin.addInventoryDragListener(event: InventoryDragEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslInventoryDragEvent(bukkitEvent: InventoryDragEvent) = bukkitEvent.event()
})

fun JavaPlugin.addInventoryListener(event: InventoryEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslInventoryEvent(bukkitEvent: InventoryEvent) = bukkitEvent.event()
})

fun JavaPlugin.addInventoryInteractListener(event: InventoryInteractEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslInventoryInteractEvent(bukkitEvent: InventoryInteractEvent) = bukkitEvent.event()
})

fun JavaPlugin.addInventoryMoveItemListener(event: InventoryMoveItemEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslInventoryMoveItemEvent(bukkitEvent: InventoryMoveItemEvent) = bukkitEvent.event()
})

fun JavaPlugin.addInventoryOpenListener(event: InventoryOpenEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslInventoryOpenEvent(bukkitEvent: InventoryOpenEvent) = bukkitEvent.event()
})

fun JavaPlugin.addInventoryPickupItemListener(event: InventoryPickupItemEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslInventoryPickupItemEvent(bukkitEvent: InventoryPickupItemEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPrepareItemCraftListener(event: PrepareItemCraftEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPrepareItemCraftEvent(bukkitEvent: PrepareItemCraftEvent) = bukkitEvent.event()
})

fun JavaPlugin.addMapInitializeListener(event: MapInitializeEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslMapInitializeEvent(bukkitEvent: MapInitializeEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPluginDisableListener(event: PluginDisableEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPluginDisableEvent(bukkitEvent: PluginDisableEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPluginEnableListener(event: PluginEnableEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPluginEnableEvent(bukkitEvent: PluginEnableEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPluginListener(event: PluginEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPluginEvent(bukkitEvent: PluginEvent) = bukkitEvent.event()
})

fun JavaPlugin.addRemoteServerCommandListener(event: RemoteServerCommandEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslRemoteServerCommandEvent(bukkitEvent: RemoteServerCommandEvent) = bukkitEvent.event()
})

fun JavaPlugin.addServerCommandListener(event: ServerCommandEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslServerCommandEvent(bukkitEvent: ServerCommandEvent) = bukkitEvent.event()
})

fun JavaPlugin.addServerListener(event: ServerEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslServerEvent(bukkitEvent: ServerEvent) = bukkitEvent.event()
})

fun JavaPlugin.addServerListPingListener(event: ServerListPingEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslServerListPingEvent(bukkitEvent: ServerListPingEvent) = bukkitEvent.event()
})

fun JavaPlugin.addServiceListener(event: ServiceEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslServiceEvent(bukkitEvent: ServiceEvent) = bukkitEvent.event()
})

fun JavaPlugin.addServiceRegisterListener(event: ServiceRegisterEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslServiceRegisterEvent(bukkitEvent: ServiceRegisterEvent) = bukkitEvent.event()
})

fun JavaPlugin.addServiceUnregisterListener(event: ServiceUnregisterEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslServiceUnregisterEvent(bukkitEvent: ServiceUnregisterEvent) = bukkitEvent.event()
})

fun JavaPlugin.addVehicleBlockCollisionListener(event: VehicleBlockCollisionEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslVehicleBlockCollisionEvent(bukkitEvent: VehicleBlockCollisionEvent) = bukkitEvent.event()
})

fun JavaPlugin.addVehicleCollisionListener(event: VehicleCollisionEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslVehicleCollisionEvent(bukkitEvent: VehicleCollisionEvent) = bukkitEvent.event()
})

fun JavaPlugin.addVehicleCreateListener(event: VehicleCreateEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslVehicleCreateEvent(bukkitEvent: VehicleCreateEvent) = bukkitEvent.event()
})

fun JavaPlugin.addVehicleDamageListener(event: VehicleDamageEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslVehicleDamageEvent(bukkitEvent: VehicleDamageEvent) = bukkitEvent.event()
})

fun JavaPlugin.addVehicleDestroyListener(event: VehicleDestroyEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslVehicleDestroyEvent(bukkitEvent: VehicleDestroyEvent) = bukkitEvent.event()
})

fun JavaPlugin.addVehicleEnterListener(event: VehicleEnterEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslVehicleEnterEvent(bukkitEvent: VehicleEnterEvent) = bukkitEvent.event()
})

fun JavaPlugin.addVehicleEntityCollisionListener(event: VehicleEntityCollisionEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslVehicleEntityCollisionEvent(bukkitEvent: VehicleEntityCollisionEvent) = bukkitEvent.event()
})

fun JavaPlugin.addVehicleListener(event: VehicleEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslVehicleEvent(bukkitEvent: VehicleEvent) = bukkitEvent.event()
})

fun JavaPlugin.addVehicleExitListener(event: VehicleExitEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslVehicleExitEvent(bukkitEvent: VehicleExitEvent) = bukkitEvent.event()
})

fun JavaPlugin.addVehicleMoveListener(event: VehicleMoveEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslVehicleMoveEvent(bukkitEvent: VehicleMoveEvent) = bukkitEvent.event()
})

fun JavaPlugin.addVehicleUpdateListener(event: VehicleUpdateEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslVehicleUpdateEvent(bukkitEvent: VehicleUpdateEvent) = bukkitEvent.event()
})

fun JavaPlugin.addLightningStrikeListener(event: LightningStrikeEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslLightningStrikeEvent(bukkitEvent: LightningStrikeEvent) = bukkitEvent.event()
})

fun JavaPlugin.addThunderChangeListener(event: ThunderChangeEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslThunderChangeEvent(bukkitEvent: ThunderChangeEvent) = bukkitEvent.event()
})

fun JavaPlugin.addWeatherChangeListener(event: WeatherChangeEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslWeatherChangeEvent(bukkitEvent: WeatherChangeEvent) = bukkitEvent.event()
})

fun JavaPlugin.addWeatherListener(event: WeatherEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslWeatherEvent(bukkitEvent: WeatherEvent) = bukkitEvent.event()
})

fun JavaPlugin.addChunkListener(event: ChunkEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslChunkEvent(bukkitEvent: ChunkEvent) = bukkitEvent.event()
})

fun JavaPlugin.addChunkLoadListener(event: ChunkLoadEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslChunkLoadEvent(bukkitEvent: ChunkLoadEvent) = bukkitEvent.event()
})

fun JavaPlugin.addChunkPopulateListener(event: ChunkPopulateEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslChunkPopulateEvent(bukkitEvent: ChunkPopulateEvent) = bukkitEvent.event()
})

fun JavaPlugin.addChunkUnloadListener(event: ChunkUnloadEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslChunkUnloadEvent(bukkitEvent: ChunkUnloadEvent) = bukkitEvent.event()
})

fun JavaPlugin.addPortalCreateListener(event: PortalCreateEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslPortalCreateEvent(bukkitEvent: PortalCreateEvent) = bukkitEvent.event()
})

fun JavaPlugin.addSpawnChangeListener(event: SpawnChangeEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslSpawnChangeEvent(bukkitEvent: SpawnChangeEvent) = bukkitEvent.event()
})

fun JavaPlugin.addStructureGrowListener(event: StructureGrowEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslStructureGrowEvent(bukkitEvent: StructureGrowEvent) = bukkitEvent.event()
})

fun JavaPlugin.addWorldListener(event: WorldEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslWorldEvent(bukkitEvent: WorldEvent) = bukkitEvent.event()
})

fun JavaPlugin.addWorldInitListener(event: WorldInitEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslWorldInitEvent(bukkitEvent: WorldInitEvent) = bukkitEvent.event()
})

fun JavaPlugin.addWorldLoadListener(event: WorldLoadEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslWorldLoadEvent(bukkitEvent: WorldLoadEvent) = bukkitEvent.event()
})

fun JavaPlugin.addWorldSaveListener(event: WorldSaveEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslWorldSaveEvent(bukkitEvent: WorldSaveEvent) = bukkitEvent.event()
})

fun JavaPlugin.addWorldUnloadListener(event: WorldUnloadEvent.() -> Unit) = DefaultKslListener(this, object : Listener {
	@EventHandler
	fun kslWorldUnloadEvent(bukkitEvent: WorldUnloadEvent) = bukkitEvent.event()
})