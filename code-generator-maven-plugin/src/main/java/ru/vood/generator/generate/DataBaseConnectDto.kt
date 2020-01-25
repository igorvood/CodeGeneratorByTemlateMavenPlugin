package ru.vood.generator.generate

data class DataBaseConnectDto(
        var jdbcDriver: String
        , var urlDb: String
        , var userDb: String
        , var passwordDb: String
) {
    constructor() : this("jdbcDriver", "urlDb", "userDb", "passwordDb")
}