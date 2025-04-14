package dgtic.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "La categoria no puede estar vacía")
    @Column(nullable = false)
    private String nombre;
    @NotBlank(message = "La descripción no puede estar vacía")
    @Column(nullable = false)
    private String descripcion;


    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", categoria='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
