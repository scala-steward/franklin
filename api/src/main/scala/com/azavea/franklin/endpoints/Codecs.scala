package com.azavea.franklin.endpoints

import geotrellis.server.stac.Implicits._
import geotrellis.vector._
import tapir.Codec.JsonCodec
import tapir.Schema.{SObjectInfo, SProduct}
import tapir.SchemaFor
import tapir.json.circe._

case class FranklinPoint(_type: String,
                         coordinates: (Double, Double),
                         bbox: (Double, Double, Double, Double))

object Codecs {

  implicit val schemaForMyCustomType: SchemaFor[Geometry] = SchemaFor(
    SProduct(
      SObjectInfo("io.circe.Json"),
      List.empty,
      List.empty
    )
  )
  implicit val geometryCodec: JsonCodec[Geometry] = encoderDecoderCodec[Geometry]

}
