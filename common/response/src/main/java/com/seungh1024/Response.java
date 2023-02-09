package com.seungh1024;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter //이거 왜 필요하지?
@NoArgsConstructor
public class Response<T> {
    private int code;
    private String message;
    private T data;

    @Builder
    public Response(int code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public static  <T> Response<T> success(T data){
        return new Response(200,"성공",data);
    }

    public enum ResponseType{
        SUCCESS,
        Failure;

        public String getCode(){
            return this.name(); // name() -> enum의 상수를 String 문자열로 반환
        }

    }
}
