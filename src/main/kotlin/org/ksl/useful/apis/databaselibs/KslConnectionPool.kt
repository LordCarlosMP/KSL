@file:Suppress("unused")

package org.ksl.useful.apis.databaselibs

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.bukkit.plugin.java.JavaPlugin
import org.intellij.lang.annotations.Language
import org.ksl.useful.extensions.async
import org.ksl.useful.extensions.printStackTrace
import org.ksl.useful.extensions.sync
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

/**
 * Created by LordCarlosMP on 27/04/2017.
 * Main goals:
 * Simple
 * Thread Safe
 * Fast: it uses HickaryCP Connection pool
 * Avoiding common mistakes such as memory leacks or Feezing Main Thread
 */

class KslConnectionPool(private val plugin: JavaPlugin, val connectionPool: HikariDataSource) {

	//For custom HikaryConfig
	constructor(plugin: JavaPlugin, config: HikariConfig) : this(plugin, HikariDataSource(config))

	//Default constructor for common connection pools
	constructor(plugin: JavaPlugin, jdbcUrl: String, user: String, pass: String) : this(plugin, {
		var ds: HikariDataSource?
		try {
			val config = HikariConfig()
			config.jdbcUrl = jdbcUrl
			config.username = user
			config.password = pass
			config.addDataSourceProperty("cachePrepStmts", "true")
			config.addDataSourceProperty("prepStmtCacheSize", "250")
			config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")
			ds = HikariDataSource(config)
		} catch (ex: Exception) {
			throw SQLException("Could not connect to the database", ex)
		}
		ds!!
	}())

	/**
	 * @Description This method won´t block the thread who called it, it will make a new one and when the query is done, it will call @Param f
	 *              in Bukkit´s main thread using its scheduler.
	 * @Param sql   The sql to run.
	 * @Param replacements   The replacements for the PreparedStatement, this will replace all ? characters with the replacements after compiling SQL,
	 *              for avoiding SQL injections.
	 * @Param f     The closure to run in bukkit´s primary thread when the query is done and the ResultSet received.
	 */

	fun query(@Language("SQL") sql: String, vararg replacements: Any, f: ResultSet.() -> Unit) {
		plugin.async {
			var conn: Connection? = null
			var rs: ResultSet? = null
			var pst: PreparedStatement? = null
			fun close() = makeSureYouClose(rs, pst, conn)
			try {
				conn = connectionPool.connection
				pst = prepare(conn, sql, *replacements)
				rs = pst.executeQuery()
				plugin.sync {
					try {
						f(rs!!)
					} catch (ex: SQLException) {
						plugin.printStackTrace(ex, "§cAn exception was thrown in f function")
					} finally {
						plugin.async { close() }
					}
				}
			} catch (ex: Exception) {
				close()
				plugin.printStackTrace(ex, "§cAn exception was thrown while getting or preparing/queryng the statement")
			}
		}
	}

	/**
	 * @Description This method blocks the thread who called it till the query is done
	 * @Param sql   The sql to run.
	 * @Param replacements   The replacements for the PreparedStatement, this will replace all ? characters with their respective replacement after compiling SQL
	 *              for avoiding SQL injections.
	 * @Param f     The closure to run in the thread who called it when the query is done and the ResultSet received, NOTE: this can not be transformed into a ResulSet
	 *              returning method because the connection, the statement and the ResultSet should be closed after executing f.
	 */
	inline fun queryInThisThread(@Language("SQL") sql: String, vararg replacements: Any, f: ResultSet.() -> Unit) {
		connectionPool.connection.use { conn -> prepare(conn, sql, *replacements).use { pst -> pst.executeQuery().use { rs -> f(rs) } } }
	}

	/**
	 * @Description This method won´t block the thread who called it, it will make a new one and when de update is done, it will call @Param f
	 *              in Bukkit´s main thread using its scheduler.
	 * @Param sql   The sql to run
	 * @Param replacements   The replacements for the PreparedStatement, this will replace all ? characters with the replacements after compiling SQL,
	 *              for avoiding SQL injections.
	 * @Param f     The closure to run in Bukkit´s primary thread when the update in done.
	 */
	fun update(@Language("SQL") sql: String, vararg replacements: Any, f: () -> Unit = {}) {
		plugin.async {
			updateInThisThread(sql, *replacements)
			if (f != {}) plugin.sync(f) //we dont need to call the scheduler if f == {}
		}
	}

	/**
	 * @Description This method blocks the thread who called it till the update is done, no closure since whatever we need to do after the update can
	 *              be done just after calling it.
	 * @Param sql   The sql to run.
	 * @Param replacements   The replacements for the PreparedStatement, this will replace all ? characters with their respective replacement after compiling SQL
	 *              for avoiding SQL injections.
	 */
	fun updateInThisThread(@Language("SQL") sql: String, vararg replacements: Any) {
		connectionPool.connection.use { conn -> prepare(conn, sql, *replacements).use { pst -> pst.executeUpdate() } }
	}

	fun prepare(conn: Connection, sql: String, vararg replacements: Any): PreparedStatement {
		val pst = conn.prepareStatement(sql)

		for (i in replacements.indices) {
			pst.setObject(i + 1, replacements[i])
		}
		return pst
	}

	private fun makeSureYouClose(vararg closeables: AutoCloseable?) {
		for (c in closeables) {
			try {
				c?.close()
			} catch (e: Exception) {
				plugin.printStackTrace(e, "§cError when closing a closeable")
			}
		}
	}
}

fun KslConnectionPool.queryIfFirst(@Language("SQL") sql: String, vararg replacements: Any, f: (Boolean) -> Unit) = query(sql, *replacements) { f(next()) }

fun KslConnectionPool.queryInFirstRow(@Language("SQL") sql: String, vararg replacements: Any, f: ResultSet.() -> Unit) = query(sql, *replacements) { if (next()) f() }

fun KslConnectionPool.queryForEach(@Language("SQL") sql: String, vararg replacements: Any, f: ResultSet.() -> Unit) = query(sql, *replacements) { while (next()) f() }