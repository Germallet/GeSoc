package Main;

import javax.persistence.*;

@MappedSuperclass
public class IDGenerator {
    @Id
    @GeneratedValue
    public long id; //tuve que cambiar esto para poder hacerle los getId para las rutas al egreso
}
