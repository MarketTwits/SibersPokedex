package com.markettwits.cahce

import me.sujanpoudel.utils.contextProvider.applicationContext
import java.io.File

actual val defaultInStorageCachePath: String =
    File(applicationContext.cacheDir, "com.markettwits.siberspokedex").absolutePath
actual val defaultInStorageFilePath: String =
    File(applicationContext.filesDir, "com.markettwits.siberspokedex").absolutePath