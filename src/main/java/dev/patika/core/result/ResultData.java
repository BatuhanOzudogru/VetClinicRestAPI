package dev.patika.core.result;

import lombok.Getter;

@Getter
public class ResultData<T> extends Result{
    private T data;
    public ResultData(boolean status, String code, String message, T data) {
        super(status, code, message);
        this.data = data;
    }
}
