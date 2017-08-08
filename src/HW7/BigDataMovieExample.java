/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW7;

import big.data.DataSource;
import java.util.Scanner;

/**
 *
 * @author dsli
 */
public class BigDataMovieExample {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String title = "";

        do {
            System.out.print("Enter a movie title: ");
            //generate the webaddress for the movie
            String prefix = "http://www.omdbapi.com/?t=";
            title = sc.nextLine();
            String postfix = "&y=&plot=short&r=xml";
            if (title.length() > 0) {
                DataSource ds = DataSource.connectXML(prefix + title.
                        replace(' ', '+') + postfix);
                ds.load();
                System.out.println("true title: " + ds.fetchString("movie/title"));
                System.out.println("actors: " + ds.fetchString("movie/actors"));
                System.out.println("year: " + ds.fetchInt("movie/year"));
            }
        } while (title.length() > 0);

        System.out.println("done.");

    }
}

class TestDataSource {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter a movie title: ");
            //generate the webaddress for the movie
            String prefix = "http://www.omdbapi.com/?t=";
            String title = reader.nextLine();
            String postfix = "&y=&plot=short&r=xml";
            if (title.length() > 0) {
                DataSource ds = DataSource.connectXML(prefix + title.
                        replace(' ', '+') + postfix);
                ds.load();
                //System.out.println(ds.fetchString("movie/title"));
                String actors = ds.fetchString("movie/actors");
                //System.out.println(ds.fetchInt("movie/year"));
                String[] actorList = actors.split(", ");
                for (String s: actorList) {
                    System.out.println(s);
                }
            }
    }
}