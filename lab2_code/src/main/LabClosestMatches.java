/**
 * 
 */
package main;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.ArrayList; 
import java.util.List; 

/**
 * @author 20171001234 xxx
 *
 *假设在 codes/lab1/目录下存在以下结构的文件组织：
*├─Java课内实习-201710001234-xxx-实习1
*│  ├─Java课内实习-20171000123-xxx-实习1
*│  │  └─lab1_code
*│  │      ├─rules
*│  │      └─turtle
*│  └─lab1_code
*│      ├─rules
*│      └─turtle
*├─Java课内实习-20171001235-xxx-实习一
*│  └─lab1
*│      └─lab1_code
*│          └─lab1_code
*│              ├─bin
*│              │  ├─rules
*│              │  └─turtle
*│              ├─rules
*│              └─turtle
*├─Java课内实习-20171001236-xxxx-实习一
*│  ├─rules
*│  └─turtle
*└─Java课内实习20171001237-xxxx-实习一
*    └─Java课内实习20171001237-xxx-实习一
*       └─Java课内实习20171001237-xxxx-实习一
*            └─lab1_code
*               ├─123
*                ├─rules
*                │  └─bin
*               └─turtle
*                    └─bin
*
*/
public class LabClosestMatches {
	
	static Map<String, Integer> dic1 = new HashMap<String, Integer>();
	static Map<String, Integer> dic2 = new HashMap<String, Integer>();
	
	
	public static double compare(Map<String, Integer>shorter,Map<String, Integer>longer)
	{
		double save = 0.0;
		for(String key:shorter.keySet())
		{
			if(longer.getOrDefault(key,-1)!=-1)
				save +=shorter.get(key)*longer.get(key);
		}
		
		double deno1 = 0.0;
		double deno2 = 0.0;
		for(String key:shorter.keySet())
			deno1+=Math.pow(shorter.get(key), 2);
		deno1 = Math.sqrt(deno1);
		for(String key:longer.keySet())
			deno2+=Math.pow(longer.get(key), 2);
		deno2 = Math.sqrt(deno2);
		double ans = save/(deno1*deno2);
		return ans;
	}
	/**
	 * 
	 * @param path  路径名
	 */
	
	public static void GetWord(String line,int num)
	{
		String word = "";
		for(char each:line.toCharArray()){
			//if (each==' ')continue;
			if((each>='A' && each<='Z') ||( each>='0' && each<='9') || ( each>='a' && each <= 'z')) 
            	word+=each;
			else{
				if (word.length()!=0){
					if(NotKeyWord(word)==true)
						AddWord(word,num);
					word = "";
				}
			}	
		}
		if (word.length()!=0){
			//System.out.println(word);
			AddWord(word,num);
		}
	}
	
	public static void AddWord(String word,int num)
	{
		
		if(num==1)
		{
			if (dic1.getOrDefault(word,-1)==-1)
				dic1.put(word,1);
			else
				dic1.put(word,dic1.get(word)+1);
		}
		else
		{
			if (dic2.getOrDefault(word,-1)==-1)
				dic2.put(word,1);
			else
				dic2.put(word,dic2.get(word)+1);
		}
	}
	
	
	public static double ReadFileByScaner(String filname,int num) throws FileNotFoundException
	{
		Scanner s = new Scanner(new File(filname));
		//System.out.println(num);
		if(num==1)
			dic1.clear();
		if(num==2)
			dic2.clear();
		while(s.hasNextLine())
		{
			GetWord(s.nextLine(),num); 
		}
		
		if (num==2)
		{
			if (dic1.size()<dic2.size())
        		return compare(dic1, dic2);
        	else
        		return compare(dic2, dic1);
		}
		return -1;
	}
	
	
	public static void problem1() throws FileNotFoundException
	{
		ReadFileByScaner("D:\\myjava\\lab2_code\\sample2.code",1);
		System.out.print("sample1.code和sample2.code的相似度为");
		System.out.println(ReadFileByScaner("D:\\myjava\\lab2_code\\sample2.code",2));
	}
	
	
	
