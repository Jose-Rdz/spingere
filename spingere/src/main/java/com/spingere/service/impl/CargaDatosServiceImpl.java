package com.spingere.service.impl;

import com.spingere.service.CargaDatosService;
import com.spingere.utils.SpingereException;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author AnGeL
 */
@Repository
@Service
public class CargaDatosServiceImpl implements CargaDatosService {
    
    private static final Logger logger = LoggerFactory.getLogger(CargaDatosServiceImpl.class);
    
    @Override
    public void cargaDatosFromXls(MultipartFile file) throws SpingereException {
        //try {
            logger.debug("contentType=" + file.getContentType());
            
            /*
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            HSSFSheet sheetMain = workbook.getSheetAt(0);
            // TODO procesar info del archivo
            workbook.close();
            
        } catch (IOException ex) {
            logger.error("{{no se pudo obtener el stream del archivo xls}}", ex);
            throw new SpingereException("No fue posible leer el archivo proporcionado.");
        }*/
    }
    
}
