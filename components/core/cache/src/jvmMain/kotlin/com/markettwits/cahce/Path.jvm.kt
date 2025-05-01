package com.markettwits.cahce

import java.io.File

actual val defaultInStorageCachePath: String = File(System.getProperty("java.io.tmpdir")).absolutePath

actual val defaultInStorageFilePath: String = File(System.getProperty("java.io.tmpdir")).absolutePath