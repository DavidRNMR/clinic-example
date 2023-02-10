package com.clinic.exceptions;

public class PatientNotFoundException extends Exception{

    public PatientNotFoundException (String message){
        super(message);
    }
}
