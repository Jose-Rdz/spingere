package com.spingere.controller;

import com.spingere.service.CargaDatosService;
import com.spingere.utils.SpingereException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author AnGeL
 */
@Controller
@RequestMapping("/carga")
public class CargaDatosController {
    
    private @Autowired CargaDatosService cargaDatosService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String main() {
        return "carga_datos";
    }
    
    @RequestMapping(value = "/xls", method = RequestMethod.POST)
    public void readXls(@RequestParam("xlsFile") MultipartFile file) {
        try {
            cargaDatosService.cargaDatosFromXls(file);
        } catch (SpingereException ex) {
            // TODO completar funcionalidad
        }
    }
    
    @RequestMapping(value = "/xlsx", method = RequestMethod.POST)
    public void readXlsx(@RequestParam("xlsxFile") MultipartFile file) {
        try {
            cargaDatosService.cargaDatosFromXls(file);
        } catch (SpingereException ex) {
            // TODO completar funcionalidad
        }
    }
    
}
