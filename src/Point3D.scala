// Point3D.scala
package src
case class Point3D(x: Double, y: Double, z: Double)

object Point3D {
  def distance_between(p1: Point3D, p2: Point3D): Double = {
    val dx = p2.x - p1.x
    val dy = p2.y - p1.y
    val dz = p2.z - p1.z
    Math.sqrt(dx * dx + dy * dy + dz * dz)
  }
}