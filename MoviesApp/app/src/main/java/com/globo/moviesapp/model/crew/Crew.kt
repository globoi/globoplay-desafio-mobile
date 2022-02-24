package com.globo.moviesapp.model.crew

import java.io.Serializable

data class Crew(
    var name: String,
    var original_name: String,
    var department: String
): Serializable