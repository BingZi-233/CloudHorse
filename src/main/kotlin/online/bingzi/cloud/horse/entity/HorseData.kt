package online.bingzi.cloud.horse.entity

/**
 * Horse data
 * 马的数据
 *
 * @property name 马的名称
 * @property uuid 马的UUID
 * @property model 模型
 * @property owner 驯服的人
 * @constructor Create empty Horse data
 */
data class HorseData(
    val name: String,
    val uuid: String,
    val model: String,
    val owner: OwnerDate,
)
