package com.example.demo.common;

public class ReturnObject {
    public static enum RespStatus {
        ERROR("error"), INFO("info"), SUCCESS("success"), WARNING("warning");

        private String value;

        private RespStatus(String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }
    };

    public static final String ERROR = "error";

    public static final String INFO = "info";

    public static final String SUCCESS = "success";

    public static final String WARNING = "warning";

    private String status;

    private String message;

    private Object retObj;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getRetObj() {
        return retObj;
    }

    public void setRetObj(Object retObj) {
        this.retObj = retObj;
    }

    public void setMessage(String status, String message) {
        this.status = String.valueOf(status);
        this.message = String.valueOf(message);
    }

    public void setMessageSuccess(String message) {
        this.status = SUCCESS;
        this.message = String.valueOf(message);
    }

    public void setMessageError(String message) {
        this.status = ERROR;
        this.message = String.valueOf(message);
    }

    public void setMessage(RespStatus respStatus, String message) {
        this.status = String.valueOf(respStatus.toString());
        this.message = String.valueOf(message);
    }
}
