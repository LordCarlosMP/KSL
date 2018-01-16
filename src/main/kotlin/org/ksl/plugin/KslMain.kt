package org.ksl.plugin

import org.bukkit.plugin.java.JavaPlugin
import org.ksl.useful.apis.imgmessage.ImageChar
import org.ksl.useful.apis.imgmessage.getBufferedImageInPluginsJar
import org.ksl.useful.apis.imgmessage.toImageMessage
import org.ksl.useful.extensions.print

@Suppress("unused")
/**
 * Created by LordCarlosMP on 26/06/2017.
 */
class KslMain : JavaPlugin() {

	init {
		unl = this
	}


	//TODO: potion API
	override fun onLoad() {
//		print("""
//§3               __
//§3          |_/ /__` |
//§3          | \ .__/ |__
//
//§5       K§aotlin §5S§apigot §5L§aib §8v§41§8.§40 §bwas succesfully loaded
//
//""")

		getBufferedImageInPluginsJar("icon.png")?.toImageMessage(ImageChar.MEDIUM_SHADE, 20,
				"",
				"§4                    ____",
				"§4          |    /   /      |",
				"§3          |___/   /____   |",
				"""§3          |   \        /  |""",
				"""§6          |    \  ____/   |_____""",
				"",
				"§5       K§aotlin §5S§apigot §5L§aib §8v§41§8.§40 §bwas succesfully loaded")?.printInConsole()
/*		print("""
§3                    ____
§3          |    /   /      |
§3          |___/   /____   |
§3          |   \        /  |
§3          |    \  ____/   |_____

§5       K§aotlin §5S§apigot §5L§aib §8v§41§8.§40 §bwas succesfully loaded

""")*/
	}

	override fun onEnable() {
		print("Enabled")

//		newScoreboard().apply {
//			sidebar.addRows(
//					"1",
//					"2",
//					"3",
//					"4",
//					"5",
//					"6",
//					"7",
//					"8",
//					"9",
//					"10",
//					"11",
//					"12",
//					"13",
//					"14",
//					"15",
//					"16",
//					"17")
//			print("§§3Willyi§§3To§3")
//			print("§§3Willyi§§3To§wasd")
//			addPlayerJoinListener {
//				setScoreboardTo(player)
//			}
//			var i = 1
//			var s = ""
//			fun reDo() {
//				later(20) {
//					i++
//					if (i > 9) i = 0
//	for (u in 0 until 17) sidebar.editRow(u, s + i.toString())
//	s += i.toString()
//	reDo()
//	}
//	}
//			reDo()
//		}
	}
}

private var unl: JavaPlugin? = null
	private set(value) = if (field == null) field = value else throw Exception("2 times instanciated, please, do NOT create 2 intances of your plugin")
	get() = field ?: throw Exception("KslMain is not loaded yet")

internal val KslPlugin by lazy { unl ?: throw Exception("KslMain is not loaded yet") }
