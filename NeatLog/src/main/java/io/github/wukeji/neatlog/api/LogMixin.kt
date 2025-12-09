package io.github.wukeji.neatlog.api

import io.github.wukeji.neatlog.core.LogFacade
import io.github.wukeji.neatlog.core.NeatLogLevel
import io.github.wukeji.neatlog.elements.InterceptorElement
import io.github.wukeji.neatlog.interceptors.MessagePrefixInterceptor

val Any?.neatName: String get() = this?.javaClass?.simpleName ?: "NULL"
private const val MAX_LOG_TAG_LENGTH = 23

public typealias LogFun = (message: String) -> Unit
public typealias LogFunWithException = (message: String, th: Throwable) -> Unit

interface LogMixin {

    open val LogMixin.logTAG: String get() = this.neatName.take(MAX_LOG_TAG_LENGTH)

    /**
     * Override it if u need.
     */
    val neatLogger: LogFacade
        get() = LoggerRegistry.getOrCreate(this) {
            val messagePrefix = "${this.neatName}: ${this.hashCode()}: "
            val prefix = MessagePrefixInterceptor(messagePrefix)
            AndroidUtilLogger.clone(InterceptorElement(prefix))
        }

    val logI: LogFun get() = neatLogger.log(NeatLogLevel.INFO, logTAG)
    val logD: LogFun get() = neatLogger.log(NeatLogLevel.DEBUG, logTAG)
    val logE: LogFun get() = neatLogger.log(NeatLogLevel.ERROR, logTAG)
    val logEt: LogFunWithException get() = { message, th -> neatLogger.e(logTAG, message, th) }

}