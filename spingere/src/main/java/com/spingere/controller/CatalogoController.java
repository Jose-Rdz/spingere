package com.spingere.controller;

import com.spingere.service.CatalogoService;
import com.spingere.utils.JSONResponse;
import com.spingere.utils.SpingereException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Jose-Rdz
 */
@Controller
@RequestMapping(value = "/cat")
public class CatalogoController {
    
    private static final Logger logger = LoggerFactory.getLogger(CatalogoController.class);
    
    private @Autowired CatalogoService catalogoService;
    
    @ResponseBody
    @RequestMapping(value = "/tipo-cliente", method = RequestMethod.GET)
    public JSONResponse retrieveTipoClients() {
        logger.info("-----> Obteniendo catálogo de tipo de clientes...");
        JSONResponse jsonResponse = new JSONResponse();
        try {
            jsonResponse.setIsOk(true);
            jsonResponse.setInfo(catalogoService.catAllTiposCliente()); 
        } catch (SpingereException ex) {
            jsonResponse.setIsOk(false);
            jsonResponse.setMessage(ex.getMessage());
        }
        return jsonResponse;
    }
    
    @ResponseBody
    @RequestMapping(value = "/cliente-usuario", method = RequestMethod.GET)
    public JSONResponse getClientesUsuarios() {
        logger.info("-----> Obteniendo catálogo de clientes-usuarios...");
        JSONResponse jsonResponse = new JSONResponse();
        try {
            jsonResponse.setIsOk(true);
            jsonResponse.setInfo(catalogoService.catAllClientesUsuarios());
        } catch (SpingereException ex) {
            jsonResponse.setIsOk(false);
            jsonResponse.setMessage(ex.getMessage());
        }
        return jsonResponse;
    }
    
}
