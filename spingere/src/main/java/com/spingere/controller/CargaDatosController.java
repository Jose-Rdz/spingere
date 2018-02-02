package com.spingere.controller;

import com.spingere.service.CargaDatosService;
import com.spingere.utils.SpingereException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author AnGeL
 */
@Controller
@RequestMapping("/carga")
public class CargaDatosController {
    
    private static final Logger logger = LoggerFactory.getLogger(CargaDatosController.class);
    private static final String XLSX_MIME_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    
    private @Autowired CargaDatosService cargaDatosService;
    
    @GetMapping
    public String main() {
        return "carga_datos";
    }
    
    @PostMapping("/xlsx")
    public ResponseEntity<Object> readXlsx(@RequestParam("xlsxFile") MultipartFile file) {
        try {
            if (file == null) {
                return new ResponseEntity<>("¡No se ha seleccionado ningún archivo!", HttpStatus.BAD_REQUEST);
            }
            
            String contentType = file.getContentType();
            
            if (contentType == null) {
                return new ResponseEntity<>("El archivo no posee una extensión de archivo válida [.xlsx]", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
            }
            
            if (!contentType.equals(XLSX_MIME_TYPE)) {
                return new ResponseEntity<>("Formato de archivo no soportado, se requiere [.xlsx]", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
            }
            
            cargaDatosService.cargaDatosFromXlsx(file);
            
            return new ResponseEntity<>("¡La información se ha guardado correctamente!", HttpStatus.OK);
            
        } catch (SpingereException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
}
