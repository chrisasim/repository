import java.util.*;
import java.util.Random;
public class MiniMax
{
	public static void main(String... arg)
	{
		String[][] arr = {{"_", "_", "_"}, {"o", "_", "o"}, {"_", "_", "_"}};
		System.out.println(Arrays.deepToString(arr).replace("],","\n").replace(",","\t| ").replaceAll("[\\[\\]]", " "));
		System.out.println("Press 1 to start or 0 to exit");
		Scanner scanner = new Scanner(System.in);
		int mode = scanner.nextInt();
		int row;
		int col;
		int count=0;
		int player_scores = 0;
		int bot_scores = 0;
		Random rand = new Random();
		String[] act = {"s","o"};
		String player = "player";
		String bot = "bot";
		boolean isPlayerTurn = true;
		while (mode!=1 && mode!=0)
		{
			System.out.println("Press 1 to start or 0 to Exit");
			mode = scanner.nextInt();
		}
		if (mode == 1)
		{
			if (player=="player" && bot=="bot")
			{
				while (count!=7)
				{
					if (isPlayerTurn == true)
					{
						System.out.println(player+" turn");
						row = scanner.nextInt();
						col = scanner.nextInt();
						while (row < 0 || row > 2)
						{
							System.out.println("Row must be 0-2");
							row = scanner.nextInt();
						}
						while (col < 0 || col > 2)
						{
							System.out.println("Col must be 0-2");
							col = scanner.nextInt();
						}
						if (arr[row][col] == "_")
						{
							System.out.println("s or o");
							String playeract = scanner.nextLine();
							playeract = scanner.nextLine();
							arr[row][col] = playeract;
							count+=1;
							player_scores += checkScore(arr, row, col);
							isPlayerTurn = false;
						}
						else if (arr[row][col] != "_")
						{
							isPlayerTurn = false;
						}
					}
					else
					{
						//System.out.println(bot+" turn");
						row = rand.nextInt(3);
						col = rand.nextInt(3);
						int indexact = rand.nextInt(act.length);
						String finalact = act[indexact];
						//System.out.println(finalact);
						while ((row==1 || col==0) && (row==1 || col==2))
						{
							row = rand.nextInt(3);
							col = rand.nextInt(3);
						}
						arr[row][col] = finalact;
						System.out.println(Arrays.deepToString(arr)
			                         .replace("],","\n").replace(",","\t| ")
                        			 .replaceAll("[\\[\\]]", " "));
						count+=1;
						bot_scores+=checkScore(arr, row, col);
						isPlayerTurn = true;
					}
				}
				System.out.println("player score"+player_scores);
				System.out.println("bot scores"+bot_scores);
				System.out.println(Arrays.deepToString(arr)
	                         .replace("],","\n").replace(",","\t| ")
        	                 .replaceAll("[\\[\\]]", " "));
				if(player_scores>bot_scores)
				{
					System.out.println("you win");
				}
				else if (player_scores<bot_scores)
				{
					System.out.println("you lose");
				}
				else
				{
					System.out.println("standoff");
				}
			}

		}
		else if (mode == 0)
		{
			System.out.println("End of game");
			System.exit(0);
		}
	}


	public static int checkScore(String[][] arr, int row, int col)
	{
		int total_score = 0;
		if (arr[row][col] == "s")
		{
			if (row==0)
			{
				if (col==0)
				{
					if (arr[row+1][col+1] == "o" && arr[row+2][col+2]=="s")
					{
						total_score+=1;
					}
					else if (arr[row+1][col] == "o" && arr[row+2][col]=="s")
					{
						total_score+=1;
					}
					else if (arr[row][col+1] =="o" && arr[row][col+2]=="s")
					{
						total_score+=1;
					}
					else
					{
						total_score=0;
					}
				}
				else if (col==1)
				{
					if (arr[row+1][col] == "o" && arr[row+2][col] == "s")
					{
						total_score+=1;
					}
					else
					{
						total_score=0;
					}
				}
				else if (col==2)
				{
					if (arr[row][col-1] == "o" && arr[row][col-2] == "s")
					{
						total_score+=1;
					}
					else if (arr[row+1][col-1] == "o" && arr[row+2][col-2]=="s")
					{
						total_score+=1;
					}
					else if (arr[row+1][col] == "o" && arr[row+2][col] == "s")
					{
						total_score+=1;
					}
					else
					{
						total_score+=0;
					}
				}
			}
			//Second row should not be examined since it does not give any match
			else if (row==1)
			{
				total_score+=0;
			}
			else if (row==2)
			{
				if (col==0)
				{
					if (arr[row-1][col] == "o" && arr[row-2][col] == "s")
					{
						total_score+=1;
					}
					else if (arr[row-1][col+1]=="o" && arr[row-2][col+2]=="s")
					{
						total_score+=1;
					}
					else
					{
						total_score+=0;
					}
				}
				else if (col==1)
				{
					if (arr[row-1][col]=="o" && arr[row-2][col]=="s")
					{
						total_score+=1;
					}
					else
					{
						total_score+=0;
					}
				}
				else if (col==2)
				{
					if (arr[row][col-1] == "o" && arr[row][col-1] == "s")
					{
						total_score+=1;
					}
					else if (arr[row-1][col-1]=="o" && arr[row-2][col-2]=="s")
					{
						total_score+=1;
					}
					else
					{
						total_score+=0;
					}
				}
			}
		}
		else if (arr[row][col] == "o")
		{
			if (row==0)
			{
				if (col==0)
				{
					total_score+=0;
				}
				else if (col==1)
				{
					if (arr[row][col-1]=="s" && arr[row][col+1] =="s")
					{
						total_score+=1;
					}
					else
					{
						total_score+=0;
					}
				}
				else if (col==2)
				{
					total_score+=0;
				}
			}
			else if (row==1)
			{
				if (col==0)
				{
					if (arr[row-1][col]=="s" && arr[row+1][col]=="s")
					{
						total_score+=1;
					}
					else
					{
						total_score+=0;
					}
				}
				else if (col==1)
				{
					if (arr[row-1][col-1]=="s" && arr[row+1][col+1]=="s")
					{
						total_score+=1;
					}
					else if (arr[row-1][col]=="s" && arr[row+1][col]=="s")
					{
						total_score+=1;
					}
					else if (arr[row-1][col+1]=="s" && arr[row+1][col-1]=="s")
					{
						total_score+=1;
					}
					else
					{
						total_score+=0;
					}
				}
				else if (col==2)
				{
					if (arr[row-1][col] == "s" && arr[row+1][col]=="s")
					{
						total_score+=1;
					}
					else
					{
						total_score+=0;
					}
				}
			}
			else if (row==2)
			{
				if (col==0)
				{
					total_score+=0;
				}
				else if (col==1)
				{
					if (arr[row][col-1]=="s" && arr[row][col+1]=="s")
					{
						total_score+=1;
					}
					else
					{
						total_score+=0;
					}
				}
				else if (col==2)
				{
					total_score+=0;
				}
			}
		}
		else
		{
			total_score+=0;
		}
		return total_score;
	}
}
