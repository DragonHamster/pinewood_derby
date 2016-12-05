import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

public class Client {
	public static int DELIMITER = -1;
	
	public static void bubbleSort2D(int[][] array){
	//This is from a wikipedia article on bubble sort
	//		procedure bubbleSort( A : list of sortable items )
	//		   n = length(A)
	//		   repeat 
	//		     swapped = false
	//		     for i = 1 to n-1 inclusive do
	//		       /* if this pair is out of order */
	//		       if A[i-1] > A[i] then
	//		         /* swap them and remember something changed */
	//		         swap( A[i-1], A[i] )
	//		         swapped = true
	//		       end if
	//		     end for
	//		   until not swapped
	//		end procedure
		int n = array.length;
		while (true) {
			boolean swapped = false;
			for(int i = 0; i < n-1;  i++){
				if(array[i][1] > array[i+1][1]){
					int temp_index = array[i][0];
					int temp_val = array[i][1];
					array[i][0] = array[i+1][0];
					array[i][1] = array[i+1][1];
					array[i+1][0] = temp_index;
					array[i+1][1] = temp_val;
					swapped = true;
				} //end if
			} //end for
			if (swapped == false) { break; }
		} //end while
	}
	
	public static void printXx2Array(int[][] array){
		for(int i=0; i<array.length; ++i){
			System.out.println(array[i][0] + " " + array[i][1]);
		}
	}
	
	public static int[][] populateRaces(int[][] Races) throws IOException {
		Scanner inFile = new Scanner(new File("C:\\Users\\break\\Desktop\\AP_CompSci\\PinewoodDerby\\src\\Data"));
		// throw away the first two lines
		inFile.nextLine();
		inFile.nextLine();

		// iterating throuhg the data file until we reach the delimiter
		while (inFile.hasNext()) {
			int val = 0;
			// Assign the values in the text file to the Races array
			for (int row = 0; row < Races.length; row++) {
				for (int col = 0; col < Races[0].length; col++) {
					val = inFile.nextInt();
					if (val == DELIMITER) {
						break;
					}
					Races[row][col] = val;
				}
				if (val == DELIMITER) {
					break;
				}
			}
			if (val == DELIMITER) {
				break;
			}
		}

		return Races;
	}

	public static int[][] populatePlaces(int[][] Places) throws IOException {

		Scanner inFile = new Scanner(new File("C:\\Users\\break\\Desktop\\AP_CompSci\\PinewoodDerby\\src\\Data"));
		// throw away all lines up to and including the delimiter
		while (inFile.nextInt() != -1) {}
		

		// iterating through the data file until we reach the end of the
		// file
		while (inFile.hasNext()) {
			int val = 0;
			// Assign the values in the text file to the Places array
			for (int row = 0; row < Places.length; row++) {
				for (int col = 0; col < Places[0].length; col++) {
					val = inFile.nextInt();
					Places[row][col] = val;
				}
			}
		}
			return Places;
	}

	public static int[][] populateResults(int[][] Results) throws IOException {
		for(int row = 0; row < Results.length; row++){
			Results[row][0] = row+1;
		}
		return Results;
	}
	
	public static int getScore(int racer, int[][] Races, int[][] Places){
		int tally=0;
		for(int i = 0; i < Races.length; i++){
			for(int j = 0; j< Races[0].length; j++){
				if(Races[i][j] == racer){
					tally += Places[i][j];
				}
			}
		}
		return tally;
	}
	
	public static void setResults(int racer, int score, int[][] Results){
		for(int i =0; i < Results.length; i++){
			if(Results[i][0] == racer){
				Results[i][1] = score;
			}
		}
	}
	
	//findFirst(Results)
	public static int findFirst(int[][] Results){
		int minimum = 0;
		int personIndex = 0;
		boolean notEqual = true;
		while(notEqual){
			if(Results[minimum][1] == Results[minimum+1][1]){
				personIndex++;
				minimum++;
			}
			else{
				notEqual = false;
			}
		}
		return personIndex;

	}
	
	public static int findNextScoreChange(int[][] Results, int startingIndex){
		int minimum = startingIndex+1;
		int personIndex = startingIndex+1;
		boolean notEqual = true;
		while(notEqual){
			if(Results[minimum][1] == Results[minimum+1][1]){
				personIndex++;
				minimum++;
			}
			else{
				notEqual = false;
			}
		}
		return personIndex;
	}
	
	public static int getNumberOfRacers(String filename) throws IOException {
		int rows = 0;
		Scanner inFile = new Scanner(new File(filename));
		rows = inFile.nextInt();
		return rows;
	}

