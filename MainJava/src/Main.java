import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class LienThong
{
	static int N;
	static boolean array[][];
	static List<Integer> list=new ArrayList<Integer>();
	static int result=0;
	static boolean firstVertex;
	
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("INPUT:");
		N=sc.nextInt();
		final int M=sc.nextInt();
		array=new boolean[N][N];
		
		for (int i=0; i<N; ++i)
		{
			list.add(i);
			for (int j=0; j<N; ++j) array[i][j]=false;
		}
		
		int u, v;
		for (int i=0; i<M; ++i)
		{
			u=sc.nextInt()-1; v=sc.nextInt()-1;
			array[u][v]=array[v][u]=true;
		}
		
		while (list.size()!=0)
		{
			firstVertex=true;
			dequy(list.remove(0));
		}
		
		System.out.println("\nOUTPUT:");
		System.out.println(result);
	}
	
	static void dequy(int vertex)
	{
		for (int i=0; i<N; ++i)
			if (list.contains(i) && array[vertex][i])
			{
				if (firstVertex)
				{
					firstVertex=false; ++result;
				}
				list.remove((Object)i);
				dequy(i);
			}
	}
}
