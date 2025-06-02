package com.example.visitsservice.infrastructure.exceptionshandler;

public class ExceptionConstants {
    private ExceptionConstants(){}

    public static final String SCHEDULE_EXISTS = "Schedule already exists for the given date and time.";
    public static final String VISIT_EMAIL_EXCEPTION = "The email is not valid or is missing.";
    public static final String SCHEDULE_REQUIRED_EXCEPTION = "The schedule is not available.";
}
