package io.github.wukeji.neatlog.core

import io.github.wukeji.neatlog.domain.DomainContext


interface LogFacade {

    fun v(tag: String, message: String)

    fun i(tag: String, message: String)

    fun d(tag: String, message: String)

    fun e(tag: String, message: String)

    fun e(tag: String, message: String, t: Throwable)

    /**
     * Preserve previous context and copy a new one object.
     */
    fun clone(newLogContext: DomainContext? = null): LogFacade

    fun dumpContext(): String
}

enum class LogLevel { VERBOSE, INFO, DEBUG, ERROR }
