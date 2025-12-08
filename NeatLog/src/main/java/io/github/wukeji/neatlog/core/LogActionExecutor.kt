package io.github.wukeji.neatlog.core

import io.github.wukeji.neatlog.domain.DomainContext

interface LogActionExecutor {

    val logContext: DomainContext

    fun performLog(
        logContext: DomainContext,
        level: NeatLogLevel,
        tag: String,
        message: String,
        throwable: Throwable? = null,
    )

}
