package wordament.twodee;

import gridsearch.GridSearch;
import gridsearch.Node;

import java.util.ArrayList;
import java.util.List;

public class WordamentGrid{

	private int rows = 0;
	private int cols = 0;

	private String str = "";
	private String goal = "";
	private int index = 0;
	
	private WordamentGrid prev = null;
	private WordamentGrid next = null;
	
	public WordamentGrid(String str, int rows, int cols, int index){
	
		this.rows = rows;
		this.cols = cols;
		this.str = str;
		this.index = index;
		
	}
	
	public List<Integer> findWord(String word){
		this.goal = word;
		GridSearch gridSearch = new GridSearch();
	
		return gridSearch.startSearch(
				this.getRootNodes(word.charAt(0)), 
				word.length(), 
				node -> ((WordamentNode) node).getPath().equals(word) 
			  );
		
		
	}
	
	public String toString(){
		StringBuilder result = new StringBuilder(str.length() + rows);
		
		for (int i = 0; i<str.length(); i++){
			result.append(str.charAt(i));
			if ((i+1)%cols == 0){
				result.append("\n");
			}
		}
		
		return result.toString();
	}
	
	
	public char getChar(int index){
		return this.str.charAt(index);
	}


	public List<Node> getRootNodes(char c) {
		List<Node>  nodes = new ArrayList<Node>(str.length());
		
		for (int i = 0; i<str.length(); i++){
			
			if (str.charAt(i) == c){
				nodes.add(new WordamentNode(i, this.rows, this.cols, str.substring(i, i+1), this));
			}
			
		}
		
		return nodes;
	}
	
	public List<Node> getNodes(List<Integer> indexes, String path, int level){
		List<Node> nodes = new ArrayList<Node>();
		
		for (int currentIndex: indexes){
			if (getChar(currentIndex) == goal.charAt(level+1) ){
				nodes.add(new WordamentNode(currentIndex, this.rows, this.cols, path+getChar(currentIndex), this));
			}
		}	
		
		return nodes;
	}
	
	public List<Node> getAdjacents(WordamentNode node, int level){
		List<Node> adjacents = new ArrayList<Node>();
		
		List<Integer> indexes = node.getAdjacentIndexes();
		
		
		// get adjacents from same grid
		for (int currentIndex: indexes){
			if (getChar(currentIndex) == goal.charAt(level+1) ){
				adjacents.add(new WordamentNode(currentIndex, this.rows, this.cols, node.getPath()+getChar(currentIndex), this));
			}
		}
		
		
		// get adjacents from upper and lower grids
		
		indexes.add(node.getId());
		
		if (this.prev != null){
			adjacents.addAll(this.prev.getNodes(indexes, node.getPath(), level));
		}
		
		if (this.next != null){
			adjacents.addAll(this.next.getNodes(indexes, node.getPath(), level));
		}
		
		return adjacents;
	}
	
	
	public String markPath(List<Integer> indexes){
		StringBuilder result = new StringBuilder(str.length() + rows);
		
		for (int i = 0; i<str.length(); i++){
			if (indexes.contains(i)){
			result.append((char)(str.charAt(i)-32));	
			}else {
			result.append(str.charAt(i));
			}
			if ((i+1)%cols == 0){
				result.append("\n");
			}
		}
		
		return result.toString();
	}
	
	
	
	
	
	public int getIndex(){
		return index;
	}


	public String getGoal() {
		return goal;
	}


	public void setGoal(String goal) {
		this.goal = goal;
	}

	public WordamentGrid getPrev() {
		return prev;
	}

	public void setPrev(WordamentGrid prev) {
		this.prev = prev;
	}

	public WordamentGrid getNext() {
		return next;
	}

	public void setNext(WordamentGrid next) {
		this.next = next;
	}
	
	
	
}
