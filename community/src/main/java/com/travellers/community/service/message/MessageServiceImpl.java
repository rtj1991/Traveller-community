package com.travellers.community.service.message;

import com.travellers.community.dto.MessageDto;
import com.travellers.community.model.Message;
import com.travellers.community.model.User;
import com.travellers.community.repository.MessageRepository;
import com.travellers.community.repository.UserRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message sendMessage(Message message) {
        return messageRepository.save(message);
    }
}
