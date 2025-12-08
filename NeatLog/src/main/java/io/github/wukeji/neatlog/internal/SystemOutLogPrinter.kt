package io.github.wukeji.neatlog.internal

import io.github.wukeji.neatlog.core.LogLevel
import io.github.wukeji.neatlog.uitlity.LogPrinter

internal class SystemOutLogPrinter : LogPrinter {
    override fun print(
        level: LogLevel,
        tag: String,
        message: String,
        throwable: Throwable?
    ) {
        println("$tag $message")
    }

}
