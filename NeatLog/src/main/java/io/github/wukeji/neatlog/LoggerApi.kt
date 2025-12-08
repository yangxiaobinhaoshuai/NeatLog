package io.github.wukeji.neatlog

import io.github.wukeji.neatlog.RawLogger.clone
import io.github.wukeji.neatlog.core.LogFacade
import io.github.wukeji.neatlog.core.NeatLogLevel
import io.github.wukeji.neatlog.core.NeatLogPrinter
import io.github.wukeji.neatlog.core.internal.LoggerImpl
import io.github.wukeji.neatlog.domain.DomainContext
import io.github.wukeji.neatlog.domain.EmptyDomainContext
import io.github.wukeji.neatlog.elements.EnableElement
import io.github.wukeji.neatlog.elements.LogLevelThresholdElement
import io.github.wukeji.neatlog.elements.LogPrinterElement

/**
 * Used for [clone]
 */
object RawLogger : LogFacade by LoggerImpl()

/**
 * In most cases, you should create you own logger instance by calling this for your own specific configs.
 *
 * Preserve the previous context.
 */
fun LogFacade.clone(
    enable: Boolean? = null,
    minLogLevel: NeatLogLevel? = null,
    printer: NeatLogPrinter? = null,
    newLogContext: DomainContext? = null
): LogFacade {

    var mergedContext: DomainContext = EmptyDomainContext

    if (enable != null) mergedContext += EnableElement(enable)
    if (minLogLevel != null) mergedContext += LogLevelThresholdElement(minLogLevel)
    if (printer != null) mergedContext += LogPrinterElement(printer)
    if (newLogContext != null) mergedContext += newLogContext

    return this.clone(mergedContext)
}

/**
 * Function currying.
 * e.g.
 *    val logI = LogFacade.clone().log(LogLevel.INFO,TAG)
 *    logI( your log message)
 */
fun LogFacade.log(level: NeatLogLevel, tag: String) = fun(message: String) =
    when (level) {
        NeatLogLevel.VERBOSE -> this.v(tag, message)
        NeatLogLevel.INFO -> this.i(tag, message)
        NeatLogLevel.DEBUG -> this.d(tag, message)
        NeatLogLevel.ERROR -> this.e(tag, message)
    }


