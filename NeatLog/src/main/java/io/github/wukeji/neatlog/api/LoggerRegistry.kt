package io.github.wukeji.neatlog.api

import io.github.wukeji.neatlog.core.LogFacade
import java.util.Collections
import java.util.WeakHashMap

/**
 * Keep field singleton in interface
 */
internal object LoggerRegistry {

    private val cache: MutableMap<Any, LogFacade> = Collections.synchronizedMap(WeakHashMap())

    fun getOrCreate(owner: Any, factory: () -> LogFacade): LogFacade {
        val existing: LogFacade? = cache[owner]
        if (existing != null) return existing

        // double-checked，避免多线程下重复创建
        val created = factory()
        cache[owner] = created
        return created
    }
}