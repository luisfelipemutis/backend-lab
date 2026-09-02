# DAO - Data Access Object Pattern

## ¿Qué es DAO?

Es un **patrón de diseño** que encapsula la lógica de acceso a datos.

### Beneficios

1. **Separación de responsabilidades**: Lógica de negocio ≠ Acceso a datos
2. **Reutilizable**: Cambiar BD sin afectar el resto del código
3. **Testeable**: Fácil mockear (simular BD)
4. **Mantenible**: Un solo lugar donde están todas las queries

### Estructura DAO
┌─────────────────────────┐
│ Application / Service │
└────────────┬────────────┘
│ Usa interfaz
▼
┌─────────────────────────┐
│ IProductoDAO │ ← Interfaz (contrato)
│ + obtenerTodos() │
│ + obtenerPorId(id) │
│ + guardar() │
│ + actualizar() │
│ + eliminar() │
└────────────┬────────────┘
△
│ Implementa
┌──────┴──────┐
│ │
┌──▼──┐ ┌───▼──┐
│JDBC │ │Mock │ ← Para testing
│Impl │ │Impl │
└─────┘ └──────┘
│
▼
MySQL DB


### Ventaja principal

Si mañana cambias de MySQL a PostgreSQL, solo cambias la implementación, 
pero la interfaz sigue igual.


### Archivo: IProductoDAO.java
```java
public interface IProductoDAO {
    
    List<Producto> obtenerTodos();
    
    Producto obtenerPorId(int id);
    
    void guardar(Producto producto);
    
    void actualizar(Producto producto);
    
    void eliminar(int id);
}
```

### Archivo: ProductoDAOImpl.java
```java
public class ProductoDAOImpl implements IProductoDAO {
    
    @Override
    public List<Producto> obtenerTodos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto";
        
        try (Connection con = ConexionSimple.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                productos.add(new Producto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getDouble("precio")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return productos;
    }
    
    // ... resto de métodos
}
```