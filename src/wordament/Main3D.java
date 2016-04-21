package wordament;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import wordament.threedee.WordamentGrid3D;

public class Main3D {

	public static void main(String[] args) {
		
		WordamentGrid3D grid3d = new WordamentGrid3D(4, 4, new String("AJFEAPUWOGMRMNXKDNSIFODSJEGIWKPREQMFRKIDDMIREOSDRTSLDKPISPOIJQDT").toLowerCase());
		
		
		String fileName = "src/words.txt";

		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			
			int found = 0;
			
			Object[] arr = stream.toArray();
			for (int i = 0; i< arr.length; i++){
				
				String search = (String)arr[i];
				if (grid3d.findWord(search)){ 
					found++;
				}
				
			}
				
			System.out.println(found);
			

		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

}
