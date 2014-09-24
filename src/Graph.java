import java.util.*;
public class Graph {
  private ArrayList<Vertex> vertices;
	public Graph(int numberVertices){
		vertices = new ArrayList<Vertex>(numberVertices);
		for(int i=0;i<numberVertices;i++){
			vertices.add(new Vertex("v"+Integer.toString(i)));
		}
	}
	
	public void addEdge(int src, int dest, int weight){
		Vertex s = vertices.get(src);
		Edge new_edge = new Edge(vertices.get(dest),weight);
		s.neighbours.add(new_edge);
	}
	
	/**
	 * <p>
	 * 删除边， 取得两个顶点即可:
	 * 查找原顶点对应的邻接表的边，边的另一端等于目标顶点就是要删除的边
	 * <p>
	 * 
	 * @param src 原顶点
	 * @param dest 目标顶点
	 * @return
	 */
	public Edge delEdge(int src, int dest){
		Vertex s = vertices.get(src);
		Vertex d = vertices.get(dest);
		
		Edge delEdge = null;
		for(Edge edge : s.neighbours){
			if(edge.target == d){
				delEdge = edge;
			}
		}
		s.neighbours.remove(delEdge);
		
		return delEdge;
	}
	
	/**
	 *  <p>
	 *  默认最短路径充值，图被算一次选择一个顶点算了一次最短路径后会
	 *  更改最短路径值，要算其他顶点开始的最短路径得重新设置每个顶点最短
	 *  路径的初始值
	 *  <p>
	 */
	public void resetMinDistance(){
		for(Vertex v : vertices){
			v.setMinDistance(Double.POSITIVE_INFINITY);
		}
	}
	
	public ArrayList<Vertex> getVertices() {
		return vertices;
	}
	
	public Vertex getVertex(int vert){
		return vertices.get(vert);
	}
}
