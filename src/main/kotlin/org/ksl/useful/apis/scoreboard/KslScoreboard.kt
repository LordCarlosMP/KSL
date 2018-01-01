@file:Suppress("unused")

package org.ksl.useful.apis.scoreboard

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.ChatColor.RESET
import org.bukkit.entity.Player
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.DisplaySlot.BELOW_NAME
import org.bukkit.scoreboard.DisplaySlot.PLAYER_LIST
import org.bukkit.scoreboard.DisplaySlot.SIDEBAR
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.Team

/**
 * Created by LordCarlosMP on 01/06/2017.
 */
//class KslScoreboard/*(val plugin: KotlinPlugin)*/ {
//
//	private val scoreboard = Bukkit.getScoreboardManager().newScoreboard!!
//
//	val sidebar by lazy {
//		kslObjective(SIDEBAR)
//	}
//	val belowname by lazy {
//		kslObjective(BELOW_NAME)
//	}
//	val playerlist by lazy {
//		kslObjective(PLAYER_LIST)
//	}
//
//	inner class kslObjective internal constructor(slot: DisplaySlot) {
//		val objective: Objective = scoreboard.registerNewObjective(
//				when (slot) {
//					PLAYER_LIST -> "list"
//					BELOW_NAME -> "belowname"
//					SIDEBAR -> "sidebar"
//				}, "").apply {
//			displaySlot = slot
//		}
//
//		private val rows = ArrayList<String>()
//
//		fun size() = rows.size
//
//		fun editRow(oldRow: String, newRow: String) {
//			val i = rows.indexOf(oldRow)
//			if (i == -1) return
//			editRow(i, newRow)
//		}
//
//		fun editRow(position: Int, newRow: String) {
//			scoreboard.resetScores(rows[position])
//			objective.getScore(newRow).score = size() - 1 - position
//			rows[position] = newRow
//		}
//
//		fun getRow(i: Int) = rows[i]
//
//		fun addRows(vararg newRows: String) {
//			var size = size()
//			rows.addAll(newRows)
//			for (s in rows) scoreboard.resetScores(s)
//			for (row in rows) objective.getScore(row).score = size--
//		}
//
//		fun addRawStringRow(newRow: String) {
//			addRows(*newRow.trimIndent().split("""
//""").toTypedArray())
//		}
//
//		fun removeRow(position: Int) {
//			if (position >= size()) return
//			scoreboard.resetScores(rows[size() - position])
//			rows.removeAt(position)
//		}
//
//		fun clear() {
//			for (s in rows) scoreboard.resetScores(s)
//			rows.clear()
//		}
//
//		fun setName(title: String) {
//			objective.name = title
//		}
//
//	}
//
//	var tc = 0
//	fun createTeam(name: String = "Team number ${tc++}"): Team = scoreboard.registerNewTeam(name)
//
//	fun setScoreboardTo(vararg players: Player) {
//		for (p in players) p.scoreboard = scoreboard
//	}
//}
//
//fun Player.setScoreboard(s: KslScoreboard) = s.setScoreboardTo(this)
//
//class KslScoreboardV2/*(val plugin: KotlinPlugin)*/ {
//
//	private val scoreboard = Bukkit.getScoreboardManager().newScoreboard!!
//
//	val sidebar by lazy {
//		kslObjective(SIDEBAR)
//	}
//	val belowname by lazy {
//		kslObjective(BELOW_NAME)
//	}
//	val playerlist by lazy {
//		kslObjective(PLAYER_LIST)
//	}
//
//	inner class kslObjective internal constructor(slot: DisplaySlot) {
//		private val objective: Objective = scoreboard.registerNewObjective(
//				when (slot) {
//					PLAYER_LIST -> "list"
//					BELOW_NAME -> "belowname"
//					SIDEBAR -> "sidebar"
//				}, "").apply {
//			displaySlot = slot
//		}
//
//		private val rows = ArrayList<Team>()
//
//		fun size() = rows.size
//
//		var displayname: String
//			get() = objective.name
//			set(newName) {
//				objective.name = newName
//			}
//
////		fun editRow(oldRow: String, newRow: String) {
////			val i = rows.indexOf(oldRow)
////			if (i == -1) return
////			editRow(i, newRow)
////		}
//
//		fun editRow(position: Int, newRow: String) {
//			rows[position].changeName(newRow)
//		}
//
////		fun getRow(i: Int): String = rows[i].tname
//
//		fun addRows(vararg newRows: String) {
////			objective.getScore("1234567890123456789012")
//			val teams = Array(newRows.size) {
//				val i = it + size()
//				scoreboard.registerNewTeam("SCTeam$i").apply {
//					addEntry(formatTeam(it))
//					changeName(newRows[it])
//				}
//			}
//			for (s in rows) scoreboard.resetScores(s.name)
//			rows.addAll(teams)
//			var size = size()
//			for (i in rows.indices) objective.getScore(formatTeam(i)).score = size--
//		}
//
//		fun addRawStringRow(newRow: String) {
//			addRows(*newRow.trimIndent().split("""
//""").toTypedArray())
//		}
//
//		fun removeRow(position: Int) {
//			if (position >= size()) return
//			scoreboard.resetScores(rows[size() - position].name)
//			rows.removeAt(position)
//		}
//
//		fun clear() {
//			for (s in rows) scoreboard.resetScores(s.name)
//			rows.clear()
//		}
//
//		fun setName(title: String) {
//			objective.name = title
//		}
//	}
//
//	//	private fun formatTeam(i: Int) = "123456789012345678901234567890123"
//	private fun formatTeam(i: Int) = "${ChatColor.values()[i]}${ChatColor.RESET}"
//
//	var tc = 0
//	fun createTeam(name: String = "Team number ${tc++}"): Team = scoreboard.registerNewTeam(name)
//
//	fun setScoreboardTo(vararg players: Player) {
//		for (p in players) p.scoreboard = scoreboard
//	}
//}
//
//private val Team.tname get() = prefix
//
//private fun Team.changeName(newRow: String) {
//	if (newRow.length > 15) {
//		prefix = newRow.subSequence(0, 15).toString()
//		suffix = if (newRow.length > 31) newRow.subSequence(15, 31).toString() else newRow.subSequence(15, newRow.length).toString()
//	} else {
//		prefix = newRow.subSequence(0, newRow.length).toString()
//	}
//
//}

