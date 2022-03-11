package com.tapestry
package apps.iot.runtime

import com.fasterxml.jackson.annotation.{JsonIgnoreProperties, JsonProperty}


@JsonIgnoreProperties(Array("name", "tag"))
case class DockerImage(
                        name: String,
                        tag: String,
                        containerName: String,
                        ports: List[String] = List.empty,
                        volumes: List[String] = List.empty,
                        environment: Map[String, String] = Map.empty,
                        stdinOpen: Boolean = false,
                        tty: Boolean = false,
                        workingDir: String = null
                      ) {

  @JsonProperty("image")
  def image() = s"$name:$tag"
}
