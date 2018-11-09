package com.asp.scala.test

import com.asp.scala.api.CoreHttpMethods
import com.asp.scala.model.Users
import org.scalatest.{FlatSpec, Matchers}

class UserResourceTest extends FlatSpec with Matchers with CoreHttpMethods {

  "Get All User Request" should "should list users more than 1 users" in {
    println(get("users/all").right.asInstanceOf[Users])
  }
}
