import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    int row;
    int col;
    int mineCount;
    int [][] map;
    String [][] gameBoard; //Map's elements is hidden by this array with #
    Scanner scan = new Scanner(System.in);
    Random random = new Random();
    int getNumberFromUser(){
        String rawNumber = scan.nextLine();
        int number;
        try{
            number = Integer.parseInt(rawNumber);
        }
        catch (Exception e){
            System.out.println("--->Please enter a positive integer number.");
            number = getNumberFromUser();
        }
        if(number<=0){
            System.out.print("Check your number and re-enter it: ");
            number = getNumberFromUser();
        }
        return number;
    }
    void createMap(){
        System.out.print("Enter land row: ");
        this.row = getNumberFromUser();
        System.out.print("Enter land column: ");
        this.col = getNumberFromUser();
        this.map = new int[row][col];
        this.gameBoard = new String[row][col];
        this.mineCount = (this.row*this.col)/4;
    }
    void print(int [][] arr){
        for (int i = 0; i < arr.length ; i++) {
            for (int j = 0; j < arr[0].length ; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
    void print(String [][] arr){
        for (int i = 0; i < arr.length ; i++) {
            for (int j = 0; j < arr[0].length ; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
    void fillArrays(){
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length ; j++) {
                gameBoard[i][j] = "#";
            }
        }
         int count = 0;
        int row, col;
        while(count != this.mineCount){
            row = random.nextInt(this.row);
            col = random.nextInt(this.col);
            if(map[row][col]==0){
                map[row][col] = -1;
                count++;
            }
        }

    }
    boolean selectLocation(){
        boolean continueGame;
        int locationRow, locationCol;
        boolean isInputWrong;
        do {
            System.out.print("Select your location's row: ");
            locationRow = getNumberFromUser();
            if(locationRow > this.row){
                System.out.printf("You must select between 1 and %d%n",this.row);
                isInputWrong = true;
            }
            else isInputWrong = false;
        }
        while (isInputWrong);

        do {
            System.out.print("Select your location's col: ");
            locationCol = getNumberFromUser();
            if(locationCol > this.row){
                System.out.printf("You must select between 1 and %d%n",this.row);
                isInputWrong = true;
            }
            else isInputWrong = false;
        }
        while (isInputWrong);
        return checkLocation(locationRow,locationCol);
    }
    boolean checkLocation(int row, int col){
        boolean continueGame;
        --row;
        --col;
        //Check the location was selected before by player
        if(!gameBoard[row][col].equals("#")){
            System.out.println("This location have already selected!");
            selectLocation();
        }
        //Check the location have mine
        if(map[row][col] == -1 ){
            System.out.print("< Game Over >");
            continueGame = false;
        }
        else{
            continueGame = true;
            if(row!=0 && col!=0 && row<=this.row-2 && col<=this.row-2){
                if(map[row+1][col] == -1) map[row][col]++;
                if(map[row+1][col+1] == -1) map[row][col]++;
                if(map[row+1][col-1] == -1) map[row][col]++;
                if(map[row][col-1] == -1) map[row][col]++;
                if(map[row][col+1] == -1) map[row][col]++;
                if(map[row-1][col-1] == -1) map[row][col]++;
                if(map[row-1][col+1] == -1) map[row][col]++;
                if(map[row-1][col] == -1) map[row][col]++;
            } //EDGES
            //Upper Left
            else if (row==0 && col==0) {
                if(map[row][col+1] == -1) map[row][col]++;
                if(map[row+1][col] == -1) map[row][col]++;
                if(map[row+1][col+1] == -1) map[row][col]++;
            }
            //Lower Left
            else if (row==this.row-1 && col==0) {
                if(map[row-1][col] == -1) map[row][col]++;
                if(map[row-1][col+1] == -1) map[row][col]++;
                if(map[row][col+1] == -1) map[row][col]++;
            }
            //Upper Right
            else if (row==0 && col==this.row-1) {
                if(map[row][col-1] == -1) map[row][col]++;
                if(map[row+1][col] == -1) map[row][col]++;
                if(map[row+1][col-1] == -1) map[row][col]++;
            }
            //Lower Right
            else if (row==this.row-1 && col==this.col-1) {
                if(map[row-1][col] == -1) map[row][col]++;
                if(map[row-1][col-1] == -1) map[row][col]++;
                if(map[row][col-1] == -1) map[row][col]++;
            }
            //ROWS
            //Upper row
            else if (row==0) {
                if(map[row+1][col] == -1) map[row][col]++;
                if(map[row+1][col+1] == -1) map[row][col]++;
                if(map[row+1][col-1] == -1) map[row][col]++;
                if(map[row][col-1] == -1) map[row][col]++;
                if(map[row][col+1] == -1) map[row][col]++;
            }
            //Lower row
            else if (row==this.row-1) {
                if(map[row-1][col] == -1) map[row][col]++;
                if(map[row-1][col+1] == -1) map[row][col]++;
                if(map[row-1][col-1] == -1) map[row][col]++;
                if(map[row][col-1] == -1) map[row][col]++;
                if(map[row][col+1] == -1) map[row][col]++;
            }
            //COLUMNS
            //Left Column
            else if (col==0) {
                if(map[row][col+1] == -1) map[row][col]++;
                if(map[row-1][col] == -1) map[row][col]++;
                if(map[row-1][col+1] == -1) map[row][col]++;
                if(map[row+1][col] == -1) map[row][col]++;
                if(map[row+1][col+1] == -1) map[row][col]++;
            }
            //Right Column
            else if (col==this.row-1) {
                if(map[row][col-1] == -1) map[row][col]++;
                if(map[row+1][col-1] == -1) map[row][col]++;
                if(map[row-1][col-1] == -1) map[row][col]++;
                if(map[row+1][col] == -1) map[row][col]++;
                if(map[row-1][col] == -1) map[row][col]++;
            }
        }
        swipeMapAndBoard(row,col);
    return continueGame;
    }
    void swipeMapAndBoard(int row, int col){
        gameBoard[row][col] = String.valueOf(map[row][col]);
    }
    void gameStart(){
        createMap();
        fillArrays();
        int counter = -1;
        do {
           // print(map);
            print(gameBoard);
            counter++;
            if(this.mineCount+counter==this.row*this.col){
                System.out.println("<>  You win this game!!! <> ");break;
            }
        }while (selectLocation());
    }

}
