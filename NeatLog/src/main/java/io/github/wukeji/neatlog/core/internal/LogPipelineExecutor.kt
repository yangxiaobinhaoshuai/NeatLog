package io.github.wukeji.neatlog.core.internal

import io.github.wukeji.neatlog.core.LogActionExecutor
import io.github.wukeji.neatlog.core.NeatLogLevel
import io.github.wukeji.neatlog.domain.DomainContext
import io.github.wukeji.neatlog.domain.DomainElement
import io.github.wukeji.neatlog.domain.Key
import io.github.wukeji.neatlog.elements.EnableElement
import io.github.wukeji.neatlog.elements.InterceptorElement
import io.github.wukeji.neatlog.elements.LogLevelThresholdElement
import io.github.wukeji.neatlog.elements.LogPrinterElement

object LogPipelineExecutor : LogActionExecutor {

    private val defaultElements: DomainContext by lazy {
        listOf<DomainContext>(
            EnableElement(true),
            LogLevelThresholdElement(NeatLogLevel.VERBOSE),
            LogPrinterElement(SystemOutLogPrinter()),
        ).reduce { acc, e -> acc + e }
    }

    override val logContext: DomainContext get() = defaultElements

    override fun performLog(
        logContext: DomainContext,
        level: NeatLogLevel,
        tag: String,
        message: String,
        throwable: Throwable?
    ) {
        try {
            performLogInner(logContext, level, tag, message, throwable)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    /**
     * Execution pipeline.
     */
    private fun performLogInner(
        logContext: DomainContext,
        level: NeatLogLevel,
        tag: String,
        message: String,
        throwable: Throwable?
    ) {

        /**
         * Check whether working element has substitutes.
         */
        fun <E : DomainElement> Key<E>.resolveElement(): E? {

            val element: E = logContext[this] ?: return null
            val interceptorWrapper: InterceptorElement =
                logContext[InterceptorElement] ?: return element
            val interceptor = interceptorWrapper.interceptor
            val maybeIntercepted: DomainElement =
                if (interceptor.shouldIntercept(element)) interceptor.intercept(element)
                else element

            @Suppress("UNCHECKED_CAST")
            return maybeIntercepted as? E
        }

        val enable: Boolean? = EnableElement.resolveElement()?.enable
        if (enable == false) return

        val minLevel: NeatLogLevel? = LogLevelThresholdElement.resolveElement()?.level

        /**
         * NB. Only do logging when cur level >= minLevel
         */
        if (minLevel == null || level >= minLevel) {

            LogPrinterElement.resolveElement()
                ?.neatLogPrinter
                ?.print(
                    level,
                    tag,
                    message,
                    throwable
                )
        }
    }


}
