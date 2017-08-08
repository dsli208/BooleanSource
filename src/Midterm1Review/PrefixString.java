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
public class PrefixString {
    public int evaluate(String prefix) throws FullStackException, EmptyStackException{
        String postfix = "";
        for (int i = prefix.length() - 1; i >= 0; i--) {
            postfix += prefix.charAt(i);
        }
        IntStack stack = new IntStack();
        for (int i = 0; i < postfix.length(); i++) {
            if (postfix.charAt(i) >= '0' && postfix.charAt(i) <= '9')
                stack.push(postfix.charAt(i) - '0');
            
        }
    }
}
