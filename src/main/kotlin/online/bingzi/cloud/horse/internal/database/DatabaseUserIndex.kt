package online.bingzi.cloud.horse.internal.database

import online.bingzi.cloud.horse.internal.util.ConfigUtil.conf

/**
 * Database user index
 * 数据库用户索引
 *
 * @constructor Create empty Database user index
 */
enum class DatabaseUserIndex {
    NAME, UUID;

    companion object {
        /**
         * Instance
         */
        val INSTANCE: DatabaseUserIndex by lazy {
            try {
                valueOf(conf.getString("database.user-index")!!.uppercase())
            } catch (ignore: Throwable) {
                UUID
            }
        }
    }
}