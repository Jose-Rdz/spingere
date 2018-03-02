package com.spingere.controller;

import com.spingere.service.ModeloService;
import com.spingere.service.UsuarioService;
import com.spingere.utils.JSONResponse;
import com.spingere.utils.SpingereException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jose-Rdz
 */
@Controller
@RequestMapping("modelos")
public class ModelosController {
    
    private @Autowired ModeloService modeloService;
    private @Autowired UsuarioService usuariosService;
    
    @GetMapping
    public ModelAndView main() throws SpingereException {
        ModelAndView mv = new ModelAndView("modelos");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        mv.addObject("clientesUsuario", usuariosService.getClientesUsuario(userDetails.getUsername()));
        mv.addObject("modelos", new String[]{"Log Normal", "Normal"}); // TODO se debe cambiar por consulta en DB
        return mv;
    }
    
    @ResponseBody
    @GetMapping("/data")
    public JSONResponse datosModelo(
            @RequestParam("c") Integer idCliente, 
            @RequestParam("p") Integer idProyecto,
            @RequestParam("m") Integer idModelo) {
        JSONResponse jsonResponse = new JSONResponse();
        try {
            jsonResponse.setIsOk(true);
            jsonResponse.setInfo(modeloService.getDataModelo(idCliente, idProyecto, idModelo));
        } catch (SpingereException ex) {
            jsonResponse.setIsOk(false);
            jsonResponse.setMessage(ex.getMessage());
        }
        return jsonResponse;
    }
    
}
