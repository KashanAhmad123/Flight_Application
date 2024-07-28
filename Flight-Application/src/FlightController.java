import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private KafkaTemplate<String, Flight> kafkaTemplate;

    @GetMapping("/flights")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @PostMapping("/subscribe")
    public void subscribe(@RequestBody SubscriptionRequest request) {
        subscriptionService.addSubscription(request);
    }

    @PostMapping("/update-flight")
    public void updateFlight(@RequestBody Flight flight) {
        flightService.updateFlight(flight);
        kafkaTemplate.send("flight-updates", flight);
    }
}
