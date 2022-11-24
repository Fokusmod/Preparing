package ru.geekbrains.db;

import java.sql.Connection;

public interface Query {

    void createTable();

    void dropTable();

    void preparingForWorkInBD();

    void getConnection(DBConnection dbConnection);

    void errorInSchedule();

    void breakInSchedule();

    void movieStats();

    void countVisitors();

}
