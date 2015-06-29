package com.thenewmotion.geocode

import org.specs2.mutable.Spec
import scala.concurrent._, duration._, ExecutionContext.Implicits.global


class GeocodeSpec extends Spec {
  "Geocode" should {
    "find data by location" in {
      val geocode = new Geocode()

      def ?(x: Location) = Await.result(geocode ? x, 3.seconds)

      ?(Location(50.516196, 30.466651)) must beRight
      ?(Location(50.445057, 30.521049)) must beRight
      ?(Location(51.498685, -0.12967)) must beRight
    }
  }
}
