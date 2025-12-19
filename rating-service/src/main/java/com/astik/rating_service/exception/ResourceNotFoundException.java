package com.astik.rating_service.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException()
    {
        System.out.println("Resource Not Found at server!!");
    }
    public ResourceNotFoundException(String msg)
    {
        super(msg);
    }
}
