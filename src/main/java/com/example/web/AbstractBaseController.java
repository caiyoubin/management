package com.example.web;

import com.example.common.code.MessageCode;
import com.example.common.code.StateCode;

public abstract class AbstractBaseController {

    protected HttpCodeResponse responseOK() {
        HttpCodeResponse response = new HttpCodeResponse();
        response.setCode(StateCode.SUCCESS);
        response.setMessage(MessageCode.SUCCESS);
        return response;
    }
}
