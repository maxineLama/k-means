// Kmeans.scala
package src

import scala.sys.process._
object Kmeans {
  // Fonction pour initialiser les clusters avec des centroids initiaux
  def initialize_clusters(centroids: Array[Centroid]): List[Cluster] = {
    centroids.map(centroid => Cluster(centroid, List.empty)).toList
  }

  // Fonction pour attribuer les points à des clusters en fonction des centroids
  def assign_cluster(points: List[Point3D], clusters: List[Cluster]): List[Cluster] = {
    // Pour chaque point, trouver le cluster avec le centroid le plus proche et y ajouter le point
    points.foldLeft(clusters) { (accClusters, point) =>
      // Trouver le cluster avec le centroid le plus proche du point
      val closestCluster = accClusters.minBy(cluster =>
        Point3D.distance_between(point, cluster.centroid)
      )
      // Ajouter le point au cluster le plus proche
      val updatedPoints = point :: closestCluster.points
      // Mettre à jour la liste des clusters avec le point ajouté
      accClusters.updated(accClusters.indexOf(closestCluster), closestCluster.copy(points = updatedPoints))
    }
  }

  def main(args: Array[String]): Unit = {
    if (args.length != 1) {
      println("Usage: scala Main <file-path>")
      sys.exit(1)
    }

    val filePath = args(0)
    val points = Reader.readPoints(filePath)
    println("Entrez un nombre de centroids:")
    val numCentroids = scala.io.StdIn.readInt()

    val centroids = Centroid.initialize_centroids(numCentroids, points)
    println("Initial centroids")
    centroids.foreach(println)

    // Initialiser les clusters avec les centroids initiaux
    var clusters = initialize_clusters(centroids)

    // Assigner les points aux clusters en fonction des centroids initiaux
    clusters = assign_cluster(points, clusters)

    // Écrire les points et les centroids initiaux dans un fichier
    Writer.writePointsAndCentroidsToFile(points,centroids,"clusters_iteration_0.json")


    // Boucle pour recalculer et réassigner les clusters
    var prevSummedDistance = Double.MaxValue
    for (iteration <- 1 to 10) { // 10 itérations
      println(s"Iteration $iteration:")
      
      // Recalculer les centroids en fonction des points attribués à chaque cluster
      val recalculatedCentroids = clusters.map(cluster =>
        Cluster.recalculate(cluster.centroid, cluster.points)
      )

      // Réassigner les points aux clusters en fonction des nouveaux centroids
      clusters = assign_cluster(points, initialize_clusters(recalculatedCentroids.toArray))

      // Écrire les clusters dans un fichier
      Writer.writeClustersToFile(clusters.toList,s"clusters_iteration_$iteration.json")
      // Exécuter le script Python pour générer l'image à partir du fichier JSON
      

      // Calcul de la somme des distances
      val summedDistance = points.zip(clusters).map { case (point, cluster) =>
        val centroid = cluster.centroid
        Math.pow(Point3D.distance_between(point, centroid), 2)
      }.sum

      println(s"Summed distance for iteration $iteration: $summedDistance")

      // Vérification de la convergence
      if (summedDistance >= prevSummedDistance) {
        println("Algorithm converged.")
      }
      prevSummedDistance = summedDistance
    }
  }
}