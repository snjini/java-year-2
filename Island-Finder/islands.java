//SASHA NJINI
//260783102

import java.io.*;
import java.util.*;

public class islands {

	public static int numOfProblems;
	// number of rows for 2D array
	public static int m;
	// number of columns for 2D array
	public static int n;
	//list of individual problems
	List<char[][]> problems = new ArrayList<char[][]>();

	public static void main(String[] args) {
		//run time
		//final long startTime = System.currentTimeMillis();

		try {
			String file = "testIslands.txt";
			islands tester = new islands(file);
			PrintWriter writer;
			
			writer = new PrintWriter("testIslands_solution.txt", "UTF-8");
			
			// test all problems and write their answers into a .txt file
			for (int i = 0; i < numOfProblems; i++) {
				writer.println(num(tester.problems.get(i)));
			}
			writer.close();

		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// run time
		//final long elapsedTimeMillis = System.currentTimeMillis() - startTime;
		//System.out.println("Run time is:" + elapsedTimeMillis);
		
	}

	islands(String file) {
		
		try {
			Scanner scanner = new Scanner(new File(file));

			numOfProblems = Integer.parseInt(scanner.nextLine());

			while (scanner.hasNextLine()) {
				String[] line = scanner.nextLine().split("\\s+");
				
				m = Integer.parseInt(line[0]);
				n = Integer.parseInt(line[1]);

				String text = scanner.useDelimiter("[0-9]").next();
				String[] s = text.split("\n");
				
				// create 2D array with pixels
				char[][] pixelArray = new char[m][n];
				// populate 2D array with file input
				for (int i = 0; i < m; i++) {
					for (int j = 0; j < n; j++) {
						pixelArray[i][j] = s[i].charAt(j);
					}
				}
				// add 2D array to problem list
				problems.add(pixelArray);
			}
			scanner.close();
		}
		// in case the file is not found
		catch (FileNotFoundException e) {
			System.out.println("File not found!");
			System.exit(1);
		}
	}
	
	// recursive helper method to calculate the number of islands
	public static int num(char[][] pixels) {
		
		int total = 0;
		
		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[i].length; j++) {
				if (pixels[i][j] == '-') {
					total++;
					islandSearch(pixels, i, j);
				}
			}
		}
		System.out.println(total);
		return total;
	}
	
	// helper method to search for islands
	public static void islandSearch(char[][] pixels, int x, int y) {
		// try-catch to ensure there is no index out of bounds error
		try {
			if (pixels[x][y] == '-') {

				pixels[x][y] = 0;
				
				// check pixel to the right
				islandSearch(pixels, x, y + 1);
				// check pixel above
				islandSearch(pixels, x + 1, y);
				// check pixel to the left
				islandSearch(pixels, x, y - 1);
				// check pixel below
				islandSearch(pixels, x - 1, y);

			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}

	}

}
