package com.asp.scala.api

import com.softwaremill.sttp._
import org.json4s.native.JsonMethods.parse
import org.json4s.native.Serialization.write
import org.json4s.{DefaultFormats, JValue}

object CoreHttpMethods {

  implicit val backend: SttpBackend[Id, Nothing] = HttpURLConnectionBackend()
  implicit val formats = DefaultFormats

  def get(url: String, authenticated: Boolean = true): (Response[String], JValue) = {
    val request = authenticated match {
      case true => sttp.header("Authorization", s"Bearer TOKEN_VAL").get(uri"$url")
      case false => sttp.get(uri"$url")
    }

    println(s"Sending get request to $url")
    val response = request.send()

    val responseBody = response.body match {
      case Right(successBody) => successBody
      case Left(errorBody) => errorBody
    }

    val json = parse(responseBody)
    (response, json)
  }

  def post[T](url: String, payload: T, authenticated: Boolean = false): (Response[String], JValue) = {
    val request = authenticated match {
      case true => sttp.header("Authorization", s"Bearer AUTHORIZATION_TOKEN").post(uri"$url").body(write(payload))
      case false => sttp.post(uri"$url").body(write(payload)).header("Content-Type", "application/json")
    }

    println(s"Sending post request to $url")
    val response = request.send()

    val responseBody = response.body match {
      case Right(successBody) => successBody
      case Left(errorBody) => errorBody
    }

    val json = parse(responseBody)
    (response, json)
  }

}
