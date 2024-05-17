package src

import scala.io.Source

object Reader {

  def readPoints(filePath: String): List[Point3D] = {
    val source = Source.fromFile(filePath)
    try {
      source.getLines().drop(1).take(200).map { line =>
        val Array(x, y, z, _*) = line.split(",").map(_.trim.toDouble)
        Point3D(x, y, z)
      }.toList
    } catch {
      case e: Exception =>
        println(s"Error reading file: $e")
        List.empty[Point3D]
    } finally {
      source.close()
    }
  }
}
