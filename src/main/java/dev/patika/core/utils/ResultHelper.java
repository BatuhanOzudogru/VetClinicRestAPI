package dev.patika.core.utils;

import dev.patika.core.result.Result;
import dev.patika.core.result.ResultData;
import dev.patika.dto.response.pagination.CursorResponse;
import org.springframework.data.domain.Page;

public class ResultHelper {

    public static <T> ResultData<T> created(T data) {
        return new ResultData<>(true, "201", Message.CREATED, data);
    }

    public static <T> ResultData<T> updated(T data) {
        return new ResultData<>(true, "200", Message.UPDATED, data);
    }


    public static <T> ResultData<T> validateError(T data) {
        return new ResultData<>(false, "400", Message.VALIDATE_ERROR, data);
    }

    public static Result deleted() {
        return new Result(true, "200", Message.DELETED);
    }

    public static Result notFoundError() {
        return new Result(false, "404", Message.NOT_FOUND);
    }

    public static Result animalsDontMatch() {
        return new Result(false, "404", Message.ANIMALS_DONT_MATCH);
    }
    public static Result alreadyExistError() {
        return new Result(false, "400", Message.ALREADY_EXIST);
    }

    public static Result localDateError() {
        return new Result(false, "400", Message.DATE_ERROR);
    }

    public static Result vaccineError() {
        return new Result(false, "400", Message.VACCINE_ERROR);
    }

    public static Result doctorError() {
        return new Result(false, "400", Message.DOCTOR_ERROR);
    }

    public static Result appointmentTimeError() {
        return new Result(false, "400", Message.APP0_TIME_ERROR);
    }

    public static Result appointmentExistsError() {
        return new Result(false, "400", Message.APP0_EXIST_ERROR);
    }

    public static Result appointmentNotAvailableError() {
        return new Result(false, "400", Message.APP0_NOT_AVA_ERROR);
    }

    public static <T> ResultData<T> success(T data) {
        return new ResultData<>(true, "200", Message.OK, data);
    }

    public static <T> ResultData<CursorResponse<T>> cursor(Page<T> pageData) {

        CursorResponse<T> cursorResponse = new CursorResponse<>();
        cursorResponse.setItems(pageData.getContent());
        cursorResponse.setPageNumber(pageData.getNumber());
        cursorResponse.setPageSize(pageData.getSize());
        cursorResponse.setTotalElements(pageData.getTotalElements());

        return success(cursorResponse);
    }

    public static Result reportExistsError() {
        return new Result(false, "400", Message.REPORT_EXIST);
    }

    public static Result doctorIdNullError() {
        return new Result(false, "400", Message.DOCTOR_ID_NULL);
    }

    public static Result reportIdNullError() {
        return new Result(false, "400", Message.REPORT_ID_NULL);
    }

    public static Result animalIdNullError() {
        return new Result(false, "400", Message.ANIMAL_ID_NULL);
    }

    public static Result animalSelectIdNullError() {
        return new Result(false, "400", Message.ANIMAL_SELECT_ID_NULL);
    }

    public static Result doctorSelectIdNullError() {
        return new Result(false, "400", Message.DOCTOR_SELECT_ID_NULL);
    }

    public static Result dateSelectIdNullError() {
        return new Result(false, "400", Message.DATE_SELECT_ID_NULL);
    }


}
