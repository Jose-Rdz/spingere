package com.spingere.utils;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

/**
 *
 * @author Jose-Rdz
 */
@Service
public class SpingereUtils {
    
    private static final Logger logger = LoggerFactory.getLogger(SpingereUtils.class);
    
    private @Autowired MessageSource messageSource;
    
    /**
     * <p>Aplica un validador de Spring (previamente configurado) sobre el objeto que se indique
     * y obtiene un mapa que describe los campos que tuvieron algún errror.</p>     
     * <p>Los mensajes de error se configuran en el archivo <i>messages.properties</i></p>
     * 
     * @param <T> {@code generic type}
     * @param toValidate objeto a validar
     * @param toValidateType clase del objeto a validar
     * @param validator validador que se aplicará
     * @return mapa de campos con errores
     * @throws SpingereException 
     */
    public <T> Map<String, String> invokeValidator(Object toValidate, Class<T> toValidateType, Validator validator) throws SpingereException {
        if (!toValidateType.isInstance(toValidate)) {
            throw new SpingereException("El objeto a validar no corresponde con el tipo de clase proporcionada");
        }
        
        T t = toValidateType.cast(toValidate);
        
        BindingResult br = new BeanPropertyBindingResult(t, toValidateType.getName());
        validator.validate(t, br);
        
        Map<String, String> errorsMap = new HashMap<>();
        
        if (br.hasErrors()) {
            logger.info("{{existen errores de validación}}", errorsMap);
            br.getFieldErrors().forEach((f) -> {
                String code = f.getCode();
                String fieldName = f.getField();
                String message = messageSource.getMessage(f, null);
                logger.info("Code: " + code + ", Field: " + fieldName + ", Msg: " + message);
                errorsMap.put(fieldName, message);
            });
        }
        
        return errorsMap;
    }
    
}
