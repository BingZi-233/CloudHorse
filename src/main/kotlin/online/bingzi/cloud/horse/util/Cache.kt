package online.bingzi.cloud.horse.util

import ltd.icecold.orangeengine.api.OrangeEngineAPI

/**
 * Model cache
 * 模型名称缓存
 */
val modelCache: MutableList<String> = mutableListOf<String>().apply {
    this.addAll(OrangeEngineAPI.getModelManager().allModelData.keys)
}