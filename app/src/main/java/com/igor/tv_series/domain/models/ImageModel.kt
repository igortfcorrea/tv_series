package com.igor.tv_series.domain.models

import com.igor.tv_series.data.models.ImageDto

data class ImageModel(
    val medium: String?,
    val original: String?
)

fun ImageDto.toModel(): ImageModel {
    return ImageModel(
        medium = this.medium,
        original = this.original
    )
}