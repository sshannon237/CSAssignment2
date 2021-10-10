package UploadServer;

public class InvalidRequestException extends Exception {
    public InvalidRequestException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
//try {
//    //Code goes here
//} catch (InvalidRequestException e){
//        throw new InvalidRequestException("Invalid request", e);
//}
