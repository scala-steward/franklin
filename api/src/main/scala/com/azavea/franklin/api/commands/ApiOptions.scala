package com.azavea.franklin.api.commands

import cats.implicits._
import com.monovore.decline.Opts

trait ApiOptions {

  private val externalPort = Opts
    .option[Int]("external-port", help = "Port users/clients hit for requests")
    .withDefault(9090)

  private val internalPort = Opts
    .option[Int](
      "internal-port",
      help =
        "Port server listens on, this will be different from 'external-port' when service is started behind a proxy"
    )
    .withDefault(9090)

  private val apiHost = Opts
    .option[String]("api-host", help = "Hostname Franklin is hosted it")
    .withDefault("http://localhost:9090")

  private val apiScheme =
    Opts
      .option[String]("api-scheme", "Scheme server is exposed to end users with")
      .withDefault("http")
      .validate("Scheme must be either 'http' or 'https'")(s => (s == "http" || s == "https"))

  val apiConfig: Opts[ApiConfig] = (externalPort, internalPort, apiHost, apiScheme) mapN ApiConfig
}
