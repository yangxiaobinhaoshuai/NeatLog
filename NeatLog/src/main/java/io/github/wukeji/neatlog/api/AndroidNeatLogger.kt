package io.github.wukeji.neatlog.api

import android.util.Log
import io.github.wukeji.neatlog.core.NeatLogLevel
import io.github.wukeji.neatlog.core.NeatLogPrinter
import io.github.wukeji.neatlog.elements.LogPrinterElement


public val AndroidUtilLogger by lazy {
    RawLogger.clone(newLogContext = LogPrinterElement(AndroidUtilLogPrinter()))
}

class AndroidUtilLogPrinter : NeatLogPrinter {
    override fun print(level: NeatLogLevel, tag: String, message: String, throwable: Throwable?) {
        val intLevel = when (level) {
            NeatLogLevel.VERBOSE -> Log.VERBOSE
            NeatLogLevel.DEBUG -> Log.DEBUG
            NeatLogLevel.INFO -> Log.INFO
            NeatLogLevel.ERROR -> Log.ERROR
        }
        if (intLevel == Log.ERROR && throwable != null) {
            Log.e(tag, message, throwable)
            return
        } else Log.println(intLevel, tag, message)
    }
}