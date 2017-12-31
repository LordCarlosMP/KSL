package org.ksl.useful.apis.imgmessage

import org.bukkit.*
import org.bukkit.command.*
import org.bukkit.plugin.java.*
import org.bukkit.util.*
import org.ksl.useful.extensions.*
import org.ksl.useful.global.vals.*
import org.ksl.useful.ktlibs.*
import java.awt.Color
import java.awt.geom.*
import java.awt.image.*
import java.io.*
import java.util.*
import javax.imageio.*

/**
 * This is a modified version of bobacadodl's lib.
 *
 * For the original version, check https://github.com/bobacadodl/ImageMessage
 */

private val colors = arrayOf(Color(0, 0, 0), Color(0, 0, 170), Color(0, 170, 0), Color(0, 170, 170), Color(170, 0, 0), Color(170, 0, 170), Color(255, 170, 0), Color(170, 170, 170), Color(85, 85, 85), Color(85, 85, 255), Color(85, 255, 85), Color(85, 255, 255), Color(255, 85, 85), Color(255, 85, 255), Color(255, 255, 85), Color(255, 255, 255))
private val TRANSPARENT_CHAR = ' '

class ImageMessage {
	var lines: Array<String>
		private set

	constructor(image: BufferedImage, height: Int, imgChar: Char) {
		lines = toImgMessage(toChatColorArray(image, height), imgChar)
	}

	constructor(chatColors: Array<Array<ChatColor?>>, imgChar: Char) {
		lines = toImgMessage(chatColors, imgChar)
	}

	constructor(vararg imgLines: String) {
		lines = imgLines.asList().toTypedArray()
	}

	fun appendText(vararg text: String) {
		lines.indices.filter { text.size > it }.forEach { lines[it] += " ${text[it]}" }
	}

	fun appendTextToLine(line: Int, text: String) {
		val l = line - 1
		if (l > -1 && l < lines.size) {
			lines[l] += " " + text
		}
	}

	fun appendTextToLines(startingline: Int, vararg text: String) {
		val sl = startingline - 1
		if (sl > -1 && sl < lines.size) {
			for (x in 0 until minOf(text.size, lines.size)) appendTextToLine(sl + x + 1, text[x])
		}
	}

	fun appendCenteredText(vararg text: String) {
		for (y in lines.indices) {
			if (text.size > y) {
				val len = ChatPaginator.AVERAGE_CHAT_PAGE_WIDTH - lines[y].length
				lines[y] = lines[y] + center(text[y], len)
			}
		}
	}

	fun sendTo(sender: CommandSender) = lines.each { sendAsMsgTo(sender) }

	fun printInConsole() {
		//TODO: cambiado por el Replacer
		val spaced = arrayOfNulls<String>(lines.size + 1)
		System.arraycopy(lines, 0, spaced, 1, lines.size)
		spaced.each { this?.let { org.ksl.useful.global.functions.printInColor(it) } }
	}

	fun broadast() = onlinePlayers.each { lines.each { sendMessage(this) } }

	private fun toChatColorArray(image: BufferedImage, height: Int): Array<Array<ChatColor?>> {
		val ratio = image.height.toDouble() / image.width
		val resized = resizeImage(image, (height / ratio).toInt(), height)
		return Array(resized.width) { x -> Array(resized.height) { y -> getClosestChatColor(Color(resized.getRGB(x, y), true)) } }
	}

	private fun toImgMessage(colors: Array<Array<ChatColor?>>, imgchar: Char) =
			Array(colors[0].size) { y ->
				val line = StringBuilder()
				for (x in colors.indices) line.append(if (colors[x][y] != null) "${colors[x][y]}$imgchar" else TRANSPARENT_CHAR)
				line.toString() + ChatColor.RESET
			}

