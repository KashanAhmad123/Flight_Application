import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private AppNotificationService appNotificationService;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @KafkaListener(topics = "flight-updates", groupId = "notification-group")
    public void listen(Flight flight) {
        List<Subscription> subscriptions = subscriptionRepository.findByFlightNumber(flight.getFlightNumber());

        for (Subscription sub : subscriptions) {
            switch (sub.getNotificationMethod()) {
                case "Email":
                    emailService.sendEmail(sub.getEmail(), flight);
                    break;
                case "SMS":
                    smsService.sendSms(sub.getPhoneNumber(), flight);
                    break;
                case "App Notification":
                    appNotificationService.sendNotification(sub.getUserId(), flight);
                    break;
            }
        }
    }
}
