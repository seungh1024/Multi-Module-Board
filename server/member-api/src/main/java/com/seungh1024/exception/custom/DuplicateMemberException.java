package com.seungh1024.exception.custom;

import com.seungh1024.exception.ErrorCode;
import lombok.Getter;
import org.springframework.dao.DuplicateKeyException;

@Getter
public class DuplicateMemberException extends DuplicateKeyException {
    public DuplicateMemberException(String msg) {
        super(msg);
    }
}
