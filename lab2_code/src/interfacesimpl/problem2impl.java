package interfacesimpl;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import main.ReadFile;
import Interfaces.problem2;

public class problem2impl implements problem2{

	public void ClosestCodeMatch(List<String> URLName) {
		double similiar = 0.0;
		String fil1 = "None";
		String fil2 = "None";
		for(int i = 0;i<URLName.size();i++)
		{
			try {
				ReadFile.ReadFileByScaner(URLName.get(i),1);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return ;
			}
			for(int j = i+1;j<URLName.size();j++)
			{
				double mid;
				try {
					mid = ReadFile.ReadFileByScaner(URLName.get(j),2);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					return ;
				}
				if(mid>similiar)
				{
					similiar = mid;
					fil1 = URLName.get(i);
					fil2 = URLName.get(j);
				}
			}
		}
		System.out.println("相似的两个文件分别为：");
		System.out.println(fil1);
		System.out.println(fil2);
		System.out.println("相似度为"+similiar);	
	}

	
	public  void main(String[] args) throws FileNotFoundException 
	{
		List<String>URLName = new ArrayList<String>();
		URLName.add("D:\\myjava\\lab2_code\\lab1-codes-fortest\\Java课内实习20171001234-xxxx-实习一\\lab1_code\\turtle\\TurtleSoup.java");
		URLName.add("D:\\myjava\\lab2_code\\lab1-codes-fortest\\Java课内实习-20171001235-xxx-实习一\\lab1\\lab1_code\\lab1_code\\turtle\\TurtleSoup.java");
		URLName.add("D:\\myjava\\lab2_code\\lab1-codes-fortest\\Java课内实习-20171001235-xxx-实习一\\lab1\\lab1_code\\lab1_code\\turtle\\DrawableTurtle.java");
		URLName.add("D:\\myjava\\lab2_code\\lab1-codes-fortest\\Java课内实习-20171001235-xxx-实习一\\lab1\\lab1_code\\lab1_code\\turtle\\Action.java");
		URLName.add("D:\\myjava\\lab2_code\\lab1-codes-fortest\\Java课内实习-20171001235-xxx-实习一\\lab1\\lab1_code\\lab1_code\\turtle\\Turtle.java");
		URLName.add("D:\\myjava\\lab2_code\\lab1-codes-fortest\\Java课内实习-20171001235-xxx-实习一\\lab1\\lab1_code\\lab1_code\\turtle\\TurtleSoupTest.java");
		ClosestCodeMatch(URLName);
	}
}
