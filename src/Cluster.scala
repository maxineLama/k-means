// Cluster.scala
package src

case class Cluster(centroid: Centroid, points: List[Point3D])

object Cluster{
  
  
  // Fonction pour recalculer le centroid en fonction de la liste de points
  def recalculate(centroid: Centroid, points: List[Point3D]): Centroid = {
    val newCentroidPoint = if (points.nonEmpty) {
      val centroidX = points.map(_.x).sum / points.length
      val centroidY = points.map(_.y).sum / points.length
      val centroidZ = points.map(_.z).sum / points.length
      Point3D(centroidX, centroidY, centroidZ)
    } else {
      centroid // Si aucun point dans le cluster, le centroid reste le même
    }
    new Centroid(newCentroidPoint.x, newCentroidPoint.y, newCentroidPoint.z)
  }

}
