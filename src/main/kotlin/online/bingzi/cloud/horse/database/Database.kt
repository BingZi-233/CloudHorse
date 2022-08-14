package online.bingzi.cloud.horse.database

import online.bingzi.cloud.horse.api.event.HorseDataUpdateEvent
import online.bingzi.cloud.horse.api.event.OwnerDataUpdateEvent
import online.bingzi.cloud.horse.entity.HorseData
import online.bingzi.cloud.horse.entity.OwnerData
import online.bingzi.cloud.horse.util.HorseDataCache
import online.bingzi.cloud.horse.util.OwnerDataCache
import org.bukkit.entity.AbstractHorse
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.Schedule
import taboolib.common.platform.event.SubscribeEvent

abstract class Database {
    /**
     * Select Player
     * 获取玩家数据模型
     *
     * @param player 玩家
     * @return 玩家数据模型
     */
    abstract fun selectPlayer(player: Player): OwnerData?

    /**
     * Update player
     * 更新玩家数据模型
     *
     * @param ownerData 玩家数据模型
     */
    abstract fun updatePlayer(ownerData: OwnerData)

    /**
     * Delete player
     * 删除玩家数据模型
     *
     * @param player 玩家
     */
    abstract fun deletePlayer(player: Player)

    /**
     * Select horse
     * 获取马数据模型
     *
     * @param abstractHorse 马（抽象）
     * @return 马数据模型
     */
    abstract fun selectHorse(abstractHorse: AbstractHorse): HorseData?

    /**
     * Update horse
     * 更新马数据模型
     *
     * @param horseData 马数据模型
     */
    abstract fun updateHorse(horseData: HorseData)

    /**
     * Delete horse
     * 删除马数据模型
     *
     * @param abstractHorse 马数据模型
     */
    abstract fun deleteHorse(abstractHorse: AbstractHorse)

    companion object {
        val INSTANCE: Database by lazy {
            try {
                when (DatabaseType.INSTANCE) {
                    DatabaseType.LOCAL -> DatabaseError(IllegalAccessError("Local Database is not supported"))
                    DatabaseType.SQL -> DatabaseSQL()
                    DatabaseType.MONGODB -> DatabaseError(IllegalAccessError("MongoDB Database is not supported"))
                }
            } catch (e: Throwable) {
                DatabaseError(e)
            }
        }

        /**
         * On enable
         * 对数据库链接进行初始化
         *
         */
        @Awake(LifeCycle.ENABLE)
        fun onEnable() {
            INSTANCE
        }

        /**
         * On player join
         * 玩家数据模型缓存读取
         *
         * @param event PlayerJoinEvent
         */
        @SubscribeEvent
        fun onPlayerJoin(event: PlayerJoinEvent) {
            val player = event.player
            OwnerDataCache[player] = INSTANCE.selectPlayer(player) ?: OwnerData(player.name, player.uniqueId.toString())
        }

        /**
         * On player exit
         * 玩家数据模型缓存读取
         *
         * @param event PlayerQuitEvent
         */
        @SubscribeEvent
        fun onPlayerExit(event: PlayerQuitEvent) {
            val player = event.player
            val ownerData = OwnerDataCache[player] ?: throw Exception("出现内存数据损坏，${player.name}-${player.uniqueId}的数据出现问题！")
            INSTANCE.updatePlayer(ownerData)
        }

        /**
         * On entity death
         * 无效马数据模型清理
         *
         * @param event EntityDeathEvent
         */
        @SubscribeEvent
        fun onEntityDeath(event: EntityDeathEvent) {
            val entity = event.entity
            if (entity is AbstractHorse) {
                // 清除无效马的模型记录
                INSTANCE.deleteHorse(entity)
            }
        }

        /**
         * Update200
         * 每200进行全部数据保存
         * 注意：
         * 1. 会触发OwnerDataUpdateEvent事件
         * 2。 会触发HorseDataUpdateEvent事件
         *
         */
        @Schedule(period = 200, async = true)
        internal fun update200() {
            OwnerDataCache.forEach { (t, u) ->
                INSTANCE.updatePlayer(u)
                OwnerDataUpdateEvent(t, u).call()
            }
            HorseDataCache.forEach { (t, u) ->
                INSTANCE.updateHorse(u)
                HorseDataUpdateEvent(t, u).call()
            }
        }
    }
}