package src

import java.io.{File, PrintWriter}

object Writer {
  def writeClustersToFile(clusters: List[Cluster], filePath: String): Unit = {
    val writer = new PrintWriter(new File(filePath))
    try {
      writer.println("{")
      
      writer.println("  \"clusters\": [")

      // Écriture des clusters et des points
      clusters.zipWithIndex.foreach { case (cluster, idx) =>
        writer.println("    {")
        writer.println(s"      ${'"'}centroid${'"'}: {${'"'}x${'"'}: ${cluster.centroid.x}, ${'"'}y${'"'}: ${cluster.centroid.y}, ${'"'}z${'"'}: ${cluster.centroid.z}},")
        writer.println("      \"points\": [")

        cluster.points.zipWithIndex.foreach { case (point, pointIdx) =>
          val pointComma = if (pointIdx < cluster.points.length - 1) "," else ""
          writer.println(s"        {${'"'}x${'"'}: ${point.x}, ${'"'}y${'"'}: ${point.y}, ${'"'}z${'"'}: ${point.z}}$pointComma")
        }

        val clusterComma = if (idx < clusters.length - 1) "," else ""
        writer.println("      ]")
        writer.println(s"    }$clusterComma")
      }

      writer.println("  ]")
      writer.println("}")
    } finally {
      writer.close()
    }
  }

  def writePointsAndCentroidsToFile(points: List[Point3D], centroids: Array[Centroid], filePath: String): Unit = {
    val writer = new PrintWriter(new File(filePath))
    try {
      writer.println("{")
      writer.println("  \"centroids\": [")

      // Écriture des centroids
      centroids.zipWithIndex.foreach { case (centroid, idx) =>
        val comma = if (idx < centroids.length - 1) "," else ""
        writer.println(s"    {${'"'}x${'"'}: ${centroid.x}, ${'"'}y${'"'}: ${centroid.y}, ${'"'}z${'"'}: ${centroid.z}}$comma")
      }

      writer.println("  ],")
      writer.println("  \"points\": [")

      // Écriture des points
      points.zipWithIndex.foreach { case (point, idx) =>
        val comma = if (idx < points.length - 1) "," else ""
        writer.println(s"    {${'"'}x${'"'}: ${point.x}, ${'"'}y${'"'}: ${point.y}, ${'"'}z${'"'}: ${point.z}}$comma")
      }

      writer.println("  ]")
      writer.println("}")
    } finally {
      writer.close()
    }
  }
}
