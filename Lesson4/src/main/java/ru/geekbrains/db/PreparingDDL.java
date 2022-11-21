package ru.geekbrains.db;

public enum PreparingDDL {

    tableMovies ("CREATE TABLE IF NOT EXISTS movies (id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR (100) NOT NULL ,duration int2 NOT NULL);"),

    tableDaysWeek ("CREATE TABLE IF NOT EXISTS days_week(id INTEGER PRIMARY KEY AUTOINCREMENT,title varchar (20) NOT NULL );"),

    tableSessions ("CREATE TABLE IF NOT EXISTS sessions(id INTEGER PRIMARY KEY AUTOINCREMENT,movie_id INTEGER not null,started_at date " +
            "not null ,end_in DATE not null ,price int2 not null,FOREIGN KEY (movie_id) references movies (id));"),

    buyTickets ("CREATE TABLE if not exists buy_tickets(id INTEGER PRIMARY KEY AUTOINCREMENT ,session_id INTEGER not null,foreign key (session_id) references sessions(id));"),

    schedules ("CREATE TABLE IF NOT EXISTS schedules(day_week INTEGER,session_id INTEGER,foreign key (session_id) references sessions(id),foreign key(day_week) references days_week(id));");

    private final String value;

    PreparingDDL(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
