package com.spingere.controller;

import com.spingere.utils.JSONResponse;
import com.spingere.utils.SpingereException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.spingere.service.CatalogoService;

/**
 *
 * @author Jose-Rdz
 */
@Controller
@RequestMapping(value = "/cat")
public class CatalogosController {
    
    private static final Logger logger = LoggerFactory.getLogger(CatalogosController.class);
    
    private @Autowired CatalogoService catalogoService;
    
    @ResponseBody
    @RequestMapping(value = "/tipo-cliente", method = RequestMethod.GET)
    public JSONResponse retrieveTipoClientes() {
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
    
    @ResponseBody
    @RequestMapping(value = "/proyectos-cliente", method = RequestMethod.GET)
    public JSONResponse getProyectosCliente(@RequestParam("i") Integer idCliente) {
        logger.info("-----> Obteniendo proyectos del cliente {}", idCliente);
        JSONResponse jsonResponse = new JSONResponse();
        try {
            jsonResponse.setIsOk(true);
            jsonResponse.setInfo(catalogoService.catProyectosCliente(idCliente));
        } catch (SpingereException ex) {
            jsonResponse.setIsOk(false);
            jsonResponse.setMessage(ex.getMessage());
        }
        return jsonResponse;
    }
    
}
