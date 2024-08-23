package com.eCom.dhailer.adviser;

import com.eCom.dhailer.exception.DuplicateEntryException;
import com.eCom.dhailer.exception.EntryNotFoundException;
import com.eCom.dhailer.util.StandardResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(EntryNotFoundException.class)
    public ResponseEntity<StandardResponce> handlerEntryNotFoundException(EntryNotFoundException e){
        return new ResponseEntity<>(
                new StandardResponce(404, e.getMessage(), e),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<StandardResponce> handlerDuplicateEntryException(DuplicateEntryException e){
        return new ResponseEntity<>(
                new StandardResponce(409, e.getMessage(), e),
                HttpStatus.CONFLICT
        );
    }
}
