// Versions
object Versions {
  val AsyncHttpClientVersion = "2.12.3"
  val AWSVersion             = "1.11.751"
// There were issues with accessing S3 from the Fargate ECS instance
// The easiest fix for now is to rollback dep to the last and the most tested version,
// which is the same as that in the GeoTrellis
// https://github.com/locationtech/geotrellis/blob/v3.6.0/project/Dependencies.scala#L86
  val AWSSdk2Version         = "2.18.12"
  val CatsEffectVersion      = "2.5.4"
  val CatsScalacheckVersion  = "0.3.1"
  val CatsVersion            = "2.7.0"
  val CirceFs2Version        = "0.14.1"
  val CirceJsonSchemaVersion = "0.2.0"
  val CirceVersion           = "0.14.1"
  val DeclineVersion         = "2.2.0"
  val DisciplineScalatest    = "2.1.5"
  val DoobieVersion          = "0.13.4"
  val EmojiVersion           = "1.2.3"
  val Fs2Version             = "2.5.11"
  val GeoTrellisVersion      = "3.6.3"
  val GuavaVersion           = "31.1-jre"
  val HikariVersion          = "4.0.3"
  val Http4sVersion          = "0.21.33"
  val JtsVersion             = "1.18.1"
  val LogbackVersion         = "1.2.5"
  val Log4CatsVersion        = "1.1.1"
  val MagnoliaVersion        = "0.17.0"
  val MonocleVersion         = "2.1.0"
  val OsLib                  = "0.8.1"
  val Postgis                = "2.5.1"
  val PureConfig             = "0.12.1"
  val Refined                = "0.9.29"
  val ScalacheckVersion      = "1.16.0"
  val ScapegoatVersion       = "1.4.11"
  val ShapelessVersion       = "2.3.9"
  val Slf4jVersion           = "2.0.3"
  val Specs2Version          = "4.18.0"
  val Stac4SVersion          = "0.8.1"
  val SttpClientVersion      = "2.3.0"
  val SttpShared             = "1.3.11"
  val SttpModelVersion       = "1.4.26"
  val TapirVersion           = "0.17.20"
  val TapirOpenAPIVersion    = "0.17.20"
  val ThreeTenExtra          = "1.7.0"
  val TypenameVersion        = "1.0.0"
}
