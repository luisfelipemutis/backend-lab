package fm.inventarios.controller;

import fm.inventarios.exception.RecursoNoEncontradoExcepcion;
import fm.inventarios.service.ProductoService;
import fm.inventarios.model.Producto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("inventario-app") // http://localhost:8080/inventario-app
@CrossOrigin(value = "http://localhost:4200") // Permitir solicitudes desde el frontend en Angular
public class ProductoController {

    private static final Logger log = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoService productoService;

    @GetMapping("/getProductos") // http://localhost:8080/inventario-app/getProductos
    public List<Producto> getAllProducts() {
        List<Producto> products = productoService.getAllProducts();
        log.info("Productos obtenidos...");
        products.forEach(p -> log.info(p.toString()));
        return products;
    }

    @PostMapping("/agregarProducto") // http://localhost:8080/inventario-app/addProduct
    public Producto addProduct(@RequestBody Producto producto) {
        log.info("Producto agregado: " + producto.toString());
        return productoService.saveProduct(producto);
    }

    @GetMapping("/getProducto/{id}") // http://localhost:8080/inventario-app/getProducto/1
    public ResponseEntity<Producto> getProductById(@PathVariable Integer id) {
        Producto product = productoService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            throw new RecursoNoEncontradoExcepcion("Producto con id " + id + " no encontrado");
        }
    }

    @PutMapping("/editarProducto/{id}") // http://localhost:8080/inventario-app/updateProduct/1
    public ResponseEntity<Producto> updateProduct(@PathVariable Integer id,
                                                  @RequestBody Producto productRequest) {
        Producto product = productoService.getProductById(id);
        if (product != null) {
            product.setDescripcion(productRequest.getDescripcion());
            product.setPrecio(productRequest.getPrecio());
            product.setExistencia(productRequest.getExistencia());
            log.info("Producto actualizado: " + product);
            return ResponseEntity.ok(this.productoService.saveProduct(product));
        } else {
            throw new RecursoNoEncontradoExcepcion("Producto con id " + id + " no encontrado");
        }
    }

    @DeleteMapping("/eliminarProducto/{id}") // http://localhost:8080/inventario-app/deleteProduct/1
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Integer id) {
        Producto product = this.productoService.getProductById(id);
        if (product != null) {
            this.productoService.deleteProduct(id);
            log.info("Producto eliminado: " + product);
            Map<String, Boolean> response = new HashMap<>();
            response.put("eliminado", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } else {
            throw new RecursoNoEncontradoExcepcion("Producto con id " + id + " no encontrado");
        }
    }
}
