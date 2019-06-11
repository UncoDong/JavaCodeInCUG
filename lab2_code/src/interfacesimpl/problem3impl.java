package interfacesimpl;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Interfaces.problem3;
import main.ReadFile;

public class problem3impl implements problem3{

	@Override
	public void ClosestCodeMatchs(List<String> URLName) {
		
		double[][] sin_arr = new double[URLName.size()][URLName.size()];
		for(int i = 0;i<URLName.size();i++)
		{
			try {
				ReadFile.ReadFileByScaner(URLName.get(i),1);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int j = i+1;j<URLName.size();j++)
			{
				double similiar = 0.0;
				try {
					similiar = ReadFile.ReadFileByScaner(URLName.get(j),2);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sin_arr[i][j] = similiar;
				sin_arr[j][i] = similiar;
			}
			
			double similiar = 0.0;
			int index = 0;
			for(int j = 0;j<URLName.size();j++)
			{
				if(sin_arr[i][j]>similiar)
				{
					similiar = sin_arr[i][j];
					index = j;
				}
			}
			System.out.println("�ļ�"+URLName.get(i)+"���ļ�"+URLName.get(index));
			System.out.println("���ƶ�����ߣ�Ϊ"+similiar);
		}
		
	}
	
	public  void main(String[] args) throws FileNotFoundException 
	{
		List<String>URLName = new ArrayList<String>();
		URLName.add("D:\\myjava\\lab2_code\\lab1-codes-fortest\\Java����ʵϰ20171001234-xxxx-ʵϰһ\\lab1_code\\turtle\\TurtleSoup.java");
		URLName.add("D:\\myjava\\lab2_code\\lab1-codes-fortest\\Java����ʵϰ-20171001235-xxx-ʵϰһ\\lab1\\lab1_code\\lab1_code\\turtle\\TurtleSoup.java");
		URLName.add("D:\\myjava\\lab2_code\\lab1-codes-fortest\\Java����ʵϰ-20171001235-xxx-ʵϰһ\\lab1\\lab1_code\\lab1_code\\turtle\\DrawableTurtle.java");
		URLName.add("D:\\myjava\\lab2_code\\lab1-codes-fortest\\Java����ʵϰ-20171001235-xxx-ʵϰһ\\lab1\\lab1_code\\lab1_code\\turtle\\Action.java");
		URLName.add("D:\\myjava\\lab2_code\\lab1-codes-fortest\\Java����ʵϰ-20171001235-xxx-ʵϰһ\\lab1\\lab1_code\\lab1_code\\turtle\\Turtle.java");
		URLName.add("D:\\myjava\\lab2_code\\lab1-codes-fortest\\Java����ʵϰ-20171001235-xxx-ʵϰһ\\lab1\\lab1_code\\lab1_code\\turtle\\TurtleSoupTest.java");
		ClosestCodeMatchs(URLName);
	}

}
