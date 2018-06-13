package com.teamc.jms;

import com.teamc.model.Share;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class TransactionProducer {

    private JmsTemplate jmsTemplate;

    @Autowired
    public TransactionProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(Share share) {
        jmsTemplate.convertAndSend("share.queue", share);
    }
}
