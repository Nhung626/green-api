package com.green.constants;

public class Const {

    public interface AppCode {
        String SUCCESS_CODE = "API000";
        String SYSTEM_ERROR = "API001";
        String NOT_FOUND = "API002";
        String CONFLICT = "API003";
        String UNAUTHORIZED = "API004";
        String BAD_REQUEST = "API005";
        String JWT_EXPIRED = "API201";
        String SYSTEM_UNAVAILABLE = "API006";
    }

    public interface GeneralStatus {
        int INACTIVE = 0;
        int ACTIVE = 1;
        int DELETED = 2;
    }

    public interface LOCALE {
        String VI = "vi";
        String EN = "en";
    }

    public interface ResponseErrorCode {
        String UNKNOWN_HOST = "UNKNOWN_HOST";
        String TIMEOUT = "TIMEOUT";
    }

    public interface HeaderKey {
        String HMAC_SHA_256 = "X-Haravan-Hmacsha256";
        String AUTHORIZATION = "Authorization";
        String SOCKET_ID = "simpSessionId";
        public static final String X_TENANT_ID = "X-Tenant-Id";
        public static final String X_ORG_UNIT_ID = "X-Org-Unit-Id";
        public static final String X_IS_DISTRIBUTED_TRANSACTION = "X-Is-Distributed-Transaction";
        public static final String X_DISTRIBUTED_TRANSACTION_ID = "X-Distributed-Transaction-Id";
    }

    public interface ApplicationProfile {
        String LOCAL = "local";
        String DEVELOPMENT = "dev";
        String STAGING = "stg";
        String PRODUCTION = "prod";
    }

    public interface KEYCLOAK_TOKEN_CLAIM_KEY {
        String OTP = "otp";
        String OTP_EXPIRE = "otp_expire";
    }

    public interface KeycloakUserAttribute {
        String TWO_FA = "twoFA";
        String CUSTOMER_ID = "customerId";
        String COUNT_LOGIN_NON_TWO_FA = "countLoginNonTwoFa";
        String CREATED_BY = "createdBy";
        String CREATED_TIME = "createdTime";
        String UPDATED_BY = "updatedBy";
        String UPDATED_TIME = "updatedTime";
        String IS_SSO_ACCOUNT = "isSsoAccount";
        String SSO_USERNAME = "ssoUsername";
        String MOBILE = "mobile";
        String EMAIL_NOTIFICATION = "email_notification";
        String OTP = "otp";
        String OTP_EXPIRE = "otp_expire";
        String STAFF_ID = "staffId";
        String SECRET_KEY = "secretKey";
        String AVATAR = "avatar";
    }

    public interface GlobalListCode {
        String GL_RESOURCE_TYPE = "GL_RESOURCE_TYPE";
        String GL_UNIT = "GL_UNIT";
        String GL_STATUS_CONNECT = "GL_STATUS_CONNECT";
        String GL_WATER_TYPE = "GL_WATER_TYPE";
        String GL_CHEMICAL_TYPE = "GL_CHEMICAL_TYPE";
        String GL_WTP_CHEMICAL_NORM_MEASURE_CYCLE = "WTP_CHEMICAL_NORM.MEASURE_CYCLE";
        String GL_WTP_CHEMICAL_NORM_NORM_CYCLE = "WTP_CHEMICAL_NORM.NORM_CYCLE";
    }

    public interface Extension {
        String SQL = "sql";
        String RPTDESIGN = "rptdesign";
    }

    public interface CacheKey {
        String USER_PERMISSION = "user_permission_%s";
        String USER_TOKEN = "user_token_%s";
    }

    public interface ClientId {
        String WP_SYS = "WP_SYS";
    }

    public interface TemplateCode {
        String EMAIL_VERIFY_CODE = "EMAIL_VERIFY_CODE";
        String EMAIL_CREATE_ACCOUNT = "EMAIL_CREATE_ACCOUNT";
        String EMAIL_RESET_PASSWORD = "EMAIL_RESET_PASSWORD";
    }

    public interface SocketTopic {
        String AUTHORIZATION_VERIFY_TOKEN_FAIL = "/topic/authorization/verify-token-fail";
        String ASM_ASSET_DATA_SYNC_SUCCESS = "/topic/asm-access-data/sync-success";
    }

    public interface GlResourceType {
        String WEB = "web";
        String API = "api";
    }

    public interface GlApproveStatus {
        Integer NOT_YET_APPROVE = 0;
        Integer APPROVED = 1;
    }

    public interface Weeks {
        int FIRST_WEEK = 1;
        int SECOND_WEEK = 2;
        int THIRD_WEEK = 3;
        int FOURTH_WEEK = 4;
    }
    public interface Result {
        int TRUE = 1;
        int FALSE = 0;
    }
}
