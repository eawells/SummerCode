package compute;
import java.util.*;

public class UserInput {

	public static void main(String args[]) {
		UserInput us = new UserInput();
		
	}
	
	private String expression1;
	
	public UserInput()
	{
		System.out.println("This program calculates the answer to an expression. Example of an expression: 45.9*3 + 2 - 9.44 / 61 +78. \nType exit to exit.");
		System.out.println("Please enter an expression: ");
		Scanner s = new Scanner(System.in);
		expression1 = s.nextLine();
		while(!expression1.equals("exit")){
			System.out.println("The answer is " + this.calculate(expression1));
			System.out.println("Please enter an expression: ");
			expression1 = s.nextLine();
		}
		s.close();
//		Scanner s = new Scanner(System.in);
//		System.out.println("Enter an operator please: ");
//		op = s.next().charAt(0);
//		//if char is not operator, print error
//		System.out.println("Please enter var1: ");
//		var1 = s.next();
//		//if var is not ints and operator, print error
//		System.out.println("Please enter var2: ");
//		var2 = s.next();
//		System.out.println("The answer is " + this.calculate(var1 + op + var2));
//		s.close();
	}
	
//	//count the number of ops in the string and store them in an arraylist
//	//make a double array of that size plus one
//	//store each number in that array
//	public ArrayList<Character> parseForOps(String var){
//		//for each char in var, check if it is an op. If it is, then 
//		//store it in arraylist
//		ArrayList<Character> ops = new ArrayList<Character>();
//		for (int i = 0; i < var.length(); i++) {
//			if(var.charAt(i) == '*' || var.charAt(i) == '/' || var.charAt(i) == '+' || var.charAt(i) == '-'){
//				ops.add(var.charAt(i));
//			}
//		}
//		//System.out.println(ops.toString());
//		return ops;		
//		
//	}
//	public String[] parseForNumbers(String var){
//		String delims = "[+\\-*/]+"; 
//		String[] doubles = var.split(delims);
//		//System.out.println(doubles[0] + doubles[1] + doubles[2]);
//		return doubles;		
//	}
//		
//	//now that we have the doubles and the ops, we need to calculate the answer
//	public double calculate(String[] op, String[] numbers){
//		//for each op, push the op and push the next two numbers
//		
//		return 0;
//	}
	
	//use a stack to calculate the value of the expression
	public double calculate(String expression){
		//1. go through each char in the expression,
		//2. if the char is an op, then push that op into the s_op stack
		//3. push all the chars behind that op (until the last op) into the s_num stack, casted as doubles
		//if the op is + or -, then continue pushing
		//if the op is * or /, then pop the last number and perform the op on it and the next number
		double ans = 0;
		
		//to keep track of where the last op was
		int j = -1;
		Stack<Character> s_op = new Stack<Character>();
		Stack<Double> s_num = new Stack<Double>();
		for(int i = 0; i<expression.length(); i++){
			if (expression.charAt(i) == '*' || expression.charAt(i) == '/' || expression.charAt(i) == '+' || expression.charAt(i) == '-'){
				if(i != 0){
					s_op.push(expression.charAt(i));
					j=i;
				}
				else{
					//get the double that was before the op
					double doub = getDouble(j,i,expression);
					//push the number		
					s_num.push(doub);
					if(!s_op.isEmpty()){
						if(s_op.peek() == '*'){
							ans = s_num.pop()*s_num.pop();
							s_num.push(ans);
							s_op.pop();
						}
						else if(s_op.peek() == '/'){
							double one = s_num.pop();
							double two = s_num.pop();
							ans = two/one;
							s_num.push(ans);
							s_op.pop();
						}
					}
					s_op.push(expression.charAt(i));
					j=i;
					//System.out.println(doub);
					//System.out.println(expression.charAt(i));
				}
			}
		}
		//push the last double
		double doub = getDouble(j, expression.length() ,expression);
		s_num.push(doub);
		while(!s_op.isEmpty() && (s_num.size()>1)){
			if(s_op.peek()== '*'){
					ans = s_num.pop()*s_num.pop();
			}
			else if(s_op.peek()== '/'){
					double one = s_num.pop();
					double two = s_num.pop();
					ans = two/one;
			}
			else if(s_op.peek()== '+'){
					ans = s_num.pop()+s_num.pop();
			}
			else if(s_op.peek()== '-'){
					double one1 = s_num.pop();
					double two2 = s_num.pop();
					ans = two2-one1;
			}
			s_num.push(ans);
			s_op.pop();
		}
		
		return ans;
	}
	
	private double getDouble(int j, int i, String expression){
		//get the double that was before the op
		String d = "";
		for(int k = j+1; k < i; k++){
			d += expression.charAt(k);
		}
		return Double.parseDouble(d);	
	}
	
	public double addEq(double variable1, double variable2){
		return variable1 + variable2;
	}
	public double subEq(double variable1, double variable2){
		return variable1 - variable2;
	} 
	
	
	}
	
