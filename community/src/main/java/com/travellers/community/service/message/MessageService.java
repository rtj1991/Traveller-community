package com.travellers.community.service.message;

import com.travellers.community.dto.MessageDto;
import com.travellers.community.model.Message;

public interface MessageService {

    Message sendMessage(Message message);


}
