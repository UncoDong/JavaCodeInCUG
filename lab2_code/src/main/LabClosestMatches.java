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
 *������ codes/lab1/Ŀ¼�´������½ṹ���ļ���֯��
*����Java����ʵϰ-201710001234-xxx-ʵϰ1
*��  ����Java����ʵϰ-20171000123-xxx-ʵϰ1
*��  ��  ����lab1_code
*��  ��      ����rules
*��  ��      ����turtle
*��  ����lab1_code
*��      ����rules
*��      ����turtle
*����Java����ʵϰ-20171001235-xxx-ʵϰһ
*��  ����lab1
*��      ����lab1_code
*��          ����lab1_code
*��              ����bin
*��              ��  ����rules
*��              ��  ����turtle
*��              ����rules
*��              ����turtle
*����Java����ʵϰ-20171001236-xxxx-ʵϰһ
*��  ����rules
*��  ����turtle
*����Java����ʵϰ20171001237-xxxx-ʵϰһ
*    ����Java����ʵϰ20171001237-xxx-ʵϰһ
*       ����Java����ʵϰ20171001237-xxxx-ʵϰһ
*            ����lab1_code
*               ����123
*                ����rules
*                ��  ����bin
*               ����turtle
*                    ����bin
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
	 * @param path  ·����
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
		System.out.print("sample1.code��sample2.code�����ƶ�Ϊ");
		System.out.println(ReadFileByScaner("D:\\myjava\\lab2_code\\sample2.code",2));
	}
	
	
	
	public static void problem2() throws FileNotFoundException
	{
		List<String>URLName = new ArrayList<String>();
		int num = 0;
		try {
			 Scanner sc = new Scanner(System.in); 
	         System.out.println("�������ļ��ĸ�����"); 
	         num  = sc.nextInt(); 
	         System.out.println("�������ļ�����"); 
	         sc.nextLine();
	         for(int i = 0;i<num;i++)
	        	 URLName.add(sc.nextLine());
	         System.out.println("�������"); 
	         }catch(Exception e){
	        	 System.out.println("��������");
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
		System.out.println("���Ƶ������ļ��ֱ�Ϊ��");
		System.out.println(fil1);
		System.out.println(fil2);
		System.out.println("���ƶ�Ϊ"+similiar);
	}
	
	
	public static void problem3() throws FileNotFoundException
	{
		List<String>URLName = new ArrayList<String>();
		int num=0;
		try {
			 Scanner sc = new Scanner(System.in); 
	         System.out.println("�������ļ��ĸ�����"); 
	         num  = sc.nextInt(); 
	         sc.nextLine();
	         System.out.println("�������ļ�����"); 
	         for(int i = 0;i<num;i++)
	        	 URLName.add(sc.nextLine());
	         System.out.println("�������"); 
	         }catch(Exception e){
	        	 System.out.println("��������");
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
			System.out.println("�ļ�"+URLName.get(i)+"\n��\n�ļ�"+URLName.get(index));
			System.out.println("���ƶ�����ߣ�Ϊ"+similiar+"");
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
	 * �������۸����Ŀ¼�£�ָ���ļ��������ԡ�
	 * Similarity   ��Ŀ¼1                        ��Ŀ¼2
	 * 100%        Java����ʵϰ-201710001234-xxx-ʵϰ1     Java����ʵϰ-201710001235-xxx-ʵϰ1
	 * 89%         Java����ʵϰ-201710001234-xxx-ʵϰ1     Java����ʵϰ-201710001236-xxx-ʵϰ1
	 * ....
	 * @param path ��ҵ�ļ����ڵ�Ŀ¼�����������ǣ�codes/lab1
	 * @param fileNameMatches���������˽��бȽϵ��ļ������ļ�������������ʽ.
	 * �� "DrawableTurtle.java"��"*.java","turtle/*.java"
	 * ���һ����Ŀ¼���ж�������������ļ���������ļ��ϲ���һ���ļ���
	 * 
	 * @param topRate:ȡֵ��Χ��[0,100],������Ƶ���ֵ
	 * 	�Ӹ������������topRate%�����б���
	 * @param removeComments:�Ƿ��Ƴ�ע������	
	 * @throws FileNotFoundException 
	 * 	 */
	public static void closestCodes(String path, String fileNameMatches,double topRate,boolean removeComments) throws FileNotFoundException
	{
		//����������ʽ
		Pattern pattern = Pattern.compile(fileNameMatches);
		
		//�ֵ��б�
		List<Map<String, Integer>>DicList = new ArrayList<Map<String, Integer>>();
		
		// �ļ��б�
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

				System.out.println(files[i].getName()+"��ƥ��̶�Ϊ");
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
               // System.out.println("�ļ����ǿյ�!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                       // System.out.println("�ļ���:" + file2.getAbsolutePath());
                        OpenFile(file2.getAbsolutePath(),pattern);
                    }
                    else {
                       // System.out.println("�ļ�:" + file2.getAbsolutePath());
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
		String path = "C:\\Users\\UncleDong\\Desktop\\�γ�ʵϰ���\\java\\Java lab2\\lab1-codes-fortest";
		
		//�ַ���
		//String[] filename = {"turtle/*.java","DrawableTurtle.java","111.txt"};
			
		//������ʽ
		String regex = ".*\\.txt";
			
		/*
		//����������ʽ
		Pattern pattern = Pattern.compile(regex);
				
		//ƥ��
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
       	 System.out.println("�ļ���ʧ��");
        }
	
	}

}
