package com.tapestry
package apps.search.data

import com.tapestry.apps.search.models.Song
import kantan.csv._
import kantan.csv.ops._
import kantan.csv.generic._

import java.io.File

object SongData {
  def readSongDocuments(file: String): List[Song] = {
    val documents = new File(file).asCsvReader[Song](rfc.withHeader).toList
    val validDocuments:List[Song] = documents.filter(_.isRight).map(_.getOrElse(null))
    println(s"Read ${validDocuments.length}")

    validDocuments
  }
}
