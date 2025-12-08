package io.github.wukeji.neatlog

import io.github.wukeji.neatlog.RawLogger.clone
import io.github.wukeji.neatlog.core.LogFacade
import io.github.wukeji.neatlog.core.LogLevel
import io.github.wukeji.neatlog.domain.DomainContext
import io.github.wukeji.neatlog.domain.EmptyDomainContext
import io.github.wukeji.neatlog.elements.EnableLogElement
import io.github.wukeji.neatlog.elements.GlobalTagPrefixLogElement
import io.github.wukeji.neatlog.elements.GlobalTagSuffixLogElement
import io.github.wukeji.neatlog.elements.LogLevelLogElement
import io.github.wukeji.neatlog.elements.LogPrinterLogElement
import io.github.wukeji.neatlog.internal.LoggerImpl
import io.github.wukeji.neatlog.uitlity.LogPrinter

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
    globalTagPrefix: String? = null,
    globalTagSuffix: String? = null,
    logLevel: LogLevel? = null,
    printer: LogPrinter? = null,
    newLogContext: DomainContext? = null
): LogFacade {

    var mergedContext: DomainContext = EmptyDomainContext

    if (enable != null) mergedContext += EnableLogElement(enable)
    if (logLevel != null) mergedContext += LogLevelLogElement(logLevel)
    if (globalTagPrefix != null) mergedContext += GlobalTagPrefixLogElement(globalTagPrefix)
    if (globalTagSuffix != null) mergedContext += GlobalTagSuffixLogElement(globalTagSuffix)
    if (printer != null) mergedContext += LogPrinterLogElement(printer)
    if (newLogContext != null) mergedContext += newLogContext

    return this.clone(mergedContext)
}

/**
 * Function currying.
 * e.g.
 *    val logI = LogFacade.clone().log(LogLevel.INFO,TAG)
 *    logI( your log message)
 */
fun LogFacade.log(level: LogLevel, tag: String) = fun(message: String) =
    when (level) {
        LogLevel.VERBOSE -> this.v(tag, message)
        LogLevel.INFO -> this.i(tag, message)
        LogLevel.DEBUG -> this.d(tag, message)
        LogLevel.ERROR -> this.e(tag, message)
    }


