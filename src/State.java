
public class State {
	char [][]matrix;
	char [][] parent;
	int numberOfMisplacedTiles;
	public State() {
		this.matrix = new char[3][3];
		this.parent = null;
	}
}
