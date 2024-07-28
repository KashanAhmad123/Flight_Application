import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Flight {

    @Id
    private String flightNumber;
    private String status;

    // getters and setters
}
