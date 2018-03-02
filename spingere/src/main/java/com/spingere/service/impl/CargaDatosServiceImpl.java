package com.spingere.service.impl;

import com.spingere.model.Cliente;
import com.spingere.model.ClienteProyecto;
import com.spingere.model.Metrica;
import com.spingere.model.Proyecto;
import com.spingere.model.SubtipoMetrica;
import com.spingere.model.TipoMetrica;
import com.spingere.repository.ClienteProyectoRepository;
import com.spingere.repository.ClienteRepository;
import com.spingere.repository.MetricaRepository;
import com.spingere.repository.ProyectoRepository;
import com.spingere.repository.SubtipoMetricaRepository;
import com.spingere.repository.TipoMetricaRepository;
import com.spingere.service.CargaDatosService;
import com.spingere.utils.SpingereException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.StreamSupport;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Jose-Rdz
 */
@Repository
@Service
public class CargaDatosServiceImpl implements CargaDatosService {
    
    private static final Logger logger = LoggerFactory.getLogger(CargaDatosServiceImpl.class);
    
    private @Autowired ClienteRepository clienteRepository;
    private @Autowired MetricaRepository metricaRepository;
    private @Autowired ProyectoRepository proyectoRepository;
    private @Autowired SubtipoMetricaRepository subtipoMetricaRepository;
    private @Autowired TipoMetricaRepository tipoMetricaRepository;
    private @Autowired ClienteProyectoRepository clienteProyectoRepository;
    
