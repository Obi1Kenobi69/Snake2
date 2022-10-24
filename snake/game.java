package snake;

public class game 
{
	public int [][] mas;
	public int napr;
	private int gX,gY;
	public int kol;
	public void start()
	{
		for(int i=0;i<30;i++) {
			for(int j = 0;j<30;j++) {
				mas[i][j]=0;
			}
		}
		napr = 0;
		kol = 0;
		gX = gY = 15;
		mas[15][15]=1;
		make_new();
	}
	public game()
	{
		mas = new int[30][30];
	}
	private void make_new()
	{
		while(true)
		{
			int x = (int)(Math.random()*30);
			int y = (int)(Math.random()*30);
			if(mas[y][x]==0)
			{
				mas[y][x] = -1;
				break;
			}
		}
	}

	public void peremGolova()
	{
		mas[gY][gX] = 0;
		if (napr==0)
		{
			if ((gX-1)>=0)
				gX--;
			else
				gX = 29;
				
		}
		else if (napr==1)
		{
			if ((gY-1)>=0)
				gY--;
			else
				gY = 29;
		}
		else if (napr==2)
		{
			if ((gX+1)<=29)
				gX++;
			else
				gX = 0;
		}
		else if (napr==3)
		{
			if ((gY+1)<=29)
				gY++;
			else
				gY = 0;
		}
		if (mas[gY][gX]==-1)
		{
			make_new();
			kol += 10;
		}
		mas[gY][gX] = 1;
	}
}
