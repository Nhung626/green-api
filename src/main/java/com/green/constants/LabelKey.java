package com.green.constants;

public interface LabelKey {
    //BEGIN COMMON
    String ERROR_REQUIRED = "error.required";
    String ERROR_MIN_LENGTH = "error.min-length";
    String ERROR_MAX_LENGTH = "error.max-length";
    String ERROR_FORMAT = "error.format";
    String ERROR_ALREADY_EXIST = "error.already-exist";
    String ERROR_ALREADY_EXIST_WITH_DETAIL = "error.already-exist-with-detail";

    String ERROR_SCHOOL_IS_LOCKED = "error.school-is-locked";
    String ERROR_ALREADY_DELETED = "error.already-deleted";

    String ERROR_MISSING_REQUIRED_FIELD = "error.missing.required.field";
    String ERROR_INTERNAL_SERVER_ERROR = "error.internal.server.error";
    String ERROR_MIN = "error.min";
    String ERROR_MAX = "error.max";
    String ERROR_NOT_EXIST = "error.not-exist";
    String ERROR_NOT_EXIST_WITH_DETAIL = "error.not-exist-with-detail";
    String ERROR_DELETE_USED = "error.delete-used";
    String ERROR_ILLEGAL = "error.illegal";
    String ERROR_OTP_INVALID = "error.otp-invalid";
    String ERROR_GREATER_THAN = "error.greater-than";
    String ERROR_FILE_UPLOAD_MAX_SIZE = "error.file-upload-max-size";
    String ERROR_FAILED_TO_UPLOAD_FILE = "error.failed-to-upload-file";
    String ERROR_GET_TOTAL_ITEM_FAILED = "error.get-total-item-failed";
    String ERROR_CANNOT_SAME = "error.cannot-same";
    String ERROR_NOT_FOUND_DATA = "error.not-found-data";
    String ERROR_FORMAT_PASSWORD = "error.format-password";
    String ERROR_NOT_MATCH = "error.not-match";
    String ERROR_TIMEOUT = "error.timeout";
    String ERROR_UNKNOWN_HOST = "error.unknown-host";
    String ERROR_CREATE_FAILED = "error.create-failed";
    String ERROR_UPDATE_FAILED = "error.update-failed";
    String ERROR_DELETE_FAILED = "error.delete-failed";
    String ERROR_NOT_ALLOWED_TO_DELETE = "error.not-allowed-to-delete";
    String ERROR_FILE_OR_URL_REQUIRED = "error.file-or-url-required";

    String LABEL_STATUS = "label.status";
    String LABEL_CREATED_AT = "label.created-at";
    String LABEL_UPDATED_AT = "label.updated-at";
    String LABEL_CREATED_BY = "label.created-by";
    String LABEL_UPDATED_BY = "label.updated-by";
    String LABEL_PHONE = "label.phone";
    String LABEL_PHONE_RECEIVER = "label.phone-receiver";
    String LABEL_EMAIL = "label.email";
    String LABEL_PROVINCE_ID = "label.province-id";
    String LABEL_DISTRICT_ID = "label.district-id";
    String LABEL_COMMUNE_ID = "label.commune-id";

