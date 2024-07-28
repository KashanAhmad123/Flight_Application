import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Subscription {

    @Id
    private String flightNumber;
    private String notificationMethod;

    // getters and setters
}
