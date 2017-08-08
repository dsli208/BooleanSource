/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW3;

import java.io.FileNotFoundException;

/**
 * David S. Li 
 * 110328771 
 * Assignment #3 
 * CSE 214 R05 
 * Frank Migliorno 
 * Sun Lin
 *
 * @author dsli
 */
public class JavascriptFormatter {

    /**
     * Invariants: stack, a JSStack object containing BlockTypes waiting to be
     * popped (when their corresponding "right" version appears in the
     * JavaScript code indentLevel, an <code> int </code> keeping track of how
     * many times the <code> '\t' </code> is supposed to be used when creating a
     * newline <code> ('\n') </code>
     *
     */
    private JSStack stack;
    private int indentLevel;

    /**
     * Formats the inputted JavaScript code, alerting the user to any problems.
     * Evaluates the code as commented in the method.
     *
     * @param input A String containing the JavaScript code that is to be
     * formatted
     * @exception EmptyListException Thrown if this method tries to pop from <code> stack
     * </code> but it is empty
     * @exception IllegalParenthesesException Thrown if there is a missing or
     * extra parenthesis (containing code formatted up to that point and a
     * boolean value for missing or extra)
     * @exception IllegalBraceException Thrown if there is a missing or extra
     * brace. Works in the same manner as the above (contains formatted up to
     * that point and a boolean value for missing or extra)
     * @return A String, <code> answer </code>, that contains the formatted
     * JavaScript code (with newlines and tabs inserted)
     *
     */
    public String format(String input) throws EmptyListException, IllegalParenthesesException, IllegalBraceException, IllegalQuoteException {
        //String to be returned as formatted JavaScript code
        String answer = "";
        //Going char by char in the String
        for (int i = 0; i < input.length(); i++) {
            //Append each char as it is evaluated in the String
            answer += input.charAt(i) + "";
            //After '{', insert '\n' and '\t' chars as needed
            if (input.charAt(i) == '{') {
                //NOTE: Cannot put any brace exceptions here, since left braces can be inserted while there is the left brace in the stack (for loops, if statements, etc.)
                stack.push(BlockType.BRACE);
                indentLevel++;
                answer += '\n';
                for (int j = 0; j < indentLevel; j++) {
                    answer += '\t';
                }
            } //If there is a (left) parenthesis, push to stack and pop when the right parenthesis is found
            //NOTE: If this parenthesis is for a for loop, don't push PAREN, as FOR will have already been pushed and will be popped with the ')'
            else if (input.charAt(i) == '(') {
                if (stack.isEmpty()) {
                    stack.push(BlockType.PAREN);
                } else if (stack.peek().getData() != BlockType.FOR) { //So when we have a "for" loop, the FOR is popped with a ')'
                    stack.push(BlockType.PAREN);
                }
            } //The two methods below pop the corresponding '(' or '{' (or FOR BlockType if we are using a for loop)
            else if (input.charAt(i) == ')') {
                //If it's an empty stack, then this is deemed to be an extra parenthesis since there is nothing to pop
                if (stack.isEmpty()) {
                    throw new IllegalParenthesesException(answer, false);
                } //If the most recent stack item is a brace, it is deemed an extra since there wasn't the '(' to append PAREN on to the stack
                else if (stack.peek().getData() == BlockType.BRACE) {
                    throw new IllegalParenthesesException(answer, false);
                } //If there is only one quote in the parentheses, it does not complete a String
                else if (stack.peek().getData() == BlockType.QUOTE) {
                    throw new IllegalQuoteException(answer);
                }
                //else if (stack.peek().getData() == BlockType.PAREN || stack.peek().getData() == BlockType.FOR)
                stack.pop();
            } //When we reach a closing brace, pop the corresponding opening brace to "close" the method, loop, if statement, etc.
            else if (input.charAt(i) == '}') { //if the char is a '}', we have to make a '\n' and decrease indentation as needed
                //No braces to close, so this is deemed an extra brace
                if (stack.isEmpty()) {
                    throw new IllegalBraceException(answer, false);
                }
                stack.pop();
                indentLevel--;
                //Insert newline and indentLevel tab chars
                answer += '\n';
                for (int j = 0; j < indentLevel; j++) {
                    answer += '\t';
                }
                //stack.pop();
            }/*
            If we reach a semi-colon, we are presumed to have reached the end of a statement.  Thus we create a newline and indent as needed.
            NOTE: If there are any open parentheses on the statement when we reach the semicolon (EXCEPT WHEN THERE IS A FOR LOOP), an Exception is thrown
             */ else if (input.charAt(i) == ';') {

                if (stack.isEmpty()) {
                    answer += '\n';
                    for (int j = 0; j < indentLevel; j++) {
                        answer += '\t';
                    }
                } else if (stack.peek().getData() != BlockType.FOR) {
                    if (stack.peek().getData() == BlockType.PAREN) {
                        throw new IllegalParenthesesException(answer, true);
                    }
                    answer += '\n';
                    for (int j = 0; j < indentLevel; j++) {
                        answer += '\t';
                    }
                }
                if (i < input.length() - 1 && input.charAt(i + 1) == '}') { //Peek at the next character, if it is a '}', automatically decrease the indentation ahead of time
                    indentLevel--;
                }
            } //If we reach a double quotation mark, we handle it as follows:
            else if (input.charAt(i) == '"') {
                //If an empty stack, we can simply push it onto the stack
                if (stack.isEmpty()) {
                    stack.push(BlockType.QUOTE);
                } //Else if we already have a QUOTE on the top of the stack
                else if (stack.peek().getData() == BlockType.QUOTE) {
                    stack.pop(); //We have completed the quotation
                } else //Meaning stack.pop() does not have a QUOTE on top
                {
                    stack.push(BlockType.QUOTE);
                }
            } //Provision for pushing "FOR": must have "for", with the f having 2 chars ahead of it to ensure that there is room for "for"
            else if (input.charAt(i) == 'f' && i < input.length() - 2 && (input.charAt(i + 1) == 'o' && input.charAt(i + 2) == 'r')) {
                if (stack.isEmpty()) { //Has nothing, not even quotes (for the below scenario), so this will not leak to the peek() method being invoked and triggering an EmptyListException
                    stack.push(BlockType.FOR);
                    int j = i + 3; //j is set to the character after the 'r' in "for" to examine what follows the "for" in the while loop below
                    while (input.charAt(j) == ' ' && j < input.length()) {
                        j++;
                    }
                    if (input.charAt(j) != '(') //Ensures that if a FOR loop is being created, then it is followed by the proper syntax (the loop will traverse whitespace characters, as more than one space is allowed between the "for" and the '(')
                    {
                        throw new IllegalParenthesesException(answer, true); //If there is no '(' following the "for", there is a missing parenthesis
                    }
                } else if (stack.peek().getData() != BlockType.QUOTE) { //If we are in a quotation and it has the word for, prevents IllegalParenthesisException from falsely being thrown
                    stack.push(BlockType.FOR);
                    int j = i + 3;
                    while (input.charAt(j) == ' ' && j < input.length()) {
                        j++;
                    }
                    if (input.charAt(j) != '(') {
                        throw new IllegalParenthesesException(answer, true);
                    }
                }
                //Else, stack is not empty, and the top element is of type quote.  Since we don't want to push the for and mistakenly pop that instead of the quote, do nothing
            }
            if ((i < input.length() - 1 && input.charAt(i + 1) == '}')) { //Peek at the next character, if it is a '}', automatically decrease the indentation ahead of time
                indentLevel--;
            }
        }
        //If we have formatted all the code, and the stack is not empty, throw an IllegalBraceException
        //It should be braces (since parentheses and quotes should have been taken care of before the end of a line
        if (stack.isEmpty() == false) { //Meaning there are extras in the stack after the program has finished (stack should ideally be empty)
            throw new IllegalBraceException(answer, true);
        }
        stack.closePrintWriter();
        return answer;
    }

    /**
     * Returns an instance of the JavascriptFormatter Object
     * <dt> Postcondition: </dt>
     * <code> stack </code> is initialized and <code> indentLevel </code> is set
     * to 0
     *
     */
    public JavascriptFormatter() throws FileNotFoundException {
        stack = new JSStack();
        indentLevel = 0;
    }
}
