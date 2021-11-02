import java.io.*;
import java.util.Scanner;

/*
 * Client program that uses Stack Impementation in order to find the exit in a
 * maze that is given as a txt file input.
 */

public class Thiseas {

	/*
	 * Static class that holds coordinates
	 */

	static class Position {
		public int row;
		public int column;

		Position(int row, int column) {
			this.row = row;
			this.column = column;
		}
	}

	/**
	 * @param args The text file that represents the maze.
	 * @throws Exception is thrown if text document is not found or is in incorrect
	 *                   form.
	 */
	public static void main(String[] args) throws Exception {

		try {

			// txt file path
			String path = args[0];
			File file = new File(path);

			// Scanner object to read the txt file character by character
			Scanner input1 = new Scanner(file);

			// Buffered reader to read all lines
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line = reader.readLine();

			int line_counter = 0;

			while (line != null) {
				line_counter++;
				line = reader.readLine();
			}
			reader.close();

			/*
			 * Variable that holds the rows dimensions, -2 because the first 2 lines of the
			 * table are the dimensions of the table and the coordinates of E. Will be used
			 * in check_maze method.
			 */
			line_counter = line_counter - 2;

			// calling this method to check if the txt file is in correct form
			check_maze(input1, line_counter);
			input1.close();

			// Second scanner object to put txt in a char table
			Scanner input2 = new Scanner(file);

			int table[] = new int[4];
			for (int i = 0; i < 4; i++) {
				table[i] = Integer.parseInt(input2.next());
			}

			int rows = table[0];
			int columns = table[1];
			int e_x = table[2];
			int e_y = table[3];

			// Creating a position object that holds the coordinates of E
			Position E_pos = new Position(e_x, e_y);

			String character = "";

			/*
			 * Creating a char table that holds all elements of the txt files, first 2 lines
			 * are excluded.
			 */
			char buffer[][] = new char[rows][columns];

			for (int i = 0; i <= buffer.length - 1; i++) {
				for (int j = 0; j <= buffer[0].length - 1; j++) {
					character = input2.next();
					buffer[i][j] = character.charAt(0);
				}
			}
			input2.close();

			/*
			 * Checking if E is in a different position than the one the txt file mentions
			 */
			if (buffer[e_x][e_y] != 'E') {
				throw new Exception();
			}

			// solve_maze method is called to check if there is an exit in the maze
			solve_maze(E_pos, buffer);
		}

		catch (Exception e) {
			System.out.println("\n\nText File was not found, or is in Incorrect Form.");
		}
	}

	/**
	 * Searches for an exit in the maze.
	 * 
	 * @param Entry_pos The entry point of the maze.
	 * @param buffer    A 2d char table that represents the maze.
	 */

