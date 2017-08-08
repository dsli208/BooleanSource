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
class IllegalBraceException extends Exception {
    /**
     * Invariants:
     * codeUpToThatPoint: A String that represents all code written up to the point that the Exception was thrown
     * missing: A boolean value denoting if this is a missing brace (true) or an extra brace (false)
     **/
    private String codeUpToThatPoint;
    private boolean missing;
    /**
     * Creates an instance of the IllegalBraceException Object
     * @param s
     *      A String that <code> codeUpToThatPoint </code> is to be set to
     * @param missing
     *      A boolean value that denotes if it this Exception is for a missing brace or an extra brace
     **/
    public IllegalBraceException(String s, boolean missing) {
        this.codeUpToThatPoint = s;
        this.missing = missing;
    }
    /**
     * 
     **/
    public String getCode() {
        return codeUpToThatPoint;
    }
    
    public boolean isMissingBrace() {
        return missing;
    }
    
}
