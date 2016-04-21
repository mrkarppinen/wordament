package gridsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class GrindSearch3D {

	
	int visits;
	
	
	public List<Integer> startSearch(List<Node> rootNodes, int maxLevels, Predicate<Node> isGoal){
		
		for (Node node: rootNodes){
			
			
			List<Integer> result = search(node, new HashMap<Integer, List<Integer>>(), 0, maxLevels, isGoal);
			if (result != null){
				
//				System.out.println("Nodes visited:"+visits);

				return result;
				
			}
			
		}
		
//		System.out.println("Nodes visited:"+visits);
		return null;
	}
	
	
	private List<Integer> search(Node root, Map<Integer, List<Integer>> visited, int level, int maxLevels, Predicate<Node> isGoal){
		visits++;
		
		// Add node to visited list
		if (visited.get(root.getGridIndex()) == null){
			visited.put(root.getGridIndex(), new ArrayList<Integer>());
		}
		visited.get(root.getGridIndex()).add(root.getId());
		
		
		if (isGoal.test(root)){
			return visited.get(root.getGridIndex());
		}
		
		if (level == maxLevels){
			return null;
		}
	
		
		for (Node node: root.getAdjacents(level)){
			
			if (visited.get(node.getGridIndex()) == null || visited.get(node.getGridIndex()).indexOf(node.getId()) == -1){
				
				List<Integer> result = search(node, new HashMap<Integer, List<Integer>>(visited), level+1, maxLevels, isGoal);
				if (result != null){
					return result;
				}
				
			}
			
		}
		
		
		return null;
	}
	
}
