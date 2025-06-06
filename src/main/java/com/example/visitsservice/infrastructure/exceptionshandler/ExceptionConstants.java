package com.example.visitsservice.infrastructure.exceptionshandler;

public class ExceptionConstants {
    private ExceptionConstants(){}

    public static final String SCHEDULE_EXISTS = "Schedule already exists for the given date and time.";
    public static final String VISIT_EMAIL_EXCEPTION = "The email is not valid or is missing.";
    public static final String SCHEDULE_REQUIRED_EXCEPTION = "The schedule is not available.";
    public static final String FINAL_DATE_EXCEPTION = "the final date can not null, before to today, later than 21 days a 21 días or before to initial date";
    public static final String INITIAL_DATE_EXCEPTION = "the initial date can not null, before to today or later than 21 days";
    public static final String USER_IS_REQUIRED_EXCEPTION = "The user is required and must exists.";
    public static final String REAL_STATE_IS_REQUIRED_EXCEPTION = "The real state is required and must exists.";
}
