package com.example.advancedjavaweek2;

import java.sql.*;
import java.util.ArrayList;

public class DBUtility {
    // variables for username, password and connection url
    private static String user = "Evan200529232";
    private static String pass = "PoyI_Ta1ZX";
    private static String connectURL = "jdbc:mysql://172.31.22.43:3306/" + user;

    // static method to insert data to db
    public static int insertBookIntoDB(Book book) throws SQLException {
        int bookId = -1;
        ResultSet resultSet = null;

        // store the sql statement
        String sql = "INSERT INTO BOOKS(BOOK_NAME, AUTHOR, GENRE, PRICE, IS_AVAILABLE) VALUES (?, ?, ?, ?, ?);";

        // try with resource block
        // anything inside the () will be automatically closed if the exception occurs or not
        try(
                Connection conn = DriverManager.getConnection(connectURL, user, pass);
                PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[]{"bookId"});
                )
        {
            // run prepared statement and attach data instead of ?
            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getGenre());
            preparedStatement.setDouble(4, book.getPrice());
            preparedStatement.setBoolean(5, book.isAvailable());

            // execute the query
            preparedStatement.executeUpdate();

            // add result set
            resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                bookId = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null) {
                resultSet.close();
            }
        }

        return bookId;
    }

    //static method to get data from db
    public static ArrayList<Book> getBooksFromDB() {
        ArrayList<Book> books = new ArrayList<>();

        // string to hold sql select statement
        String sql = "SELECT BOOKS.BOOK_ID, BOOKS.BOOK_NAME, BOOKS.AUTHOR, BOOKS.GENRE, BOOKS.PRICE, BOOKS.IS_AVAILABLE, COUNT(SALES.DATE_SOLD) AS 'UNITS_SOLD'\n" +
                "FROM BOOKS\n" +
                "INNER JOIN SALES\n" +
                "ON BOOKS.BOOK_ID = SALES.BOOK_ID\n" +
                "GROUP BY BOOKS.BOOK_ID;\n";

        //establish connection and run query
        try(
                Connection conn = DriverManager.getConnection(connectURL, user, pass);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                ) {
            // loop to result set until we have the last row and store each of them in a book object
            while (resultSet.next()) {
                int bookId = resultSet.getInt("BOOK_ID");
                String bookName = resultSet.getString("BOOK_NAME");
                String author = resultSet.getString("AUTHOR");
                String genre = resultSet.getString("GENRE");
                Double price = resultSet.getDouble("PRICE");
                Boolean isAvailable = resultSet.getBoolean("IS_AVAILABLE");
                int unitsSold = resultSet.getInt("UNITS_SOLD");

                // create book object for variables
                Book book = new Book(bookId, bookName, author, genre, price, isAvailable, unitsSold);

                books.add(book);

            }
        } catch (Exception e){
            e.printStackTrace();
        }


        return books;
    }
}
