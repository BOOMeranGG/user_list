package com.example.demo

data class BaseOperationResult(
    var isSuccess: Boolean = true,
    var error: String = ""
) {

    constructor(error: String): this(false, error)
}