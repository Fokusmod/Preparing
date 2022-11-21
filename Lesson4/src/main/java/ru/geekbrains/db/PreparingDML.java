package ru.geekbrains.db;

public enum PreparingDML {

    insertIntoMovies("INSERT INTO movies (title,duration) values " +
                    "('horizon line'," + Duration.MIDDLE.getValue() + ")," +
                    "('soul'," + Duration.MIDDLE.getValue() + ")," +
                    "('tenet'," + Duration.SHORT.getValue() + ")," +
                    "('drunk'," + Duration.LONG.getValue() + ")," +
                    "('palm spring'," + Duration.LONG.getValue() + ");"),


    insertIntoBuyTickets("INSERT INTO buy_tickets (session_id) values " +
            "(1)," +
            "(1)," +
            "(1)," +
            "(9)," +
            "(5)," +
            "(10)," +
            "(10)," +
            "(3)," +
            "(15)," +
            "(15)," +
            "(15)," +
            "(15)," +
            "(15)," +
            "(15)," +
            "(20)," +
            "(20)," +
            "(20)," +
            "(20)," +
            "(20)," +
            "(20);"),

    insertIntoDayWeek("INSERT INTO days_week (title) values " +
            "('monday')," +
            "('tuesday')," +
            "('wednesday')," +
            "('thursday')," +
            "('friday')," +
            "('saturday')," +
            "('sanday');"),

    insertIntoSchedules("INSERT INTO schedules (day_week, session_id) values " +
            "(1,1)," +
            "(1,9)," +
            "(1,5)," +
            "(1,10)," +
            "(1,3)," +
            "(1,15)," +
            "(1,20);"),

    insertIntoSessions("INSERT INTO sessions (movie_id, started_at, end_in, price) values " +
            "(1, datetime('2022-11-20 09:00:00'), datetime('2022-11-20 10:30:00'), 120)," +
            "(1, datetime('2022-11-20 13:00:00'), datetime('2022-11-20 15:30:00'), 120)," +
            "(1, datetime('2022-11-20 16:30:00'), datetime('2022-11-20 18:00:00'), 130)," +
            "(1, datetime('2022-11-20 20:00:00'), datetime('2022-11-20 21:30:00'), 140)," +
            "(2, datetime('2022-11-20 12:00:00'), datetime('2022-11-20 13:30:00'), 150)," +
            "(2, datetime('2022-11-20 10:00:00'), datetime('2022-11-20 11:30:00'), 150)," +
            "(2, datetime('2022-11-20 15:30:00'), datetime('2022-11-20 17:00:00'), 170)," +
            "(2, datetime('2022-11-20 20:30:00'), datetime('2022-11-20 22:00:00'), 180)," +
            "(3, datetime('2022-11-20 10:00:00'), datetime('2022-11-20 11:00:00'), 100)," +
            "(3, datetime('2022-11-20 15:00:00'), datetime('2022-11-20 16:00:00'), 100)," +
            "(3, datetime('2022-11-20 20:00:00'), datetime('2022-11-20 21:00:00'), 140)," +
            "(3, datetime('2022-11-20 22:00:00'), datetime('2022-11-20 23:00:00'), 120)," +
            "(4, datetime('2022-11-20 10:00:00'), datetime('2022-11-20 12:00:00'), 150)," +
            "(4, datetime('2022-11-20 14:00:00'), datetime('2022-11-20 16:00:00'), 170)," +
            "(4, datetime('2022-11-20 18:30:00'), datetime('2022-11-20 20:30:00'), 200)," +
            "(4, datetime('2022-11-20 21:00:00'), datetime('2022-11-20 23:00:00'), 190)," +
            "(5, datetime('2022-11-20 09:00:00'), datetime('2022-11-20 11:00:00'), 150)," +
            "(5, datetime('2022-11-20 14:30:00'), datetime('2022-11-20 16:30:00'), 170)," +
            "(5, datetime('2022-11-20 18:00:00'), datetime('2022-11-20 20:00:00'), 200)," +
            "(5, datetime('2022-11-20 21:00:00'), datetime('2022-11-20 23:00:00'), 180);");

    private final String value;

    PreparingDML(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


