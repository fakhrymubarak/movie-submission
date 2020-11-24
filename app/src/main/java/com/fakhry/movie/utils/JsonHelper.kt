package com.fakhry.movie.utils

import android.content.Context
import com.fakhry.movie.data.source.remote.response.MovieAndTvShowResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadPopularMovies(): List<MovieAndTvShowResponse> {
        val listMovie = ArrayList<MovieAndTvShowResponse>()
        try {

            val result = parsingFileToString("PopularMovies.json").toString()
            val responseObject = JSONObject(result)
            val items = responseObject.getJSONArray("results")

            for (i in 0 until items.length()) {
                val item = items.getJSONObject(i)

                val id = item.getInt("id")
                val title = item.getString("title")
                val synopsis = item.getString("overview")
                val posterUrl =
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2${item.getString("poster_path")}"
                val backdropUrl =
                    "https://image.tmdb.org/t/p/w500_and_h282_face${item.getString("backdrop_path")}"
                val rating = item.getDouble("vote_average")

                val movieResponse =
                    MovieAndTvShowResponse(id, title, synopsis, posterUrl, backdropUrl, rating)
                listMovie.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return listMovie
    }

    fun loadPopularTvShows(): List<MovieAndTvShowResponse> {
        val listTvShow = ArrayList<MovieAndTvShowResponse>()
        try {

            val result = parsingFileToString("PopularTvShow.json").toString()
            val responseObject = JSONObject(result)
            val items = responseObject.getJSONArray("results")

            for (i in 0 until items.length()) {
                val item = items.getJSONObject(i)

                val id = item.getInt("id")
                val title = item.getString("name")
                val synopsis = item.getString("overview")
                val posterUrl =
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2${item.getString("poster_path")}"
                val backdropUrl =
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2${item.getString("backdrop_path")}"
                val rating = item.getDouble("vote_average")

                val tvShowResponse =
                    MovieAndTvShowResponse(id, title, synopsis, posterUrl, backdropUrl, rating)
                listTvShow.add(tvShowResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return listTvShow
    }

    fun loadPopularMovieDetails(pmId: Int): MovieAndTvShowResponse {
        lateinit var movieResponse: MovieAndTvShowResponse
        try {

            val result = parsingFileToString("PopularMovies.json").toString()
            val responseObject = JSONObject(result)
            val items = responseObject.getJSONArray("results")

            for (i in 0 until items.length()) {
                val item = items.getJSONObject(i)

                val id = item.getInt("id")
                if (pmId == id) {

                    val title = item.getString("title")
                    val synopsis = item.getString("overview")
                    val posterUrl =
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2${item.getString("poster_path")}"
                    val backdropUrl =
                        "https://image.tmdb.org/t/p/w500_and_h282_face${item.getString("backdrop_path")}"
                    val rating = item.getDouble("vote_average")

                    movieResponse = MovieAndTvShowResponse(
                        id,
                        title,
                        synopsis,
                        posterUrl,
                        backdropUrl,
                        rating)


                    break
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return movieResponse
    }

    fun loadPopularTvShowDetails(pmId: Int): MovieAndTvShowResponse {
        lateinit var tvShow: MovieAndTvShowResponse
        try {

            val result = parsingFileToString("PopularTvShow.json").toString()
            val responseObject = JSONObject(result)
            val items = responseObject.getJSONArray("results")

            for (i in 0 until items.length()) {
                val item = items.getJSONObject(i)

                val id = item.getInt("id")
                if (pmId == id) {
                    val title = item.getString("name")
                    val synopsis = item.getString("overview")
                    val posterUrl =
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2${item.getString("poster_path")}"
                    val backdropUrl =
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2${item.getString("backdrop_path")}"
                    val rating = item.getDouble("vote_average")

                    tvShow = MovieAndTvShowResponse(
                        id,
                        title,
                        synopsis,
                        posterUrl,
                        backdropUrl,
                        rating)
                    break
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return tvShow
    }
}