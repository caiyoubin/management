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

    protected HttpCodeResponse responseOK(Object data) {
        HttpCodeResponse response = new HttpCodeResponse();
        response.setCode(StateCode.SUCCESS);
        response.setMessage(MessageCode.SUCCESS);
        response.setData(data);
        return response;
    }

    protected HttpCodeResponse responseError(String message) {
        HttpCodeResponse response = new HttpCodeResponse();
        response.setCode(StateCode.FAIL);
        response.setMessage(message);
        return response;
    }
}
