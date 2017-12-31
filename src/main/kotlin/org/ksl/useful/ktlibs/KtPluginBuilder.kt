package org.ksl.useful.ktlibs

/**
 * Created by LordCarlosMP on 31/05/2017.
 */
internal fun main(args: Array<String>) {
	PlayerEvents.values().each { printAddMethod(name) }
	BlockEvents.values().each { printAddMethod(name) }
	EnchantEvents.values().forEach { printAddMethod(it.name) }
	EntityEvents.values().forEach { printAddMethod(it.name) }
	HangingEvents.values().forEach { printAddMethod(it.name) }
	InventoryEvents.values().forEach { printAddMethod(it.name) }
	ServerEvents.values().forEach { printAddMethod(it.name) }
	VehicleEvents.values().forEach { printAddMethod(it.name) }
	WeatherEvents.values().forEach { printAddMethod(it.name) }
	WorldEvents.values().forEach { printAddMethod(it.name) }
}

private fun printAddMethod(eventName: String) {
	val methodName = "add${eventName.replace("Event", "Listener")}"
	println("fun JavaPlugin.$methodName (event: $eventName.() -> Unit) = KotlinListener(this,object : Listener {\n" +
			"\t\t@EventHandler fun ksl$eventName(bukkitEvent: $eventName) = bukkitEvent.event()\n" +
			"\t})")
}

enum class PlayerEvents {
	AsyncPlayerChatEvent, AsyncPlayerPreLoginEvent, PlayerAchievementAwardedEvent, PlayerAnimationEvent, PlayerBedEnterEvent, PlayerBedLeaveEvent, PlayerBucketEmptyEvent,
	PlayerBucketEvent, PlayerBucketFillEvent, PlayerChangedWorldEvent, PlayerChannelEvent, /*PlayerChatEvent, this is deprecated*/ PlayerChatTabCompleteEvent, PlayerCommandPreprocessEvent,
	PlayerDropItemEvent, PlayerEditBookEvent, PlayerEggThrowEvent, PlayerEvent, PlayerExpChangeEvent, PlayerFishEvent, PlayerGameModeChangeEvent, PlayerInteractEntityEvent,
	PlayerInteractEvent, /*PlayerInventoryEvent, this is deprecated*/ PlayerItemBreakEvent, PlayerItemConsumeEvent, PlayerItemHeldEvent, PlayerJoinEvent, PlayerKickEvent, PlayerLevelChangeEvent,
	PlayerLoginEvent,
	PlayerMoveEvent, PlayerPickupItemEvent, PlayerPortalEvent, /*PlayerPreLoginEvent, this is deprecated*/ PlayerQuitEvent, PlayerRegisterChannelEvent, PlayerRespawnEvent, PlayerShearEntityEvent,
	PlayerStatisticIncrementEvent, PlayerTeleportEvent, PlayerToggleFlightEvent, PlayerToggleSneakEvent, PlayerToggleSprintEvent, PlayerUnleashEntityEvent, PlayerUnregisterChannelEvent, PlayerVelocityEvent
}

enum class BlockEvents {
	BlockBreakEvent, BlockBurnEvent, BlockCanBuildEvent, BlockDamageEvent, BlockDispenseEvent, BlockEvent, BlockExpEvent, BlockFadeEvent, BlockFormEvent, BlockFromToEvent, BlockGrowEvent, BlockIgniteEvent,
	BlockMultiPlaceEvent, BlockPhysicsEvent, BlockPistonEvent, BlockPistonExtendEvent, BlockPistonRetractEvent, BlockPlaceEvent, BlockRedstoneEvent, BlockSpreadEvent, EntityBlockFormEvent,
	LeavesDecayEvent, NotePlayEvent, SignChangeEvent
}

enum class EnchantEvents {
	EnchantItemEvent, PrepareItemEnchantEvent
}

enum class EntityEvents {
	CreatureSpawnEvent, CreeperPowerEvent, EntityBreakDoorEvent, EntityChangeBlockEvent, EntityCombustByBlockEvent, EntityCombustByEntityEvent, EntityCombustEvent, EntityCreatePortalEvent,
	EntityDamageByBlockEvent, EntityDamageByEntityEvent, EntityDamageEvent, EntityDeathEvent, EntityEvent, EntityExplodeEvent, EntityInteractEvent, EntityPortalEnterEvent, EntityPortalEvent,
	EntityPortalExitEvent, EntityRegainHealthEvent, EntityShootBowEvent, EntityTameEvent, EntityTargetEvent, EntityTargetLivingEntityEvent, EntityTeleportEvent, EntityUnleashEvent, ExpBottleEvent,
	ExplosionPrimeEvent, FoodLevelChangeEvent, HorseJumpEvent, ItemDespawnEvent, ItemSpawnEvent, PigZapEvent, PlayerDeathEvent, PlayerLeashEntityEvent, PotionSplashEvent, ProjectileHitEvent,
	ProjectileLaunchEvent, SheepDyeWoolEvent, SheepRegrowWoolEvent, SlimeSplitEvent
}

enum class HangingEvents {
	HangingBreakByEntityEvent, HangingBreakEvent, HangingEvent, HangingPlaceEvent
}

enum class InventoryEvents {
	BrewEvent, CraftItemEvent, FurnaceBurnEvent, FurnaceExtractEvent, FurnaceSmeltEvent, InventoryClickEvent, InventoryCloseEvent, InventoryCreativeEvent, InventoryDragEvent, InventoryEvent,
	InventoryInteractEvent, InventoryMoveItemEvent, InventoryOpenEvent, InventoryPickupItemEvent, PrepareItemCraftEvent
}

enum class ServerEvents {
	MapInitializeEvent, PluginDisableEvent, PluginEnableEvent, PluginEvent, RemoteServerCommandEvent, ServerCommandEvent, ServerEvent, ServerListPingEvent, ServiceEvent, ServiceRegisterEvent,
	ServiceUnregisterEvent
}

enum class VehicleEvents {
	VehicleBlockCollisionEvent, VehicleCollisionEvent, VehicleCreateEvent, VehicleDamageEvent, VehicleDestroyEvent, VehicleEnterEvent, VehicleEntityCollisionEvent, VehicleEvent, VehicleExitEvent,
	VehicleMoveEvent, VehicleUpdateEvent
}

enum class WeatherEvents {
	LightningStrikeEvent, ThunderChangeEvent, WeatherChangeEvent, WeatherEvent
}

enum class WorldEvents {ChunkEvent, ChunkLoadEvent, ChunkPopulateEvent, ChunkUnloadEvent, PortalCreateEvent, SpawnChangeEvent, StructureGrowEvent, WorldEvent, WorldInitEvent, WorldLoadEvent,
	WorldSaveEvent, WorldUnloadEvent
}