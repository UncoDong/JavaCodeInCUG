package main;

import java.io.FileNotFoundException;
import main.ReadFile;

public class problem1 {
	
	public static void First(String path1,String path2) throws FileNotFoundException
	{
		ReadFile.ReadFileByScaner(path1,1);
		System.out.print("sample1.code和sample2.code的相似度为");
		System.out.println(ReadFile.ReadFileByScaner(path2,2));
	}
	
	public static void main(String[] args) throws FileNotFoundException 
	{
		First("sample1.code","sample2.code");
	}
	

}
