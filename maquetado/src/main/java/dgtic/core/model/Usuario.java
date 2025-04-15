package dgtic.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Usuario")
public class Usuario
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    @Column(nullable = false, length = 50, unique = true)
    private String nombre;
    @NotBlank(message = "La contraseña no puede estar vacía")
    @Column(nullable = false, length = 50, unique = true)
    @Size(min = 5, max = 30)
    private String password;
    @Column(nullable = false, length = 50, unique = true)
    private String correo;


    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;

    @ToString.Exclude
    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private List<UsuarioRol> rolesAsignados;
}
