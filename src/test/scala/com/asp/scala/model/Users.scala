package com.asp.scala.model

case class Users(
                  name: String,
                  salary: Double,
                  id: Option[Int] = None
                )
