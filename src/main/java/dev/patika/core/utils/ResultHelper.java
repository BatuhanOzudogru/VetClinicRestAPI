package dev.patika.core.utils;

import dev.patika.core.result.Result;
import dev.patika.core.result.ResultData;
import dev.patika.dto.response.CursorResponse;
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


    public static Result alreadyExistError() {
        return new Result(false, "400", Message.ALREADY_EXIST);
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


}
