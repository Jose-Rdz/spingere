package com.spingere.service.impl;

import com.spingere.model.Cliente;
import com.spingere.model.ClienteUsuario;
import com.spingere.model.Grafica;
import com.spingere.model.Proyecto;
import com.spingere.model.Usuario;
import com.spingere.repository.ClienteRepository;
import com.spingere.repository.ClienteUsuarioRepository;
import com.spingere.repository.GraficaRepository;
import com.spingere.repository.UsuarioRepository;
import com.spingere.utils.SpingereException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spingere.service.CatalogoService;

/**
 *
 * @author Jose-Rdz
 */
@Service
@Repository
public class CatalogoServiceImpl implements CatalogoService {

    private static final Logger logger = LoggerFactory.getLogger(CatalogoServiceImpl.class);
    
    private @Autowired ClienteRepository clienteRepository;
    private @Autowired ClienteUsuarioRepository clienteUsuarioRepository;
    private @Autowired GraficaRepository graficaRepository;
    private @Autowired UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> catAllTiposCliente() throws SpingereException {
        try {
            List<Cliente> lcu = clienteRepository.findAll(new Sort(Sort.Direction.ASC, "idCliente"));
            logger.debug("{{Tipos de cliente obtenidos desde DB}}", lcu);
            return lcu; 
        } catch (RuntimeException re) {
            logger.error("{{ Tipos de cliente no recuperados desde DB }}", re);
            throw new SpingereException("No fue posible obtener los tipo de clientes registrados...");
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ClienteUsuario> catAllClientesUsuarios() throws SpingereException {
        try {
            List<ClienteUsuario> lcu = clienteUsuarioRepository.findAll(new Sort(Sort.Direction.ASC, "idCliente"));
            return lcu != null ? lcu : new ArrayList<>(); 
        } catch (RuntimeException re) {
            logger.error("{{ usuarios no recuperados desde DB }}", re);
            throw new SpingereException("No fue posible obtener los usuarios registrados...");
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> catAllUsuarios() throws SpingereException {
        try {
            List<Usuario> lu = usuarioRepository.findAll(new Sort(Sort.Direction.ASC, "nombre"));
            return lu != null ? lu : new ArrayList<>();
        } catch (RuntimeException re) {
            logger.error("{{ usuarios no recuperados desde DB }}", re);
            throw new SpingereException("No fue posible obtener los usuarios registrados...");
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Grafica> catAllGraficas() throws SpingereException {
        try {
            List<Grafica> lg = graficaRepository.findAll(new Sort(Sort.Direction.ASC, "nombreGrafica"));
            return lg != null ? lg : new ArrayList<>();
        } catch (RuntimeException re) {
            logger.error("{{ graficas no recuperadas desde DB }}", re);
            throw new SpingereException("No fue posible obtener las gráficas...");
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Proyecto> catProyectosCliente(Integer idCliente) throws SpingereException {
        Optional<Cliente> optional = clienteRepository.findById(idCliente);
        Cliente cliente = optional.orElseThrow(() -> new SpingereException("No fue posible obtener los proyectos del cliente..."));
        List<Proyecto> proyectos = cliente.getProyectos();
        proyectos.size();
        return proyectos;
    }
    
}
