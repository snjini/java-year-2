package A2;
import java.util.*;

public class Kruskal{

    public static WGraph kruskal(WGraph g){

        /* Fill this method (The statement return null is here only to compile) */
    
    	DisjointSets newSet = new DisjointSets(g.getNbNodes());
    	//List<Edge> MST = g.listOfEdgesSorted();	
    	List<Edge> MST = new ArrayList<Edge>();
    	WGraph newGraph = new WGraph();
    	int numOfEdges = g.listOfEdgesSorted().size();
      
    	int i = 0;
    	
    	while(i != numOfEdges && MST.size() != g.getNbNodes()-1) {
    	  Edge e = g.listOfEdgesSorted().get(i);
    	  if(IsSafe(newSet, e)) {
    		  newGraph.addEdge(e);
    		  newSet.union(e.nodes[0], e.nodes[1]);  
    	  }
    	  i++;
    	 }
        return newGraph;
    }

    public static Boolean IsSafe(DisjointSets p, Edge e){

        /* Fill this method (The statement return 0 is here only to compile) */
    	
    	if(p.find(e.nodes[0]) == p.find(e.nodes[1])) {	
    		p.union(e.nodes[0], e.nodes[1]);
    	}
    	else if(e.nodes[0] < e.nodes[1]) {
        return true;
    	} 	
    	return false;
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        WGraph t = kruskal(g);
        System.out.println(t);

   } 
}
