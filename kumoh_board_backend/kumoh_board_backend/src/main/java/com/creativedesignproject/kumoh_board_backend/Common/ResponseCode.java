package com.creativedesignproject.kumoh_board_backend.Common;

public interface ResponseCode {
    //Http 200
    String SUCCESS = "SU";

    //Http 400
    String VALIDATION_FAIL = "VF";
    String DUPLICATE_NICKNAME = "DN";
    String DUPLICATE_EMAIL = "DE";
    String DUPLICATE_ID = "DI";
    String DUPLICATE_PASSWORD = "DP";
    String DUPLICATE_CATEGORY_NAME = "DCN";
    String NOT_EXISTED_BOARD = "NEB";
    String NOT_EXISTED_USER = "NEU";
    String NOT_EXISTED_CATEGORY = "NEC";

    //Http 401
    String SIGN_IN_FAIL = "SF";
    String CERTIFICATION_FAIL = "CF";
    String NO_PERMISSION = "NP";

    //Http 500
    String MAIL_FAIL = "MF";
    String DATABASE_ERROR = "DBE";
}