	private fun resizeImage(originalImage: BufferedImage, width: Int, height: Int): BufferedImage {
		val af = AffineTransform()
		af.scale(width / originalImage.width.toDouble(), height / originalImage.height.toDouble())
		val operation = AffineTransformOp(af, AffineTransformOp.TYPE_NEAREST_NEIGHBOR)
		return operation.filter(originalImage, null)
	}

	private fun getDistance(c1: Color, c2: Color): Double {
		val rmean = (c1.red + c2.red) / 2.0
		val r = (c1.red - c2.red).toDouble()
		val g = (c1.green - c2.green).toDouble()
		val b = c1.blue - c2.blue
		val weightR = 2 + rmean / 256.0
		val weightG = 4.0
		val weightB = 2 + (255 - rmean) / 256.0
		return weightR * r * r + weightG * g * g + weightB * b.toDouble() * b.toDouble()
	}

	private fun areIdentical(c1: Color, c2: Color) = Math.abs(c1.red - c2.red) <= 5 && Math.abs(c1.green - c2.green) <= 5 && Math.abs(c1.blue - c2.blue) <= 5

	private fun getClosestChatColor(color: Color): ChatColor? {
		if (color.alpha < 128) return null
		var index = 0
		var best = -1.0
		colors.indices.filter { areIdentical(colors[it], color) }.forEach { return ChatColor.values()[it] }
		for (i in colors.indices) {
			val distance = getDistance(color, colors[i])
			if (distance < best || best == -1.0) {
				best = distance
				index = i
			}
		}
		// Minecraft has 15 colors
		return ChatColor.values()[index]
	}

	private fun center(s: String, length: Int) = when {
		s.length > length -> s.substring(0, length)
		s.length == length -> s
		else -> {
			val leftPadding = (length - s.length) / 2
			val leftBuilder = StringBuilder()
			for (i in 0 until leftPadding) leftBuilder.append(' ')
			"$leftBuilder$s"
		}
	}
}

fun JavaPlugin.getBufferedImageInPluginsJar(pathInJar: String): BufferedImage? {
	try {
		return ImageIO.read(getResource(pathInJar))
	} catch (e: IOException) {
		printStackTrace(e, "The image could not be located, the path was: $pathInJar")
	}
	return null
}

fun BufferedImage.toImageMessage(c: ImageChar, height: Int, vararg lore: String): ImageMessage {
	var newLore = lore
	if (newLore.size > height) newLore = Arrays.copyOf(newLore, height)
	val arraySize = height / 2 + newLore.size / 2
	val start = arraySize - newLore.size
	val array = Array(arraySize) { i ->
		if (i < start) {
			""
		} else {
			newLore[i - start]
		}
	}
	return ImageMessage(this, height, c.char).apply { appendText(*array) }
}

/**
 * This is for sending several images in a gift format
 */

class AnimatedMessage {
	private var images: Array<ImageMessage>
	private var index = 0

	constructor(vararg images: ImageMessage) {
		this.images = images.asList().toTypedArray()
	}

	constructor(gifFile: File, height: Int, imgChar: Char) {
		val frames = getFrames(gifFile)
		images = Array(frames.size) { i -> ImageMessage(frames[i], height, imgChar) }
	}

	fun getFrames(input: File): List<BufferedImage> = ArrayList<BufferedImage>().apply {
		val reader = ImageIO.getImageReadersBySuffix("GIF").next()
		val img = ImageIO.createImageInputStream(input)
		reader.input = img
		(0 until reader.getNumImages(true)).mapTo(this) { reader.read(it) }
	}

	fun current() = images[index]

	operator fun next(): ImageMessage {
		if (++index >= images.size) index = 0
		return images[index]
	}

	fun previous(): ImageMessage {
		if (--index <= 0) index = images.size - 1
		return images[index]
	}

	fun getIndex(index: Int) = images[index]
}

enum class ImageChar(val char: Char) {
	BLOCK('\u2588'), DARK_SHADE('\u2593'), MEDIUM_SHADE('\u2592'), LIGHT_SHADE('\u2591')
}


