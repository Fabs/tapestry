package com.tapestry
package framework.utils

import com.google.common.reflect.ClassPath
import com.tapestry.framework.primitives.Service

import java.io.IOException
import scala.jdk.CollectionConverters._

object ServiceLoader {
  // def getTypeTag[T: ru.TypeTag](obj: T) = ru.typeTag[T]
  //catalog(0).getClass.getMethods.map(x => x.getTypeParameters()).map(x => getTypeTag(x)).foreach(println(_))
  //https://docs.scala-lang.org/overviews/reflection/overview.html

  def servicesFromCatalog(catalogNamespace: String): List[Service[_, _, _]] = {
    val loader = Thread.currentThread.getContextClassLoader
    try {
      val classpath = ClassPath.from(loader) // scans the class path used by classloader
      val services = classpath.getTopLevelClasses(catalogNamespace).asScala
        .map(service => Class.forName(service.getName))

      return services.map(s => s.newInstance().asInstanceOf[Service[_, _, _]]).toList
    } catch {
      case e: IOException => {
        e.printStackTrace()
      }
    }
    List.empty
  }

}
