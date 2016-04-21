package gridsearch;

import java.util.List;

public interface Node {

	List<Node> getAdjacents(int level);
	
	int getId();
	
	int getGridIndex();
	
	
	
	
}
