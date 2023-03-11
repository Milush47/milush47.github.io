package com.example.app.errors;


import java.util.List;
import java.util.Map;


public class ExceptionMessage {
    public static final String      USER_NOT_FOUND      = "User with %s email not found";
    public static final String      EMAIl_IS_TAKEN      = "Email %s is already taken";

    public static final List<String> INVALID_INPUT      = List.of(
            "email.valid",
            "email.is_req",
            "fn.is.req",
            "ln.is.req",
            "pswd.conf.is.req",
            "pswd.is.req",
            "pswd.valid",

            "pswds.must.match"
    );
}
