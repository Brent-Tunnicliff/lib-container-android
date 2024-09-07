// Copyright © 2024 Brent Tunnicliff <brent@tunnicliff.dev>

package dev.tunnicliff.container.demo

import dev.tunnicliff.container.Container

class AppContainer : Container() {
    fun exampleService(): ExampleService = resolveWeak {
        ExampleService()
    }
}