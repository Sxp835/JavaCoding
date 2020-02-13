package lab07;

import java.util.*;

//Extension of Chapter 14.4 Case Study: Expression Evaluator

public class Postfixer {


	/**
	*  Determines if the first operator has same or greater
    *  precedence than the second
	*
	* @param op1 the first operator
	* @param op2 the second operator
	* @return the boolean result
	*/
	private static boolean hasPrecedence(String op1, String op2) {
		
		int p1 = opToPrcd(op1);
		int p2 = opToPrcd(op2);
		
		if (p1<=p2) {
			return true;
		}
		else {
			return false; 
		}
	}


	/**
	* Converts an operator to its precedence priority
	*
	* We expect you to be able to handle +, -, *, /, ^, and (
	* (why don't you need ")" as well? see algorithm in part 4)
	*
	* The order of these is as follows:
	*    ^, * and /, + and -, (
	*
	* @param op a string representing an operator, e.g. "+" or "-"
	* @return an integer value reflecting its precedence
	*/
	private static int opToPrcd(String op) {
		
		switch(op) {
		
		case "^" :
			return 1;
		case "*":	
			return 2;
		case "/":
			return 2;
		case "+":
			return 3;
		case "-":
			return 3;
		case "(":
			return 4;
		
		}
		
		
	    return 0; 
	}

	/**
	* determines if the input token is an operator
	*
	* @param token the string token to check
	* @return a boolean reflecting the result
	*/
	private static boolean isOperator(String token) {
		
		if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^") || token.equals("(") || token.equals(")")) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
    * Evaluates an expression
    *
    * NOTE Beware the order of pop and evaluation when receiving operand1
    * and operand2 as input.
    *
    * @param operand1 the first operand
    * @param operator the operator to apply
    * @param operand2 the second operand
    * @return a double expressing the result
    * @throws IllegalArgumentException if operator passed is not one of
    *  "+", "-", "*", "/", or "^"
    *
	*/
	private static double evaluate(double operand1, String operator, double operand2){
		
		double answer;
		
		if(operator.equals("+")) {
			answer = operand1 + operand2;
		}
		else if(operator.equals("-")) {
			answer = operand1 - operand2;
		}
		else if(operator.equals("*")) {
			answer = operand1 * operand2;
		}
		else if(operator.equals("/")) {
			answer = operand1 / operand2;
		}
		else if(operator.equals("^")) {
			answer = Math.pow(operand1,operand2);
		}
		else {
			throw new IllegalArgumentException("operator is not valid");
		}
		
		
		
		return answer; // placeholder
	}


	/**
	* give a description of the purpose of this method
	* @param line fill in
	* @return fill in
	*/
	public static double infixEvaluator(String line){
		
		StringSplitter data = new StringSplitter(line);
		Stack<String> operators = new Stack<String>();
		Stack<Double> operands = new Stack<Double>();
		
		while(data.hasNext()) {
			String token = data.next();
			if (token.equals("(")){
				operators.push(token);
			}
			else if (token.equals(")")) {
				while(!operators.peek().equals("(")) {
					String operator = operators.pop();
					Double op1 = operands.pop();
					Double op2 = operands.pop();
					Double answr = evaluate(op2, operator, op1);
					operands.push(answr);
				}
				operators.pop();
			}
			else if (isOperator(token)) {
				while((!operators.isEmpty()) && (hasPrecedence(operators.peek(),token))) {
					String operator = operators.pop();
					Double op1 = operands.pop();
					Double op2 = operands.pop();
					Double answr = evaluate(op2, operator, op1);
					operands.push(answr);
				}
				operators.push(token);
			}
			else {
				Double token1 = Double.parseDouble(token);
				operands.push(token1);
			}
			
		}
		while(!operators.isEmpty()) {
			String operator = operators.pop();
			Double op1 = operands.pop();
			Double op2 = operands.pop();
			Double answr = evaluate(op2, operator, op1);
			operands.push(answr);
		}
		
		return operands.pop(); // placeholder

	}

	/**
	* give a description of the purpose of this method
	* @param line fill in
	* @return fill in
	*/
	public static String toPostfix(String line){
		
		StringSplitter data = new StringSplitter(line);
		Stack<String> operators = new Stack<String>();
		String postFix = "";
		String op1;
		
		while(data.hasNext()) {
			String token = data.next();
			
			if(token.equals("(")) {
				operators.push(token);
			}
			else if(token.equals(")")) {
				op1 = operators.pop();
				while(!op1.equals("(") && (!operators.isEmpty())) {
					postFix+=op1;
					op1 = operators.pop();
				}
				
			}
			else if(isOperator(token)) {
				if(!operators.isEmpty()) {
					op1 = operators.peek();
					while((!op1.equals(")")) && (hasPrecedence(op1, token))){
						operators.pop();
						postFix+=op1;
						op1 = operators.peek();
					}
				}
				operators.push(token);
			}
			else {
				postFix+=token;
			}
		}
		
		
		return postFix;
	}


	public static void main(String[] args){

        if (infixEvaluator("10 + 2") != 12)
            System.err.println("test1 failed --> your answer should have been 12");

        if (infixEvaluator("10 - 2 * 2 + 1") != 7)
            System.err.println("test1 failed --> your answer should have been 7");

        if (infixEvaluator("100 * 2 + 12") != 212)
            System.err.println("test2 failed --> your answer should have been 212");

        if (infixEvaluator("100 * ( 2 + 12 )") != 1400)
            System.err.println("test3 failed --> your answer should have been 1400");

        if (infixEvaluator("100 * ( 2 + 12 ) / 14") != 100)
            System.err.println("test4 failed --> your answer should have been 100");
        
        System.out.println("Lab Testing Done!!!");
        
        
//		System.out.print(toPostfix(new String("(4+5)")));
//		System.out.print(toPostfix(new String("9")));
//		System.out.println(toPostfix(new String("((4+5)*6)")));
		
		if (!toPostfix(new String("(4+5)")).equals("45+"))
            System.err.println("test1 failed --> should have been 45+");

        if (!toPostfix(new String("((4+5)*6)")).equals("45+6*"))
            System.err.println("test2 failed --> should have been 45+6*");

        if (!toPostfix(new String("((4+((5*6)/7))-8)")).equals("456*7/+8-"))
            System.err.println("test3 failed --> should have been 456*7/+8-");

        if (!toPostfix(new String("((4+5*(6-7))/8)")).equals("4567-*+8/"))
            System.err.println("test4 failed --> should have been 4567-*+8/");

        if (!toPostfix(new String("(9+(8*7-(6/5^4)*3)*2)")).equals("987*654^/3*-2*+"))
            System.err.println("test5 failed --> should have been 987*654^/3*-2*+");

        System.out.println("Assignment Testing Done!!!");


	}

}
