package com.embl.test.restfulmicroservice.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ErrorModel {
    private Date timeStamp;

    private String message;

    private String details;

    public static ErrorModel of(Date timeStamp, String message, String details) {
        return new ErrorModel(timeStamp, message, details);
    }


}
