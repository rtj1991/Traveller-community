package com.travellers.community.dataFetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import com.travellers.community.dto.MessageDto;
import com.travellers.community.exceptions.UserNotFoundException;
import com.travellers.community.mapper.MessageMapper;
import com.travellers.community.model.Message;
import com.travellers.community.model.User;
import com.travellers.community.repository.UserRepository;
import com.travellers.community.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

@DgsComponent
public class MessageDataFetcher {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasAnyRole('ADMIN','PREMIUM')")
    @DgsMutation(field = "sendMessage")
    public Message sendMessage(@InputArgument("id") int id,@InputArgument MessageDto inputMsg) {
        User user_ = userRepository.findById(id).get();
        if (user_ == null) throw new UserNotFoundException();

        User user = new User();
        user.setUser_id(id);
        Message message = messageMapper.modelToDto(inputMsg);
        message.setSender(user);

        return messageService.sendMessage(message);
    }

}
