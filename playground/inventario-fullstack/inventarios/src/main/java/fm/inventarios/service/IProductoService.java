package fm.inventarios.service;

import fm.inventarios.model.Producto;

import java.util.List;

public interface IProductoService {

    public List<Producto> getAllProducts();

    public Producto getProductById(Integer id);

    public Producto saveProduct(Producto producto);

    public void deleteProduct(Integer id);

}
