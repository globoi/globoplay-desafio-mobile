package com.ftoniolo.factory.response

import com.ftoniolo.core.domain.model.WatchToo
import com.ftoniolo.core.domain.model.WatchTooPaging

class WatchTooPagingFactory {

    fun create() = WatchTooPaging(

        page = 1L,
        totalPages = 1L,
        films = listOf(
            WatchToo(
                id = 1L,
                imageUrl = "imagem-homem-aranha"
            ),
            WatchToo(
                id = 1L,
                imageUrl = "imagem-dr-estranho"
            ),

        )
    )
}