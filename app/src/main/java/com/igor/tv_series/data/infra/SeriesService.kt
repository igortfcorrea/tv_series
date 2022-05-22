package com.igor.tv_series.data.infra

import com.igor.tv_series.data.models.EpisodeDto
import com.igor.tv_series.data.models.SerieDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface SeriesService {
    @GET("shows")
    suspend fun shows(
        @Query("page") page: Int
    ) : List<SerieDto>

    @GET("shows/{serieId}/episodes")
    suspend fun episodes(
        @Path("serieId") serieId: Int
    ) : List<EpisodeDto>
}