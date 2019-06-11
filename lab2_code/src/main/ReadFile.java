package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ReadFile {
	
	public ReadFile() {}
	
	static Map<String, Integer> dic1 = new HashMap<String, Integer>();
	static Map<String, Integer> dic2 = new HashMap<String, Integer>();
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
	 * @param path  Â·¾¶Ãû
	 */

	
	public static Set<String> GetWord(String line,int num)
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
			if(NotKeyWord(word)==true)
				AddWord(word,num);
		}
		if(num==1)
			return dic1.keySet();
		return dic2.keySet();
	}
	
	public static  Set<String> AddWord(String word,int num)
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
		if(num==1)
			return dic1.keySet();
		return dic2.keySet();
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
	
	public static void clear()
	{
		dic2.clear();
		dic2.keySet();
	}
	
	public static Set<String> KeySet()
	{
		return dic2.keySet();
	}
	
	public static int Get(String key)
	{
		return dic2.getOrDefault(key,-1);
	}

}
