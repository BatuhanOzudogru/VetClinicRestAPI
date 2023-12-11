package dev.patika.core;


import dev.patika.core.exception.*;
import dev.patika.core.result.Result;
import dev.patika.core.result.ResultData;
import dev.patika.core.utils.ResultHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result>  handleNotFoundException(){
       return new ResponseEntity<>(ResultHelper.notFoundError(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<Result>  handleAlreadyExistException(){
        return new ResponseEntity<>(ResultHelper.alreadyExistError(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LocalDateException.class)
    public ResponseEntity<Result>  handleLocalDateException(){
        return new ResponseEntity<>(ResultHelper.localDateError(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VaccineExistsException.class)
    public ResponseEntity<Result>  handleVaccineExistException(){
        return new ResponseEntity<>(ResultHelper.vaccineError(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DoctorNotAvailableException.class)
    public ResponseEntity<Result>  handleDoctorNotAvailableException(){
        return new ResponseEntity<>(ResultHelper.doctorError(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppointmentTimeException.class)
    public ResponseEntity<Result>  handleAppointmentTimeException(){
        return new ResponseEntity<>(ResultHelper.appointmentTimeError(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppointmentExistsException.class)
    public ResponseEntity<Result>  handleAppointmentExistsException(){
        return new ResponseEntity<>(ResultHelper.appointmentExistsError(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppointmentNotAvailableException.class)
    public ResponseEntity<Result>  handleAppointmentNotAvailableException(){
        return new ResponseEntity<>(ResultHelper.appointmentNotAvailableError(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData<List<String>>> handleValidationErrors(MethodArgumentNotValidException e) {
        List<String> validationErrorList = e.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());

        return new ResponseEntity<>(ResultHelper.validateError(validationErrorList), HttpStatus.BAD_REQUEST);
    }


}
