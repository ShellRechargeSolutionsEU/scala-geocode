package com.thenewmotion.geocode

/**
 * @author Yaroslav Klymko
 */
case class AddressComponent(long_name: String, short_name: String, types: List[String])
case class ResponseResult(address_components: List[AddressComponent], formatted_address: String, types: List[String])
case class GeocodeResponse(results: List[ResponseResult], status: ResponseStatus.Value)