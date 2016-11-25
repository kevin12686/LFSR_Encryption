import java.util.Scanner;

public class LFSR {
	
	private String FB_mask;
	private String Init;
	
	public LFSR(String Init, String FB_mask){
		this.Init = new String(Init);
		this.FB_mask = new String(FB_mask);
	}
	
	public String getKey(){
		String temp = "";
		for(int i = 0; i < this.FB_mask.length(); i++){
			if(this.FB_mask.charAt(i) == '1' || i == this.FB_mask.length() - 1)	temp += temp.equals("") ? this.Init.charAt(i) : "," + this.Init.charAt(i);
		}
		String[] xor = temp.split(",");
		temp = xor[0];
		for(int i = 1; i < xor.length; i++){
			temp = LFSR.XOR_cal(temp, xor[i]);
		}
		this.Init = temp + this.Init;
		return remove_return_last();
	}
	
	private String remove_return_last(){
		String temp = this.Init.substring(this.Init.length() - 1);
		this.Init = this.Init.substring(0, this.Init.length() - 1);
		return temp;
	}
	
	public static String XOR_cal(String a, String b){
		return a.equals(b) ? "0" : "1";
	}
	
	public static void main(String Arge[]){
		String s = "";
		Scanner keyboard = new Scanner(System.in);
		LFSR lfsr = new LFSR("0001", "0011");
		for(int i = 0; i < 20; i++){
			s += lfsr.getKey();
		}
		System.out.println("Program : " + s);
		System.out.print("Please key in your answer : ");
		if(s.equals(keyboard.nextLine()))	System.out.println("Correct.");
		else	System.out.println("Wrong.");
		keyboard.close();
	}
}
