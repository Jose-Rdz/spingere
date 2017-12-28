package com.spingere.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import com.spingere.dto.UserDto;
import com.spingere.service.ClienteService;
import com.spingere.service.ClienteUsuarioService;
import com.spingere.service.UserService;
import com.spingere.utils.JSONResponse;
import com.spingere.utils.SpingereException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author G13380
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private @Autowired MessageSource messageSource;
    private @Autowired UserService userService;
    private @Autowired Validator userDtoValidator;
    private @Autowired ClienteUsuarioService clienteUsuarioService;
    private @Autowired ClienteService clienteService;
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView mv = new ModelAndView("users");
        LocalDate hoy = LocalDate.now();
        mv.addObject("fecha", hoy.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(new Locale("es", "MX"))));
        return mv;
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/getTipoCliente", method = RequestMethod.GET)
    public JSONResponse retrieveTipoClients() {
        logger.info("-----> Obteniendo tipo de clientes...");
        JSONResponse jsonResponse = new JSONResponse();
        try {
            jsonResponse.setIsOk(true);
            jsonResponse.setInfo(clienteService.getTipoClientes()); 
        } catch (SpingereException ex) {
            jsonResponse.setIsOk(false);
            jsonResponse.setMessage(ex.getMessage());
        }
        return jsonResponse;
    }
    
    @ResponseBody
    @RequestMapping(value = "/getClients", method = RequestMethod.GET)
    public JSONResponse retrieveClients() {
        logger.info("-----> Obteniendo clientes...");
        JSONResponse jsonResponse = new JSONResponse();
        try {
            jsonResponse.setIsOk(true);
            jsonResponse.setInfo(clienteUsuarioService.getClients());
        } catch (SpingereException ex) {
            jsonResponse.setIsOk(false);
            jsonResponse.setMessage(ex.getMessage());
        }
        return jsonResponse;
    }
    
    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public JSONResponse retrieveUsers() {
        logger.info("-----> Obteniendo usuarios...");
        JSONResponse jsonResponse = new JSONResponse();
        try {
            jsonResponse.setIsOk(true);
            //jsonResponse.setInfo(userService.getUsers());
            jsonResponse.setInfo(clienteUsuarioService.getClients());
        } catch (SpingereException ex) {
            jsonResponse.setIsOk(false);
            jsonResponse.setMessage(ex.getMessage());
        }
        return jsonResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    //@RequestParam (@name)....
    public JSONResponse saveUser(@RequestBody UserDto dto) {
        logger.info("-----> Guardando usuario...");
        
        JSONResponse jr = new JSONResponse();
        try {
            BindingResult br = new BeanPropertyBindingResult(dto, UserDto.class.getName());
            userDtoValidator.validate(dto, br);
            
            if (br.hasErrors()) {
                logger.info("{{existen errores de validación}}", br.getAllErrors());
                jr.setIsOk(false);
                jr.setMessage("La información proporcionada para el cliente no es válida");
                Map<String, String> errorsMap = new HashMap<>();
                for (FieldError f : br.getFieldErrors()) {
                    String fieldName = f.getField();
                    String message = messageSource.getMessage(f, null);
                    logger.info("Code: " + f.getCode() + ", Field: " + fieldName + ", Msg: " + message);
                    errorsMap.put(fieldName, message);
                }
                jr.setErrors(errorsMap);
                return jr;
            }
            
            userService.saveUser(dto);
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
