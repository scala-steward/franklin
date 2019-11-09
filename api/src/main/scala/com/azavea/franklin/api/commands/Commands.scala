package com.azavea.franklin.api.commands

import cats.effect.{ContextShift, ExitCode, IO}
import com.azavea.franklin.crawler.StacImport
import com.monovore.decline.{Command, Opts}
import doobie.util.transactor.Transactor
import org.flywaydb.core.Flyway
import doobie.implicits._
import cats.implicits._

object Commands {

  final case class RunMigrations(databaseConfig: DatabaseConfig)

  final case class RunServer(port: Int, dbConfig: DatabaseConfig)

  final case class RunImport(catalogRoot: String, config: DatabaseConfig)

  private val runImportOpts: Opts[RunImport] = Opts.subcommand("import", "Import a STAC catalog") {
    (Options.catalogRoot, Options.databaseConfig).mapN(RunImport)
  }

  private val runMigrationsOpts: Opts[RunMigrations] =
    Opts.subcommand("migrate", "Runs migrations against database") {
      Options.databaseConfig map RunMigrations
    }

  private val serverPort = Opts
    .option[Int]("port", help = "Port to start web service on")
    .withDefault(9090)

  private val runServerOpts: Opts[RunServer] =
    Opts.subcommand("server", "Runs web service") {
      (serverPort, Options.databaseConfig) mapN RunServer
    }

  def runMigrations(dbConfig: DatabaseConfig): IO[ExitCode] = IO {
    Flyway
      .configure()
      .dataSource(
        s"${dbConfig.jdbcUrl}",
        dbConfig.dbUser,
        dbConfig.dbPass
      )
      .locations("classpath:migrations/")
      .load()
      .migrate()
    ExitCode.Success
  }

  def runImport(stacCatalog: String, config: DatabaseConfig)(
      implicit contextShift: ContextShift[IO]
  ): fs2.Stream[IO, Unit] = {
    val xa =
      Transactor.fromDriverManager[IO](config.driver, config.jdbcUrl, config.dbUser, config.dbPass)
    new StacImport(stacCatalog).run().transact(xa)
  }

  val applicationCommand: Command[Product] =
    Command("", "Your Friendly Neighborhood OGC API - Features and STAC Web Service") {
      runServerOpts orElse runMigrationsOpts orElse runImportOpts
    }

}
