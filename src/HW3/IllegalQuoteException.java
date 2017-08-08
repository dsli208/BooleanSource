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
class IllegalQuoteException extends Exception {
    /**
     * Invariant:
     * codeWrittenUpToThatPoint, a String containing code formatted up to the point that this exception was thrown
     **/
    private String codeWrittenUpToThatPoint;
    /**
     * Returns an instance of IllegalQuoteException
     * @param codeWritten
     *      A String that codeWrittenUpToThatPoint is to be set to
     **/
    public IllegalQuoteException(String codeWritten) {
        this.codeWrittenUpToThatPoint = codeWritten;
    }
    /**
     * Returns a String containing formatted code written up to the point where this IllegalQuoteException was thrown
     * @return
     *      <code> codeWrittenUpToThatPoint </code> a variable representing the String containing the code
     **/
    public String getCodeWritten() {
        return codeWrittenUpToThatPoint;
    }
}
