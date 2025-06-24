

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.MatchResult;
/**
 *
 * @author VasuK
 */
public class MovieSort {

    public static class Movie{
        String title;
        int year;
        public Movie (String title, int year){
            this.title = title;
            this.year = year;
        }
        @Override
        public String toString(){
            return (title + "-" + year);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Welcome to the Movie Sorter!\n");
        System.out.println("This application will sort your movies in "
                + "chronological order based on the year they were released. "
                + "If multiple movies came out in the same year, then they "
                + "will be sorted in alphabetical order based on their titles.\n");
        System.out.println("List all the movies you want to sort. Provide their "
                + "title and their year of release when prompted. When the "
                + "application asks if you are ready to sort, you can enter 'no' "
                + "to keep listing movies, or you can enter 'sort' to start the "
                + "sorting process.\n");
        System.out.println("Let's get started!\n");

        ArrayList<Movie> movies = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        
        while (true){
            // User provides title
            String title = "";
            int year = 0;
            System.out.print("Enter your movie's title: ");
            if(s.hasNextLine()){
                title = s.nextLine();
            }
            else{
                System.out.println("Invalid input");
                System.exit(1);
            }

            // User provides year of release
            System.out.print("Enter your movie's year of release: ");
            String pattern1 = "(19\\d\\d)"; // Year must match 4-digit pattern
            String pattern2 = "(20\\d\\d)"; // Year must be 19-- or 20--
            if(s.hasNextLine()){
                if(s.findInLine(pattern1) != null || s.findInLine(pattern2) != null){
                    MatchResult m = s.match();
                    year = Integer.parseInt(m.group(1));
                }
                else{
                    System.out.println("Invalid input");
                    System.exit(1);
                }
            }
            else{
                System.out.println("Invalid input");
                System.exit(1);
            }

            Movie movie = new Movie(title, year); // Creates new movie from user input and adds it to the list
            movies.add(movie);
            for (Movie m : movies){
                System.out.println(m);
                
            }

            s.nextLine(); // Consumes leftover newline from previous input

            System.out.println("Are you ready to begin sorting your movies? ");
            String input;
            if(s.hasNextLine()){
                input = s.nextLine();
                if(input.equalsIgnoreCase("sort")){
                    break;
                }
                else if(input.equalsIgnoreCase("no")){
                    continue;
                }
                else{
                    System.out.println("Invalid input");
                    System.exit(1);
                }
            }
            else{
                System.out.println("Invalid input");
                System.exit(1);
            }
        }
        s.close(); // Closes scanner to prevent memory leaks

        movies = sortYear(movies, 0, movies.size()); // Sorts movies in chronological order with merge sort
        movies = sortTitle(movies); // Sorts movies of same year in alphabetical order with bubble sort
        for(Movie movie : movies){
            System.out.println(movie);
        }
    }

    public static ArrayList<Movie> sortYear(ArrayList<Movie> movies, int start, int end){
        if(end - start < 2){
            return movies;
        }
        int mid = start+(end - start)/2;
        sortYear(movies, start, mid);
        sortYear(movies, mid, end);

        ArrayList<Movie> first = new ArrayList<>();
        ArrayList<Movie> second = new ArrayList<>();
        for(int i = 0; i < mid - start; i++){
            first.add(movies.get(i + start));
        }
        for(int i = 0; i < end - mid; i++){
            second.add(movies.get(i + mid));
        }
        int i1 = 0, i2 = 0, i = start;
        while(i1 < first.size() && i2 < second.size()){
            if(first.get(i1).year <= second.get(i2).year){
                movies.set(i, first.get(i1));
                i1++;
                i++;
            }
            else{
                movies.set(i, second.get(i2));
                i2++;
                i++;
            }
        }
        while(i1 < first.size()){
            movies.set(i, first.get(i1));
            i1++;
            i++;
        }
        while(i2 < second.size()){
            movies.set(i, second.get(i2));
            i2++;
            i++;
        }
        return movies;
    }

    public static ArrayList<Movie> sortTitle(ArrayList<Movie> movies){
        boolean done = false;
        while(!done){
            done = true;
            for(int i = 0; i < movies.size() - 1; i++){
                if(movies.get(i).year == movies.get(i + 1).year){
                    if(movies.get(i).title.compareToIgnoreCase(movies.get(i + 1).title) > 0){
                        Movie m = movies.get(i);
                        movies.set(i, movies.get(i + 1));
                        movies.set(i + 1, m);
                        done = false;
                    }
                }
            }
        }
        return movies;
    }
}
