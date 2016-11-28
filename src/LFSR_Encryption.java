import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LFSR_Encryption {
	
	public static void main(String Arge[]){
		String FRin = null, FRout = null;
		Scanner keyboard = new Scanner(System.in);
		DataInputStream instream = null;
		DataOutputStream outstream = null;
		LFSR lfsr = null;
		
		System.out.print("Please key in the route of input File : ");
		FRin = keyboard.nextLine();
		FRout = FRin.substring(0, FRin.length() - 4) + "_out.txt";
		
		try {
			instream = new DataInputStream(new FileInputStream(FRin));
			outstream = new DataOutputStream(new FileOutputStream(FRout));
			
			System.out.print("Please key in the initial value : ");
			FRin = keyboard.nextLine();
			System.out.print("Please key in the mask (Separated by ,) : ");
			FRout = transform(keyboard.nextLine(), ",", FRin.length());

			lfsr = new LFSR(FRin, FRout);
			
			while(instream.available() > 0){
				outstream.writeBytes(LFSR.XOR_cal(String.valueOf((char)instream.readByte()), lfsr.getKey()));
			}
			
			System.out.println("Finished.");
			instream.close();
			outstream.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found.");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Read file error.");
			e.printStackTrace();
		}
		keyboard.close();
	}
	
	public static String transform(String input, String symbol, int length){
		String temp = "";
		for(int i = 0; i < length; i++){
			temp += "0";
		}
		for(int i = 0; i < input.split(",").length; i++){
			int j = length - Integer.parseInt(input.split(",")[i]) - 1;
			temp = temp.substring(0, j) + "1" + temp.substring(j + 1, temp.length());
		}
		return temp;
	}
	
}
