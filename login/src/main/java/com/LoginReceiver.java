package com;


import com.enums.TypeEnum;
import com.handler.LoginMessageHandler;
import com.handler.MessageGroup;
import com.handler.MessageThreadHandler;
import com.pojo.Packet;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LoginReceiver extends BaseReceiver {

    private MessageGroup m;

    @Override
    public void startup(AtomicInteger count) {
        m = new MessageGroup(TypeEnum.GroupEnum.LOGIN_GROUP.name()) {
            @Override
            public MessageThreadHandler getMessageThreadHandler() {
                return new LoginMessageHandler();
            }
        };
        m.startup(count);
    }

    @Override
    public void onReceive(Packet message) {
        m.messageReceived(message);
    }
}
