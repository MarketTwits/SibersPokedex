package com.markettwits.cahce

/**
 * The directory for saving files in the device's cache
 *
 * ```
 *  InStorageCacheDirectory.path = cacheDir.path
 * ```
 */
object InStorageCacheDirectory {
    var path: String = defaultInStorageCachePath
}

expect val defaultInStorageCachePath: String

/**
 * The directory for saving files in the device's file storage
 *
 * ```
 *  InStorageFileDirectory.path = filesDir.path
 * ```
 */
object InStorageFileDirectory {
    var path = defaultInStorageFilePath
}

expect val defaultInStorageFilePath: String