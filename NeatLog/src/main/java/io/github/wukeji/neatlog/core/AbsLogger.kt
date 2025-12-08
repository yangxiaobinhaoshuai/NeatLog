package io.github.wukeji.neatlog.core

import io.github.wukeji.neatlog.domain.DomainContext
import io.github.wukeji.neatlog.core.internal.LogPipelineExecutor

abstract class AbsLogger : LogFacade, LogActionExecutor by LogPipelineExecutor {

    private val logFunc = bindContext(logContext)

    private fun bindContext(logContext: DomainContext) =
        fun(
            level: NeatLogLevel,
            tag: String,
            message: String,
            throwable: Throwable?
        ) = performLog(
            logContext,
            level,
            tag,
            message,
            throwable
        )

    override fun v(tag: String, message: String) = logFunc(NeatLogLevel.VERBOSE, tag, message, null)

    override fun i(tag: String, message: String) = logFunc(NeatLogLevel.INFO, tag, message, null)

    override fun d(tag: String, message: String) = logFunc(NeatLogLevel.DEBUG, tag, message, null)

    override fun e(tag: String, message: String) = logFunc(NeatLogLevel.ERROR, tag, message, null)

    override fun e(tag: String, message: String, t: Throwable) = logFunc(NeatLogLevel.ERROR, tag, message, t)

}

