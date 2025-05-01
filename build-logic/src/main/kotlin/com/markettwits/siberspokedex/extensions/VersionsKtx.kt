package com.markettwits.siberspokedex.extensions

import org.gradle.api.JavaVersion
import org.gradle.api.Project
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

val Project.PROJECT_JAVA_VERSION: JavaVersion
    get() = JavaVersion.toVersion(libs.versions.jvm.get())

val Project.PROJECT_VERSION_NAME: String
    get() = libs.versions.versionName.get()

val Project.PROJECT_VERSION_CODE: Int
    get() = versionCodeFromBuildTime()

private fun versionCodeFromBuildTime(): Int {
    val format = SimpleDateFormat("yyMMddHH", Locale.US)
    val timeString = format.format(Date())
    return timeString.toInt()
}