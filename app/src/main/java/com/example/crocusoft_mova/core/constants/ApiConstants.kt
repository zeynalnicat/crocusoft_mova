package com.example.crocusoft_mova.core.constants

object ApiConstants {

    const val BASE_URL = "https://api.themoviedb.org/3"

    const val DISCOVER = "$BASE_URL/discover/movie"

    const val DISCOVER_TV = "$BASE_URL/discover/tv"

    const val TRENDING = "$BASE_URL/trending/movie/day"

    const val SEARCH = "$BASE_URL/search/movie"

    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
    
    const val POSTER_SIZE_W500 = "w500"


    fun getPosterUrl(path: String?): String {
        if (path.isNullOrEmpty()) return ""
        return "$IMAGE_BASE_URL$POSTER_SIZE_W500$path"
    }
}
