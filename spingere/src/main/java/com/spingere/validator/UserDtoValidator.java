package com.spingere.validator;

import com.spingere.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author G13380
 */
@Service
public class UserDtoValidator implements Validator {

    private @Autowired Validator springValidatorAdapter;
    
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        // anotaciones
        springValidatorAdapter.validate(o, errors);
        // personalizadas
    }

}
