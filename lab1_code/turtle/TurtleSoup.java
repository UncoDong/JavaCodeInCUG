/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package turtle;

import java.util.List;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

import java.math.*;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
    	turtle.forward(sideLength);
    	turtle.turn(90);
    	turtle.forward(sideLength);
    	turtle.turn(90);
    	turtle.forward(sideLength);
    	turtle.turn(90);
    	turtle.forward(sideLength);
    	turtle.turn(90);
        //throw new RuntimeException("implement me!");
    	
    }
    

    /**
     * 确定一个多边形的内角
     * 
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
    	
    	//sides = 边的数量 
    	double a = (sides-2)*180;
    	BigDecimal f = new BigDecimal(a/(sides));
    	if(a<=0)return 0;
    	double f4 = f.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    	return f4;
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
                //(side-2)/side = angle/180
        		//(180*side-360) = angle*side
                BigDecimal f = new BigDecimal(360.0/(180-angle));
                double f4 = f.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
        		//System.out.print(f4);
        		return (int)f4;
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        if(sides<=2)return;
    	double angle = 180-calculateRegularPolygonAngle(sides);
    	for(int i = 0;i<sides;i++)
        {
        	turtle.forward(sideLength);
        	turtle.turn(angle);
        }
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,
                                                 int targetX, int targetY) {
    	
    	currentBearing = currentBearing%360;
    	int x1 = 0;int y1 = 1;
    	int x2 = targetX-currentX;int y2 = targetY - currentY;
    	double mid = 0;
		mid = x1*x2+y1*y2/Math.sqrt( (Math.pow(x1, 2)+Math.pow(y1, 2))*(Math.pow(x2, 2)+Math.pow(y2, 2)) );
    	//垂直
		if(mid==0)
    	{
    		if(90-currentBearing<0)
    			return 360+90-currentBearing;
    		return 90-currentBearing;
    	}
    	//共线
    	if(mid==1) 
    	{
    		if(currentBearing==0)
    			return 0;
    		else
    			return 360-currentBearing;
    	}
		mid = Math.toDegrees(Math.acos(mid));
		if(y2*x1-x2*y1>0)
			mid = 360-mid;
		//System.out.println(Math.toDegrees(Math.acos(mid)));
		mid-=currentBearing;
		if(mid<0)
			mid+=360;
		return mid;
    }

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
    	List<Double> ans=new ArrayList<>();
    	int i = 0;
    	int frox = 0,froy = 0;
    	int x1,y1,x2,y2;
    	double mid = 0;
    	while(i<xCoords.size()-1)
    	{
    		x1 = xCoords.get(i) - frox;
    		x2 = xCoords.get(i+1) - xCoords.get(i);
    		y1 = yCoords.get(i) - froy;
    		y2 = yCoords.get(i+1) - yCoords.get(i);
    		if(x1==0&&y1==0)
    			mid = x2/Math.sqrt(Math.pow(x2, 2)+Math.pow(y2, 2));
    		else
    			mid = x1*x2+y1*y2/Math.sqrt( (Math.pow(x1, 2)+Math.pow(y1, 2))*(Math.pow(x2, 2)+Math.pow(y2, 2)) );
    		//System.out.println(mid);
    		mid = Math.toDegrees(Math.acos(mid));
    		if(y2*x1-x2*y1>0)
    			mid = 360-mid;
    		//System.out.println(mid);
    		ans.add(mid);
    		frox = xCoords.get(i);
    		froy = yCoords.get(i);
    		i++;
    	}
    	return ans;

    }
    
    
    /**
     * 
     * @param c = in
     * @param a = i
     * @param b = not
     * @return ca^ba 得到ca和ab向量之间的夹角
     */
   
    public static double cross(Point c, Point a, Point b) {
        return (c.x() - a.x()) * (a.y() - b.y()) - (c.y() - a.y()) * (a.x() - b.x());
    }
    /**
     * 
     * @param p1 第一个点
     * @param p2 第二个点
     * @return 两个点之间的距离
     */
    public static double distance(Point p1, Point p2) {
        return (Math.hypot((p1.x() - p2.x()), (p1.y() - p2.y())));
    }
    /**
     * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
     */
    public static Set<Point> convexHull(Set<Point> points) {
      Set<Point> ANS = new HashSet<Point>();
      if(points.size()==0)
    	  return ANS;
      Point mid = new Point(0,0);
      Point[] pa = (Point[])points.toArray(new Point[points.size()]);
      //排序
      for(int i = 0;i<pa.length-1;i++)
      {
    	  for(int j = i;j<pa.length-1;j++)
    	  {
    		  if(pa[j].x()>pa[j+1].x()||(pa[j].x()==pa[j+1].x()&&pa[j].y()>pa[j+1].y()))
    		  {
    			  mid = pa[j];
    			  pa[j] = pa[j+1];
    			  pa[j+1] = mid;
    		  }
    	  }
      }
      
      boolean[] vis;// 标pointA[i]是否已在凸包中
      vis = new boolean[pa.length];
      vis[0] = true;// 注意这里将point[0]标记为放进凸包,不过并没有真的放入队列
      int in = 0;//在凸包上的点
      while (true) 
      {
          int not = -1;
          for (int i = 0; i < pa.length ; i++) 
          {
        	  // 找一个不在凸包上的点
              if (!vis[i])
              {
                  not = i;
                  break;
              }
          }
          if (not == -1)
              break;// 找不到,结束
          for (int i = 0; i < pa.length; i++) {
              /*
               *  遍历所有点, 每个点都和现有最外侧的点比较,得到新的最外侧的点
               *  第二个条件是找到极点，不包括共线点
               */
              if ((cross(pa[in], pa[i], pa[not]) > 0)|| (cross(pa[in], pa[i], pa[not]) == 0)
                      && (distance(pa[in], pa[i]) > distance(pa[in], pa[not])))
                  not = i;
          }
          if (vis[not])
              break;// 找不到最外侧的点了
          ANS.add(pa[not]);
          vis[not] = true;// 标记这点已放进凸包了
          in = not;//in始终表示一个必定在凸包里的点
      }
      ANS.add(pa[0]);
      return ANS;
    }
    
    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle)
    {
    	
    	
    	double mid = 70.0;
    	for(int i = 0;i<36;i++)
        {
        	turtle.forward(100);
        	turtle.turn(mid);
        	if(i>30)
        	{
        		for(int j = 0;j<5;j++)
        		{
        			turtle.forward(100);
        			turtle.turn(180-35.97);
        		}
        	}
        }
    	

    	
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
    	
    	DrawableTurtle turtle = new DrawableTurtle();
    	drawPersonalArt(turtle);
    	turtle.draw();
    	
		/*
		Set<Point> points = new HashSet<Point>();
		Set<Point> convexHull = new HashSet<Point>();
		Point p11 = new Point(1, 1);
		Point p1010 = new Point(10, 10);
		Point p110 = new Point(1, 10);
		Point p12 = new Point(1, 2);
		Point p23 = new Point(2, 3);
		Point p32 = new Point(3, 2);
		points.add(p1010);
		convexHull.add(p1010);
		points.add(p110);
		convexHull.add(p110);
		points.add(p11);
		convexHull.add(p11);
		points.add(p12);
		convexHull = TurtleSoup.convexHull(points);
		Iterator<Point> value = convexHull.iterator();
		while (value.hasNext())
		{
		    Point s = value.next();
		    System.out.println(s.x()+" "+s.y());
		}  
   	 
        //System.out.print(calculateRegularPolygonAngle(7));
        //System.out.print(calculatePolygonSidesFromAngle(calculateRegularPolygonAngle(101)));
		List<Integer> xpoints = new ArrayList<>();
		List<Integer> ypoints = new ArrayList<>();
		xpoints.add(0);
		xpoints.add(1);
		xpoints.add(1);
		ypoints.add(0);
		ypoints.add(1);
		ypoints.add(2);

		//List<Double> result = calculateBearings(xpoints, ypoints);
    	
		System.out.println(calculateBearingToPoint(180,0,0,1,0));
		System.out.println(calculateBearingToPoint(0.0, 0, 0, 0, 1));
		System.out.println(calculateBearingToPoint(0.0, 0, 0, 1, 0));
		System.out.println(calculateBearingToPoint(1.0, 4, 5, 4, 6));
        //drawSquare(turtle, 100);
        //turtle.draw();
         
         */

    }

}
