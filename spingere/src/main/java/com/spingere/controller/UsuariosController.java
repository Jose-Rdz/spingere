package com.spingere.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Map;
import com.spingere.dto.UsuarioDto;
import com.spingere.utils.JSONResponse;
import com.spingere.utils.SpingereException;
import com.spingere.utils.SpingereUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.spingere.service.UsuarioService;

/**
 *
 * @author Jose-Rdz
 */
@Controller
@RequestMapping(value = "/users")
public class UsuariosController {

    private static final Logger logger = LoggerFactory.getLogger(UsuariosController.class);

    private @Autowired SpingereUtils spingereUtils;
    private @Autowired UsuarioService userService;
    private @Autowired Validator userDtoValidator;
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView mv = new ModelAndView("users");
        LocalDate hoy = LocalDate.now();
        mv.addObject("fecha", hoy.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(new Locale("es", "MX"))));
        return mv;
    }
    
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JSONResponse saveUser(@RequestBody UsuarioDto dto) {
        logger.info("-----> Guardando usuario...");
        JSONResponse jr = new JSONResponse();
        try {
            /*
            BindingResult br = new BeanPropertyBindingResult(dto, UsuarioDto.class.getName());
            userDtoValidator.validate(dto, br);
            if (br.hasErrors()) {
            logger.info("{{existen errores de validación}}", br.getAllErrors());
            jr.setIsOk(false);
            jr.setMessage("La información proporcionada para el cliente no es válida");
            Map<String, String> errorsMap = new HashMap<>();
            br.getFieldErrors().forEach((f) -> {
            String fieldName = f.getField();
            String message = messageSource.getMessage(f, null);
            logger.info("Code: " + f.getCode() + ", Field: " + fieldName + ", Msg: " + message);
            errorsMap.put(fieldName, message);
            });
            jr.setErrors(errorsMap);
            return jr;
            }*/
            Map<String, String> errorsMap = spingereUtils.invokeValidator(dto, UsuarioDto.class, userDtoValidator);
            
            if (!errorsMap.isEmpty()) {                
                jr.setIsOk(false);
                jr.setMessage("La información proporcionada para el cliente no es válida");
                jr.setErrors(errorsMap);
                return jr;
            }
            
            userService.saveUsuario(dto);
            jr.setIsOk(true);
            jr.setMessage("El registro ha sido guardado correctamente");
            logger.info("--> ok");
        } catch (SpingereException ex) {
            logger.info("--> fail");
            jr.setIsOk(false);
            jr.setMessage(ex.getMessage());
        }
        return jr;
    }

}