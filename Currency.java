import java.util.Scanner;

public class Currency{
	private static String[] small = {"one", "two", "three", "four", "five",
		"six", "seven", "eight", "nine"};
	private static String[] mediem = {"ten","eleven", "twelve", "thirteen",
		"fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nighteen"};
	private static String[] large = {"twenty", "thirty", "forty", "fifty",
		"sixty", "seventy", "eighty", "ninety"};

	public static String baseCardinalToString(int value)
	{
		/*Value = 0, which is already defined*/
		if (value == 0){
			return ""; 
		}
		/*10 >Value > 0*/
		if (value > 0 && value < 10){
			return small[value - 1]; 
		}
		/*20>Value > 10*/
		if (value >= 10 && value < 20){
			return mediem[value - 10]; 
		}
		/*100 >Value > 20*/
		if (value >=20 && value <100){
			return large[value/10-2]; //

		}
		return null;
	}

	public static String convertToWords(double value){
		
		String[] done = new String[1000];
		int more20 = 0;
		boolean no = false;
		int integer = (int)value;
		int thousand;
		int hundred;
		int largeNums;
		int smallNums;
		int counter = 0;

		/*Find the result first*/
		double value100 = value * 100.0;
		int cents = ((int)(value * 100.0 + 0.5)) % 100; 
		

		if(integer == 0){
			return "zero ---------------------------------------------------- " + cents + "/100 dollars";
		} 

		if(integer == 10000) {
			return "ten thousand -------------------------------------------- " + cents + "/100 dollars";
		}

		/*See if thounds correct*/
		if(integer <10000 && integer >999 ){
			thousand = integer / 1000;
			if (thousand != 0)
			{
				done[counter] = ( baseCardinalToString(thousand) + " thousand ");
				counter++;
			}
			integer -= (integer / 1000) * 1000; 
		}

		/*See if hundreds correct*/
		if(integer <1000 && integer >99 ){
			hundred = integer /100;
			if (hundred != 0)
			{
				done[counter] = ( baseCardinalToString(hundred) + " hundred and ");
				counter++;
			} 
			integer -= (integer /100) * 100;
			
		}

		/*See if below 20 is correct*/
		if(integer <100 && integer >=20 ){
			more20 = 1;
			largeNums = integer /10;
			if (largeNums != 0){
				done[counter] = ( baseCardinalToString(integer) + "-");
				counter++;
			}
			integer -= (integer /10) * 10;
		}

		if(more20 == 0 && integer > 10){
			done[counter] = ( baseCardinalToString(integer) + " ");
			counter ++;
			no = true;
		}

		if(integer > 0 && !no){
			done[counter] = (baseCardinalToString(integer) + " ");
			counter ++;
		}

		int listLen = 0;
		int longths = 0;
		// fill up the rest and keep the length
		while (done[longths] != null){
			listLen += done[longths].length();
			longths++;
			listLen--;
		}

		/*Put them all together*/
		if(listLen < 71){
			while (listLen <= 55){
				done[longths] = "-";
				longths++;
				listLen++;
			}
		}

		String wholeword = "";
		for(int i =0; done[i] != null ;i++){
			wholeword += done[i];
		}

		wholeword += " "+ cents + "/100 dollars";
		return wholeword;

}
	public static double getValueFromUser(){
		
		/*Scan the number*/
		Scanner sc = new Scanner(System.in);
		double nums;		
		int yes = 0;


		while(yes == 0) {
			System.out.print("What is the monetary amount? ");
			
			if(sc.hasNextDouble() == true){
				yes = 1;
				nums = sc.nextDouble();
				/*Put the number into the nums and return*/
				return (double)nums;
			} else {
				sc.nextLine();
			}

		}
		/*Return nothing...*/
		return 0.0;
	}

	public static void main(String[] args){
		double value=getValueFromUser();
		String text=convertToWords(value);
		System.out.println(text);
	}
	
	
	
}