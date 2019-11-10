package com.azavea.franklin.endpoints

object Codecs {
  implicit val geometryCodec: JsonCodec[Geometry] = encoderDecoderCodecr[Geometry]

}
