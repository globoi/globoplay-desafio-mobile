package br.com.nerdrapido.themoviedbapp.data.model.recommendation

/**
 * Created By FELIPE GUSBERTI @ 14/03/2020
 */
data class RecommendationRequest(
    val movieId: Int,
    val language: String?,
    val page: Int?
)