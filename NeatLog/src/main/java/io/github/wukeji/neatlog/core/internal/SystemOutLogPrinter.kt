package io.github.wukeji.neatlog.core.internal

import io.github.wukeji.neatlog.core.NeatLogLevel
import io.github.wukeji.neatlog.core.NeatLogPrinter

internal class SystemOutLogPrinter : NeatLogPrinter {

    override fun print(
        level: NeatLogLevel,
        tag: String,
        message: String,
        throwable: Throwable?
    ) {
        println("$tag: $message")
    }

}
