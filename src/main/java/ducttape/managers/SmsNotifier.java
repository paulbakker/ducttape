package ducttape.managers;

import ducttape.entities.WebOrder;
import org.jboss.seam.jms.TopicBuilder;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.Topic;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @Author Paul Bakker - paul.bakker.nl@gmail.com
 */

@Stateless
public class SmsNotifier {
    @Inject TopicBuilder topicBuilder;
    @Resource(mappedName="topic/orders") Topic ordersTopic;
    
    public
    @Asynchronous
    void sms(@Observes @OrderPlaced WebOrder webOrder) {
        topicBuilder.destination(ordersTopic).sendString("Order for " + webOrder.getCustomer().getName());

    }
}
