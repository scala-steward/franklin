package com.azavea.franklin.api.commands

import com.monovore.decline.Opts
import cats.implicits._

object Options {

  private val databasePort = Opts
    .option[Int]("db-port", help = "Port to connect to database on")
    .withDefault(5432)

  private val databaseHost = Opts
    .option[String]("db-host", help = "Database host to connect to")
    .withDefault("database.service.internal")

  private val databaseName = Opts
    .option[String]("db-name", help = "Database name to connect to")
    .withDefault("franklin")

  private val databasePassword = Opts
    .option[String]("db-password", help = "Database password to use")
    .withDefault("franklin")

  private val databaseUser = Opts
    .option[String]("db-user", help = "User to connect with database with")
    .withDefault("franklin")

  val databaseConfig: Opts[DatabaseConfig] = (
    databaseUser,
    databasePassword,
    databaseHost,
    databasePort,
    databaseName
  ) mapN DatabaseConfig

  val catalogRoot: Opts[String] = Opts
    .option[String]("catalog-root", "Root of STAC catalog to import")
}
