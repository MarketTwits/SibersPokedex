package com.markettwits.siberspokedex.app

import android.app.Application
import com.markettwits.activityholder.CurrentActivityHolder
import com.markettwits.cahce.InStorageCacheDirectory
import com.markettwits.cahce.InStorageFileDirectory

class SibsersPokedexApp : Application() {

    override fun onCreate() {
        super.onCreate()
        CurrentActivityHolder.register(this@SibsersPokedexApp)
        InStorageCacheDirectory.path = cacheDir.path
        InStorageFileDirectory.path = filesDir.path
    }
}