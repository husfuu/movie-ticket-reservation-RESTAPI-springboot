package org.binar.movieticketreservation.exception;

public class TransactionServiceException extends RuntimeException{
    public TransactionServiceException(String message){
        super(message);
    }
}