	private static void solve_maze(Position Entry_pos, char buffer[][]) {

		// Stack containing Position objects
		StringStackImpl<Position> stack = new StringStackImpl<>();

		// Entry point is the first element in the stack
		stack.push(Entry_pos);

		// Prints the coordinates of the entry
		System.out.print("\n\nENTRY POSITION:(" + Entry_pos.row + "," + Entry_pos.column + ")\n\n");

		while (true) {

			/*
			 * Checks if a right move is possible. If it is it places a 'd' character in
			 * these coordinates in the char table, so that we know we have already visited
			 * it in next searches. Also this position gets placed at the top of the stack,
			 * as it is the latest point we've visited.
			 */
			if ((stack.peek().column + 1) <= (buffer[0].length - 1)
					&& buffer[stack.peek().row][stack.peek().column + 1] == '0') {

				System.out.println("Move right");
				int y = stack.peek().column + 1;
				stack.push(new Position(stack.peek().row, y));
				buffer[stack.peek().row][y] = 'd';
			}

			// We work in a similar way for the left move.
			else if ((stack.peek().column - 1) >= 0 && buffer[stack.peek().row][stack.peek().column - 1] == '0') {

				System.out.println("Move left");
				int y = stack.peek().column - 1;
				stack.push(new Position(stack.peek().row, y));
				buffer[stack.peek().row][y] = 'd';
			}

			// Same steps.
			else if ((stack.peek().row - 1) >= 0 && buffer[stack.peek().row - 1][stack.peek().column] == '0') {

				System.out.println("Move up");
				int x = stack.peek().row - 1;
				stack.push(new Position(x, stack.peek().column));
				buffer[x][stack.peek().column] = 'd';
			}

			// Same steps.
			else if ((stack.peek().row + 1) <= (buffer.length - 1)
					&& buffer[stack.peek().row + 1][stack.peek().column] == '0') {

				System.out.println("Move down");
				int x = stack.peek().row + 1;
				stack.push(new Position(x, stack.peek().column));
				buffer[x][stack.peek().column] = 'd';
			}

			// When no move is possible
			else {

				/*
				 * If we come back to the entry point, then that means we've visited all
				 * possible points and there is no exit.
				 */
				if (stack.peek().row == Entry_pos.row && stack.peek().column == Entry_pos.column) {
					System.out.println("BACK TO ENTRY POINT, THERE IS NO ESCAPE!");
					break;
				}

				/*
				 * If we are not at the entry point, we backtrack by removing the current top of
				 * the stack. In the next loop we check which moves are available again.
				 */
				System.out.println("Dead End! Backtracking...\n");
				stack.pop();
				System.out.println("Current Position:(" + stack.peek().row + "," + stack.peek().column + ")" + "\n");
				continue;
			}

			// When a move has been made coordinates of the current position are printed
			System.out.println("Current Position:(" + stack.peek().row + "," + stack.peek().column + ")" + "\n");

			// Checking if we found an exit.
			if (stack.peek().row == buffer.length - 1 || stack.peek().column == buffer[0].length - 1
					|| stack.peek().row == 0 || stack.peek().column == 0) {

				System.out.println("CONGRATULATIONS,YOU FOUND A WAY OUT! EXIT OF THE LABYRINTH IS:(" + stack.peek().row
						+ "," + stack.peek().column + ")");

				break;
			}
		}
	}

	/**
	 * Checks if the maze has the desired format.
	 * 
	 * @param input        An input object that will scan through the txt file
	 * @param line_counter The total rows of the maze.
	 * @throws Exception is thrown if the maze is not in correct form.
	 */

	private static void check_maze(Scanner input, int line_counter) throws Exception {

		String character = "";

		// Counts all characters in the txt file
		int size_counter = 0;
		int rows = 0;
		int columns = 0;
		int e_x = 0;
		int e_y = 0;

		// Counts how many 'E' characters exist
		int E_counter = 0;

		try {

			while (input.hasNext()) {

				size_counter++;

				/*
				 * If we are at the first 4 characters, we check the dimensions of the maze and
				 * the coordinates of E. If for example, a String value instead of an int value
				 * is found, an Exception will be thrown.
				 */
				switch (size_counter) {
				case 1: {
					rows = Integer.parseInt(input.next());
					break;
				}

				case 2: {
					columns = Integer.parseInt(input.next());
					break;
				}

				case 3: {
					e_x = Integer.parseInt(input.next());
					break;
				}

				case 4: {
					e_y = Integer.parseInt(input.next());
					break;
				}

				default: {
					character = input.next();

					if (character.charAt(0) == 'E') {
						E_counter++;
					}

					else {
						/*
						 * If the text file contains a character that is not a 1 or 0 then Throw an
						 * Exception
						 */
						if (character.charAt(0) != '0' && character.charAt(0) != '1') {
							throw new Exception();
						}
					}
				}
				}
			}
			/*
			 * removing 4 characters (dimensions and coordinates of E), so we keep only the
			 * size of the characters of the maze
			 */
			size_counter = size_counter - 4;

			// If even 1 of these conditions is true, then text file is in incorrect form
			if (line_counter != rows || size_counter != rows * columns || E_counter != 1
					|| ((e_x != 0) && (e_x != rows - 1) && (e_y != 0 && e_y != columns - 1))) {

				throw new Exception();
			}
		}

		catch (Exception e) {
			System.out.println("\n\nIncorrect form of Text Document.");
			System.exit(1);
		}
	}

}
