/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package naivebayes;

/**
 *
 * @author
 */


import java.io.*;
import java.util.*;
class Naive27
{
    public static void main(String args[])throws IOException
    {
         int i,j;
         Scanner sc=new Scanner(System.in);
         
         String age[]={"<=30","<=30","31-40","31-40",">=40"};
         String income[]={"high","low","high","medium","medium"};
         String student[]={"no","yes","yes","no","yes"};
         String buys[]={"yes","yes","no","no","yes"};
         
         	System.out.println("Given Data Set is: ");
         	System.out.println("age   income   student   buys");

       	 for ( i=0;i<5;i++)
	System.out.println(age[i]+"\t"+income[i]+"\t"+student[i]+"\t"+buys[i]);

         System.out.println();
         
String age1,inc1,stud1,buy1;
         System.out.println("Enter the data to be sampled");
         System.out.print("Age= ");
         age1=sc.next();
         System.out.print("\nIncome= ");
         inc1=sc.next();
         System.out.print("\nStudent= ");
         stud1=sc.next();
        
         double n=0,y=0;
         for(i=0;i<5;i++)
         {
             if(buys[i]=="yes")
             y++;
         else n++;
		}
         
        
         System.out.println("P(Buy=yes) "+y+"/5");
         System.out.println("P(Buy=no) "+n+"/5");
          System.out.println();
         System.out.println("The data to be sampled is");
         System.out.println("X=(age "+age1+" income= "+inc1+" student= "+stud1+")");
         
         double agey=0,agen=0,incy=0,incn=0,study=0,studn=0;
         for(j=0;j<5;j++)
         {
             if(age[j].contentEquals(age1))
             {
                 if(buys[j].contentEquals("yes"))
                     agey++;
                 else
                     agen++;
             }
         }
         
         System.out.println("P(age|yes)="+agey+"/"+y);
         System.out.println("P(age|no)="+agen+"/"+n);
         
          System.out.println();
         for(j=0;j<5;j++)
         {
             if(income[j].contentEquals(inc1))
             {
                 if(buys[j].contentEquals("yes"))
                     incy++;
                 else
                     incn++;
             }
         }
         
         System.out.println("P(income|yes)="+incy+"/"+y);
         System.out.println("P(income|no)="+incn+"/"+n);
          System.out.println();
          for(j=0;j<5;j++)
         {
             if(student[j].contentEquals(stud1))
             {
                 if(buys[j].contentEquals("yes"))
                     study++;
                 else
                     studn++;
             }
         }
         
         System.out.println("P(student|yes)="+study+"/"+y);
         System.out.println("P(student|no)="+studn+"/"+n);
          System.out.println();
         double pyes,pno;
         pyes=(incy/y)*(study/y)*(agey/y)*(y/5);
         pno=(incn/n)*(studn/n)*(agen/n)*(n/5);
         
         System.out.println("P(h|D)yes="+pyes);
         System.out.println("P(h|D)no="+pno);
          System.out.println();
         if(pyes>pno)
	System.out.println("data is sampled to buy YES");
          else
	System.out.println("data is sampled to buy NO");	
    }
   
}

/*output:

C:\Users\Asher\Desktop\Asher_engg\sem 6\temp6>java Naive27
Given Data Set is:
age   income   student   buys
<=30    high    no      yes
<=30    low     yes     yes
31-40   high    yes     no
31-40   medium  no      no
>=40    medium  yes     yes

Enter the data to be sampled
Age= <=30

Income= low

Student= yes
P(Buy=yes) 3.0/5
P(Buy=no) 2.0/5

The data to be sampled is
X=(age <=30 income= low student= yes)
P(age|yes)=2.0/3.0
P(age|no)=0.0/2.0

P(income|yes)=1.0/3.0
P(income|no)=0.0/2.0

P(student|yes)=2.0/3.0
P(student|no)=1.0/2.0

P(h|D)yes=0.08888888888888888
P(h|D)no=0.0

data is sampled to buy YES



C:\Users\Asher\Desktop\Asher_engg\sem 6\temp6>java Naive27
Given Data Set is:
age   income   student   buys
<=30    high    no      yes
<=30    low     yes     yes
31-40   high    yes     no
31-40   medium  no      no
>=40    medium  yes     yes

Enter the data to be sampled
Age= 31-40

Income= high

Student= no
P(Buy=yes) 3.0/5
P(Buy=no) 2.0/5

The data to be sampled is
X=(age 31-40 income= high student= no)
P(age|yes)=0.0/3.0
P(age|no)=2.0/2.0

P(income|yes)=1.0/3.0
P(income|no)=1.0/2.0

P(student|yes)=1.0/3.0
P(student|no)=1.0/2.0

P(h|D)yes=0.0
P(h|D)no=0.1

data is sampled to buy NO
*/
