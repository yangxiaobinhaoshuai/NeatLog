# NeatLog

A lightweight logging library for Kotlin/Android with scoped loggers, simple configuration, and an interceptor-based design.

## Design Philosophy

1.	Loggers are scope-aware.
Each scope can have its own logger with customized configuration, without affecting global behavior.
2.	Scoped logging is effortless.
Simply implement a mixin interface, and your class automatically gains structured, scoped logging capabilities—no manual setup or tag handling required.


## Features

- Easy global configuration
- Automatic tag detection
- Scoped loggers (`tag("...")`)
- Message / tag prefix & suffix interceptors
- Pluggable printers
- Inspired by `CoroutineContext` (but simple to use)

## Installation

Add JitPack:

```gradle
// settings.gradle
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}
```

Add dependency:

```gradle
implementation("com.github.yangxiaobinhaoshuai:NeatLog:0.0.1")
```

## Initialize

Call once (e.g., in Application):

```kotlin
NeatLogger.init {
    minLevel = NeatLogLevel.DEBUG
    tagPrefix = "[APP] "
}
```

## Basic Usage

Automatic tag:

```kotlin
NeatLogger.d("hello world")
NeatLogger.e("something went wrong")
```

Scoped logger:

```kotlin
val apiLog = NeatLogger.tag("[API]")
apiLog.d("request started")
```

## Using LogMixin

```kotlin
class Foo : LogMixin {
    fun work() {
        logD("hello")   // tag = "Foo"
    }
}
```

## JitPack Page

https://jitpack.io/#yangxiaobinhaoshuai/NeatLog