    String LABEL_PROVINCE_CODE = "label.province-code";
    String LABEL_DISTRICT_CODE = "label.district-code";
    String LABEL_COMMUNE_CODE = "label.commune-code";
    String LABEL_PROVINCE_NAME = "label.province-name";
    String LABEL_DISTRICT_NAME = "label.district-name";
    String LABEL_COMMUNE_NAME = "label.commune-name";
    String LABEL_ADDRESS = "label.address";
    String LABEL_ADDRESS_DETAIL = "label.address-detail";
    String LABEL_FILE = "label.file";
    String LABEL_FILE_EXTENSION = "label.file-extension";
    String LABEL_FILE_ID = "label.file.id";
    String LABEL_FILE_NAME = "label.file-name";
    String LABEL_DATA_BASE64 = "label.data-base64";
    String LABEL_FIRST_NAME = "label.first-name";
    String LABEL_LAST_NAME = "label.last-name";
    String LABEL_DATE_OF_BIRTH = "label.date-of-birth";
    String LABEL_OLD_PASSWORD = "label.old-password";
    String LABEL_PASSWORD = "label.password";
    String LABEL_NOW = "label.now";
    String LABEL_OTP = "label.otp";
    String LABEL_IS_DEFAULT = "label.is-default";
    String LABEL_BEGIN_DATE = "label.begin-date";
    String LABEL_END_DATE = "label.end-date";
    String LABEL_REASON = "label.reason";
    String LABEL_WEIGHT = "label.weight";
    String LABEL_NOTE = "label.note";
    String LABEL_RECEIVER_NAME = "label.receiver-name";
    String LABEL_RECEIVER_PHONE = "label.receiver-phone";
    String LABEL_RECEIVER_EMAIL = "label.receiver-email";
    String LABEL_RATING_STAR = "label.rating-star";
    String LABEL_QUANTITY = "label.quantity";
    String LABEL_EXTENSION = "label.extension";
    String LABEL_AREA = "label.area";
    String LABEL_AVATAR = "label.avatar";
    String LABEL_BANNER = "label.banner";
    String LABEL_CURRENT_PASSWORD = "label.current-password";
    String LABEL_NEW_PASSWORD = "label.new-password";
    String LABEL_CONFIRM_PASSWORD = "label.confirm-password";
    String LABEL_FROM_DATE = "label.from-date";
    String LABEL_TO_DATE = "label.to-date";
    String LABEL_WEBSITE = "label.website";
    String LABEL_DESCRIPTION = "label.description";
    String LABEL_USERNAME = "label.username";
    String LABEL_TOKEN = "label.token";
    String LABEL_REFRESH_TOKEN = "label.refresh-token";
    String LABEL_KEY = "label.key";
    String LABEL_IS_SECURE = "label.is-secure";
    String LABEL_YEAR = "label.year";
    String LABEL_MONTH = "label.month";
    String LABEL_DAY = "label.day";
    String LABEL_SEARCH_CONTENT = "label.search.content";
    //END COMMON

    //BEGIN SEND MAIL
    String ERROR_SEND_MAIL_FAILED = "error.send-mail.failed";
    //END SEND MAIL

    //BEGIN AUTHENTICATION
    String ERROR_AUTH_TOKEN_EXPIRED = "error.auth.token-expired";
    String ERROR_AUTH_LOGIN_FAILED = "error.auth.login-failed";
    String ERROR_AUTH_LOGOUT_FAILED = "error.auth.logout-failed";
    String ERROR_AUTH_TOTP_INCORRECT = "error.auth.totp-incorrect";
    String ERROR_AUTH_TOTP_SECRET_INCORRECT = "error.auth.totp-secret-incorrect";
    String ERROR_AUTH_REFRESH_TOKEN_FAILED = "error.auth.refresh-token-failed";
    String ERROR_AUTH_USER_NOT_ACTIVE = "error.auth.user-not-active";
    String ERROR_AUTH_USERNAME_OR_PASSWORD_INCORRECT = "error.auth.username-or-password-incorrect";
    String ERROR_AUTH_PASSWORD_INCORRECT = "error.auth.password-incorrect";
    String ERROR_AUTH_GET_USER_PERMISSIONS_FAILED = "error.auth.get-user-permissions-failed";
    String ERROR_AUTH_YOU_ARE_NOT_ACCESS_TO_THIS_CLIENT = "error.auth.you-are-not-access-to-this-client";
    String LABEL_AUTH_DATA_ACTIVE_ACCOUNT = "label.auth.data-active-account";
    //END AUTHENTICATION

    //BEGIN USER
    String LABEL_USER = "label.user";
    String LABEL_USER_ID = "label.user.id";
    String LABEL_USER_NAME = "label.user.name";
    String LABEL_USER_PHONE = "label.user.phone";
    String LABEL_USER_EMAIL = "label.user.email";
    String LABEL_USER_PASSWORD = "label.user.password";
    String LABEL_USER_ROLE = "label.user.role";
    //END USER

    //BEGIN USER INFO
    String LABEL_USER_INFO = "label.user-info";
    String LABEL_USER_INFO_ID = "label.user-info.id";
    String LABEL_USER_INFO_USER_ID = "label.user-info.user-id";

