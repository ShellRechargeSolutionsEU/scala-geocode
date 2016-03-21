package com.thenewmotion.geocode


import dispatch._


class Geocode(protected val http: Http = Http) extends GeocodeBase {

  protected def requestParameters(loc: Location) = {
    import loc._

    List(
      "latlng" -> s"$latitude,$longitude",
      "sensor" -> "false"
    )
  }
}


