package com.spingere.validator;

import com.spingere.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Jose-Rdz
 */
@Service
public class UsuarioDtoValidator implements Validator {

    private @Autowired Validator springValidatorAdapter;
    
    @Override
    public boolean supports(Class<?> clazz) {
        return UsuarioDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        // anotaciones
        springValidatorAdapter.validate(o, errors);
        // personalizadas
    }

}
