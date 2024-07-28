import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public void addSubscription(SubscriptionRequest request) {
        Subscription subscription = new Subscription(request.getFlightNumber(), request.getNotificationMethod());
        subscriptionRepository.save(subscription);
    }
}
