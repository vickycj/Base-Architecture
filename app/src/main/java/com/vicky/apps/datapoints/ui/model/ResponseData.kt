package com.vicky.apps.datapoints.ui.model

data class ResponseData(
    val _id: String,
    val about: String,
    val company: String,
    val logo: String,
    val members: List<Member>,
    val website: String
)