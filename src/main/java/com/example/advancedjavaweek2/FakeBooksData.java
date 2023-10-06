package com.example.advancedjavaweek2;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Formatter;

public class FakeBooksData {
    //create  insert statements for random data
    public static void createSQL (){
        //Example INSERT INTO SALES(BOOK_ID, DATE_SOLD) VALUES (1, '2023-01-01');
        SecureRandom secureRandom = new SecureRandom();

        try (
                Formatter formatter = new Formatter("salesData.sql")
                ){
            //create loop to get random 500 data
            for (int i = 0; i < 500; i++) {
                int bookId = secureRandom.nextInt(1,6);
                LocalDate dateSold = LocalDate.now().minusDays(secureRandom.nextInt(365));
                formatter.format("INSERT INTO SALES(BOOK_ID, DATE_SOLD) VALUES (%d, '%s');\n", bookId, dateSold);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public static void main(String[] args) {
        createSQL();
    }*/
}
