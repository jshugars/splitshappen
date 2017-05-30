package splits_happen;
import java.util.Scanner;
import java.lang.Character;
public class Splits_happen {
	
	static int total_score = 0;
	static char[] a;
	
	public static void main(String[] args){
		
		// reads in the user input
		Scanner input = new Scanner(System.in);
		System.out.println("Enter frame scores: ");
		String s = input.next();	// assigns user input to a String
		a = s.toCharArray();	// makes the string into an array of character inputs (i.e. each roll is a separate entry in the array)
		
		//  for loop for the first 9 frames and the 1st roll of the 10th frame
		Frames(a);
		
		// runs the method for the last 2 rolls in the 10th frame
		final_Two(a);
		
		// prints out the final score for the player
		System.out.println(total_score);
	}
	
	public static void Frames(char[] a){
		
		for( int i = 0; i < a.length-2; i++){
			
			// If the player rolls a strike
			if(a[i] == 'X'){
				strike(a[i+1],a[i+2]);
			}
			
			// If the player rolls a spare
			else if(a[i] == '/'){
				char b = a[i+1];
				char c = a[i-1];
				spare(b,c);
			}
			
			// If the player misses all pins on a roll
			else if(a[i] == '-'){
				total_score = total_score + 0;
			}
			
			// If the player hits a certain number of pins (not a strike or spare)
			else{
				int j = Character.getNumericValue(a[i]);
				num(j);
			}
		}
	}
	
	public static void strike(char b,char c){
		
		// Assigns the given character, for the 2 rolls after the strike, its numerical value
		int m = Character.getNumericValue(b);
		int n = Character.getNumericValue(c);
		
		// how to add up the score based on a given situation
		if(b != 'X' && c != 'X' && b != '-' && c != '/' && c != '-'){
			total_score = total_score + 10 + m + n;
		}
		else if(b != 'X' && c == '/'){
			total_score = total_score + 20;
		}
		else if(b != 'X' && c == '-'){
			total_score = total_score + 10 + m; 
		}
		else if(b == 'X' && c == 'X'){
			total_score = total_score + 30;
		}
		else if(b == '-' && c != 'X' && c != '-'){
			total_score = total_score + 10 + n;
		}
		else if(b == 'X' && c != 'X' && c != '-'){
			total_score = total_score + 20 + n;
		}
		else if(b != 'X' && b!= '-' && c == 'X'){
			total_score = total_score + 20 + m;
		}
		else if(b == '-' && c == 'X'){
			total_score = total_score + 20;
		}
	}
	
	// Method for a bowl that hits a number of pins (not a strike or spare)
	public static void num(int k){
		total_score = total_score + k;
	}
	
	// Method that is run if a bowler gets a spare
	// The input is the roll before the spare (a) and the roll after (b)
	public static void spare(char b, char a){
		
		// initializes the result of the bowls before and after the spare
		int j = 0;
		int k = 0;
		
		// assigns j the value of 0 if the bowl following the spare was a miss
		if( b == '-'){
			j = 0;
		}
		else{
			j = Character.getNumericValue(b);
		}
		
		// assigns k the value of 0 if the bowl before the spare was a miss
		if (a == '-'){
			k = 0;
		}
		else{
			k = Character.getNumericValue(a);
		}
		total_score = total_score + (10 - k) + j;
	}
	
	// Method that deals with the final 2 bowls of the 10th frame
	public static void final_Two(char[] a){
		
		// Runs a for loop for the final 2 rolls
		for( int j = a.length-2; j < a.length; j++){
			
			int k = Character.getNumericValue(a[j]);
			
			// NOTE: If a strike is bowled in the 1st roll of the 10th frame then a spare will not be possible until the 3rd roll.
			// NOTE: A spare would not be achievable until at least the 2nd roll.
			// NOTE: If the first roll is a strike, the following 2 rolls will be taken into account in the strike method when 
			// 		it runs earlier in the program.
			
			// If statement for 3rd roll
			if(a[j-1] == '/'){
				if(a[j] == 'X'){
					total_score = total_score + 20;
				}
				else if(a[j] == '-'){
					total_score = total_score + 10;
				}
				else{
					spare(a[j],a[j-2]);
				}
			}
			
			// Accounts for a miss or regular pin knock down without the influence of a spare or strike
			else if(a[j-1] != 'X' && a[j-1] != '/' && a[j] != 'X' && a[j] != '/' && a[j-2] != 'X'){
				if(a[j] == '-'){
					total_score = total_score + 0;
				}
				else{
					total_score = total_score + k;
				}
			}
		}
	}
}