/** For the first 20 ones */
//§a §a§athe same score
//§a §b§athe same score
//§a §c§athe same score
//§a §d§athe same score
//§a §e§athe same score
//§a §0§athe same score
//§a §2§athe same score

/**For the last 12 ones*/
//	the same score
// the same scor§re
// the same sco§rre
// the same sc§rore
// the same s§rcore
// the same §rscore
// the same§r score
// the sam§re score
// the sa§rme score
// the s§rame score
// the §rsame score
// the§r same score
// th§re same score
// th§re same score
// t§rhe same score
// §rthe same score

class KslScoreboardV3 internal constructor() {
	//TODO: recrdar color entre el prefix, nombre y sufix

	private val scoreboard = Bukkit.getScoreboardManager().newScoreboard!!

	val sidebar by lazy {
		KslObjective(SIDEBAR)
	}
	val belowname by lazy {
		KslObjective(BELOW_NAME)
	}

	val playerlist by lazy {
		KslObjective(PLAYER_LIST)
	}

	inner class KslObjective internal constructor(slot: DisplaySlot) {
		fun editRow(oldRow: String, newRow: String) {
			val i = rows.indexOf(rows.first { it.score == oldRow })
			if (i == -1) return
			editRow(i, newRow)
		}

		inner class ScoreboardRow(val n: Int) {

			val team: Team = createTeam("${objective.displayName}$n")

			init {
				team.addEntry("${n.chatColor}$RESET")
			}

			val score: String get() = team.entries.first()

			fun changeName(newName: String) {
				val newRow = if (newName.length > 70) newName.substring(0, 70) else newName
				val length = newRow.length
				val prefix = newRow.substring(0, minOf(length, 16))
				val suffix = if (length <= 16) "" else newRow.substring(maxOf(16, length - 16), length).run {
					val last = lastColorModificator()
					if (last == 'f') this else last + this.dropLast(2)
				}
				val newScore = (if (length <= 32) "" else newRow.substring(16, length - 16)).insertLongNeutralBbcAtBeginning(newRow, n)
				team.prefix = prefix
				team.suffix = suffix
				if (newScore == score) return
				//Avoid same entry duplication!!!
				if (newScore in rows.map { it.score }) {
					//still duplicated with some §3Hello §3Guy but this has not been generated by
					//the scoreboard, only by a mistake, this ir really few probable to happen, the best way is correcting.
					if (newScore in rows.map { it.score }) {
						print("happened!, its: " + rows.first { it.score == newScore } + "and if removed, " + newScore.removeUnNecessaryBbcs())
						rows.first { it.score == newScore }.changeName(newScore.removeUnNecessaryBbcs())
					}
				}
				replaceScore(score, newScore)
			}

			private fun String.lastColorModificator(): Char {
				val last = lastIndexOf('§')
				return if (last == -1 || last >= length - 2) 'f' else this[last + 1]
			}

//			private fun String.insertShortNeutralBbcAt(holeString:String,i: Int): String {
//				val lastModificator = holeString.lastColorModificator()
//				val s = "${subSequence(0, i)}§$lastModificator${subSequence(i, length)}"
//				print("this is $this and returning $s")
//				return s
//			}

			private fun String.insertLongNeutralBbcAtBeginning(holeString: String, rowPosition: Int)
					= "${rowPosition.chatColor}§${holeString.lastColorModificator()}$this"

			fun replaceScore(oldscore: String, newScore: String) {
				team.removeEntry(oldscore)
				val sc = objective.getScore(oldscore).score
				scoreboard.resetScores(oldscore)
				team.addEntry(newScore)
				objective.getScore(newScore).score = sc
			}
		}

		private val objective: Objective = scoreboard.registerNewObjective(
				when (slot) {
					PLAYER_LIST -> "list"
					BELOW_NAME -> "belowname"
					SIDEBAR -> "sidebar"
				}, "").apply {
			displaySlot = slot
		}

		private val rows = ArrayList<ScoreboardRow>()

		fun size() = rows.size

		var displayname: String
			get() = objective.displayName
			set(newName) {
				objective.displayName = newName
			}

		//TODO: while para cuando no esta creada, recuarda paladins
		fun editRow(position: Int, newRow: String) {
			if (rows.size <= position) return
			rows[position].changeName(newRow)
		}

		fun getRow(i: Int): String = rows[i].team.name

		fun addRows(vararg newLines: String) {
			val newRows = Array(newLines.size) {
				ScoreboardRow(it + size()).apply {
					changeName(newLines[it])
				}
			}

			for (s in rows) scoreboard.resetScores(s.team.name)
			rows.addAll(newRows)
			var size = size()
			for (i in rows) objective.getScore(i.score).score = size--
		}

		fun addRawStringRow(newRow: String) {
			addRows(*newRow.trimIndent().split("""
""").toTypedArray())
		}

		fun removeRow(position: Int) {
			if (position >= size()) return
			scoreboard.resetScores(rows[size() - position].team.name)
			rows.removeAt(position)
		}

		fun clear() {
			for (s in rows) scoreboard.resetScores(s.team.name)
			rows.clear()
		}

		fun setDisplayName(title: String) {
			objective.displayName = title
		}

	}

	val Int.chatColor get() = ChatColor.values()[this]

	//TODO: que no quite el color
	private fun formatEmptyEntry(i: Int) = "${ChatColor.values()[i]}$RESET"

	var tc = 0

	fun createTeam(name: String = "Team number ${tc++}"): Team = scoreboard.registerNewTeam(name)
	fun setScoreboardTo(vararg players: Player) {
		for (p in players) p.scoreboard = scoreboard
	}


}

fun main(args: Array<String>) {
//	print("§§3Willyi§§3To§3".removeUnNecessaryBbcs())
}

private fun String.removeUnNecessaryBbcs(): String {
	var lastchar = 'f'
	val sb = StringBuilder()

	val iter = iterator()
	while (iter.hasNext()) {
		val c = iter.next()
		if (c != '§') {
			sb.append(c)
		} else {
			if (iter.hasNext()) {
				val nc = iter.next()
				if (nc != lastchar) {
					sb.append(c)
					sb.append(nc)
				}
				lastchar = nc
			} else sb.append('§')

		}
	}
	return sb.toString()
}



