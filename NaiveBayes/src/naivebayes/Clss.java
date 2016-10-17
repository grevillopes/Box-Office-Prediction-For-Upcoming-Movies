/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package naivebayes;

/**
 *
 * @author
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Student
 */
public class Clss {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        int number = 0, generator = 0, leftMost;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String numberBinary = "", generatorBinary = "", frame = "";
        System.out.println("Enter the number follwed by the generator code in decimal");
        number = Integer.parseInt(bf.readLine());
        numberBinary = Integer.toBinaryString(number);
        System.out.println("The binary equivalent of the number is "+numberBinary);
        generator = Integer.parseInt(bf.readLine());
        generatorBinary = Integer.toBinaryString(generator);
        System.out.println("The binary equivalent of the number is "+generatorBinary);
        numberBinary = numberBinary + "0000";
        System.out.println(numberBinary);
        
        
        //add leftmost
        do{
        leftMost = firstBit(numberBinary);
        frame = frame + leftMost;
        //substring numberBinary
        numberBinary = numberBinary.substring(1, numberBinary.length());
        System.out.println(numberBinary);
        //if leftmost is 1
            //exor (1 to len of gen, 0 to len of gen) + remainging of number
        //else
            //exor (0000, 0 to len of gen) + remaining of number
        if(leftMost == 1){
            numberBinary = exor(generatorBinary.substring(1, generatorBinary.length()), numberBinary.substring(0, generatorBinary.length()-1)) + numberBinary.substring(generatorBinary.length()-1,numberBinary.length());
            System.out.println("check point "+numberBinary);
        }
        else {
            numberBinary = exor("0000", numberBinary.substring(0, generatorBinary.length()-1)) + numberBinary.substring(generatorBinary.length()-1,numberBinary.length());
        }
        
        }while(numberBinary.length()>= generatorBinary.length());
        
        System.out.println("message transmitted is "+frame);
        System.out.println("remainder is "+numberBinary);
        
    }
    public static String exor(String number, String number2){
        String exorredString = "";
        for(int i=0;i<number.length();i++){
            if(number.charAt(i)==number2.charAt(i)){
                exorredString = exorredString + "0";
            }else exorredString = exorredString + "1";
        }
        return exorredString;
    }
    public static int firstBit(String number){
        int leftMost = Integer.parseInt(number.charAt(0)+"");
        return leftMost;
    }
}

