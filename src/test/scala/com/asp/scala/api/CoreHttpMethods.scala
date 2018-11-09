package com.asp.scala.api

import com.softwaremill.sttp._

trait CoreHttpMethods {

  def get(endpoint: String) = {
    implicit val backend = HttpURLConnectionBackend()
    val endpoint = "users/all"
    val url = uri"http://localhost:8080/rest/" + endpoint
    val request = sttp.get(uri"$url")
    val response = request.send()
    response.body
  }

}
