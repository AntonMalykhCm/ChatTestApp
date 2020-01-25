package com.example.dependencyholder

import java.lang.IllegalStateException

object DependencyHolder {

    private val dependencies = mutableMapOf<String, Any>()

    fun put(key: String, dependency: Any) {
        dependencies[key] = dependency
    }

    fun get(key: String): Any {
        return dependencies[key]
            ?: throw IllegalStateException("put the dependency for the key first")
    }

    fun release(key: String) {
        dependencies.remove(key)
    }

    fun contains(key: String): Boolean {
        return dependencies.containsKey(key)
    }

}