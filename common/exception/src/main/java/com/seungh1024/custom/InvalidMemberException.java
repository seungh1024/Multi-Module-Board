package com.seungh1024.custom;

import lombok.Getter;

/*
 * custom invalid member exception
 *
 * @Author 강승훈
 * @Since 2023.07.14
 *
 * */

@Getter
public class InvalidMemberException extends RuntimeException {
    public InvalidMemberException(String msg) {
        super(msg);
    }
}
