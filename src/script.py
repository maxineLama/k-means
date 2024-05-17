import json
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D
import os
import sys
import os
import glob

def plot_clusters(file_path, output_directory):
    try:
        print(f"Reading JSON file from {file_path}")
        with open(file_path, 'r') as file:
            data = json.load(file)
        print("Successfully read JSON file")

        fig = plt.figure()
        ax = fig.add_subplot(111, projection='3d')

        if 'clusters' in data:
            print("Found clusters in the JSON file")
            clusters = data['clusters']
            colors = plt.get_cmap('tab10', len(clusters))
            
            # Plot each cluster with a different color
            for idx, cluster in enumerate(clusters):
                cluster_points = cluster['points']
                color = colors(idx)
                if cluster_points:
                    x_values = [point['x'] for point in cluster_points]
                    y_values = [point['y'] for point in cluster_points]
                    z_values = [point['z'] for point in cluster_points]
                    ax.scatter(x_values, y_values, z_values, color=color, label=f'Cluster {idx}')
                
                # Plot centroid with the same color
                centroid = cluster['centroid']
                ax.scatter(centroid['x'], centroid['y'], centroid['z'], c=color, marker='o', s=100)
        else:
            print("No clusters found, plotting points and centroids")
            centroids = data['centroids']
            points = data['points']

            # Plot all points
            x_values = [point['x'] for point in points]
            y_values = [point['y'] for point in points]
            z_values = [point['z'] for point in points]
            ax.scatter(x_values, y_values, z_values, c='blue')
            
            # Plot centroids with large black markers
            for centroid in centroids:
                ax.scatter(centroid['x'], centroid['y'], centroid['z'], c='black', marker='o', s=50)

        ax.set_xlabel('X Label')
        ax.set_ylabel('Y Label')
        ax.set_zlabel('Z Label')
        
        # Create the output directory if it doesn't exist
        os.makedirs(output_directory, exist_ok=True)
        
        # Generate the output file path
        output_file = os.path.join(output_directory, os.path.basename(file_path).replace('.json', '.png'))
        
        # Save the plot to the file
        plt.savefig(output_file)
        
        # Show the plot
        plt.show()
        
        # Close the plot
        plt.close()
        
        # Print debug message
        print(f"Plot saved to {output_file}")
    
    except Exception as e:
        print(f"An error occurred: {e}")

plot_clusters("clusters_iteration_10.json", "Results")
