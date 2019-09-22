package com;

import com.disruptor.DisruptorEnum;
import com.enums.TypeEnum;
import com.handler.GameMessageHandler;
import com.handler.MessageGroup;
import com.handler.MessageThreadHandler;
import com.pojo.Packet;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;


@Component
public class GameReceiver extends BaseReceiver {
    private MessageGroup m;

    private DisruptorEnum type = DisruptorEnum.GAME_MESSAGE;

    @Override
    public void startup(AtomicInteger count) {
        m = new MessageGroup(TypeEnum.GroupEnum.GAME_GROUP.name()) {
            @Override
            public MessageThreadHandler getMessageThreadHandler() {
                return new GameMessageHandler();
            }
        };
        m.startup(count);

//        DisruptorCreater disruptorCreater = new DisruptorCreater(type.name(), MessageWorkerHandler.class);
//        disruptorCreater.create();
//        DisruptorManager.addDisruptor(type, disruptorCreater);
    }

    @Override
    public void onReceive(Packet message) {
//        MessageEventProducer.publishMessage(type, message);
        m.messageReceived(message);
    }

}
