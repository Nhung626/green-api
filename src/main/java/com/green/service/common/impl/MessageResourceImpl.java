package com.green.service.common.impl;

import com.green.service.common.CommonService;
import com.green.service.common.MessageResource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageResourceImpl implements MessageResource {
    private final MessageSource messageSource;
    private final CommonService commonService;

    public String getMessage(String var1) throws NoSuchMessageException {
        return messageSource.getMessage(var1, null,  new Locale("en"));
    }

    public String getMessage(String var1, @Nullable Object[] var2) throws NoSuchMessageException {
        if (var2 != null) {
            var2 = Arrays.stream(var2)
                    .map(item -> messageSource.getMessage(item.toString(), null,  new Locale("en")))
                    .toArray();
        }

        return messageSource.getMessage(var1, var2,  new Locale("en"));
    }

    public String getMessage(MessageSourceResolvable var1) throws NoSuchMessageException {
        return messageSource.getMessage(var1, new Locale("vi"));
    }
}
