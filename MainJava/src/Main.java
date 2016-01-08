class MyPoint
{
	private double x, y;
	
	public MyPoint(double x, double y)
	{
		this.x=x; this.y=y;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public boolean equals(MyPoint p)
	{
		return ( p.x==x && p.y==y );
	}
	
	public double getDistance(MyPoint p)
	{
		return Math.sqrt(Math.pow(p.x-x,2)+Math.pow(p.y-y,2));
	}
}

class TriAngle2D
{
	private MyPoint p1, p2, p3;
	
	public TriAngle2D(MyPoint p1, MyPoint p2, MyPoint p3)
	{
		this.p1=p1; this.p2=p2; this.p3=p3;
	}
	
	public TriAngle2D()
	{
		this(new MyPoint(0,0), new MyPoint(1,1), new MyPoint(2,5));
	}
	
	
	
	public double getPerimeter()
	{
		return p1.getDistance(p2)+p2.getDistance(p3)+p3.getDistance(p1);
	}
	
	private static final double getArea(MyPoint p1, MyPoint p2, MyPoint p3)
	{
		final double A=p1.getDistance(p2), B=p2.getDistance(p3), C=p3.getDistance(p1);
		final double P=(A+B+C)/2;
		return Math.sqrt(P*(P-A)*(P-B)*(P-C));
	}
	
	public double getArea()
	{
		return getArea(p1,p2,p3);
	}
	
	public boolean contains(MyPoint p)
	{
		final double AREA=getArea(), AREA1=getArea(p1,p2,p), AREA2=getArea(p1,p3,p), AREA3=getArea(p2,p3,p);
		return ( AREA == AREA1 + AREA2 + AREA3 );
	}
	
	public boolean contains(TriAngle2D t)
	{
		return ( contains(t.p1) && contains(t.p2) && contains(t.p3) );
	}
	
	public boolean overlaps(TriAngle2D t)
	{
		if (contains(t)) return false;
		MyPoint lineArrayA[][]={ {t.p1,t.p2}, {t.p2,t.p3}, {t.p3,t.p1} };
		MyPoint lineArrayB[][]={ {p1,p2}, {p2,p3}, {p3,p1} };
		
		for (MyPoint[] lineA:lineArrayA)
		{
			double vectoA[]={ (lineA[1].getX()-lineA[0].getX()), (lineA[1].getY()-lineA[0].getY()) };
			for (MyPoint[] lineB:lineArrayB)
			{
				double vectoB[]={ (lineB[1].getX()-lineB[0].getX()), (lineB[1].getY()-lineB[0].getY()) };
				final double DET=vectoB[0]*vectoA[1]-vectoA[0]*vectoB[1];
				if (DET==0) continue;
				double temp=(vectoB[1]*(lineA[0].getX()-lineB[0].getX())+vectoB[0]*(lineB[0].getY()-lineA[0].getY()))/DET;
				MyPoint M=new MyPoint( (lineA[0].getX()+temp*vectoA[0]), (lineA[0].getY()+temp*vectoA[1]) );
				if ( M.equals(lineA[0])||M.equals(lineA[1])||M.equals(lineB[0])||M.equals(lineB[1]) ) continue;
				
				if ( (M.getDistance(lineA[0])+M.getDistance(lineA[1])==lineA[0].getDistance(lineA[1])) && (M.getDistance(lineB[0])+M.getDistance(lineB[1])==lineB[0].getDistance(lineB[1])) ) return true;
			}
		}
		
		return false;
	}
}
