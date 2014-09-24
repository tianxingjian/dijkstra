package cn.Adjacency;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * <p>
 * 最短路径算法
 * <p>
 * @author 天行健
 *
 */
public class Dijkstra{

  public static void main(String[] arg){
		
		Dijkstra obj = new Dijkstra();
		
		// Create a new graph.
		Graph g = new Graph(new String[]{"v0", "v1", "v2", "v3", "v4", "v5", "v6", "v7", "v8"});
		
		// Add the required edges.
		g.addEdge("v0", "v1", 4); g.addEdge("v0", "v7", 8);
		g.addEdge("v1", "v2", 8); g.addEdge("v1", "v7", 11); g.addEdge("v2", "v1", 8);
		g.addEdge("v2", "v8", 2); g.addEdge("v2", "v5", 4); g.addEdge("v2", "v3", 7);
		g.addEdge("v3", "v2", 7); g.addEdge("v3", "v5", 14); g.addEdge("v3", "v4", 9); 
		g.addEdge("v4", "v3", 9); g.addEdge("v4", "v5", 10); 
		g.addEdge("v5", "v4", 10); g.addEdge("v5", "v3", 9); g.addEdge("v5", "v2", 4); g.addEdge("v5", "v6", 2);
		g.addEdge("v6", "v7", 1); g.addEdge("v6", "v8", 6); g.addEdge("v6", "v5", 2);
		g.addEdge("v7", "v0", 8); g.addEdge("v7", "v8", 7); g.addEdge("v7", "v1", 11); g.addEdge("v7", "v6", 1);
		g.addEdge("v8", "v2", 2); g.addEdge("v8", "v7", 7); g.addEdge("v8", "v6", 6);
		
	
		// Calculate Dijkstra.
		obj.calculate(g.getVertex("v0"));	

		// Print the minimum Distance.
		for(Vertex v : g.getVertices().values()){
			System.out.print("Vertex - "+v+" , Dist - "+ v.minDistance+" , Path - ");
			for(Vertex pathvert:v.path) {
				System.out.print(pathvert+" ");
			}
			System.out.println(""+v);
		}

		System.out.println("---------**********------------");
		Edge delEdge = g.delEdge("v0", "v7");
		System.out.println("被删除的边(已字符为顶点主键)：" + delEdge);
		g.resetMinDistance();
		obj.calculate(g.getVertex("v0"));
		// 删除一条边后的最短路径
		for(Vertex v : g.getVertices().values()){
			System.out.print("Vertex - " + v + " , Dist - " + v.minDistance
					+ " , Path - ");
			for (Vertex pathvert : v.path) {
				System.out.print(pathvert + " ");
			}
			System.out.println("" + v);
		}
	}

	public void calculate(Vertex source){
		// Algo:
		// 1. Take the unvisited node with minimum weight.
		// 2. Visit all its neighbours.
		// 3. Update the distances for all the neighbours (In the Priority Queue).
		// Repeat the process till all the connected nodes are visited.
		
		source.minDistance = 0;
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		queue.add(source);
		
		while(!queue.isEmpty()){
			
			Vertex u = queue.poll();
		
			for(Edge neighbour:u.neighbours){
				Double newDist = u.minDistance+neighbour.weight;
				
				if(neighbour.target.minDistance>newDist){
					// Remove the node from the queue to update the distance value.
					queue.remove(neighbour.target);
					neighbour.target.minDistance = newDist;
					
					// Take the path visited till now and add the new node.s
					neighbour.target.path = new LinkedList<Vertex>(u.path);
					neighbour.target.path.add(u);
					
					//Reenter the node with new distance.
					queue.add(neighbour.target);					
				}
			}
		}
	}

}

