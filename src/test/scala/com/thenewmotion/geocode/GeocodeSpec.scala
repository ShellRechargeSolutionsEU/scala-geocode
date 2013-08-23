package com.thenewmotion.geocode

import org.specs2.mutable.SpecificationWithJUnit
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * @author Yaroslav Klymko
 */
class GeocodeSpec extends SpecificationWithJUnit {
  "Geocode" should {
    "find data by location" in {
      val geocode = new Geocode()

      def ?(x: Location) = Await.result(geocode ? x, Duration(3, SECONDS))

      ?(Location(50.516196, 30.466651)) must beRight
      ?(Location(50.445057, 30.521049)) must beRight
      ?(Location(51.498685, -0.12967)) must beRight
    }
  }
}
