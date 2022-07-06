package com.travellers.community.service.message;

import com.travellers.community.dto.MessageDto;
import com.travellers.community.model.Message;
import com.travellers.community.model.User;
import com.travellers.community.repository.MessageRepository;
import com.travellers.community.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Message sendMessage(int id, MessageDto inputMessage) {

        User senderUser = userRepository.findById(id).get();
        User receiverUser = userRepository.findById(inputMessage.getReceiver()).get();

        Message message = new Message();
        message.setSender(senderUser);
        message.setReceiver(receiverUser);
        message.setMessage(inputMessage.getMessage());
        return messageRepository.save(message);
    }
}
