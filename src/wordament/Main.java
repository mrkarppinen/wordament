package wordament;

import java.util.List;

import wordament.twodee.WordamentGrid;


public class Main {

	public static void main(String[] args) {
		
		WordamentGrid wordamentGrid = new WordamentGrid("rsiuekuvinnapeks", 4, 4, 0);
		List<Integer> path = wordamentGrid.findWord("kanki"); 
		if (path != null){
			
			System.out.println("Word found");
			System.out.println(path.toString());
			System.out.println(wordamentGrid.markPath(path));
			
		}else {
			
			System.out.println("Not found");
			
		}
		

		
		
		
		
	}
	
	

}
