import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;
public class Decryption extends JFrame implements ActionListener
{
Container c;
JButton btn;
JLabel  lbl3;
JTextField  tf3;
JPanel p;
int [ ]ciphers;
	int index=0;
	String store="";
	String tobin="";
	String mes="";
	int [] q=new int[3];
	int [] k=new int[3];
	int [] m=new int[3];
	//char suspense;//when all 9 bits are zero
	int chk=0;
Decryption()
{
super("Decryption..");
c=getContentPane();
c.setBackground(Color.red);
btn=new JButton("Decrypt");
btn.addActionListener(this);

tf3=new JTextField(30);

lbl3=new JLabel("RESULT");
p=new JPanel();
p.setLayout(new GridLayout(3,2));

p.add(lbl3) ; p.add(tf3);

c.add(new JLabel("Receiver Side.."), "North");
c.add(p,"Center");
c.add(btn,"South");
}
	void InitializeKeys()
	{
	int index=0;
		try
		{
			File fileexample=new File("storekeysk.txt");
			File fileexample1=new File("storekeysq.txt");
			Scanner scn=new Scanner(fileexample);
			Scanner scn1=new Scanner(fileexample1);
			String s;
			while(scn.hasNext())
		{
			
			s=scn.nextLine();

				k[index++]=Integer.parseInt(s);
		}
		index=0;
		while(scn1.hasNext())
		{
			
			s=scn1.nextLine();

				q[index++]=Integer.parseInt(s);
		}
	
		}
		catch (Exception e)
		{
			System.out.println("caught");
		}
	
	}
public void actionPerformed(ActionEvent e)
{
if(e.getSource()==btn)
{
InitializeKeys();
	try
	{
		File fileexample=new File("ciphers.txt");
	Scanner scn=new Scanner(fileexample);
	int j=0;
	String s;
		while(scn.hasNext())//to compute how many ciphers to be generated
		{
			 scn.nextLine();
			j++;
			
		}
		//System.out.println("j=="+j);//j contains the no of ciphers
		ciphers=new int[j];
		 scn=new Scanner(fileexample);//again point scanner to the start of file
		while(scn.hasNext())
		{
			
		s=scn.nextLine();
		if(s!="\n")
			{
		 ciphers[index]=Integer.parseInt(s);
		// System.out.println(ciphers[index]);
		 index++;
			}
		}
		/*for (int i=0;i<ciphers.length;i++ )//printing all the ciphers
		{
			System.out.println(ciphers[i]);
		}*/
		BeginDecryption();
		tf3.setText(""+mes);
	}
			catch (Exception e1)
	{
		System.out.println("Something gone wrong in generating ciphers");
	}

}
}
void BeginDecryption()
	{
		for (int i=0;i<ciphers.length ;i++ )
		{
			SendCipher(ciphers[i]);
		}
		System.out.println("Mess=="+mes);
	}
	void SendCipher(int value)
	{
		String temp;
	
		if(value==1)
		{
		//System.out.println("converting...");
		//convert tobin to text...make tobin null..
		char [] Ar=tobin.toCharArray();//Ar holds the tobin string character wise
		//System.out.println("len==="+Ar.length);
		int in=0;
		String concat="";
		for (int i=0;i<Ar.length/8 ;i++)
		{
			concat=concat+Ar[in++]+Ar[in++]+Ar[in++]+Ar[in++]+Ar[in++]+Ar[in++]+Ar[in++]+Ar[in++];
			  int decimalNumber = Integer.parseInt(concat,2);
			//  System.out.println("no=="+decimalNumber);
			  char mx=(char)decimalNumber;
			//	suspense=mx;
			  mes=mes+mx;
			concat="";
			
		}
		mes=mes+" ";
		tobin="";
		}
		else
			{
			for (int i=0;i<3 ;i++ )
		{
			m[i]=((k[i]*value)/q[i])%k[i];
			
			//System.out.println("m=="+m[i]);
			
			temp=Integer.toBinaryString(m[i]);
			String op=String.format("%3s",temp).replace(' ','0');
			//System.out.println("opopopop=="+op);
			tobin=tobin+op;
		//	System.out.println("Tobin=="+tobin);
	}
		}
	}
public static void main(String args[])
{
Decryption b=new Decryption();
b.setSize(300,300);
b.setVisible(true);
}
}