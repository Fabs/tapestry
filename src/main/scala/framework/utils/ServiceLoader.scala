package com.tapestry
package framework.utils

import framework.primitives.Service

import com.google.common.reflect.ClassPath

import java.io.IOException
import scala.jdk.CollectionConverters._

object ServiceLoader {
  // def getTypeTag[T: ru.TypeTag](obj: T) = ru.typeTag[T]
  //catalog(0).getClass.getMethods.map(x => x.getTypeParameters()).map(x => getTypeTag(x)).foreach(println(_))
  //https://docs.scala-lang.org/overviews/reflection/overview.html

  def servicesFromPackage(catalogNamespace: String): List[Service[_, _, _]] = {
    val loader = Thread.currentThread.getContextClassLoader
    try {
      val classpath = ClassPath.from(loader) // scans the class path used by classloader
      val services = classpath.getTopLevelClassesRecursive(catalogNamespace).asScala
        .map(service => Class.forName(service.getName))

      return services
        .filter(canCastToService(_))
        .map(s => s.newInstance().asInstanceOf[Service[_, _, _]]).toList
    } catch {
      case e: IOException => {
        e.printStackTrace()
      }
    }
    List.empty
  }

  def canCastToService(value: Class[_]): Boolean = {
    try {
      println(value.getName)
      value.newInstance().asInstanceOf[Service[_, _, _]]
      println(s"WORKS ${value.getName}")
    } catch {
      case e: Exception => {
        println(e.getMessage)
        return false
      }
    }

    true
  }
}
