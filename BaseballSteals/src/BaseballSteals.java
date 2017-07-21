import java.util.Scanner;
import java.util.Random;

public class BaseballSteals {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				double[] percent = new double[8]; 
				Scanner input = new Scanner(System.in);
				System.out.println("Type in player name. One word no spaces please");
				String name = input.next();
				System.out.println("Type in players number of plate appearances");
				percent[5] = input.nextDouble();
				System.out.println("Type in player on base percentage in a decimal. dont make higher than .6");   
				percent[0] = input.nextDouble();
				System.out.println("Type number of home runs");
				percent[1] = input.nextDouble() / percent[5];
				System.out.println("Type in number of doubles");
				percent[2] = input.nextDouble() / percent[5];
				System.out.println("Type in number of triples");
				percent[3] = input.nextDouble() / percent[5];
				System.out.println("Type number of total hits");
				percent[4] =( input.nextDouble() / percent[5]) - (percent[1] + percent[2] + percent[3]);
				System.out.println("Type in player stealing 2nd success percentage or 0 for no stealing");
				percent[6] = input.nextDouble();
				System.out.println("Type in player stealing 3rd success percentage or 0 for no stealing");
				percent[7] = input.nextDouble();
				int inning = 1; 
				int[] inningstats = {0,0,0,0,0,0,0,0};
				
				while(inning <=9000000){
	//				System.out.println("This is inning number " + inning);
					inningstats[4]=0;  //outs
					inningstats[0] = 0; //first base
					inningstats[1] = 0;  //second
					inningstats[2] = 0;  //third base
					while (inningstats[4] < 3){
//						System.out.println("There are " + inningstats[4] + " outs.");
						rando(inningstats, percent);
					
					}
//					System.out.println("inning over");
	//				System.out.println("You have " + inningstats[3] + " runs.");
					inning = inning + 1;
				}
	//			System.out.println("double plays " + inningstats[5]);
		//		System.out.println("sacrifice flies " + inningstats[6]);
	//			System.out.println("errors " + inningstats[7]);
				
