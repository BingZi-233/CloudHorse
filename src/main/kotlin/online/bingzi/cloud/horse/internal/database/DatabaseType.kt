package online.bingzi.cloud.horse.internal.database

import online.bingzi.cloud.horse.internal.util.ConfigUtil

/**
 * Database type
 * 数据库类型
 *
 * @constructor Create empty Database type
 */
enum class DatabaseType {
    LOCAL, SQL, MONGODB;

    companion object {
        /**
         * Instance
         */
        val INSTANCE: DatabaseType by lazy {
            try {
                valueOf(ConfigUtil.conf.getString("database.use")!!.uppercase())
            } catch (ignore: Throwable) {
                LOCAL
            }
        }
    }
}