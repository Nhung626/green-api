//package com.green.exception;
//
//import com.green.constants.Const;
//import com.green.dto.email.sdi.EmailSendSdi;
//import com.green.service.EmailService;
//import com.green.service.common.CommonService;
//import com.green.service.common.MessageResource;
//import io.jsonwebtoken.JwtException;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.BindException;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.multipart.MaxUploadSizeExceededException;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Optional;
//
//import static com.green.utils.DataUtil.*;
//
//@Slf4j
//@RestControllerAdvice
//@RequiredArgsConstructor
//public class ApiExceptionHandler {
//    @Value("${spring.profiles.active}")
//    private String profileActive;
//
//    private final MessageResource messageResource;
//    private final EmailService emailService;
//    private final CommonService commonService;
//
//    /**
//     * Tất cả các Exception không được khai báo sẽ được xử lý tại đây
//     */
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    public ErrorMessage handleAllException(Exception ex, HttpServletRequest request) {
//        log.error(ex.getMessage(), ex);
//
//        var userName = safeToString(commonService.getUsernameLogin(), "process");
//        emailService.sendSimpleMailMessage(
//                EmailSendSdi.builder()
//                        .emailFrom("Exception")
//                        .emailTo(userName)
//                        .subject("With url: " + request.getRequestURI())
//                        .content("" +
//                                "Uri: " + request.getRequestURI() +
//                                "\n " +
//                                "Method: " + request.getMethod() +
//                                "\n " +
//                                "RemoteAddr: " + request.getRemoteAddr() +
//                                "\n " +
//                                "--------------------" +
//                                "\n " + ex.getMessage()).build()
//        );
//
//        if (safeEqual(profileActive, Const.ApplicationProfile.PRODUCTION)) {
//            return new ErrorMessage("ERR-9999", "Đã có lỗi sảy ra", null);
//        }
//        return new ErrorMessage("ERR-9999", ex.getLocalizedMessage(), null);
//    }
//
//    @ExceptionHandler(MaxUploadSizeExceededException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ErrorMessage maxSizeUploadException(MaxUploadSizeExceededException ex) {
//        return new ErrorMessage("API-UP001", "Không được phép tải lên file có dung lượng quá 20MB", null);
//    }
//
//    @ExceptionHandler(JwtException.class)
//    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
//    public ErrorMessage handleJWTException(JwtException ex) {
//        return new ErrorMessage(ex.getMessage(), "Phiên làm việc đã hết hạn", null);
//    }
//
//    @ExceptionHandler(BindException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ErrorMessage handleBindException(BindException ex) {
//        String message = ex.getMessage();
//
//        Optional<ObjectError> optional = ex.getBindingResult().getAllErrors().stream().findFirst();
//        if (optional.isPresent()) {
//            String exceptionJson = optional.get().getDefaultMessage();
//            AppException appException = jsonToObject(exceptionJson, AppException.class);
//            return this.todoAppException(appException);
//        }
//
//        return new ErrorMessage(message, message, null);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ErrorMessage handleJWTException(MethodArgumentNotValidException ex) {
//        String message = ex.getMessage();
//
//        Optional<ObjectError> optional = ex.getBindingResult().getAllErrors().stream().findFirst();
//        if (optional.isPresent()) {
//            String exceptionJson = optional.get().getDefaultMessage();
//            AppException appException = jsonToObject(exceptionJson, AppException.class);
//            return this.todoAppException(appException);
//        }
//
//        return new ErrorMessage(message, message, null);
//    }
//
//    @ExceptionHandler(AppException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ErrorMessage todoAppException(AppException ex) {
//        if (ex.getHandleException()) {
//            String errorCode = safeToString(ex.getCode());
//            int errorSize = 0;
//            if (ex.getErrorField() != null && !ex.getErrorField().isEmpty()) {
//                errorSize = ex.getErrorField().size();
//            }
//            String[] arrayError = null;
//            if (errorSize > 0) {
//                arrayError = new String[errorSize];
//                int i = 0;
//                for (String field : ex.getErrorField()) {
//                    arrayError[i] = field;
//                    i++;
//                }
//            }
//            String message;
//            if (errorSize > 0) {
//                message = messageResource.getMessage(errorCode, arrayError);
//            } else {
//                message = messageResource.getMessage(errorCode);
//            }
//
//            if (isNullOrEmpty(message)) {
//                message = safeToString(ex.getMessage());
//            }
//
//            ex.setMessage(message);
//            log.error(ex.getMessage(), ex);
//
//            return new ErrorMessage(errorCode, message, ex.getErrorField());
//        } else {
//            return new ErrorMessage(ex.getCode(), ex.getMessage(), ex.getErrorField());
//        }
//    }
//}
