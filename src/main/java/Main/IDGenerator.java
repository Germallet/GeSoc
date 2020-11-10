package Main;

import javax.persistence.*;

@MappedSuperclass
public class IDGenerator {
    @Id
    @GeneratedValue
<<<<<<< HEAD
    public long id; //tuve que cambiar esto para poder hacerle los getId para las rutas al egreso
=======
    private long id;

    public long getId() { return id; }
>>>>>>> e8fb21fcbb346342abbcd663ede25222117aaa6c
}
