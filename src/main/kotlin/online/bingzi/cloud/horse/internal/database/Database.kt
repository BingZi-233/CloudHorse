package online.bingzi.cloud.horse.internal.database

import online.bingzi.cloud.horse.internal.entity.OwnerData
import online.bingzi.cloud.horse.internal.util.Cache
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.Schedule
import taboolib.common.platform.event.SubscribeEvent

/**
 * Database
 * 数据库
 *
 * @constructor Create empty Database
 */
abstract class Database {
    /**
     * Insert owner data
     * 插入OwnerData
     *
     * @param ownerData owner data
     */
    abstract fun insertOwnerData(ownerData: OwnerData)

    /**
     * Select owner data
     * 获取OwnerData
     *
     * @param player player
     * @return owner data
     */
    abstract fun selectOwnerData(player: Player): OwnerData?

    /**
     * Update owner data
     * 更新OwnerData
     *
     * @param ownerData owner data
     */
    abstract fun updateOwnerData(ownerData: OwnerData)

    /**
     * Delete owner data
     * 删除OwnerData
     *
     * @param player player
     */
    abstract fun deleteOwnerData(player: Player)

    companion object {
        private val INSTANCE: Database by lazy {
            try {
                when (DatabaseType.INSTANCE) {
                    DatabaseType.LOCAL -> DatabaseSQLite()
                    DatabaseType.SQL -> throw Throwable("SQL database is not supported")
                    DatabaseType.MONGODB -> throw Throwable("MongoDB database is not supported")
                }
            } catch (e: Throwable) {
                DatabaseError(e)
            }
        }

        @Awake(LifeCycle.ENABLE)
        fun onEnable() {
            INSTANCE
        }

        /**
         * On player join
         * 获取玩家缓存数据
         *
         * @param event PlayerJoinEvent
         */
        @SubscribeEvent
        fun onPlayerJoin(event: PlayerJoinEvent) {
            val player = event.player
            Cache.cacheOwnerData[player] = INSTANCE.selectOwnerData(player) ?: OwnerData(player.name, player.uniqueId.toString())
        }

        /**
         * On player quit
         * 删除玩家缓存数据
         *
         * @param event PlayerQuitEvent
         */
        @SubscribeEvent
        fun onPlayerQuit(event: PlayerQuitEvent) {
            val player = event.player
            val ownerData = Cache.cacheOwnerData[player] ?: throw Throwable("Player ${player.name} is not cached")
            INSTANCE.updateOwnerData(ownerData)
        }

        /**
         * On update
         * 定时异步保存数据
         * 每五秒进行一次
         *
         */
        @Schedule(period = 100, async = true)
        fun onUpdate() {
            Cache.cacheOwnerData.values.forEach {
                INSTANCE.updateOwnerData(it)
            }
        }
    }
}