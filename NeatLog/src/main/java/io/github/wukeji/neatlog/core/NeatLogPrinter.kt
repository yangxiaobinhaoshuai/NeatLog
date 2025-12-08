package io.github.wukeji.neatlog.core

fun NeatLogPrinter.replaceTag(replacement: (old: String) -> String): NeatLogPrinter {
    return NeatLogPrinter { level, tag, message, throwable ->
        this@replaceTag.print(
            level,
            replacement.invoke(tag),
            message,
            throwable
        )
    }
}

fun NeatLogPrinter.replaceMessage(replacement: (old: String) -> String): NeatLogPrinter {
    return NeatLogPrinter { level, tag, message, throwable ->
        this@replaceMessage.print(
            level,
            tag,
            replacement.invoke(message),
            throwable
        )
    }
}

fun interface NeatLogPrinter {
    fun print(
        level: NeatLogLevel,
        tag: String,
        message: String,
        throwable: Throwable?
    )
}
