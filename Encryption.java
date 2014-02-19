import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.PrintWriter;
public class Encryption extends JFrame implements ActionListener
{
Container c;
JButton btn;
JLabel lbl1, lbl2;
JTextField tf1, tf2;
JPanel p;
String sol="";
	int []m=new int[3];
	int [] ss=new int[3];//holds s1,s2,s3,s4
	int [] cipher;
	int index=0;
	int count=0;
		int [ ] Q=new int[4];//holds Q1,Q2,Q3,Q4,Q5(Q[5]=Q)
		int [ ] b=new int[3];//holds b1,b2,b3,b4
		int [ ] P=new int[3];//holds P1,P2,P3,P4
		int [ ] R=new int[3];//holds R1,R2,R3,R4
		int [ ] N=new int[3];//holds N1,N2,N3,N4 float bcz ceil is used
		
		int [ ] q=new int[3];//holds q1,q2,q3,q4
		int [ ] k=new int[3];

Encryption()
{
super("Encryption..");

	
c=getContentPane();
c.setBackground(Color.red);
btn=new JButton("Press This To Encrypt");
btn.addActionListener(this);
tf1=new JTextField(100);
tf2=new JTextField(100);
lbl1=new JLabel("Enter the msg to Encrypt:");

lbl2=new JLabel("Encrypted Message");
p=new JPanel();
p.setLayout(new GridLayout(3,2));
p.add(lbl1) ; p.add(tf1);
p.add(lbl2) ; p.add(tf2);


c.add(new JLabel("Sender Side"), "North");
c.add(p,"Center");
c.add(btn,"South");
}

public void actionPerformed(ActionEvent e)
{
if(e.getSource()==btn)
{
	InitializeKeys();
	ComputeQs();
	ComputeBis();
	ComputePis();
	ComputeRis();
	ComputeNis();
	ComputeSis();
	input();
	System.out.println("List of ciphers");
	ShowCiphers();


}
}
void input()
	{
	//	System.out.println("Enter your message");
		//Scanner s=new Scanner(System.in);
		String mes=tf1.getText();
		//System.out.println("Mes=="+mes);
		String [ ] words=mes.split(" ");
		int noOfCip=0;
		for (int i=0;i<words.length ;i++ )//to determine how many ciphers to be generated
		{
		
		char a[]=words[i].toCharArray();
		noOfCip=noOfCip + a.length;
		}
		noOfCip=noOfCip+words.length;
		//System.out.println("No Of Ciphers="+noOfCip);
		cipher=new int[noOfCip];
		for (int i=0;i<words.length ;i++ )
		{
			solve(words[i]);//used to call word by word
			cipher[index++]=1;
		}
	}
	void solve(String word)
	{
	char[] c = word.toCharArray();//convert word into chars
	CharSolve(c);//encrypt the char array
	}
	void CharSolve(char [] d)//d contains the char array
	{
	for (int i=0;i<d.length ;i++ )
	{
		Ascii(d[i]);//solve for ascii of each char
		
	}
	for(int j=0;j<(sol.length())%9;j++)//padding of the word to multiple of 9
		{
		sol=sol+'0';
		}
		System.out.println("Sol=="+sol);
		GenCipherWord(sol);//gen ciphers for each word
	sol="";
	}
	void Ascii(char data)
	{
	int as=(int)data;
//	System.out.println(as);
	String by=Integer.toBinaryString(as);

	
	String pad=String.format("%8s",by).replace(' ','0');
	sol=sol+pad;
	}
void  GenCipherWord(String inp)
	{
	String store="";//store 9 bits
	String val="";//used to store 3 bits each
	char [] temp=inp.toCharArray(); 
	for (int i=0;i<inp.length() ; )
	{
		store=store+temp[i++];
		if(i%9==0)
		{
		char [] split=store.toCharArray();
	//	System.out.println("Split=="+split.length);
		for(int j=0;j<9;)
			{
			val=val+split[j++]+split[j++]+split[j++];
			int qw=Integer.parseInt(val,2);
			//System.out.println("qw=="+qw);
			m[count++]=qw;
			//System.out.println("m=="+m[count-1]);
			val="";
			}
			GenCipher();
		count=0;
		store="";
		}
		
	}
	
	}
	
	void GenCipher()
	{
		int Ev=0;//compute the encrypted value
		
	
	for (int i=0;i<3 ;i++ )
	{
		Ev=Ev+(m[i]*ss[i]);
	}
	cipher[index++]=Ev;//store all encrypted values in an array
	//System.out.println("Ev=="+Ev);
	
	}
void ShowCiphers()
	{
	String ciphertext="";

		try{
		File fileexample=new File("ciphers.txt");
		PrintWriter pw=new PrintWriter(fileexample);
	for (int i=0;i<cipher.length ;i++ )
	{
		System.out.println(cipher[i]);
		ciphertext=ciphertext+cipher[i];
			pw.println(cipher[i]);
	}
	tf2.setText(""+ciphertext);
	pw.close();
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
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
/*		for (int i=0;i<3 ;i++ )//printing q and k
		{
			System.out.println(q[i]+" "+k[i]);
		}*/
		}
		catch (Exception e)
		{
			System.out.println("caught");
		}
	
	}
	void ComputeQs()
	{
	
	
		Q[3]=1;
		for (int i=0;i<3 ;i++)
		{
			Q[i]=1;
			for(int j=0;j<3;j++)
			{
				if(i!=j)
			Q[i]=Q[i]*q[j];

			}

			Q[3]=Q[3]*q[i];
		}
	/*	System.out.println("Qi's");//printing Qis
		for (int i=0;i<4 ;i++ )
		{
			System.out.println(Q[i]);
		}*/
	}
	void ComputeBis()
	{
	for (int i=0;i<3;i++)
	{
		for (int j=1;j<600;j++)
		{
			if((Q[i]*j)%q[i]==q[i]%k[i])
			{
			b[i]=j;
			break;
			}
		}
	}
	/*System.out.println("bi's");//printing bis
	for (int i=0;i<3 ;i++ )
		{
			System.out.println(b[i]);
		}*/
	}
	void ComputePis()
	{
	for (int i=0;i<3;i++)
	{
	P[i]=Q[i]*b[i];
	}
	/*System.out.println("Pi's");//prinitng Pi's
	for (int i=0;i<3 ;i++ )
		{
			System.out.println(P[i]);
		}*/
	}
	
	void ComputeRis()
	{
	for (int i=0;i<3;i++)
	{
		R[i]=q[i]%k[i];
	}
/*	System.out.println("Ri's");printing Ri's
	for (int i=0;i<3 ;i++ )
		{
			System.out.println(R[i]);
		}*/
	}
	void ComputeNis()
	{
	for (int i=0;i<3 ;i++ )
	{
		
		N[i]=(q[i]/(k[i]*R[i]))+1;//check for ceil  function in java
	}
	/*	System.out.println("Ni's");printing Ni's
	for (int i=0;i<3 ;i++ )
		{
			System.out.println(N[i]);
		}*/
	}
	void ComputeSis()
	{
	for (int i=0;i<3 ;i++ )
	{
			ss[i]=(P[i]*N[i])%Q[3];
	}
		System.out.println("Si's");//printing Si's
	for (int i=0;i<3 ;i++ )
		{
			System.out.println(ss[i]);
		}
	}
public static void main(String args[])
{
Encryption b=new Encryption();
b.setSize(300,300);
b.setVisible(true);
}
}