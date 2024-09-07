// Copyright © 2024 Brent Tunnicliff <brent@tunnicliff.dev>

package dev.tunnicliff.container

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.UUID

class ContainerTest {
    private lateinit var container: SimpleContainer

    @Before
    fun beforeEach() {
        container = SimpleContainer()
    }

    @Test
    fun resoleSingleton_sharesInstance() {
        val firstResolve = container.getSingleton()
        val secondResolve = container.getSingleton()

        assertEquals(firstResolve, secondResolve)
    }

    @Test
    fun resoleWeak_sharesInstance() {
        val firstResolve = container.getWeak()
        val secondResolve = container.getWeak()

        assertEquals(firstResolve, secondResolve)
    }
}

// Since we are testing functionality of an abstract class we first need a class to conform to it.
private class SimpleContainer : Container() {
    fun getSingleton(): ResolverClass = resolveSingleton {
        ResolverClass()
    }

    fun getWeak(): ResolverClass = resolveWeak {
        ResolverClass()
    }
}

private class ResolverClass {
    val id: UUID = UUID.randomUUID()
}