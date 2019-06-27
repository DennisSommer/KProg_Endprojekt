package gameOfLife;
import javax.swing.*;
import java.awt.*;

public class Logic extends JFrame{

    //public static void main(String[] args){

        //Logic logi = new Logic(12,12, state);


    JPanel[][] panels;
    int[][] currentState;
    int rows;
    int cols;

    public Logic(int rows, int cols, int[][] state){
        this.rows = rows;
        this.cols = cols;

        //JFrame stuff
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setTitle("Game of Life");

        //JPanel grid
        JPanel mainPanel = new JPanel(new GridLayout(rows, cols));

        panels = new JPanel[rows][cols];
        this.currentState = state;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                panels[i][j] = new JPanel(new FlowLayout());
                if(state[i][j] == 1){
                    panels[i][j].setBackground(Color.RED);
                } else{
                    panels[i][j].setBackground(Color.GRAY);
                }
                panels[i][j].setMinimumSize(new Dimension(20,20));
                panels[i][j].setBorder(BorderFactory.createLineBorder(Color.WHITE));
                mainPanel.add(panels[i][j]);
            }
        }
        this.add(mainPanel);
        this.setVisible(true);
        evolveGeneration();
    }

    public void nextGeneration(int state[][], int row, int col){
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
       //change colors
       for(int r = 0; r < row; r++){
           for(int c = 0; c < col; c++){
               if(futureState[r][c] == 1){
                   panels[r][c].setBackground(Color.RED);
               }else{
                   panels[r][c].setBackground(Color.GRAY);
               }
           }
       }
       currentState = futureState; //store future generation in current generation
    }

    private int wrapIndex(int length, int pos){
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

    public void evolveGeneration(){
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