	public static void main(String[] args) throws IOException {
		int rows = getNumberOfRacers("C:\\Users\\break\\Desktop\\AP_CompSci\\PinewoodDerby\\src\\Data");
		int numberOfRacers = rows;
		
		if (rows != 30) {
			System.out.println("getNumberOfRacers Failed");
			System.exit(1);
		}

		int[][] Races = new int[rows][5];

		if (Races.length != 30 || Races[0].length != 5) {
			System.out.println("Races declared incorrectly");
			System.exit(1);
		}

		populateRaces(Races);
		if (Races[0][0] != 28 || Races[0][1] != 27 || Races[0][2] != 25 || Races[0][3] != 22 || Races[0][4] != 18) {
			System.out.println("Races defined incorrectly");
			System.exit(1);
		}

		int[][] Places = new int[rows][5];
		if (Places.length != 30 || Places[0].length != 5) {
			System.out.println("Places declared incorrectly");
			System.exit(1);
		}
		populatePlaces(Places);
		if (Places[0][0] != 3 || Places[0][1] != 4 || Places[0][2] != 5 || Places[0][3] != 2 || Places[0][4] != 1) {
			System.out.println("Places defined incorrectly");
			System.exit(1);
		}
		
		int[][] Results = new int[rows][2];
		if (Results.length != 30 || Results[0].length != 2) {
			System.out.println("Results declared incorrectly");
			System.exit(1);
		}
		populateResults(Results);
		if(Results[0][0] != 1 || Results[29][0] != 30){
			System.out.println("Results defined incorrectly");
			System.exit(1);
		}
		
		for(int racer = 1; racer <= numberOfRacers; racer++){
			int score = getScore(racer, Races, Places);
			
			if(racer == 1 && score != 17){
				System.out.println("getScore returned an incorrect value");
				System.exit(1);
			}
			if(racer == 28 && score != 16){
				System.out.println("getScore returned an incorrect value");
				System.exit(1);
			}
			
			setResults(racer, score, Results);
			if(Results[0][1] != 17){
				System.out.println("setReults returned an incorrect value");
				System.exit(1);
			}
		}
		
		int[][] testArray = new int[5][2];
		testArray[0][0] = 1;
		 testArray[0][1] = 4;
		testArray[1][0] = 2;
		 testArray[1][1] = 2;
		testArray[2][0] = 3;
		 testArray[2][1] = 1;
		testArray[3][0] = 4;
		 testArray[3][1] = 6;
		testArray[4][0] = 4;
		 testArray[4][1] = 3;
		
		bubbleSort2D(testArray);
		
		if(testArray[0][1] != 1 && testArray[4][1] != 6){
			System.out.println("bubbleSort2D failed");
			System.exit(1);
		}
//		printXx2Array(testArray);

		bubbleSort2D(Results);
//		System.out.println("Race Results");
//		printXx2Array(Results);
		
		//Finds the first place winner(s)
		int firstResult = findFirst(Results);
		if(firstResult != 0){
			System.out.println("findFirst failed");
			System.exit(1);
		}
		
		int secondResult = findNextScoreChange(Results, firstResult);
		if(secondResult != 1){
			System.out.println("findSecond failed");
			System.exit(1);
		}
		
		int thirdResult = findNextScoreChange(Results, secondResult);
		if(thirdResult != 2){
			System.out.println("findThird failed");
			System.exit(1);
		}
		
		int fourthResult = findNextScoreChange(Results, thirdResult);
		if(fourthResult != 3){
			System.out.println("findFourth failed");
			System.exit(1);
		}
		
		int fifthResult = findNextScoreChange(Results, fourthResult);
		if(fifthResult != 6){
			System.out.println("findFifth failed");
			System.exit(1);
		}
		
		System.out.println("All tests passed");
		
		System.out.print("FIRST PLACE: ");
		if(firstResult==0){
			System.out.println(Results[0][0]);
		}else{
			for(;firstResult> 0; firstResult--){
				System.out.printf("%d ", Results[firstResult][0]);
			}
			System.out.println();
		}
		
		System.out.print("SECOND PLACE: ");
		if(secondResult==firstResult+1){
			System.out.println(Results[secondResult][0]);
		}else{
			for(;secondResult> firstResult; secondResult--){
				System.out.printf("%d ", Results[secondResult][0]);
			}
			System.out.println();
		}
		
		System.out.print("THIRD PLACE: ");
		if(thirdResult==secondResult+1){
			System.out.println(Results[thirdResult][0]);
		}else{
			for(;thirdResult> secondResult; thirdResult--){
				System.out.printf("%d ", Results[thirdResult][0]);
			}
			System.out.println();
		}
		
		System.out.print("FOURTH PLACE: ");
		if(fourthResult==thirdResult+1){
			System.out.println(Results[fourthResult][0]);
		}else{
			for(;fourthResult> thirdResult; fourthResult--){
				System.out.printf("%d ", Results[fourthResult][0]);
			}
			System.out.println();
		}
		
		System.out.print("FIFTH PLACE: ");
		if(fifthResult==fourthResult+1){
			System.out.println(Results[fifthResult][0]);
		}else{
			for(;fifthResult> fourthResult; fifthResult--){
				System.out.printf("%d ", Results[fifthResult][0]);
			}
			System.out.println();
		}
	}
}
