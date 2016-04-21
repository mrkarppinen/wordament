package wordament.twodee;

import gridsearch.Node;

import java.util.ArrayList;
import java.util.List;

public class WordamentNode implements Node{

	private int index;
	private int cols;
	private int rows;
	private String path;
	private WordamentGrid grid;
	
	
	public WordamentNode(int index, int rows, int cols, String path, WordamentGrid grid) {
		super();
		
		this.index = index;
		this.cols = cols;
		this.rows = rows;
		this.path = path;
		this.grid = grid;
		
	}
	
	public List<Integer> getAdjacentIndexes(){
		List<Integer> indexes = new ArrayList<Integer>();
		
		int elementRow = (index/cols);
		int elementCol = index - (cols*elementRow);	
		
		int startRow =  Math.max(0, elementRow-1 );
		int endRow = Math.min(rows - 1, elementRow+1);
		
			for (int i = startRow; i<endRow + 1; i++){
			
			for (int j = Math.max(0, elementCol - 1); j < Math.min(cols-1, elementCol+1) + 1; j++){
				int currentIndex = (cols*i) + j;
				
				if (currentIndex != this.index){
					indexes.add(currentIndex);
				}
			}
		}	
				
		return indexes;
	}


	@Override
	public int getId() {
		return this.index;
	}

	public String getPath() {
		return path;
	}
	
	
	public String toString(){
		return this.index+":["+grid.getChar(this.index)+"]";
	}

	@Override
	public List<Node> getAdjacents(int level) {
		return grid.getAdjacents(this, level);
	}
	
	public int getGridIndex(){
		return this.grid.getIndex();
	}
	

}
