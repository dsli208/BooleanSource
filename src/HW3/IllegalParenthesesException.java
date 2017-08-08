/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW3;

/**
 * David S. Li
 * 110328771
 * Assignment #3
 * CSE 214 R05
 * Frank Migliorno
 * Sun Lin
 * @author dsli
 */
class IllegalParenthesesException extends Exception {
    /**
     * Invariants:
     * codeUpToThatPoint: a String containing all code written up to the point of the Exception
     * missingParentheses: a boolean determining if it is a missing parenthesis (true) or an extra parenthesis (false)
     **/
    private String codeUpToThatPoint;
    private boolean missingParentheses;
    /**
     * Returns an instance of the IllegalParenthesesException Exception
     * @param s
     *      A String that s is to be set to, contains the code written up to the point of the Exception
     * @param missing
     *      A boolean value determining if it is a missing or extra parenthesis
     **/
    public IllegalParenthesesException(String s, boolean missing) {
        this.codeUpToThatPoint = s;
        this.missingParentheses = missing;
    }
    
    /**
     * Returns the code written up to the point of the exception
     * @return 
     *      <code> codeUpToThatPoint </code> a reference to the String containing code formatted before the Exception was thrown
     **/
    public String getCode() {
        return codeUpToThatPoint;
    }
    
    /**
     * Returns <code> missingParentheses </code>, determining if it is a missing or extra parenthesis that caused this Exception to be thrown
     * @return
     *      <code> missingParentheses </code>, boolean value determining if it is a missing or extra parenthesis that caused this Exception to be thrown
     **/
    public boolean isMissingParentheses() {
        return missingParentheses;
    }
}
