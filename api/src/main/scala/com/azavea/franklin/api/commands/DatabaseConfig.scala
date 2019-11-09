package com.azavea.franklin.api.commands

final case class DatabaseConfig(
    dbUser: String,
    dbPass: String,
    dbHost: String,
    dbPort: Int,
    dbName: String
) {
  val jdbcUrl = s"jdbc:postgresql://$dbHost:$dbPort/$dbName"
  val driver  = "org.postgresql.Driver"
}
