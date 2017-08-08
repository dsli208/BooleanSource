/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * David S. Li
 * 110328771
 * Assignment #3
 * CSE 214 R05
 * Frank Migliorno
 * Sun Lin
 * @author dsli
 */
public class JavascriptFormatterRunner {

    public static void main(String[] args) throws FileNotFoundException, IllegalBraceException, IllegalParenthesesException, EmptyListException, IllegalQuoteException {
        Scanner reader = new Scanner(System.in);
        JavascriptFormatter j = new JavascriptFormatter();
        System.out.print("Enter a file name to be read in: ");
        File f = new File(reader.nextLine());
        Scanner fileReader = new Scanner(f);
        fileReader.useDelimiter("\n\t"); //Used to allow the FileReader to use NextLine() without taking in any newline characters
        String s = "";
        //String s = f.toString(); --Doesn't work, only returns the File ADDRESS
        try {
            if (f.exists() && f.canRead()) {
                while (fileReader.hasNextLine()) {
                    s += fileReader.nextLine(); //must be newline, otherwise whitespaces will be ignored
                }
                System.out.println("The file was read.  Now formatting into JavaScript code...");
                //System.out.println("The file string is: " + s);
                System.out.println(j.format(s));
            } else {
                System.out.println("File could not be read properly");
            }
        } catch (IllegalBraceException e1) {
            System.out.println(e1.isMissingBrace() == true ? "Missing brace." : "Extra brace.");
            System.out.println(e1.getCode());
        }
        catch (IllegalParenthesesException e2) {
            System.out.println(e2.isMissingParentheses() ? "Missing parenthesis." : "Extra parenthesis.");
            System.out.println(e2.getCode());
        }
        catch (IllegalQuoteException e3) {
            System.out.println("Extra or missing pair of quotes.");
            System.out.println(e3.getCodeWritten());
        }
        catch(NullPointerException e) {
            System.out.println("File not found");
        }
        catch (Exception eUltimate) {
            System.out.println("File not found");
        }
        /*catch(EmptyListException e) {
            System.out.println("Error when popping (format your code a little!)");
        }*/
        //Remove any '\n' and '\t' within this class and have that reflect in the String before it goes through the JavascriptFormatter
        /*finally {
            System.out.println("Here is the formatted code below:");
            System.out.println(j.format(s));
        }*/
        //Print the formatted file, or any errors
    }
}
