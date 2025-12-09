package io.github.wukeji.neatlog.core.internal

import io.github.wukeji.neatlog.core.AbsLogger
import io.github.wukeji.neatlog.core.LogFacade
import io.github.wukeji.neatlog.domain.DomainContext
import io.github.wukeji.neatlog.domain.EmptyDomainContext

internal class LoggerImpl(
    private val extraCtx: DomainContext = EmptyDomainContext
) : AbsLogger() {

    override val logContext: DomainContext get() = super.logContext + extraCtx

    override fun clone(newLogContext: DomainContext?): LogFacade {
        val merged = extraCtx + (newLogContext ?: EmptyDomainContext)
        return LoggerImpl(merged)
    }

    override fun dumpContext(): String = logContext.dump()
}
