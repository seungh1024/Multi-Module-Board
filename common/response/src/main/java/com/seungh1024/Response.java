package com.seungh1024;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.seungh1024.ResponseType.SUCCESS;
import static com.seungh1024.ResponseType.FAILURE;
@Getter //이거 왜 필요하지?
@NoArgsConstructor
public class Response<T> {
    private final boolean success = true;
    private int code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private T data;


    @Builder
    public Response(ResponseType responseType, T data){
        this.code = responseType.getCode();
        this.message = responseType.getMessage();
        this.data = data;
    }


    public static Response success(){
        return Response.builder()
                .responseType(SUCCESS)
                .build();
    }
    public static  <T> Response<T> success(T data){
        return new Response<>(SUCCESS,data);
    }

    public static Response failure() {
        return Response.builder()
                .responseType(FAILURE)
                .build();
    }


}
