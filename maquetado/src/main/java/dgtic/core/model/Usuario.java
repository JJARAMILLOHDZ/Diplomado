package dgtic.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "La sub marca no puede estar vacía")
    @Column(nullable = false, length = 50, unique = true)
    private String nombre;
    @Column(nullable = false, length = 50, unique = true)
    private String password;
    @Column(nullable = false, length = 50, unique = true)
    private String correo;


    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;

    @ToString.Exclude
    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<UsuarioRol> rolesAsignados;
}
