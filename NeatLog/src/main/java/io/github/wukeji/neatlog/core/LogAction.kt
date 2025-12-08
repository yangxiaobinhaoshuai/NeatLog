package io.github.wukeji.neatlog.core

import io.github.wukeji.neatlog.domain.DomainContext

interface LogAction {

    val logContext: DomainContext

    fun logPriority(
        logContext: DomainContext,
        level: LogLevel,
        tag: String,
        message: String,
        throwable: Throwable?
    )
}
