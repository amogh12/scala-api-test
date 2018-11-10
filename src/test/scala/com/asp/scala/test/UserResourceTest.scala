package com.asp.scala.test

import com.asp.scala.api.CoreHttpMethods._
import com.asp.scala.model.Users
import com.asp.scala.utils.Config
import org.apache.commons.lang3.{RandomStringUtils, RandomUtils}
import org.json4s.{DefaultFormats, Formats}
import org.scalatest.{FlatSpec, Matchers}

class UserResourceTest extends FlatSpec with Matchers {

  private implicit val formats: Formats = DefaultFormats

  "Get All User Request" should "should list users more than 1 users" in {
    val name = RandomStringUtils.randomAlphabetic(5)
    post(s"${Config.config.baseUrl}/users/insert", Users(name, 5000))
    val (_, json) = get(s"${Config.config.baseUrl}/users/all")
    val users = json.extract[Seq[Users]]
    println(users.last.name)
    users.isEmpty shouldBe false
  }

  "Post request" should "Persist data in the database" in {
    val name = RandomStringUtils.randomAlphabetic(5)
    val salary = RandomUtils.nextInt(1000, 10000)

    //making post request
    val(_, json) = post(s"${Config.config.baseUrl}/users/insert", Users(name, salary))
    val savedUser = json.extract[Users]

    println(s"Saved User Values are Name: ${savedUser.name} and Salary: ${savedUser.salary}")
    savedUser.name shouldBe name
    savedUser.salary shouldBe salary
  }
}
