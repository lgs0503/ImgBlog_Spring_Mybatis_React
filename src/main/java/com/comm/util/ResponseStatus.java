package com.comm.util;

public enum ResponseStatus {
    CONTINUE(100),
    SWITCHING_PROTOCOL(101),
    PROCESSING(102),

    OK(200),
    CREATED(201),
    ACCEPTED(202),
    NON_AUTHORITATIVE_INFORMATION(203),
    NO_CONTENT(204),
    RESET_CONTENT(205),
    PARTIAL_CONTENT(206),
    MULTI_STATUS(207),
    ALREADY_REPORTED(208),
    IM_USED(226),

    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    PAYMENT_REQUIRED(402),
    FORBIDDEN(403),
    NOT_FOUND(404)
    ;

    private final int status;

    ResponseStatus(int status) {
        this.status = status;
    }

    public int status(){
        return status;
    }
}
