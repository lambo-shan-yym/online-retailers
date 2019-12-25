package com.lambo.onlineretailers.validator;

import com.lambo.onlineretailers.util.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName: IsMobileValidator
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/25 19:53
 * @Version: 1.0
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile,String>{


    private boolean required = false;
    public void initialize(IsMobile constraintAnnotation) {
        required =constraintAnnotation.required();
    }

    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(!required){
            return true;
        }
        if(StringUtils.isBlank(value)){
            return false;
        }
        return ValidatorUtil.isMobile(value);
    }
}
