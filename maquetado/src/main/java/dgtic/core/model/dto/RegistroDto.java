package dgtic.core.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDto
{
    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Column(nullable = false, length = 100)
    private String apellidos;

    @NotBlank(message = "El CURP no puede estar vacío")
    @Pattern(regexp = "^[A-Z]{4}[0-9]{6}[HM][A-Z]{5}[A-Z0-9]{2}$", message = "El CURP no tiene un formato válido")
    @Column(nullable = false, length = 20, unique = true)

    private String curp;

    @Column(length = 20)
    @Pattern(regexp = "^[A-Z]{4}[0-9]{6}[A-Z0-9]{3}$", message = "El RFC no tiene un formato válido")
    private String rfc;

    private String usuario;
    @Column(nullable = false, length = 50, unique = true)
    private String password;
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    private Integer rolId;



}
