package com.spingere.controller;

import com.spingere.service.CatalogoService;
import com.spingere.service.GraficaService;
import com.spingere.service.UsuarioService;
import com.spingere.utils.JSONResponse;
import com.spingere.utils.SpingereException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jose-Rdz
 */
@Controller
@RequestMapping("/graficas")
public class GraficasController {
    
    private @Autowired GraficaService graficaService;
    private @Autowired CatalogoService catalogoService;
    private @Autowired UsuarioService usuariosService;
    
    @GetMapping
    public ModelAndView main() throws SpingereException {
        ModelAndView mv = new ModelAndView("graficas");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // TODO verificar que no sea un anonymous access
        mv.addObject("clientesUsuario", usuariosService.getClientesUsuario(userDetails.getUsername()));
        mv.addObject("graficas", catalogoService.catAllGraficas());
        return mv;
    }
    
    @ResponseBody
    @GetMapping("/display")
    public JSONResponse graficaHtml(
            @RequestParam("c") Integer idCliente, 
            @RequestParam("p") Integer idProyecto,
            @RequestParam("g") Integer idGrafica) {
        JSONResponse jsonResponse = new JSONResponse();
        try {
            jsonResponse.setIsOk(true);
            jsonResponse.setInfo(graficaService.getGraficaHtml(idCliente, idProyecto, idGrafica));
        } catch (SpingereException ex) {
            jsonResponse.setIsOk(false);
            jsonResponse.setMessage(ex.getMessage());
        }
        return jsonResponse;
    }
    
}
