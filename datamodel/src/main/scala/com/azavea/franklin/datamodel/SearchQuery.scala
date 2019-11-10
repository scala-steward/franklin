package com.azavea.franklin.datamodel

import geotrellis.vector.Geometry
import geotrellis.server.stac._
import geotrellis.server.stac.Implicits._
import java.time.Instant

import io.circe._
import io.circe.generic.semiauto._
import tapir.Codec.JsonCodec
import tapir.
import tapir.EndpointInput

case class DatetimeRangeQuery(start: Option[Instant], end: Option[Instant])

object DatetimeRangeQuery {
  implicit val encoderDateTimeQuery: Encoder[DatetimeRangeQuery] = deriveEncoder
  implicit val decoderDateTimeQuery: Decoder[DatetimeRangeQuery] = deriveDecoder

}

case class SearchQuery(
    bbox: Bbox,
    datetime: Option[DatetimeRangeQuery],
    geometry: Option[Geometry],
    next: Option[String],
    ids: Option[List[String]],
    collections: Option[List[String]],
    limit: Option[Int]
)

object SearchQuery {

  implicit val searchQueryCodec: JsonCodec[SearchQuery] = encoderDecoderCodec[SearchQuery]

  implicit val bboxCodec: JsonCodec[Bbox] = encoderDecoderCodec[Bbox]

  implicit val searchQueryDecoder: Decoder[SearchQuery] = deriveDecoder[SearchQuery]
  implicit val searchQueryEncoder: Encoder[SearchQuery] = deriveEncoder[SearchQuery]
//
//  val searchQueryInput: EndpointInput[SearchQuery] =
//    query[DatetimeRangeQuery]("datetime").and(query[Option[Int]]("limit"))
//      .mapTo(Paging)
//

}
