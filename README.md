# MovieSort
This is a simple Java CLI application that sorts a list of movies provided by the user in chronological order with merge sort based on the year they were released. If multiple movies came out in the same year, then those movies are sorted in alphabetical order with bubble sort.

The application utilizes the Scanner class to accept user input so that the user can provide the title and the year of release for each of their movies. If the input does not match the proper format, or if the user does not input anything, then the application will close with an **Invalid input** error. After inputting a movie, the application will display a list of all the movies added up to that point and ask the user if they would like to keep listing movies, or if they are ready to sort their movies. If the user is ready to sort, then they can enter "sort" to begin the sorting process. If not, the user can enter "no" to keep adding movies to the list.

## How to Run
Download Visual Studio Code: https://code.visualstudio.com/download

Clone the project:
```
git clone https://github.com/va2324/MovieSort.git
```

Run the following commands in the terminal to start the application:
```
javac MovieSort.java
java MovieSort
```
