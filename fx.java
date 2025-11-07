package main;
import java.util.concurrent.ThreadLocalRandom;

public class fx {
	public static Boolean GameOver = false;
	public static int DefSize;
	public static int SquaresLeft;
	////////////////////////////////////////////////////////////
	public static void SelectSquare(int pos, char c[], Boolean[] mines)
	{
		if(pos == 0)
			RevealMines(mines, c);
		else if (pos<1 || pos>c.length)
			System.out.println("Invalid");
		else
		{
			if (c[pos-1] == '⬛' && mines[pos-1] == false)
			{
				Boolean[] area = {true,true,true,true,true,true,true,true};
				SquaresLeft--;
				
				if ((pos-1)%(DefSize+1) == 0)
				{
					area[0] = false;					
					area[3] = false;					
					area[5] = false;					
				}
				if ((pos-1)%(DefSize+1) == DefSize)
				{
					area[2] = false;					
					area[4] = false;					
					area[7] = false;
				}
				if ((pos-1)/(DefSize+1) == 0)
				{
					area[0] = false;					
					area[1] = false;					
					area[2] = false;
				}
				if ((pos-1)/(DefSize+1) == DefSize)
				{
					area[5] = false;					
					area[6] = false;					
					area[7] = false;
				}
				//System.out.println(area[0]+" "+area[1]+" "+area[2]+" "+area[3]+" "+area[4]+" "+area[5]+" "+area[6]+" "+area[7]);
				c[pos-1] = numbers[V(area, mines, pos, DefSize)];
				
				
			}
			else if (c[pos-1] == '⬛' && mines[pos-1] == true)
			{
				RevealMines(mines,c);
				System.out.println("Game Over");
				GameOver = true;
			}
			if (SquaresLeft == 0)
			{
				System.out.println("You won!!!");
				GameOver = true;
			}
		}
	}
	////////////////////////////////////////////////////////////
	public static void DisplayGrid(char[] c)
	{
		int t = 0;
		for (int i=0;i<c.length;i++)
		{
			System.out.print(c[i]+" ");
			t+=1;
			if(t%Math.sqrt(c.length) == 0)
				System.out.println();
		}
	}
	/////////////////////////////////////////////////////////////
	public static void GenerateMines(Boolean[] mine, char[] grid, int pos)
	{
		int mines = (mine.length-mine.length/2)/2 - 1;
		SquaresLeft = mine.length - mines;
		int r;
		for (int i=0;i<mines;i++)
		{
			r = ThreadLocalRandom.current().nextInt(0,mine.length);
			if (mine[r] == false && r != pos-1)
			{
				mine[r] = true;
				//grid[r] = '❌';
			}
			else
			{
				i--;
			}
		}
	}
	/////////////////////////////////////////////////////////////
	public static void RevealMines(Boolean[] mine, char[] grid)
	{
		for (int i=0;i<mine.length;i++)
		{
			if (mine[i] == true)
				grid[i] = '❌';
		}
	}
	/////////////////////////////////////////////////////////////
	public static int V(Boolean[] area, Boolean[] mines, int pos, int s)
	{
		int sum = 0;
		pos--;
		s++;
		for (int i=0;i<8;i++)
		{
			if (area[i] == true)
			{
				if (i==0)
					sum+=(mines[pos-s-1]== true) ? 1 : 0;
				if (i==1)
					sum+=(mines[pos-s ]== true) ? 1  : 0;
				if (i==2)
					sum+=(mines[pos-s+1]== true) ? 1  : 0;					  //  //    _  _  _
				if (i==3)													 //  //	   |0||1||2|
					sum+=(mines[pos-1]== true) ? 1  : 0;					//  //	   |3|   |4|
				if (i==4)							  					   //  //	   |5||6||7|
					sum+=(mines[pos+1]== true) ? 1  : 0;				  //  //
				if (i==5)
					sum+=(mines[pos+s-1]== true) ? 1  : 0;
				if (i==6)
					sum+=(mines[pos+s  ]== true) ? 1  : 0;
				if (i==7)
					sum+=(mines[pos+s+1]== true) ? 1  : 0;
			}
		}
		//System.out.println(s);
		return sum;
	}
	/////////////////////////////////////////////////////////////
	public static char[] numbers = {'⚪','㊀','㊁','㊂','㊃','㊄','㊅','㊆','㊇'};
}
