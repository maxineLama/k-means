// Centroid.scala
package src

class Centroid(x: Double, y: Double, z: Double) extends Point3D(x, y, z)

object Centroid {
  def initialize_centroids(numCentroids: Int, points: List[Point3D]): Array[Centroid] = {
    val minPoint = points.reduce((p1, p2) => Point3D(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.min(p1.z, p2.z)))
    val maxPoint = points.reduce((p1, p2) => Point3D(Math.max(p1.x, p2.x), Math.max(p1.y, p2.y), Math.max(p1.z, p2.z)))
    
    val centroids = new Array[Centroid](numCentroids)
    for (i <- 0 until numCentroids) {
      val x = minPoint.x + (maxPoint.x - minPoint.x) * scala.util.Random.nextDouble()
      val y = minPoint.y + (maxPoint.y - minPoint.y) * scala.util.Random.nextDouble()
      val z = minPoint.z + (maxPoint.z - minPoint.z) * scala.util.Random.nextDouble()
      centroids(i) = new Centroid(x, y, z)
    }
    centroids
  }
}