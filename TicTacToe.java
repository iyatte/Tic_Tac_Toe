package Tic_Tac_Toe;

import java.util.Scanner;
import java.util.Arrays;


public class TicTacToe{
	// Initialize the fields
	static String[] board;
	static String[] iniNum;
	static int[] BoardNum;
	String ResultOneTime;
	int winningLines[][] = new int[][]{
		{ 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 1, 4, 7 },
		{ 2, 5, 8 }, { 3, 6, 9 }, { 1, 5, 9 }, { 3, 5, 7 }};
	static int XWin = 0;
	static int OWin = 0;
	static int DrawCnt = 0;
	
	// print board situation
	public void PrintBoard(String[] board){
		
		for (int i=0; i<4; i++) {
			System.out.println("+--+--+--+");
			if (i < 3) {
				System.out.print("|");
				for (int j=0; j<3; j++) {
					System.out.print(board[i*3+j]+" |");
				}
				System.out.println();
			}
		}
	}
	
	// calculate result
	// calculate the sum of three numbers in BoardNum, three numbers(8 cases) are all in winningLines
	public int GetResult(int[] BoardNum) {
		int CountNum = 0;
		for (int i=0; i<8; i++) {
			int numBak = 0;
			for (int j=0; j<3; j++) {
				numBak += BoardNum[winningLines[i][j]-1];
			}
			if (Math.abs(CountNum) < Math.abs(numBak))
				CountNum = numBak;
		}
		return CountNum;
	}
	
	// move
	// Update the array  synchronously as the player takes a step
	public void move(String flag, int pos, int num){
		board[pos] = flag;
		BoardNum[pos] = num;
	}
	
	// check valid of entering
	public Boolean CheckValid(int enterNum){
		Boolean validT = true;
		if (enterNum>9 | enterNum < 1 ) {
			validT = false;
			System.out.println("*** Warning!! Please enter a number from 1 to 9 !! ***");
		}
		else if (BoardNum[enterNum -1] != 0) {
			validT = false;
			System.out.println("*** Warning!! This position is already occupied, please select other positions!! ***");
		}
		
		return validT;
	}
	
	// Game
	// The full process of one game.
	public String TicTacToeOne() {
		board = new String[]{" ", " ", " ", " ", " ", " ", " ", " ", " "};
		iniNum = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
		BoardNum = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		int countnum = 0;
		Boolean resultOneStep = false;
		Boolean validT = true;
		System.out.println("****************************Guidance****************************");
		System.out.println("Please enter the corresponding number(1-9) in the following box \nto determine the position of your piece.");
		PrintBoard(iniNum);
		Scanner in = new Scanner(System.in);
		for (int i=0; i<9; i++) {
			if (resultOneStep == false){
				if (i%2 == 1){
					System.out.println("Player X Enter your move:");
					int pos = in.nextInt();
					validT = CheckValid(pos);
					while (validT == false) {
						System.out.println("Player X Enter your move:");
						pos = in.nextInt();
						validT = CheckValid(pos);
					}
					move("X", pos-1, -1);
					PrintBoard(board);
					countnum = GetResult(BoardNum);
					if (countnum == -3) {
						resultOneStep = true;
						ResultOneTime = "X";
						System.out.println("*** Player X wins!! ***");
						break;
					}
				}
				else {
					System.out.println("Player O Enter your move:");
					int pos = in.nextInt();
					while (validT == false) {
						System.out.println("Player O Enter your move:");
						pos = in.nextInt();
						validT = CheckValid(pos);
					}
					move("O", pos-1, 1);
					PrintBoard(board);
					countnum = GetResult(BoardNum);
					if (countnum == 3) {
						resultOneStep = true;
						ResultOneTime = "O";
						System.out.println("*** Player O wins!! ***");
						break;
					}
				}
			}
		}
		if (resultOneStep == false) {
			ResultOneTime = "Draw";
			System.out.println("*** Draw!! ***");
		}
		return ResultOneTime;
	}
	
	// main program
	public static void main(String[] args) {
		Boolean Continue = true;
		
		TicTacToe TicTacToe_new = new TicTacToe();
		Scanner in2 = new Scanner(System.in);

		while (Continue == true) {
			String resultRound = TicTacToe_new.TicTacToeOne();
			if (resultRound == "X")
				XWin += 1;
			else if (resultRound == "O")
				OWin += 1;
			else
				DrawCnt += 1;
			System.out.println("You want another round? (Y/N)");
			String willC = in2.next();
			if (willC.equals("N") | willC.equals("n")) {
				Continue = false;}
			else if (willC.equals("Y") | willC.equals("y")) {
				Continue = true;}
			else {
				System.out.println("I don't know what you're talking about, I guess you want to play one more game, let's get on with it.");}
		}
		System.out.println("****************************");
		System.out.println("The current performance is");
		System.out.println("Player O wins: "+OWin);
		System.out.println("Player X wins: "+XWin);
		System.out.println("Draw         : "+DrawCnt);
		System.out.println("See you next time!");
		System.exit(0); 
	}
	
}