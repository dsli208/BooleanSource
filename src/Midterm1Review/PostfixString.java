/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Midterm1Review;
import Unit3.*;
import java.util.EmptyStackException;
/**
 *
 * @author dsli
 */
public class PostfixString {
    public int evaluate(String postfix) throws FullStackException, EmptyStackException {
        IntStack stack = new IntStack();
        for (int i = 0; i < postfix.length(); i++) {
            if (postfix.charAt(i) >= '0' && postfix.charAt(i) <= '9')
                stack.push(postfix.charAt(i) - '0');
            else {
                int operandB = stack.pop();
                int operandA = stack.pop();
                switch (postfix.charAt(i)) {
                    case '+': stack.push(operandA + operandB); break;
                    case '-': stack.push(operandA - operandB); break;
                    case '*': stack.push(operandA * operandB); break;
                    case '/': stack.push(operandA / operandB); break;
                    case '^': stack.push(operandA ^ operandB); break;
                }
            }
        }
        return stack.pop();
    }
}
