package dgtic.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario_rol")
public class UsuarioRol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Usuario necesario")
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="usuario_id",nullable = true,unique = true)
    private Usuario usuario;

    @NotNull(message = "Rol necesario")
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="rol_id",nullable = true,unique = true)
    private Rol rol;

    @Column(nullable = false)
    private Boolean status = true;

}