				System.out.println("Game over. You scored " + inningstats[3] + " runs.");	
				double runsPer9 = (inningstats[3] / 1000000.0);
				System.out.println("The lineup of all " + name + "'s would score " + runsPer9 + " runs per 9 innings");
			}

			public static int[] rando(int[] inningstats, double[] percent) {
				Random randomGenerator= new Random();
				//int[] inningstats = {first, second, third, runs, outs};
				double deci = randomGenerator.nextDouble();

					if (deci > .95 && inningstats[0] ==1){
						inningstats[0] =0;
						inningstats[4] = inningstats[4] + 2;  
						inningstats[5] = inningstats[5] +1;
						
						//double play						
					}
					else if(deci > .795 && deci <= .936 && inningstats[2] ==1 && inningstats[4] < 2){   // sac flies
						inningstats[2]=0;
						inningstats[3] = inningstats[3] + 1;
						inningstats[6] = inningstats[6] +1;
						inningstats[4] = inningstats[4] + 1;
					}
					else if(deci >.936 && deci <= .95){
						// error  makes all runners advance and hitter safe at fist bc errors not included in obp
						if(inningstats[2] ==1){
							inningstats[2] =0;
							inningstats[3]= inningstats[3] + 1;
						}
						else if(inningstats[1] == 1){
							inningstats[1] =0;
							inningstats[2] =1;
						}
						else if(inningstats[0] ==1){
							inningstats[1] =1;
						}
						inningstats[0] =1;
						inningstats[7] = inningstats[7] +1;
						}
					
					else if(deci > percent[0]){
						inningstats[4] = inningstats[4] +1;
					}
//					System.out.println("Your out!"); //out
				
				else if (deci < percent[1]){
					int hr = inningstats[0]+inningstats[1]+inningstats[2]+1; 
					inningstats[0] = 0;
					inningstats[1] = 0;
					inningstats[2] = 0;
					inningstats[3] = inningstats[3] + hr;   //3 is runs
//					System.out.println("Home run");  //home run
				}
				else if (deci > percent[1] + percent[2] && deci < percent[1] + percent [2] + percent[3]){
					int trip = inningstats[0]+inningstats[1]+inningstats[2]; 
					inningstats[0] = 0;
					inningstats[1] = 0;
					inningstats[2] = 1;
					inningstats[3] = inningstats[3] + trip;   //3 is runs
//					System.out.println("Triple");  //triple
				}
				else if(deci > percent[1] + percent[2] + percent[3] && deci < percent[1] + percent[2] + percent[3] + percent[4]){   //single
					if (inningstats[0] == 0 && inningstats [1] == 0 && inningstats [2] == 0){
					
					}
					else if(inningstats[0] == 0 && inningstats [1] == 0 && inningstats [2] == 1){
						inningstats[2]=0;
						inningstats[3] = inningstats[3]+ 1;
					}
					else if(inningstats[0] == 0 && inningstats [1] == 1 && inningstats [2] == 0){
						inningstats[1]= 0;
						double second = randomGenerator.nextDouble();
						if (second <= .595){
							inningstats [2] = 0;
							inningstats[3] = inningstats[3]+ 1;
	//						System.out.println("you score from second");
						}
						else if (second > .65){
							inningstats[2] = 1;
	//						System.out.println("you stop at third");
						}
						else{
							inningstats[4] = inningstats[4] + 1;
						}
					}
					else if(inningstats[0] == 0 && inningstats [1] == 1 && inningstats [2] == 1){
						inningstats[1] = 0;
						double second = randomGenerator.nextDouble();
						if (second <= .595){
							inningstats[2] = 0;
							inningstats[3] = inningstats[3]+ 2;
	//						System.out.println("you score from second");
						}
						else if (second > .65){
							inningstats[2] = 1;
							inningstats[3] = inningstats[3]+ 1;
//							System.out.println("you stop at third");
						}
						else {
							inningstats[2] = 0;
							inningstats[3] = inningstats[3] + 1;
							inningstats[4] = inningstats[4] + 1;
						}
					}
					else if(inningstats[0] == 1 && inningstats [1] == 0 && inningstats [2] == 0){  //randomm
						double second = randomGenerator.nextDouble();
						if (second <= .7){
							inningstats [1] = 1;
						}
						else if (second > .72){
							inningstats[2] = 1;
						}
						else {
							inningstats[4] = inningstats[4] + 1;
						}
					}
					else if(inningstats[0] == 1 && inningstats [1] == 0 && inningstats [2] == 1){  
						inningstats[3] = inningstats[3] + 1;
						double second = randomGenerator.nextDouble();
						if (second <= .7){
							inningstats [1] = 1;
						}
						else if (second > .72){
							inningstats[2] = 1;
						}
						else {
							inningstats[4] = inningstats[4] + 1;
						}
					}
					else if(inningstats[0] == 1 && inningstats [1] == 1 && inningstats [2] == 0){
						double second = randomGenerator.nextDouble();
						if (second <= .595){
							inningstats[1] = 0;
							inningstats [2] = 1; 
							inningstats[3] = inningstats[3]+ 1;   //both advance 2 bases
	//						System.out.println("you score from second"); 
						}
						else if (second > .65){
							inningstats[2] = 1;
//							System.out.println("you stop at third");
						}
						else {
							inningstats[1] = 0;
							inningstats[2] = 1;
							inningstats[4] = inningstats[4] + 1;  //thrown out at the plate
						}
					}
					else if(inningstats[0] == 1 && inningstats [1] == 1 && inningstats [2] == 1){
						double second = randomGenerator.nextDouble();
						if (second <= .595){
							inningstats[1] =0;
							inningstats [2] = 1;
							inningstats[3] = inningstats[3]+ 2;
	//						System.out.println("you score from second");
						}
						else if (second > .65){
							inningstats[1] = 1;
							inningstats[2] = 1;
							inningstats[3] = inningstats[3]+ 1;
//							System.out.println("you stop at third");
						}
						else {
							inningstats[1] = 0;
							inningstats[2] = 1;
							inningstats[3] = inningstats[3] + 1;
							inningstats[4] = inningstats[4] + 1;
					}
					}
	//					System.out.println("you hit a single");
						inningstats[0] = 1;  //always gets to first
				}
				else if(deci > percent[1] + percent[2] + percent[3] + percent[4] && deci < percent[0]){
					if (inningstats[0] == 0 && inningstats [1] == 0 && inningstats [2] == 0){
					
					}
					else if(inningstats[0] == 0 && inningstats [1] == 0 && inningstats [2] == 1){
						inningstats[2]=1;
						inningstats[3] = inningstats[3];
					}
					else if(inningstats[0] == 0 && inningstats [1] == 1 && inningstats [2] == 0){
						inningstats[1]= 1;
						inningstats[2] = 0;
						inningstats[3] = inningstats[3];
					}
					else if(inningstats[0] == 0 && inningstats [1] == 1 && inningstats [2] == 1){
						inningstats[1] = 1;
						inningstats[2] = 1;
						inningstats[3] = inningstats[3];
					}
					else if(inningstats[0] == 1 && inningstats [1] == 0 && inningstats [2] == 0){
						inningstats[1] = 1;
					}
					else if(inningstats[0] == 1 && inningstats [1] == 0 && inningstats [2] == 1){
						inningstats[1]= 1;
						inningstats[2]= 1;
						inningstats[3] = inningstats[3];
					}
					else if(inningstats[0] == 1 && inningstats [1] == 1 && inningstats [2] == 0){
						inningstats[1] = 1;
						inningstats[2] = 1;
						inningstats[3] = inningstats[3];
					}
					else if(inningstats[0] == 1 && inningstats [1] == 1 && inningstats [2] == 1){
						inningstats[1] = 1;
						inningstats[2]= 1;
						inningstats[3] = inningstats[3] +1;
					}
	//					System.out.println("you get walked");
						inningstats[0] = 1;  //always gets to first
				}
				else if(deci < percent[1] + percent[2] && deci > percent[1]){   //double
					if (inningstats[0] == 0 && inningstats[1] == 0 && inningstats[2] == 0){
						
					}
					else if(inningstats[0] == 0 && inningstats [1] == 0 && inningstats [2] == 1){
						inningstats[2]=0;
						inningstats[3] = inningstats[3] + 1;
					}
					else if(inningstats[0] == 0 && inningstats [1] == 1 && inningstats [2] == 0){
						inningstats[3]  = inningstats[3] +1;
					}
					else if(inningstats[0] == 0 && inningstats [1] == 1 && inningstats [2] == 1){
						inningstats[2]=0;
						inningstats[3]= inningstats[3]+2;
					}
					else if(inningstats[0] == 1 && inningstats [1] == 0 && inningstats [2] == 0){
						double first = randomGenerator.nextDouble();
						if (first <=.4){
							inningstats[3] = inningstats[3]+ 1;
	//						System.out.println("you score from first");
						}
						else if (first > .43){
							inningstats[2] = 1;
	//						System.out.println("you stop at third");
						}
						else {
							inningstats[4] = inningstats[4] + 1;    //thrown out at the plate
						}
					}
					else if(inningstats[0] == 1 && inningstats [1] == 0 && inningstats [2] == 1){
						double first = randomGenerator.nextDouble();
						if (first <=.4){
							inningstats[2] =0;
							inningstats[3] = inningstats[3]+ 2;
	//						System.out.println("you score from first and third");
						}
						else if (first > .43){
							inningstats[2] = 1;
							inningstats[3] = inningstats[3] +1;
	//						System.out.println("you stop at third");
						}
						else {
							inningstats[2] = 0;
							inningstats[3] = inningstats[3] + 1;
							inningstats[4] = inningstats[4] + 1;    //thrown out at the plate
						}
					}
					else if(inningstats[0] == 1 && inningstats [1] == 1 && inningstats [2] == 0){				
						double first = randomGenerator.nextDouble();
						if (first <=.4){
							inningstats[2] =0;
							inningstats[3] = inningstats[3]+ 2;
	//						System.out.println("you score from first and second");
						}
						else if (first > .43){
							inningstats[2] = 1;
							inningstats[3] = inningstats[3] +1;
	//						System.out.println("you stop at third");
						}
						else {
							inningstats[2] = 0;
							inningstats[3] = inningstats[3] + 1;
							inningstats[4] = inningstats[4] + 1;    //thrown out at the plate
						}
					}
					else if(inningstats[0] == 1 && inningstats [1] == 1 && inningstats [2] == 1){
						double first = randomGenerator.nextDouble();
						if (first <=.4){
							inningstats[2] =0;
							inningstats[3] = inningstats[3]+ 3;
	//						System.out.println("you score from first, second, and third");
						}
						else if (first > .43){
							inningstats[2] = 1;
							inningstats[3] = inningstats[3] +2;
	//						System.out.println("you stop at third");
						}	
						else {
							inningstats[2] = 0;
							inningstats[3] = inningstats[3] + 2;
							inningstats[4] = inningstats[4] + 1;    //thrown out at the plate
						}
					}
					inningstats [0] = 0;
					inningstats [1] = 1;
	//				System.out.println("you hit a double");
					}
				if(percent[6] > 0 && inningstats[0] == 1 && inningstats [1] == 0 && inningstats[2] ==0 && inningstats[4]==2){  // add inningstats[4]==0  or 1 2 to make specific for outs
					double stealing = randomGenerator.nextDouble();
					if (stealing < percent[6]){
						inningstats [1] = 1;
					}
					else {
						inningstats[4] = inningstats[4] +1;
					}
					inningstats[0] = 0;
				}       
				if(percent[7] > 0 && inningstats[0] == 0 && inningstats [1] == 1 && inningstats[2] ==0 && inningstats[4] ==2){  // add inningstats[4]==0  or 1 2 to make specific for outs
					double steals = randomGenerator.nextDouble();
					if (steals < percent[7]){
						inningstats [2] = 1;
					}
					else {
						inningstats[4] = inningstats[4] +1;
					}
					inningstats[1] = 0;
				}    
				
			return inningstats;
			// 6153   .321    187   275   29  1409     mlb avg
			
		
	}
}

