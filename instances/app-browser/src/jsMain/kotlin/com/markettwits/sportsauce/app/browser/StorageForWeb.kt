package com.markettwits.sportsauce.app.browser

import com.markettwits.cahce.InStorageCacheDirectory
import com.markettwits.cahce.InStorageFileDirectory

fun InitStorageForWeb() {
    InStorageCacheDirectory.path = "data/com.markettwits.siberspokedex"
    InStorageFileDirectory.path = "cache/com.markettwits.siberspokedex"
}