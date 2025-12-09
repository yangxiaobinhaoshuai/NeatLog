package io.github.wukeji.neatlog.api


import io.github.wukeji.neatlog.core.DomainElementInterceptor
import io.github.wukeji.neatlog.core.LogFacade
import io.github.wukeji.neatlog.core.NeatLogLevel
import io.github.wukeji.neatlog.core.NeatLogPrinter
import io.github.wukeji.neatlog.core.internal.LoggerImpl
import io.github.wukeji.neatlog.domain.DomainContext
import io.github.wukeji.neatlog.domain.EmptyDomainContext
import io.github.wukeji.neatlog.elements.EnableElement
import io.github.wukeji.neatlog.elements.InterceptorElement
import io.github.wukeji.neatlog.elements.LogLevelThresholdElement
import io.github.wukeji.neatlog.elements.LogPrinterElement
import io.github.wukeji.neatlog.interceptors.MessagePrefixInterceptor
import io.github.wukeji.neatlog.interceptors.MessageSuffixInterceptor
import io.github.wukeji.neatlog.interceptors.TagPrefixInterceptor
import io.github.wukeji.neatlog.interceptors.TagSuffixInterceptor

class NeatLoggerConfig {
    var enabled: Boolean = true
    var minLevel: NeatLogLevel = NeatLogLevel.DEBUG
    var tagPrefix: String? = null
    var tagSuffix: String? = null
    var messagePrefix: String? = null
    var messageSuffix: String? = null

    var printer: NeatLogPrinter? = null

    fun buildContext(): DomainContext {
        var ctx: DomainContext = EmptyDomainContext

        // 基础元素
        ctx += EnableElement(enabled)
        ctx += LogLevelThresholdElement(minLevel)
        printer?.let { ctx += LogPrinterElement(it) }

        // 拦截器链（可为空）
        var interceptor: DomainElementInterceptor? = null

        fun add(i: DomainElementInterceptor) {
            interceptor = interceptor?.plus(i) ?: i
        }

        tagPrefix?.let { add(TagPrefixInterceptor(it)) }
        tagSuffix?.let { add(TagSuffixInterceptor(it)) }
        messagePrefix?.let { add(MessagePrefixInterceptor(it)) }
        messageSuffix?.let { add(MessageSuffixInterceptor(it)) }

        if (interceptor != null) {
            ctx += InterceptorElement(interceptor)
        }

        return ctx
    }
}


object NeatLogger : LogFacade {
    @Volatile
    private var delegate: LogFacade = RawLogger

    override fun v(tag: String, message: String) = delegate.v(tag, message)

    override fun i(tag: String, message: String) = delegate.i(tag, message)

    override fun d(tag: String, message: String) = delegate.d(tag, message)

    override fun e(tag: String, message: String) = delegate.e(tag, message)

    override fun e(tag: String, message: String, t: Throwable) = delegate.e(tag, message, t)

    override fun clone(newLogContext: DomainContext?): LogFacade = delegate.clone(newLogContext)

    override fun dumpContext(): String = delegate.dumpContext()

    fun init(configure: NeatLoggerConfig.() -> Unit) {
        val cfg = NeatLoggerConfig().apply(configure)
        val ctx: DomainContext = cfg.buildContext()
        delegate = LoggerImpl(ctx)
    }

    private const val MAX_TAG_LEN = 23

    private const val DEFAULT_LOG_TAG = "NeatLogger"

    private fun autoTag(): String {
        val stack = Throwable().stackTrace
        val frame = stack.firstOrNull { it.className != NeatLogger::class.java.name }
        return frame?.className?.substringAfterLast('.')?.take(MAX_TAG_LEN) ?: DEFAULT_LOG_TAG
    }

    fun d(message: String) = d(autoTag(), message)
    fun i(message: String) = i(autoTag(), message)
    fun e(message: String) = e(autoTag(), message)

    /**
     * Scoped logger
     */
    fun tag(tag: String): LogFacade = delegate.clone(InterceptorElement(TagPrefixInterceptor(tag)))
}


fun NeatLogger.taggedMixin(tag: String): LogMixin = object : LogMixin {
    override val LogMixin.logTAG: String get() = tag
}