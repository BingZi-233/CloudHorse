package online.bingzi.cloud.horse.database

import online.bingzi.cloud.horse.util.ConfigUtil

enum class DatabaseType {
    LOCAL, SQL, MONGODB;
    companion object{
        val INSTANCE : DatabaseType by lazy {
            try {
                valueOf(ConfigUtil.confConfig.getString("database.use")!!.uppercase())
            }catch (ignored:Throwable){
                LOCAL
            }
        }
    }
}
