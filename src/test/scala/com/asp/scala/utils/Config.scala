package com.asp.scala.utils

import java.io.{File, FileInputStream}

import org.json4s.DefaultFormats
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor

import scala.beans.BeanProperty

class Config {

  @BeanProperty var baseUrl = ""

}

object Config {
  implicit val formats: DefaultFormats.type = DefaultFormats
  lazy val config: Config = readConfig

  private def readConfig: Config = {
    val fileName = "./src/test/resources/config.yaml"
    val input = new FileInputStream(new File(fileName))
    val yaml = new Yaml(new Constructor(classOf[Config]))
    yaml.load(input).asInstanceOf[Config]
  }
}