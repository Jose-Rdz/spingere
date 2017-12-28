package com.spingere.utils;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author G13380
 */
public class JSONResponse implements Serializable{
    
    private static final long serialVersionUID = 3695752308211361092L;

    private boolean isOk;
    private String message;
    private Object info;
    private Map<String, String> errors;

    public boolean isIsOk() {
        return isOk;
    }

    public void setIsOk(boolean isOk) {
        this.isOk = isOk;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "JSONResponse{" + "isOk=" + isOk + ", message=" + message + ", info=" + info + ", errors=" + errors + '}';
    }

}
