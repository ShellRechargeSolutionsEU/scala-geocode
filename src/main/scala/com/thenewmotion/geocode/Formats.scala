package com.thenewmotion.geocode

import spray.json._, DefaultJsonProtocol._


private[geocode] object Formats {

  /** @see https://developers.google.com/maps/documentation/geocoding/#StatusCodes */
  sealed trait Status

  case object Ok extends Status

  sealed trait Error extends Status
  case object ZeroResults    extends Error
  case object OverQuotaLimit extends Error
  case object Denied         extends Error
  case object InvalidRequest extends Error
  case class OtherError(description: String) extends Error


  case class Address(
    long_name: String,
    short_name: String,
    types: List[String]
  )

  case class Result(
    address_components: List[Address],
    formatted_address: String,
    types: List[String]
  )

  case class Response(
    results: List[Result],
    status: Status
  ) {
    def allResults = status match {
      case Ok => Right(results)
      case e: Error => Left(e)
    }
  }

  implicit val ResponseStatusFmt: JsonFormat[Status] =
    lift((js: JsValue) => js match {
      case JsString("OK") => Ok
      case JsString("ZERO_RESULTS") => ZeroResults
      case JsString("OVER_QUERY_LIMIT") => OverQuotaLimit
      case JsString("REQUEST_DENIED") => Denied
      case JsString("INVALID_REQUEST") => InvalidRequest
      case x => deserializationError(s"Expected response status, but got $x")
    })

  implicit val AddressFmt = jsonFormat3(Address)
  implicit val ResponseResultFmt = jsonFormat3(Result)
  implicit val GeocodeResponseFmt = jsonFormat2(Response)

  val read = safeReader[Response].read _

  def parseResult(s: String): Either[Error, List[Result]] =
    read(s.parseJson) match {
      case Right(response) => response.allResults
      case Left(e) => Left(OtherError(e.getMessage))
    }
}
