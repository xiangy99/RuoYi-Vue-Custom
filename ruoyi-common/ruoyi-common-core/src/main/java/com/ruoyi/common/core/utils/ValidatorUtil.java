package com.ruoyi.common.core.utils;

import com.ruoyi.common.core.exception.ValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * $ValidatorUtil
 *
 * @author Link
 */
public class ValidatorUtil {
    private final static Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @SafeVarargs
    public static <T> void validate(T... param) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        // stackTraceElements[1] 是当前方法，stackTraceElements[2] 是调用当前方法的方法
        StackTraceElement caller = stackTraceElements[2];
        String callerMethodName = caller.getMethodName();
        for (T data : param) {
            if (data == null) {
                throw new ValidationException(callerMethodName + "传入参数为空");
            }

            Set<ConstraintViolation<T>> validResult = validator.validate(data);
            if (!validResult.isEmpty()) {
                Map<Path, String> validItem = new HashMap<>();
                validResult.forEach(t -> {
                    if (StringUtils.isNotBlank(t.getMessageTemplate()) && t.getMessageTemplate().startsWith("{") && t.getMessageTemplate().endsWith("}")) {
                        String errorMessage = t.getPropertyPath() + t.getMessage();
                        validItem.put(t.getPropertyPath(), errorMessage);
                    } else {
                        String errorMessage = t.getMessageTemplate();
                        validItem.put(t.getPropertyPath(), errorMessage);
                    }
                });

                throw new ValidationException(callerMethodName + ":" + String.join("、", validItem.values()));
            }
        }


    }
}
