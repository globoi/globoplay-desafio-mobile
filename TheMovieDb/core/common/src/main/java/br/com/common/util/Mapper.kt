package br.com.common.util

interface Mapper<F, T> {
    suspend fun map(from: F) : T
}