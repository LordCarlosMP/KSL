package org.ksl.useful

import org.bukkit.*

val CURRENT_NMS_VERSION by lazy {
	val packageName = Bukkit.getServer().javaClass.`package`.name
	NMSVersion.valueOf(packageName.substring(packageName.lastIndexOf(".") + 1))
}

enum class NMSVersion {
	v1_8_R2, v1_8_R3, v1_9_R1, v1_9_R2, v1_10_R1, v1_11_R1, v1_12_R1
}
