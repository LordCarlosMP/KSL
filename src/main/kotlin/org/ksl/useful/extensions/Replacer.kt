//@file:Suppress("unused")
//
//package org.ksl.useful.extensions
//
///**
// * Created by LordCarlosMP on 02/07/2017.
// */
//
//import org.ksl.javawarp.*
//
///**
// * Created by LordCarlosMP on 29/04/2017.
// *
// * GET THE ACCESS TO THE Strings (Char[],boolean) CONSTRUCTOR FOR AVOIDING MANUAL ARRAY COPY
// */
////fun newString(stringChars: CharArray): String = ReflectionInString.newString(stringChars)
//
//fun String.concat(vararg replacements: String) = append(this, *replacements)
//
//fun append(vararg replacements: String): String {
//	val newSize = replacements.sumBy { it.length }
//	val newChars = CharArray(newSize)
//	var newCharsCounter = 0
//	for (vs in replacements) {
//		val size = vs.length
//		vs.toCharArray(newChars, newCharsCounter, 0, size)
//		newCharsCounter += size
//	}
//	return newString(newChars)
//}
//
//fun append(vararg replacements: Any): String {
//	var newSize = 0
//	val rs = replacements.size
//	val strings = arrayOfNulls<String>(replacements.size)
//	for (i in 0 until rs) {
//		val rRep = replacements[i].toString()
//		newSize += rRep.length
//		strings[i] = rRep
//	}
//	val newChars = CharArray(newSize)
//	var newCharsCounter = 0
//	for (vs in strings) {
//		val size = vs!!.length
//		vs.toCharArray(newChars, newCharsCounter, 0, size)
//		newCharsCounter += size
//	}
//	return newString(newChars)
//}
//
//fun replace(s: String, charToReplace: Char, vararg replacements: Any): String {
//	val rs = replacements.size
//	val strings = arrayOfNulls<String>(rs)
//	var newSize = s.length
//	for (i in 0 until rs) {
//		val rRep = replacements[i].toString()
//		newSize += rRep.length
//		strings[i] = rRep
//	}
//	val newChars = CharArray(newSize)
//	var newCharsCounter = 0
//	var i = 0
//	var arrn = 0
//	val l = s.length
//	while (i < l) {
//		val c = s[i]
//		if (c == charToReplace) {
//			val rep = strings[arrn++]
//			val lsl = rep!!.length
//			rep.toCharArray(newChars, newCharsCounter, 0, lsl)
//			newCharsCounter += lsl
//		} else {
//			newChars[newCharsCounter] = c
//		}
//		i++
//		newCharsCounter++
//	}
//	return newString(newChars)
//}
//
//fun appendWithCharInMiddle(c: Char, vararg replacements: String): String {
//	val newSize = replacements.size + replacements.sumBy { it.length }
//	val newChars = CharArray(newSize)
//	var newCharsCounter = 0
//	for (vs in replacements) {
//		val size = vs.length
//		vs.toCharArray(newChars, newCharsCounter, 0, size)
//		newCharsCounter += size
//		if (newChars.size > newCharsCounter) {
//			newChars[newCharsCounter++] = c
//		}
//	}
//	return newString(newChars)
//}
//
