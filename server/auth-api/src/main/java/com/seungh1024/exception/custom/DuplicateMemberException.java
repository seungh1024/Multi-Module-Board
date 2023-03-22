package com.seungh1024.exception.custom;

import lombok.Getter;
import org.springframework.dao.DuplicateKeyException;

/*
 * custom duplicate member exception
 *
 * @Author 강승훈
 * @Since 2023.03.20
 *
 * */

@Getter
public class DuplicateMemberException extends DuplicateKeyException {
    public DuplicateMemberException(String msg) {
        super(msg);
    }
}
