package com.emrekentli.adoptme.library.exception;

import com.emrekentli.adoptme.library.enums.MessageCodes;
import lombok.Getter;

@Getter
public class CoreException extends RuntimeException{

    private final MessageCodes code;
    private final Object[] args;

    public CoreException(MessageCodes code,Object... args) {
        this.code = code;
        this.args = args;
    }
}
