package com.igor.tv_series.data.infra.http

import com.igor.tv_series.data.models.EpisodeDto
import com.igor.tv_series.data.models.SearchSerieDto
import com.igor.tv_series.data.models.SeasonDto
import com.igor.tv_series.data.models.SerieDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface SeriesService {
    @GET("shows")
    suspend fun shows(
        @Query("page") page: Int
    ) : List<SerieDto>

    @GET("search/shows")
    suspend fun searchShows(
        @Query("q") query: String
    ) : List<SearchSerieDto>

    @GET("seasons/{seasonId}/episodes")
    suspend fun episodesBySeason(
        @Path("seasonId") seasonId: Int
    ) : List<EpisodeDto>

    @GET("shows/{serieId}/seasons")
    suspend fun seasons(
        @Path("serieId") serieId: Int
    ) : List<SeasonDto>
}