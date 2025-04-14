package dgtic.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;


@SpringBootTest
@Sql({"/schema.sql"})
class MaquetadoApplicationTest {

    @Test
    void contextLoads() {
        System.out.println("Carga de esquema");
    }

}