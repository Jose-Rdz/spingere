package com.spingere.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Map;
import com.spingere.dto.UsuarioDto;
import com.spingere.service.UsuarioService;
import com.spingere.utils.JSONResponse;
import com.spingere.utils.SpingereException;
import com.spingere.utils.SpingereUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jose-Rdz
 */
@Controller
@RequestMapping(value = "/usuarios")
public class UsuariosController {

    private static final Logger logger = LoggerFactory.getLogger(UsuariosController.class);

    private @Autowired SpingereUtils spingereUtils;
    private @Autowired UsuarioService userService;
    private @Autowired Validator usuarioDtoValidator;
    
    @GetMapping
    public ModelAndView main() {
        ModelAndView mv = new ModelAndView("usuarios");
        LocalDate hoy = LocalDate.now();
        mv.addObject("fecha", hoy.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(new Locale("es", "MX"))));
        return mv;
    }
    
    @ResponseBody
    @PostMapping("/save")
    public JSONResponse saveUser(@RequestBody UsuarioDto dto) {
        logger.info("-----> Guardando usuario...");
        JSONResponse jr = new JSONResponse();
        try {
            Map<String, String> errorsMap = spingereUtils.invokeValidator(dto, UsuarioDto.class, usuarioDtoValidator);
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
    
    @ResponseBody
    @PostMapping("/delete")
    public JSONResponse deleteUser(
            @RequestParam("u") Integer idUsuario,
            @RequestParam("c") Integer idCliente) {
        logger.info("-----> Eliminando usuario...");
        JSONResponse jr = new JSONResponse();
        try {            
            userService.deleteUsuario(idUsuario, idCliente);
            jr.setIsOk(true);
            jr.setMessage("El registro ha sido eliminado correctamente");
            logger.info("--> ok");
        } catch (SpingereException ex) {
            logger.info("--> fail");
            jr.setIsOk(false);
            jr.setMessage(ex.getMessage());
        }
        return jr;
    }

}
