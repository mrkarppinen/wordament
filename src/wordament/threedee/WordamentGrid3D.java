package wordament.threedee;



import gridsearch.GrindSearch3D;

import java.util.ArrayList;
import java.util.List;

import wordament.twodee.WordamentGrid;
import wordament.twodee.WordamentNode;


public class WordamentGrid3D {


	private int gridsCount = 0;
	private String str = "";
	private String goal = "";
	private List<WordamentGrid> grids;
	
	
	public WordamentGrid3D(int rows, int cols, String str) {
		super();
		
		this.gridsCount =str.length() / ( rows*cols );
		this.str = str;

		
		// create grids
		grids = new ArrayList<WordamentGrid>(gridsCount);
		
		for(int i=0; i< gridsCount; i++){
			int start = i*(rows*cols);
			int end = start + (rows*cols);
			
			
			grids.add(new WordamentGrid(str.substring(start, end), rows, cols, i));
		}
		
		
	}
	
	public boolean findWord(String word){
		this.goal = word;
		GrindSearch3D gridSearch = new GrindSearch3D();

		for(int i=0; i< gridsCount; i++){
			
			grids.get(i).setGoal(word);
			
			// save reference to prev and next grid
			if (i > 0){
				grids.get(i).setPrev(grids.get(i-1));
			}
			
			if (i < gridsCount - 1){
				grids.get(i).setNext(grids.get(i+1));
			}
			

		}
		
		
		
		for (WordamentGrid grid: grids){
			List<Integer> list = gridSearch.startSearch(grid.getRootNodes(word.charAt(0)), word.length(), node -> ((WordamentNode) node).getPath().equals(word));
			if (list != null){
				return true;
			}
		}
		
		
		return false;
		
		
	}
	
	
	
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		for (WordamentGrid grid: grids){
			builder.append(grid.toString()).append("\n\n");
		}
		
		return builder.toString();
	}
	
	
	
}
