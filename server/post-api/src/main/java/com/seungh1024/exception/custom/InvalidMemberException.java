package com.seungh1024.exception.custom;

import lombok.Getter;
import org.springframework.dao.DuplicateKeyException;

/*
 * custom invalid member exception
 *
 * @Author 강승훈
 * @Since 2023.07.12
 *
 * */

@Getter
public class InvalidMemberException extends RuntimeException {
    public InvalidMemberException(String msg) {
        super(msg);
    }
}
