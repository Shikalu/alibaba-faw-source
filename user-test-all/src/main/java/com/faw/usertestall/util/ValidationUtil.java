package com.faw.usertestall.util;

import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author 鹿胜宝
 * @date 2023/03/22
 */
public class ValidationUtil {
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> void validate(T object, Class... groups) {
        Set<ConstraintViolation<T>> validate = validator.validate(object, groups);

        //如果校验结果不为空
        if (!CollectionUtils.isEmpty(validate)) {
            StringBuilder exceptionMessage = new StringBuilder();
            validate.forEach(constraintViolation -> {
                exceptionMessage.append(constraintViolation.getMessage());
            });

            throw new RuntimeException(exceptionMessage.toString());
        }
    }
}
