package com.green.dto.userinfo.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import static com.green.constants.LabelKey.LABEL_FILE;
import static com.green.constants.LabelKey.LABEL_USER_ID;
@Data
public class UserAvataUpdateSdi {
    @Validation(label = LABEL_USER_ID, required = true)
    private Long userId;

    @Validation(label = LABEL_FILE, required = true)
    private MultipartFile avata;
}
