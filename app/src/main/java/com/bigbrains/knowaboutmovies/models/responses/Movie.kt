package com.bigbrains.knowaboutmovies.models.responses

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("adult") var adult: Boolean,
    @SerializedName("backdrop_path") var backdrop_path: String,
    @SerializedName("beFloats_to_collection") var beFloats_to_collection: String,
    @SerializedName("budget") var budget: Float,
    @SerializedName("genres") var genres: List<Genres>,
    @SerializedName("homepage") var homepage: String,
    @SerializedName("id") var id: Float,
    @SerializedName("imdb_id") var imdb_id: String,
    @SerializedName("original_language") var original_language: String,
    @SerializedName("original_title") var original_title: String,
    @SerializedName("overview") var overview: String,
    @SerializedName("popularity") var popularity: Double,
    @SerializedName("poster_path") var poster_path: String,
    @SerializedName("production_companies") var production_companies: List<ProductionCompanies>,
    @SerializedName("production_countries") var production_countries: List<ProductionCountries>,
    @SerializedName("release_date") var release_date: String,
    @SerializedName("revenue") var revenue: Float,
    @SerializedName("runtime") var runtime: Float,
    @SerializedName("spoken_languages") var spoken_languages: List<SpokenLanguages>,
    @SerializedName("status") var status: String,
    @SerializedName("tagline") var tagline: String,
    @SerializedName("title") var title: String,
    @SerializedName("video") var video: Boolean,
    @SerializedName("vote_average") var vote_average: Float,
    @SerializedName("vote_count") var vote_count: Float
)

data class ProductionCompanies(
    @SerializedName("id") var id: Float,
    @SerializedName("logo_path") var logo_path: String,
    @SerializedName("name") var name: String,
    @SerializedName("origin_country") var origin_country: String
)

data class ProductionCountries(
    @SerializedName("iso_3166_1") var iso_3166_1: String,
    @SerializedName("name") var name: String
)

data class SpokenLanguages(
    @SerializedName("iso_639_1") var iso_639_1: String,
    @SerializedName("name") var name: String
)

data class Genres(
    @SerializedName("id") var id: Float,
    @SerializedName("name") var name: String
)