	public static void problem2() throws FileNotFoundException
	{
		List<String>URLName = new ArrayList<String>();
		int num = 0;
		try {
			 Scanner sc = new Scanner(System.in); 
	         System.out.println("请输入文件的个数："); 
	         num  = sc.nextInt(); 
	         System.out.println("请输入文件名："); 
	         sc.nextLine();
	         for(int i = 0;i<num;i++)
	        	 URLName.add(sc.nextLine());
	         System.out.println("输入完毕"); 
	         }catch(Exception e){
	        	 System.out.println("输入有误");
	        	 return;
	         }
		double similiar = 0.0;
		String fil1 = "None";
		String fil2 = "None";
		for(int i = 0;i<URLName.size();i++)
		{
			ReadFileByScaner(URLName.get(i),1);
			for(int j = i+1;j<URLName.size();j++)
			{
				double mid = ReadFileByScaner(URLName.get(j),2);
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
	
	
	public static void problem3() throws FileNotFoundException
	{
		List<String>URLName = new ArrayList<String>();
		int num=0;
		try {
			 Scanner sc = new Scanner(System.in); 
	         System.out.println("请输入文件的个数："); 
	         num  = sc.nextInt(); 
	         sc.nextLine();
	         System.out.println("请输入文件名："); 
	         for(int i = 0;i<num;i++)
	        	 URLName.add(sc.nextLine());
	         System.out.println("输入完毕"); 
	         }catch(Exception e){
	        	 System.out.println("输入有误");
	         }
		for(int i = 0;i<URLName.size();i++)
			System.out.println(URLName.get(i));
		
		double[][] sin_arr = new double[num][num];
		for(int i = 0;i<URLName.size();i++)
		{
			ReadFileByScaner(URLName.get(i),1);
			for(int j = i+1;j<URLName.size();j++)
			{
				double similiar = ReadFileByScaner(URLName.get(j),2);
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
			System.out.println("文件"+URLName.get(i)+"\n与\n文件"+URLName.get(index));
			System.out.println("相似度中最高，为"+similiar+"");
		}
	}
	
	
	
	public static boolean NotKeyWord(String key)
	{
		String[] list =  {"for","while","public","static","void","main","null","String","int","Integer","boolean","Boolean","double","Double","float","Float","java","util","Map","HashMap","List","ArrayList","Set","HashSet","import","class"};
		for(String str:list)
		{
			if(key.equals(str))return false;
		}
		
		boolean pan = false;
		for(int i = 0;i<key.length();i++)
		{
			if(key.toCharArray()[i]<'0' || key.toCharArray()[i]>'9')
				pan = true;
		}

		return pan;
	}
	
	
	/**
	 * 用于评价各相关目录下，指定文件的相似性。
	 * Similarity   子目录1                        子目录2
	 * 100%        Java课内实习-201710001234-xxx-实习1     Java课内实习-201710001235-xxx-实习1
	 * 89%         Java课内实习-201710001234-xxx-实习1     Java课内实习-201710001236-xxx-实习1
	 * ....
	 * @param path 作业文件所在的目录，比如这里是：codes/lab1
	 * @param fileNameMatches：用来过滤进行比较的文件名的文件名或者正则表达式.
	 * 如 "DrawableTurtle.java"，"*.java","turtle/*.java"
	 * 如果一个子目录下有多个符合条件的文件，将多个文件合并成一个文件。
	 * 
	 * @param topRate:取值范围从[0,100],输出控制的阈值
	 * 	从高往低输出高于topRate%相似列表，如
	 * @param removeComments:是否移除注释内容	
	 * @throws FileNotFoundException 
	 * 	 */
	public static void closestCodes(String path, String fileNameMatches,double topRate,boolean removeComments) throws FileNotFoundException
	{
		//编译正则表达式
		Pattern pattern = Pattern.compile(fileNameMatches);
		
		//字典列表
		List<Map<String, Integer>>DicList = new ArrayList<Map<String, Integer>>();
		
		// 文件列表
		File file = new File(path);
		File[] files = file.listFiles();
		
        for(int i  =0;i<files.length;i++)
    	{
    		System.out.println(files[i].getName());
    		ReadFile.clear();
    		OpenFile(files[i].getAbsolutePath(),pattern);
    		Map<String, Integer> dic = new HashMap<String, Integer>();
    		for(String key:ReadFile.KeySet())
    			dic.put(key,ReadFile.Get(key));
    		System.out.println(dic);
    		DicList.add(dic);
            
    	}
		double[][] sin_arr = new double[DicList.size()][DicList.size()];
		for(int i  =0;i<DicList.size();i++)
			{
				for(int j = i+1;j<DicList.size();j++)
					{
				    	double sim = ReadFile.compare(DicList.get(i),DicList.get(j));
				    	sin_arr[i][j] = sim;
				    	sin_arr[j][i] = sim;
					}

				System.out.println(files[i].getName()+"的匹配程度为");
				for(int j = 0;j<DicList.size();j++)
				{
					if(j!=i)
					{
						System.out.print(String.format("%.2f", sin_arr[i][j]));
						System.out.println("  "+files[j].getName());
					}
					
				}
				System.out.println("--------------");
				
			}
	
	}
	
	public static void OpenFile(String path,Pattern pattern) throws FileNotFoundException
	{
		File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
               // System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                       // System.out.println("文件夹:" + file2.getAbsolutePath());
                        OpenFile(file2.getAbsolutePath(),pattern);
                    }
                    else {
                       // System.out.println("文件:" + file2.getAbsolutePath());
                    	ReadFile.ReadFileByScaner(file2.getAbsolutePath(),3);
                        //System.out.println(dic2);
                    }
                }
            }
        } 
   
	}
	
	
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		//String path = "D:\\myjava\\lab2_code\\lab1-codes-fortest";
		String path = "C:\\Users\\UncleDong\\Desktop\\课程实习相关\\java\\Java lab2\\lab1-codes-fortest";
		
		//字符串
		//String[] filename = {"turtle/*.java","DrawableTurtle.java","111.txt"};
			
		//正则表达式
		String regex = ".*\\.txt";
			
		/*
		//编译正则表达式
		Pattern pattern = Pattern.compile(regex);
				
		//匹配
		for(String filname:filename) {
			boolean rs = pattern.matcher(filname).matches();
			System.out.print(rs);
			}
			*/
		
		try
		{
		//System.out.println();
		//problem1();
		//problem2();
		//problem3();
		closestCodes(path,regex,100,true);
		}catch(Exception e){
       	 System.out.println("文件打开失败");
        }
	
	}

}
