<a name="readme-top"></a>

<!-- headeer-->
<div align="center">
  <h3 align="center">k-means Project</h3>
  
  <p align="center">
    Algorithme de partitionnement en k-moyennes
  </p>
</div>

### Built 

* Scala
* Python

## Usage

Commandes de compilation et d'execution :

1. Compilation
   ```sh
   Scalac -d bin Point3D.scala Reader.scala Kmeans.scala Centroid.scala Cluster.scala Writer.scala
   ```
2. Execution
   ```sh
   scala -classpath bin src.Kmeans ../database/data2.csv
   ```

   
  
  

Commande pour la visualisation :

  ```sh
  python3 script.py clusters_iteration_*.json Results  
  ```


LAMA Maxine - [@maxineLama](https://github.com/maxineLama) 
