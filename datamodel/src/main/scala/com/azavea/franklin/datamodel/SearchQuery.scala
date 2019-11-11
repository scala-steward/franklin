package com.azavea.franklin.datamodel

import geotrellis.vector.Geometry
import geotrellis.server.stac._
import geotrellis.server.stac.Implicits._
import java.time.Instant

import cats.data.NonEmptyList
import io.circe._
import io.circe.generic.semiauto._
import tapir.Codec.JsonCodec
import tapir._
import tapir.json.circe._
import tapir.EndpointInput
import tapir.Schema.{SArray, SCoproduct, SNumber, SObjectInfo, SProduct}
import shapeless._

case class DatetimeRangeQuery(start: Instant, end: Instant)

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

  implicit val schemaForMyCustomType: SchemaFor[Geometry] = SchemaFor(
    SProduct(
      SObjectInfo("io.circe.Json"),
      List.empty,
      List.empty
    )
  )
  implicit val geometryCodec: JsonCodec[Geometry] = encoderDecoderCodec[Geometry]

  implicit val searchQueryCodec: JsonCodec[SearchQuery] = encoderDecoderCodec[SearchQuery]

  implicit val bboxValidator: Validator[Bbox] =
    Validator.all(
      Validator.minSize(4).contramap(_.toList),
      Validator.maxSize(6).contramap(_.toList)
    )

  implicit val schemaForBbox: SchemaFor[Bbox] = SchemaFor(
    SProduct(
      SObjectInfo("io.circe.Json"),
      List.empty,
      List.empty
    )
  )
  implicit val bboxCodec: JsonCodec[Bbox] = encoderDecoderCodec[Bbox]

  implicit val searchQueryDecoder: Decoder[SearchQuery] = deriveDecoder[SearchQuery]
  implicit val searchQueryEncoder: Encoder[SearchQuery] = deriveEncoder[SearchQuery]
//
//  val searchQueryInput: EndpointInput[SearchQuery] =
//    query[DatetimeRangeQuery]("datetime")
//      .and(query[Option[Int]]("limit"))
//      .mapTo(Paging)

}
