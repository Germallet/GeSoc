package Main;

import javax.persistence.*;

@MappedSuperclass
public class IDGenerator {
    @Id
    @GeneratedValue
    private long id;
}
