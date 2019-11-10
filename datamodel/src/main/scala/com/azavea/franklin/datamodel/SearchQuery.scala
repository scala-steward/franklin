package com.azavea.franklin.datamodel

import geotrellis.vector.Geometry
import geotrellis.server.stac._
import geotrellis.server.stac.Implicits._

import java.time.Instant

import io.circe._
import io.circe.generic.semiauto._

case class DatetimeRangeQuery(start: Option[Instant], end: Option[Instant])

object DatetimeRangeQuery {
  implicit val encoderDateTimeQuery: Encoder[DatetimeRangeQuery] = deriveEncoder
  implicit val decoderDateTimeQuery: Decoder[DatetimeRangeQuery] = deriveDecoder

}

case class SearchQuery(
    datetime: Option[DatetimeRangeQuery],
    next: Option[String],
    ids: Option[List[String]],
    collections: Option[List[String]],
    limit: Int = 10
)

object SearchQuery {
  implicit val searchQueryDecoder: Decoder[SearchQuery] = deriveDecoder[SearchQuery]
  implicit val searchQueryEncoder: Encoder[SearchQuery] = deriveEncoder[SearchQuery]

}
