import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.PrintWriter;

public  class RandomInteger {
		public static int determineLCM(int a, int b) {
		int num1, num2;
		if (a > b) {
			num1 = a;
			num2 = b;
		} else {
			num1 = b;
			num2 = a;
		}
		for (int i = 1; i <= num2; i++) {
			if ((num1 * i) % num2 == 0) {
				return i * num1;
			}
		}
return 0;
	}
public static void FileWritin(PrintWriter pw,Integer data[])
	{
	System.out.println("\nhere=="+data.length);
		try{
		
	
	for (int i=0;i<data.length ;i++ )
	{
		System.out.println(data[i]);
			pw.println(data[i]);
	}
	//pw.println("done");
	//pw.close();
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
	}
  
  public static  void main(String args[]){

	ArrayList<Integer> aa=new ArrayList<Integer>();
	ArrayList<Integer> bb=new ArrayList<Integer>();
	File fileexample=new File("storekeysk.txt");
	File fileexample1=new File("storekeysq.txt");
	
	try{
		PrintWriter pw=new PrintWriter(fileexample);
		PrintWriter pw1=new PrintWriter(fileexample1);
	
	
		
	   int rand = (int)(System.currentTimeMillis()%12);
	   if(rand<=7)
		   rand=6;
	int x,y;
	System.out.println("initaila "+rand);
	  for(int i=0;i<3;i++)
		 {
		  if(rand%2==0)
			  rand=rand+2;
		    if(rand%4==0)
			  rand=rand+3;
		  else
		 rand=rand+1;
		 
			aa.add(rand);
			System.out.println("Ran=="+rand);

							
		 }
	Integer a[]=new Integer[aa.size()];
	a=aa.toArray(a);

//		for(int s=0;s<a.length;s++)
//			System.out.println("values "+a[s]);


	int size=a.length;
		for(x=size-1;x>=0;x--)
		  {
			for(y=x-1;y>=0;y--)
			  {
				int c=0;
				c=a[x]-a[y];
				bb.add(c);
			  }
		  }

	//System.out.println("B list "+bb);
	Integer b[]=new Integer[bb.size()];
	b=bb.toArray(b);
	

		for(int k=0;k<b.length;k++)
		{
		if(b[k]==0)
			b[k]=b[k+1];
		}
for(int k=0;k<b.length;k++)
		{
		System.out.println("b values : "+b[k]);
		}

	RandomInteger lcm = new RandomInteger();

		int lcmvalue=0; 
	    int m,n;
		
		  for(int k=0;k<b.length;k++)
		    {
				  if((k+1)<b.length){
				  m=b[k];
				  n=b[k+1];
		    	  b[k+1]= lcm.determineLCM(m,n);
				}
			   	  else
				{
				m=b[k-1];
				n=b[k];
				lcmvalue=lcm.determineLCM(m,n);
				}
			}
	System.out.println("lcmvalue is : "+lcmvalue);

Integer [] t=new Integer[3];

for(int p=0;p<3;p++)
	  {
	t[p]=(a[p]*lcmvalue)+1;
	  }

for(int p=0;p<3;p++)
	  {
	System.out.println("t values "+t[p]);
	  }

FileWritin(pw,a);
FileWritin(pw1,t);
pw.close();
pw1.close();
	}
	catch(Exception e)
	  {}
}
}