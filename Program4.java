//Kenisha Jones
//CS375 - Algorithms
//Program 4 - Dynamic
import java.io.File; 
import java.util.*;
import java.util.Scanner; 
import java.util.ArrayList;
import java.io.*;
import java.lang.String;
import java.util.Arrays;
import java.util.Collections; 
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Program4{
	
	int count;
	int maxC;
	PrintWriter writer;
	int time;
	int numItem;	
	long start;
	long end;
	int weights[];
	int profits[];
	double timed;

	public Program4() throws FileNotFoundException, IOException{
		count = 0;
		maxC = 0;
		time = 0;
		numItem = 0;
		start = 0;
		end = 0;
		timed = 0;
		writer = new PrintWriter("output.txt", "UTF-8");
	}
	
	public static void main(String[]args) throws FileNotFoundException, IOException{
		//create instance
		Program4 two = new Program4();
		//two.writer.println("sentence");
		
		//read from the command line
		String input = args[0];
		String output = args[1];
		String algo = args[2];
		int alg = Integer.parseInt(algo);
		
		//setup
		File file = new File(input);
		Scanner reader = new Scanner(file);
		two.setup(output);
		
		// *** DYNAMIC PROGRAMMING CODE STARTS HERE ***
		if(alg == 3){
		while(reader.hasNextLine()){
			two.start = System.currentTimeMillis();
			
			//variables
			int numItems = reader.nextInt();
			int capacity = reader.nextInt();
			two.numItem = numItems;
			two.maxC = capacity;
			int profit = 0;
			
			//initalize array
			Double pw [] = new Double[numItems];
			two.weights = new int [numItems+1];
			two.profits = new int [numItems+1];
			for(int i=0;i<numItems;i++){
				pw[i] = 0.0;
				two.weights[i] = 0;
				two.profits[i] = 0;
			}
			
			//add p/w to array
			for(int i=0; i<numItems; i++){
				int w = reader.nextInt();
				int p = reader.nextInt();
				two.weights[i] = w;
				two.profits[i] = p;
			}
		}
			//System.out.println("IWfgkjdfhglkd");
			// *** DYNAMIC PROGRAMMING CODE STARTS HERE ***
			int C = two.maxC;	
			int top[] = new int [C+1];
			int bottom[] = new int [C+1];
			int solution = 0;
			
			//initalize
			ArrayList<int[]>table = new ArrayList<int[]>();		
			for(int c = 0; c < C; c++){
				top[c] = 0;
			}
			bottom[0] = 0;
			table.add(top);
			table.add(bottom);
	
			for(int i=0; i<= two.numItem;i++){
				table.add(table.get(0));
				table.remove(0);
				for(int k = 0;k<=C;k++){
					table.get(1)[k] = 0;
				}
				for(int c=1; c<=C; c++){
				if((two.weights[i] <= c) && (table.get(0)[c-two.weights[i]] + two.profits[i] > table.get(0)[c])){
					table.get(1)[c] = table.get(0)[c-two.weights[i]] + two.profits[i];
				}else{
					table.get(1)[c] = table.get(0)[c];
					}
					
				}
			
				solution = table.get(1)[C];
			
			
			//print max
			two.end = System.currentTimeMillis();
			long timer = two.end - two.start;
			double elapsed = timer / 1000.0;
			two.timed = elapsed;	
			}
			two.writer.println("Dynamic Programming: " + two.numItem + " " + solution + " " + two.timed);
			System.out.println("Dynamic Programming: " + two.numItem + " " + solution + " " + two.timed);
			two.writer.close();
		}
		}
	
		public void setup(String h) throws FileNotFoundException, IOException, UnsupportedEncodingException{
			writer = new PrintWriter(h,"UTF-8");
		}
	
	}
