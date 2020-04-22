package com.bigbrains.knowaboutmovies.models.responses

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("page") var page: Float,
    @SerializedName("total_results") var total_results: Float,
    @SerializedName("total_pages") var total_pages: Float,
    @SerializedName("results") var results: List<Results>
)

data class Results(
    @SerializedName("popularity") var popularity: Double,
    @SerializedName("vote_count") var vote_count: Float,
    @SerializedName("video") var video: Boolean,
    @SerializedName("poster_path") var poster_path: String,
    @SerializedName("id") var id: Float,
    @SerializedName("adult") var adult: Boolean,
    @SerializedName("backdrop_path") var backdrop_path: String,
    @SerializedName("original_language") var original_language: String,
    @SerializedName("original_title") var original_title: String,
    @SerializedName("genre_ids") var genre_ids: List<Float>,
    @SerializedName("title") var title: String,
    @SerializedName("vote_average") var vote_average: Float,
    @SerializedName("overview") var overview: String,
    @SerializedName("release_date") var release_date: String
)