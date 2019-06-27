package gameOfLife;

public class Model {
	
	//public static void main(String[]args) {
		
		//View mainView = new View();
		
		//mainView.setVisible(true);
	//}

	static int[][] currentState = { //testing array
			{0,1,0,0,0,0,0,0,0,0,0,0},
			{0,0,1,0,0,0,0,0,0,0,0,0},
			{1,1,1,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0}
	};
	static int rows;
	static int cols;

	Model(int rows, int cols){
		this.rows = rows;
		this.cols = cols;

	}

	public static void nextGeneration(int state[][], int row, int col){
		int futureState[][] = new int[row][col];
		//iterate through every cell
		for(int c = 0; c < col; c++){
			for(int r = 0; r < row; r++){
				//check for neighbours
				int neighbours = 0;
				if(state[wrapIndex(col, c-1)][wrapIndex(row, r-1)] == 1){ //top left
					neighbours++;
				}if(state[wrapIndex(col, c-1)][wrapIndex(row, r)] == 1){ //top middle
					neighbours++;
				}if(state[wrapIndex(col, c-1)][wrapIndex(row, r+1)] == 1){ //top right
					neighbours++;
				}if(state[wrapIndex(col, c)][wrapIndex(row, r-1)] == 1){ //middle left
					neighbours++;
				}if(state[wrapIndex(col, c)][wrapIndex(row, r+1)] == 1){ //middle right
					neighbours++;
				}if(state[wrapIndex(col, c+1)][wrapIndex(row, r-1)] == 1){ //bottom left
					neighbours++;
				}if(state[wrapIndex(col, c+1)][wrapIndex(row, r)] == 1){ //bottom middle
					neighbours++;
				}if(state[wrapIndex(col, c+1)][wrapIndex(row, r+1)] == 1){ //bottom right
					neighbours++;
				}

				//System.out.println(r+"/"+c+": "+neighbours);

				//game of life rules
				if(state[c][r] == 1 && neighbours < 2){
					futureState[c][r] = 0;
				}else if(state[c][r] == 1 && neighbours > 3){
					futureState[c][r] = 0;
				}else if(state[c][r] == 0 && neighbours == 3){
					futureState[c][r] = 1;
				}else{
					futureState[c][r] = state[c][r];
				}
				//System.out.println("---" + c + "/" + r);
			}
		}
		gameView.updatePanels(futureState);
		currentState = futureState;
	}

	private static int wrapIndex(int length, int pos){
		int index;
		if(pos < 0){
			index = (pos + length) % length;
		} else if(pos == 0){
			index = 0;
		} else {
			index = pos % length;
		}
		//System.out.println(pos + " " + index);
		return index;
	}

	public static void evolveGeneration(){
		for(int i = 0; i <= 500; i++){
			try{
				Thread.sleep(700);
				nextGeneration(currentState,rows,cols);
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
