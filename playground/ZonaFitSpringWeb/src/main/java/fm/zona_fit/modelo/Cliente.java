package fm.zona_fit.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;

@Entity
// Lombok annotation to generate getters, setters, toString, equals, and
// hashCode methods automatically.
@Data
// Lombok annotation to generate a no-argument constructor automatically.
@NoArgsConstructor
// Lombok annotation to generate an all-argument constructor automatically.
@AllArgsConstructor
// Lombok annotation to generate a toString method automatically.
@ToString
// Lombok annotation to generate equals and hashCode methods automatically.
@EqualsAndHashCode
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer membresia;
}
