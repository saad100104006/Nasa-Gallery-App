package com.nasa.gallery.util

/**
 *
 * Kotlin sealed class for state management
 */

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    class Error(val error: Throwable) : Result<Nothing>()
}