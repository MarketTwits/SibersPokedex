package com.markettwits.siberspokedex.items.api

import com.markettwits.cahce.InStorageCacheDirectory
import com.markettwits.cahce.InStorageListCache
import com.markettwits.cahce.store_wrapper.listStoreOfWrapper
import com.markettwits.siberspokedex.cloud.cloud.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * A cache implementation for storing Pokemon objects in persistent storage.
 * @property maxItemCountInStorage The maximum number of items to keep in cache (default: 100)
 */
internal class PokemonCache(
    private val maxItemCountInStorage : Int = 100,
) : InStorageListCache<Pokemon>(
    listStoreOfWrapper<Pokemon>(
        path = InStorageCacheDirectory.path,
        fileName = "pokemon_cache",
    )
) {
    override suspend fun set(key: Any, value: Pokemon) {
        val list = getList()
        if (!list.contains(value)) {
            super.set(value = value, key = key)
            if (list.size > maxItemCountInStorage) {
                remove(value = list.last())
            }
        }
    }

    override fun observe(): Flow<List<Pokemon>?> {
        return super.observe().map { it?.reversed() }
    }

    override suspend fun getList(key: Any): List<Pokemon> =
        super.getList(key).reversed()
}