package com.vicky.apps.datapoints.ui.model

data class ResponseData(
    val help: String,
    val result: Result,
    val success: Boolean
)

data class Result(
    val _links: Links,
    val fields: List<Field>,
    val limit: Int,
    val records: List<Record>,
    val resource_id: String,
    val total: Int
)

data class Field(
    val id: String,
    val type: String
)

data class Record(
    val _id: Int,
    val quarter: String,
    val volume_of_mobile_data: String
)

data class Links(
    val next: String,
    val start: String
)