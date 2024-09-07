// Copyright © 2024 Brent Tunnicliff <brent@tunnicliff.dev>

package dev.tunnicliff.container

import java.lang.ref.WeakReference

abstract class Container {
    protected val singletonObjectReferences: MutableMap<String, Any> = mutableMapOf()
    protected val weakObjectReferences: MutableMap<String, WeakReference<Any>> = mutableMapOf()

    protected inline fun <reified Object : Any> resolveSingleton(
        name: String = "",
        createObject: () -> Object
    ): Object {
        val key = "${Object::class}-$name"
        val cachedObject = singletonObjectReferences[key]

        if (cachedObject != null && cachedObject is Object) {
            return cachedObject
        }

        return createObject().also {
            singletonObjectReferences[key] = it
        }
    }


    protected inline fun <reified Object : Any> resolveWeak(
        name: String = "",
        createObject: () -> Object
    ): Object {
        val key = "${Object::class}-$name"
        val cachedObject = weakObjectReferences[key]?.get()

        if (cachedObject != null && cachedObject is Object) {
            return cachedObject
        }

        return createObject().also {
            weakObjectReferences[key] = WeakReference(it)
        }
    }
}