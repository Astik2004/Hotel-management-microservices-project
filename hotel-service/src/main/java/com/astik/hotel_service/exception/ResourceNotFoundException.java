package com.astik.hotel_service.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException()
    {

        System.out.println("Resource not found at server");
    }
    public ResourceNotFoundException(String msg)
    {

        super(msg);
    }
}
