package dgtic.core.model;

import jakarta.persistence.*;
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

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="usuario_id",nullable = true,unique = true)
    private Usuario usuario;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="rol_id",nullable = true,unique = true)
    private Rol rol;

    @Column(nullable = false)
    private Boolean status = true;

}
