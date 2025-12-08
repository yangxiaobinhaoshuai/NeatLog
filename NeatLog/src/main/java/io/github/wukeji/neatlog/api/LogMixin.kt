package io.github.wukeji.neatlog.api

import io.github.wukeji.neatlog.core.LogFacade
import io.github.wukeji.neatlog.core.NeatLogLevel
import io.github.wukeji.neatlog.elements.InterceptorElement
import io.github.wukeji.neatlog.interceptors.MessagePrefixInterceptor
import io.github.wukeji.neatlog.interceptors.MessageSuffixInterceptor
import io.github.wukeji.neatlog.log

val Any?.neatName: String get() = this?.javaClass?.simpleName ?: "NULL"
private const val MAX_LOG_TAG_LENGTH = 23


public typealias LogFun = (message: String) -> Unit
public typealias LogFunWithException = (message: String, th: Throwable) -> Unit

interface LogMixin {

    open val LogMixin.logTAG: String get() = this.neatName.take(MAX_LOG_TAG_LENGTH)

    val logger: LogFacade
        get() {
            val messagePrefix = "${this.neatName}: ${this.hashCode()}: "
            val prefix = MessagePrefixInterceptor(messagePrefix)
            val messageSuffix = "."
            val suffix = MessageSuffixInterceptor(messageSuffix)
            return AndroidUtilLogger.clone(InterceptorElement(prefix + suffix))
        }

    val logI: LogFun get() = logger.log(NeatLogLevel.INFO, logTAG)
    val logD: LogFun get() = logger.log(NeatLogLevel.DEBUG, logTAG)
    val logE: LogFun get() = logger.log(NeatLogLevel.ERROR, logTAG)
    val logEt: LogFunWithException get() = { message, th -> logger.e(logTAG, message, th) }

}