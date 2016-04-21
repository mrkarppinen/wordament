package gridsearch;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class GridSearch {

	int visits;
	
	
	public List<Integer> startSearch(List<Node> rootNodes, int maxLevels, Predicate<Node> isGoal){
		
		for (Node node: rootNodes){
			
			List<Integer> result = search(node, new ArrayList<Integer>(), 0, maxLevels, isGoal);
			if (result != null){
				
				System.out.println("Nodes visited:"+visits);

				return result;
				
			}
			
		}
		
		System.out.println("Nodes visited:"+visits);
		return null;
	}
	
	
	private List<Integer> search(Node root, List<Integer> visited, int level, int maxLevels, Predicate<Node> isGoal){
		visits++;

		visited.add(root.getId());
		
		if (isGoal.test(root)){
			return visited;
		}
		
		if (level == maxLevels){
			return null;
		}
	
		
		for (Node node: root.getAdjacents(level)){
			
			if (visited.indexOf(node.getId()) == -1){
				
				List<Integer> result = search(node, new ArrayList<Integer>(visited), level+1, maxLevels, isGoal);
				if (result != null){
					return result;
				}
				
			}
			
		}
		
		
		return null;
	}
	
	
}
