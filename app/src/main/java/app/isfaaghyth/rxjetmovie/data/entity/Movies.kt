package app.isfaaghyth.rxjetmovie.data.entity

import android.os.Parcelable
import app.isfaaghyth.rxjetmovie.BuildConfig
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Movies(
    @Expose @SerializedName("results") val results: List<Movie> = listOf()
) {
    @Parcelize data class Movie(
        @Expose @SerializedName("id") val id: String = "",
        @Expose @SerializedName("movie_id") val movieId: String = "",
        @Expose @SerializedName("original_title") val title: String = "",
        @Expose @SerializedName("poster_path") val posterPath: String = "",
        @Expose @SerializedName("overview") val overview: String = "",
        @Expose @SerializedName("backdrop_path") val backdropPath: String = "",
        @Expose @SerializedName("vote_count") val voteCount: Int = 0,
        @Expose @SerializedName("vote_average") val voteAverage: Float = 0f,
        @Expose @SerializedName("release_date") val releaseDate: String = ""
    ): Parcelable {
        fun bannerUrl() = "${BuildConfig.IMAGE_URL}$backdropPath"
        fun posterUrl() = "${BuildConfig.IMAGE_URL}$posterPath"
    }
}