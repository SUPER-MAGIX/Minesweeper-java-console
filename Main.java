package main;
import java.util.Scanner;

public class Main {
	public static int size;
	public static void main(String[] args) throws Exception
	{
		Scanner rd = new Scanner(System.in);
		
		while (true)
		{
			System.out.println("Enter grid size: ");
			size = rd.nextInt();
			if (size < 2)
				{
					size = 4;
				}
			fx.DefSize = size-1;
			size*=size;
			char[] grid = new char[size];
			Boolean[] mines = new Boolean[size];
			for (int i=0;i<size;i++)
			{
				grid[i] = '⬛';
				mines[i] = false;
			}
			fx.DisplayGrid(grid);
			int pos = rd.nextInt();
			fx.GenerateMines(mines, grid, pos);
			fx.SelectSquare(pos, grid, mines);
			fx.DisplayGrid(grid);
			while (fx.GameOver == false)
			{
				//fx.clearScreen();
				pos = rd.nextInt();
				//char choice = rd.next(".").charAt(0);
				fx.SelectSquare(pos, grid, mines);
				fx.DisplayGrid(grid);
			}
			System.out.print("Press Enter to restart...");
			System.in.read();
			fx.GameOver = false;
		}
	}
}

