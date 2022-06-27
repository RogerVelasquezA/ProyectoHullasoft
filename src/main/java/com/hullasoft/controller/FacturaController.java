package com.hullasoft.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hullasoft.models.dao.CursoDao;
import com.hullasoft.models.dao.FacturaDao;
import com.hullasoft.models.dao.ItemFacturaDao;
import com.hullasoft.models.dao.UsuarioDao;
import com.hullasoft.models.entity.Carrito;
import com.hullasoft.models.entity.Curso;
import com.hullasoft.models.entity.Factura;
import com.hullasoft.models.entity.ItemFactura;
import com.hullasoft.models.entity.Usuario;
import com.hullasoft.models.service.CursoServiceImp;
import com.hullasoft.models.service.FacturaServiceImp;

@Controller
@RequestMapping(path = "factura")
public class FacturaController {

	@Autowired private CursoServiceImp cursoImp;
	
	@Autowired private FacturaServiceImp facturaImp;
	
	@Autowired private FacturaDao facturaDAO;
	
	@Autowired private CursoDao cursoDao;
	
	@Autowired private ItemFacturaDao itemFacturaDao;
	
	@Autowired private UsuarioDao usuarioDAO;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
		
	private ArrayList<Carrito> obtenerCarrito(HttpServletRequest request) {
	    ArrayList<Carrito> carrito = (ArrayList<Carrito>) request.getSession().getAttribute("carrito");
	    if (carrito == null) {
	        carrito = new ArrayList<>();
	    }
	    return carrito;
	}
	
	
	private void guardarCarrito(ArrayList<Carrito> carrito, HttpServletRequest request) {
	    request.getSession().setAttribute("carrito", carrito);
	}
	
	
	
	@GetMapping(value = "/")
	public String interfazCarrito(Model model, HttpServletRequest request) {
	    model.addAttribute("curso", new Curso());
	    float total = 0;
	    ArrayList<Carrito> carrito = this.obtenerCarrito(request);
	    for (Carrito p: carrito) total += p.getPrecio();

	    model.addAttribute("total", total);
	    
	    return "sidebar-left";
	}
	
	


	@PostMapping(value = "/agregarcarrito/{id}")
    public String agregarAlCarrito(@PathVariable(value = "id") Integer id, HttpServletRequest request, RedirectAttributes redirectAttrs) {
        ArrayList<Carrito> carrito = this.obtenerCarrito(request);
        Curso cursoBuscadoPorCodigo = cursoDao.findFirstById(id);
        

        
        if (cursoBuscadoPorCodigo == null) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "El producto con el código " )
                    .addFlashAttribute("clase", "warning");
            return "redirect:/factura/";
        }
        
        boolean encontrado = false;
        for (Carrito cursoParaVenderActual : carrito) {
            if (cursoParaVenderActual.getId().equals(cursoBuscadoPorCodigo.getId())) {
               
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            //carrito.add(new ProductoParaVender(productoBuscadoPorCodigo.getNombre(), productoBuscadoPorCodigo.getCodigo(), productoBuscadoPorCodigo.getPrecio(), productoBuscadoPorCodigo.getExistencia(), productoBuscadoPorCodigo.getId(), 1f));
            

            carrito.add(new Carrito(cursoBuscadoPorCodigo.getId(), cursoBuscadoPorCodigo.getNombre(), cursoBuscadoPorCodigo.getPrecio()));
        }
        this.guardarCarrito(carrito, request);
        return "redirect:/factura/";
    }
	
	@GetMapping(value = "/limpiar")
    public String cancelarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs) {
        this.limpiarCarrito(request);
        redirectAttrs
                .addFlashAttribute("mensaje", "Venta cancelada")
                .addFlashAttribute("clase", "info");
        return "redirect:/factura/";
    }
	
	private void limpiarCarrito(HttpServletRequest request) {
        this.guardarCarrito(new ArrayList<>(), request);
    }
	
	
	@PostMapping(value = "/quitar/{indice}")
    public String quitarDelCarrito(@PathVariable int indice, HttpServletRequest request) {
        ArrayList<Carrito> carrito = this.obtenerCarrito(request);
        if (carrito != null && carrito.size() > 0 && carrito.get(indice) != null) {
            carrito.remove(indice);
            this.guardarCarrito(carrito, request);
        }
        return "redirect:/factura/";
    }
	
	
	@PostMapping(value = "/terminar")
    public String terminarVenta(Factura factura, HttpServletRequest request, RedirectAttributes redirectAttrs, Model model, SessionStatus status
    		, @AuthenticationPrincipal User u) {
        ArrayList<Carrito> carrito = this.obtenerCarrito(request);
        Double total=0.0;
        
        
        if (carrito == null || carrito.size() == 0) {
			model.addAttribute("titulo", "Crear Factura");
			model.addAttribute("error", "Error: La factura NO puede no tener líneas!");
			return "redirect:/factura/";
		}
        
              
        Usuario user=usuarioDAO.findByUsername(u.getUsername());
        
        
           
        
        log.info("ejecutando el controlador Spring MVC");
        log.info("usuario que hizo login:" + u);
       
        for (Carrito carritos : carrito) {
        	Curso curso = cursoDao.findCursoById(carritos.getId());

			ItemFactura linea = new ItemFactura();
			total+=carritos.getPrecio();
			
			linea.setCurso(curso);
			factura.setMontoTotal(total);
			factura.setUsuario(user);
			factura.addItemFactura(linea);
			

			//log.info("ID: " + total.toString() + ", cantidad: " + linea.toString());
			
        }
        
        System.out.println(total);
        System.out.println(factura);
        facturaImp.agregarFactura(factura);
        
        redirectAttrs
        .addFlashAttribute("mensaje", "Venta Realizada")
        .addFlashAttribute("clase", "info");
        
        status.setComplete();
        
        limpiarCarrito(request);
        return "redirect:/factura/";
    }
	
	
	
	
	@GetMapping("/listarfactura/{id}")
	public String editar(@ModelAttribute Factura factura, Model model) {
		
		Factura fac = facturaImp.buscarFactura(factura);
		
		model.addAttribute("factura", fac);
		
		return "factura";
	}
	
	
	
	
	
	
	
	
}
