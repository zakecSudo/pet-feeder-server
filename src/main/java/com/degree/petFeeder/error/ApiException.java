package com.degree.petFeeder.error;

public class ApiException extends RuntimeException {

    public static String RESOURCE_NOT_FOUND = "resource_not_found";
    public static String UNKNOWN_ENUM_VALUE = "unknown_enum_value";
    public static String INVALID_INPUT_FORMAT = "invalid_input_format";


    private Error error;

    public ApiException(String code, String message) {
        super(message);
        this.error = new Error(code, message);
    }

    public Error getApiError() {
        return error;
    }

    public void setApiError(Error error) {
        this.error = error;
    }

    public static class Error {

        private String code;

        private String message;

        public Error(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }
}
