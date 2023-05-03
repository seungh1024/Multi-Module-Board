package com.seungh1024;

public enum ResponseType{
    SUCCESS(200),
    FAILURE(400);

    private final int code;

    ResponseType(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.name(); // name() -> enum의 상수를 String 문자열로 반환 SUCCESS가 문자열로 반환되는 것이다
    }

}