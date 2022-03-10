package com.tapestry
package apps.iot.runtime

case class ContainerServiceProps(
                                name: String,
                                tag: String,
                                containerName: String,
                                ports: List[String] = List.empty,
                                volumes: List[String] = List.empty,
                                environment: Map[String, String] = Map.empty,
                                stdInOpen: Boolean = false,
                                tty: Boolean = false,
                                workingDir: String = null
                                ) {

  def image() = s"$name:$tag"
}
