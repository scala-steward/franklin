package com.azavea.franklin.endpoints

import com.azavea.franklin.datamodel.SearchQuery
import geotrellis.vector.Geometry
import io.circe._
import tapir.Codec.JsonCodec
import tapir._
import tapir.json.circe._
import geotrellis.server.stac.Implicits._
import tapir.Schema.{SObjectInfo, SProduct}

object SearchEndpoints {

  val base = endpoint.in("stac")

  val rootCatalog: Endpoint[Unit, Unit, Json, Nothing] =
    base.get
      .out(jsonBody[Json])
      .description("Root Catalog and entrypoint into STAC collections")
      .name("root")

  val searchGet: Endpoint[Unit, Unit, Json, Nothing] =
    base.get
      .in("search")
      .out(jsonBody[Json])
      .description("Search endpoint for all collections")
      .name("search-get")

  implicit val schemaForMyCustomType: SchemaFor[Geometry] = SchemaFor(
    SProduct(
      SObjectInfo("io.circe.Json"),
      List.empty,
      List.empty
    )
  )

  val searchPost: Endpoint[SearchQuery, Unit, Json, Nothing] =
    base.post
      .in("search")
      .in(jsonBody[SearchQuery])
      .out(jsonBody[Json])
      .description("Search endpoint using POST for all collections")
      .name("search-post")

  val endpoints = List(rootCatalog, searchGet, searchPost)

}
