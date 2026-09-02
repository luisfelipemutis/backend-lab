# ORM - Object Relational Mapping

## ¿Qué es ORM?

Es una **técnica** (o librería) que mapea automáticamente:
- Tablas DB → Clases Java
- Columnas → Atributos
- Filas → Objetos

### Problema sin ORM (Lo que haces ahora)

```java
// ❌ MANUAL: Escribir SQL manualmente
String sql = "INSERT INTO cliente (nombre, apellido, membresia) VALUES (?, ?, ?)";
ps.setString(1, cliente.getNombre());
ps.setString(2, cliente.getApellido());
ps.setInt(3, cliente.getMembresia());
ps.executeUpdate();
```

### Solución con ORM (Con JPA/Hibernate)
```java
// ✅ AUTOMÁTICO: Solo crear y guardar
Cliente cliente = new Cliente("Juan", "Pérez", 100);
entityManager.persist(cliente);  // ¡Hecho!
```

### Frameworks ORM en Java
1. Hibernate: Framework más popular
2. JPA (Java Persistence API): Estándar oficial
3. EclipseLink: Otra implementación de JPA
4. MyBatis: Hybrid (no es 100% ORM, pero simplifica)

### ¿Cuándo usar ORM?

✅ Aplicaciones grandes con muchas tablas
✅ Cambios frecuentes en la BD
✅ Prototipos rápidos
❌ Queries complejas muy específicas (mejor usar SQL nativo)
❌ Aplicaciones que requieren control fino de performance


Ejemplo de mapeo ORM
TABLA SQL: cliente
┌────┬─────────┬──────────┬───────────┐
│ id │ nombre  │ apellido │ membresia │
└────┴─────────┴──────────┴───────────┘

CLASE JAVA con ORM:
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id @GeneratedValue
    private int id;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "apellido")
    private String apellido;
    
    @Column(name = "membresia")
    private int membresia;
}

// El ORM automáticamente:
// 1. Crea la tabla si no existe
// 2. Convierte Cliente → INSERT SQL
// 3. Convierte ResultSet → Cliente



**Archivo: `conceptual/ORMSimulado.java`**
```java
/**
 * Simulación SIMPLE de cómo funciona un ORM internamente
 * (Esto es pseudocódigo educativo)
 */
public class ORMSimulado {
    
    // Annotation para marcar tablas
    @interface Entity {
        String table();
    }
    
    // Annotation para marcar columnas
    @interface Column {
        String value();
    }
    
    // Clase de entidad mapeada
    @Entity(table = "cliente")
    static class Cliente {
        @Column("id")
        int id;
        
        @Column("nombre")
        String nombre;
        
        @Column("apellido")
        String apellido;
        
        // getters, setters...
    }
    
    // Simulador ORM
    static class SimpleORM {
        
        /**
         * Genera automáticamente: 
         * "INSERT INTO cliente (nombre, apellido) VALUES (?, ?)"
         */
        public static String generateInsertSQL(Cliente obj) {
            return "INSERT INTO cliente (nombre, apellido) VALUES (?, ?)";
        }
        
        /**
         * Convierte un ResultSet automáticamente a Cliente
         */
        public static Cliente mapResultSetToEntity(ResultSet rs) throws SQLException {
            Cliente c = new Cliente();
            c.id = rs.getInt("id");
            c.nombre = rs.getString("nombre");
            c.apellido = rs.getString("apellido");
            return c;
        }
    }
    
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.id = 1;
        cliente.nombre = "Juan";
        cliente.apellido = "Pérez";
        
        // El ORM genera esto automáticamente
        String sql = SimpleORM.generateInsertSQL(cliente);
        System.out.println("ORM generó: " + sql);
    }
}
```