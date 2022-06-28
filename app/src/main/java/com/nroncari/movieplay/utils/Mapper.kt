package com.nroncari.movieplay.utils

interface Mapper<S, T> {
    fun map(source: S): T
}