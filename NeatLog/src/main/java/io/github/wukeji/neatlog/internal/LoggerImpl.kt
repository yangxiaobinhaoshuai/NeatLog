package io.github.wukeji.neatlog.internal

import io.github.wukeji.neatlog.core.AbsLogger
import io.github.wukeji.neatlog.core.LogFacade
import io.github.wukeji.neatlog.domain.DomainContext
import io.github.wukeji.neatlog.domain.EmptyDomainContext

internal class LoggerImpl(private val newLogContext: DomainContext? = null) : AbsLogger() {

    private val combinedContext by lazy {
        if (newLogContext != null) super.logContext + newLogContext
        else super.logContext
    }

    override val logContext: DomainContext get() = combinedContext

    override fun clone(newLogContext: DomainContext?): LogFacade {
        val newContext = logContext + (newLogContext ?: EmptyDomainContext)
        return LoggerImpl(newContext)
    }

    override fun dumpContext(): String = logContext.dump()
}