    @Override
    @Transactional(rollbackFor = SpingereException.class)
    public void cargaDatosFromXlsx(MultipartFile file) throws SpingereException {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            
            XSSFSheet sheetClientes         = workbook.getSheetAt(0);
            XSSFSheet sheetProyectos        = workbook.getSheetAt(1);
            XSSFSheet sheetTiposMetrica     = workbook.getSheetAt(2);
            XSSFSheet sheetSubTipoMetricas  = workbook.getSheetAt(3);
            XSSFSheet sheetMetricas         = workbook.getSheetAt(4);
            
            DataFormatter poiDataFormatter = new DataFormatter();
            
            logger.info("---> guardar/actualizar clientes");
            StreamSupport.stream(sheetClientes.spliterator(), false)
                    .skip(1)
                    .filter(row -> !row.getCell(0).getCellTypeEnum().equals(CellType.BLANK)) // detectar filas vacías
                    .forEach(row -> {
                        Cell cellId = row.getCell(0);
                        Cell cellCliente = row.getCell(1);
                        Cell cellDescripcion = row.getCell(2);
                        
                        Integer idCliente   = (int) cellId.getNumericCellValue();
                        String cliente      = poiDataFormatter.formatCellValue(cellCliente);
                        String descripcion  = poiDataFormatter.formatCellValue(cellDescripcion);
                        
                        clienteRepository.saveAndFlush(new Cliente(idCliente, cliente, cliente));
                    });
            
            logger.info("---> guardar/actualizar proyectos");
            StreamSupport.stream(sheetProyectos.spliterator(), false)
                    .skip(1)
                    .filter(row -> !row.getCell(2).getCellTypeEnum().equals(CellType.BLANK)) // detectar filas vacías
                    .forEach(row -> {
                        Cell cellIdProyecto     = row.getCell(2);
                        Cell cellProyecto       = row.getCell(3);
                        Cell cellDescripcion    = row.getCell(4);
                        
                        Integer idProyecto  = (int) cellIdProyecto.getNumericCellValue();
                        String proyecto     = cellProyecto.getStringCellValue();
                        String descripcion  = poiDataFormatter.formatCellValue(cellDescripcion);
                        
                        proyectoRepository.saveAndFlush(new Proyecto(idProyecto, proyecto, descripcion));
                    });
            
            logger.info("---> guardar/actualizar cliente-proyecto");
            StreamSupport.stream(sheetProyectos.spliterator(), false)
                    .skip(1)
                    .filter(row -> !row.getCell(2).getCellTypeEnum().equals(CellType.BLANK)) // detectar filas vacías
                    .forEach(row -> {
                        Cell cellIdCliente      = row.getCell(0);
                        Cell cellIdProyecto     = row.getCell(2);
                        
                        Integer idCliente  = (int) cellIdCliente.getNumericCellValue();
                        Integer idProyecto  = (int) cellIdProyecto.getNumericCellValue();
                        Integer idProveedor  = (int) 1; //Mismo proveedor. 
                        
                        ClienteProyecto cp = new ClienteProyecto(); 
                        cp.setIdCliente(idCliente);
                        cp.setIdProyecto(idProyecto);
                        cp.setIdProveedor(idProveedor);                        
                        clienteProyectoRepository.saveAndFlush(cp);
                    });
            
            logger.info("guardar/actualizar tipo Métrica");
            StreamSupport.stream(sheetTiposMetrica.spliterator(), false)
                    .skip(1)
                    .filter(row -> !row.getCell(0).getCellTypeEnum().equals(CellType.BLANK)) // detectar filas vacías
                    .forEach(row -> {
                        Cell cellIdTipoMetrica  = row.getCell(0);
                        Cell cellDescripcion    = row.getCell(1);
                        
                        Integer idTipoMetrica   = (int) cellIdTipoMetrica.getNumericCellValue();
                        String descripcion      = poiDataFormatter.formatCellValue(cellDescripcion);
                        
                        tipoMetricaRepository.saveAndFlush(new TipoMetrica(idTipoMetrica, descripcion));
                    });
            
            logger.info("guardar/actualizar subtipos metrica");
            StreamSupport.stream(sheetSubTipoMetricas.spliterator(), false)
                    .skip(1)
                    .filter(row -> !row.getCell(2).getCellTypeEnum().equals(CellType.BLANK)) // detectar filas vacías
                    .forEach(row -> {
                        Cell cellIdSubtipoMetrica   = row.getCell(2);
                        Cell cellIdTipoMetrica      = row.getCell(0);
                        Cell cellSubtipoMetrica     = row.getCell(3);
                        Cell cellNmonicoMetrica     = row.getCell(4);
                        
                        Integer idSubtipoMetrica    = (int) cellIdSubtipoMetrica.getNumericCellValue();
                        Integer idTipoMetrica       = (int) cellIdTipoMetrica.getNumericCellValue();
                        String subtipoMetrica       = poiDataFormatter.formatCellValue(cellSubtipoMetrica);
                        String nmonicoMetrica       = poiDataFormatter.formatCellValue(cellNmonicoMetrica);
                        
                        SubtipoMetrica sub = new SubtipoMetrica();
                        sub.setIdSubtipoMetrica(idSubtipoMetrica);
                        sub.setIdTipoMetrica(idTipoMetrica);
                        sub.setSubtipoMetrica(subtipoMetrica);
                        sub.setNmonicoMetrica(nmonicoMetrica);
                        
                        subtipoMetricaRepository.saveAndFlush(sub);
                    });
            
            logger.info("guardar/actualizar metricas");
            StreamSupport.stream(sheetMetricas.spliterator(), false)
                    .skip(1)
                    .filter(row -> !row.getCell(0).getCellTypeEnum().equals(CellType.BLANK)) // detectar filas vacías
                    .forEach(row -> {
                        Cell cellIdProyecto         = row.getCell(0);
                        Cell cellIdTipoMetrica      = row.getCell(2);
                        Cell cellIdSubtipoMetrica   = row.getCell(4);
                        Cell cellValorMetrica       = row.getCell(6);
                        
                        Integer idProyecto          = (int) cellIdProyecto.getNumericCellValue();
                        Integer idTipoMetrica       = (int) cellIdTipoMetrica.getNumericCellValue();
                        Integer idSubtipoMetrica    = (int) cellIdSubtipoMetrica.getNumericCellValue();
                        String valorMetrica         = poiDataFormatter.formatCellValue(cellValorMetrica);
                        
                        Metrica metrica = new Metrica();
                        metrica.setIdProyecto(idProyecto);
                        metrica.setIdTipoMetrica(idTipoMetrica);
                        metrica.setIdSubtipoMetrica(idSubtipoMetrica);
                        metrica.setFechaMetrica(LocalDateTime.now());
                        metrica.setValorMetrica(valorMetrica);
                        
                        metricaRepository.saveAndFlush(metrica);
                    });
            
            workbook.close();
            
        } catch (IOException ex) {
            logger.error("{{no se pudo obtener el stream del archivo xls}}", ex);
            throw new SpingereException("Oh oh! Ocurrió un error al leer el archivo.");
        } catch (RuntimeException re) {
            logger.error("{{no fue posible guardar los datos en DB}}", re);
            throw new SpingereException("Oh oh! No fue posible guardar la información.");
        }
    }
    
}
