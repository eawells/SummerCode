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
			System.out.println("The answer is " + this.read(expression1));
			System.out.println("Please enter an expression: ");
			expression1 = s.nextLine();
		}
		s.close();
	}
	
	//use a stack to calculate the value of the expression
	public double read(String expression){
		//1. go through each char in the expression,
		//2. if the char is an op, then push that op into the s_op stack
		//3. push all the chars behind that op (until the last op) into the s_num stack, casted as doubles
		//if the op is + or - or (, then continue pushing
		//if the op is ), then find the value of the expression until reaching ( in s_op
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
						if(s_op.peek() == ')'){
							//
						}
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
	
	private double compute(char op, double left, double right){
		switch (op){
		case '+':
			return left+right;
		case '-':
			return left-right;
		case '/':
			return left/right;
		case '*':
			return left*right;
		}
		return left;
		
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
	
