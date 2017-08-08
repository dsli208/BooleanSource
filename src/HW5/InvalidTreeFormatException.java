/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW5;

/**
 * David S. Li
 * 110328771
 * Assignment #5
 * CSE 214 R05
 * Frank Migliorno
 * Sun Lin
 * @author dsli
 */
class InvalidTreeFormatException extends Exception {
    /**
     * Invariant:
     * errorMessage, a String describing the error found
     **/
    private String errorMessage;
    /**
     * Constructs an InvalidTreeFormatException
     * @param s
     *      A String describing the error found in the program
     **/
    public InvalidTreeFormatException(String s) {
        this.errorMessage = s;
    }
    /**
     * Returns the type of error
     * @return
     *      <code> errorMessage </code>, describing what the error was
     **/
    public String getErrorMessage() {
        return errorMessage;
    }
    
}