    String LABEL_USER_INFO_AVATA = "label.user-info.avata";
    String LABEL_USER_INFO_GENDER = "label.user-info.gender";
    String LABEL_USER_INFO_IS_GENDER_P = "label.user-info.is-gender-p";
    String LABEL_USER_INFO_DATE_OF_BIRTH = "label.user-info.date-of-birth";
    String LABEL_USER_INFO_IS_DATE_OF_BIRTH_P = "label.user-info.is-date-of-birth-p";
    String LABEL_USER_INFO_PHONE = "label.user-info.phone";
    String LABEL_USER_INFO_IS_PHONE_P = "label.user-info.is-phone-p";
    String LABEL_USER_INFO_ADDRESS = "label.user-info.address";
    String LABEL_USER_INFO_IS_ADDRESS_P = "label.user-info.is-address-p";
    String LABEL_USER_INFO_INTRODUCE = "label.user-info.introduce";
    String LABEL_USER_INFO_IS_INTRODUCE_P = "label.user-info.is-introduce-p";
    //END USER INFO

    //BEGIN GARDEN INFO
    String LABEL_GARDEN_INFO = "label.garden-info";
    String LABEL_GARDEN_INFO_ID = "label.garden-info.id";
    String LABEL_GARDEN_INFO_NAME = "label.garden-info";
    String LABEL_GARDEN_INFO_COVER = "label.garden-info.cover";
    String LABEL_GARDEN_INFO_COVER_ID = "label.garden-info.cover-id";
    String LABEL_GARDEN_INFO_DESCRIPTION = "label.garden-info.description";
    //END GARDEN INFO

    //BEGIN LAND
    String LABEL_LAND = "label.land";
    String LABEL_LAND_ID = "label.land.id";
    String LABEL_LAND_ADDRESS = "label.land.address";
    String LABEL_LAND_WIDTH = "label.land.width";
    String LABEL_LAND_LENGTH = "label.land.length";
    String LABEL_LAND_NAME = "label.land.name";
    String LABEL_LAND_IMG = "label.land.img";
    //END LAND

    //BEGIN TREE
    String LABEL_TREE = "label.tree";
    String LABEL_TREE_ID = "label.tree.id";
    String LABEL_TREE_START_DATE = "label.tree.start-date";
    String LABEL_TREE_END_DATE = "label.tree.end-date";
    String LABEL_TREE_NAME = "label.tree.name";
    String LABEL_TREE_TYPE = "label.tree.type";
    String LABEL_TREE_IMG = "label.tree.img";
    //END TREE

    //BEGIN DIARY
    String LABEL_DIARY = "label.diary";
    String LABEL_DIARY_ID = "label.diary";
    String LABEL_DIARY_IMG = "label.diary.img";
    String LABEL_DIARY_DESCRIPTION = "label.diary.description";
    //END DIARY

    //BEGIN STATUS
    String LABEL_STATUS_ID = "label.status.id";
    String LABEL_STATUS_IMG = "label.status.img";
    String LABEL_STATUS_CONTENT = "label.status.content";
    String LABEL_STATUS_PUBLISH_TIME = "label.status.publish-time";
    String LABEL_STATUS_LIKE = "label.status.like";
    String LABEL_STATUS_UNLIKE = "label.status.unlike";
    //END STATUS

    //BEGIN COMMENT
    String LABEL_COMMENT = "label.comment";
    String LABEL_COMMENT_ID = "label.comment.id";
    String LABEL_COMMENTS_POST_ID = "label.comment.post.id";
    String LABEL_COMMENT_PARENT_ID = "label.comment.parent.id";
    String LABEL_COMMENT_CONTENT = "label.comment.content";
    String LABEL_COMMENT_LIKE = "label.comment.like";
    String LABEL_COMMENT_UNLIKE = "label.comment.unlike";
    //END COMMENT

    //BEGIN POST
    String LABEL_POST = "label.post";
    String LABEL_POST_ID = "label.post.id";
    String LABEL_POST_AUTH = "label.post.auth";
    String LABEL_POST_TITlE = "label.post.title";
    String LABEL_POST_TYPE_TREE = "label.post.type-tree";
    String LABEL_POST_GENERAL = "label.post.general";
    String LABEL_POST_CONTENT = "label.post.content";
    String LABEL_POST_DESCRIPTION = "label.post.description";
    String LABEL_POST_TAKE_CARE = "label.post.take-care";
    String LABEL_POST_LIKE = "label.post.like";
    String LABEL_POST_UNLIKE = "label.post.unlike";
    String LABEL_POST_SAVE = "label.post.save";
    String LABEL_POST_UNSAVE = "label.post.un-save";
    //END POST
}
