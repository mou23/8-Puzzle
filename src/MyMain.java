
public class MyMain {
	public static void main(String[] args) {
		PuzzleSolver ps=new PuzzleSolver();
		//char [][]initialMatrix={{'7','2','4'},{'5',' ','6'},{'8','3','1'}};
		//char [][]initialMatrix={{'6','4','7'},{'8','5',' '},{'3','2','1'}};
		char [][]initialMatrix={{' ','1','3'},{'4','2','5'},{'7','8','6'}};
		//char [][]initialMatrix={{'1','2','3'},{'4','5','6'},{'8','7',' '}};

		ps.solvePuzzle(initialMatrix);
	}
}
