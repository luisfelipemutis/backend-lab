package fm.inventarios.service;

import fm.inventarios.model.Producto;
import fm.inventarios.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> getAllProducts() {
        return productoRepository.findAll();
    }

    @Override
    public Producto getProductById(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto saveProduct(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void deleteProduct(Integer id) {
        productoRepository.deleteById(id);
    }
}
