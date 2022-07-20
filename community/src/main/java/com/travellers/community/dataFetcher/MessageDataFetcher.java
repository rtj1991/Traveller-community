package com.travellers.community.dataFetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import com.travellers.community.dto.MessageDto;
import com.travellers.community.exceptions.DuplicateEntryException;
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
    private UserDataFetcher userDataFetcher;

    @PreAuthorize("hasAnyRole('ADMIN','PREMIUM')")
    @DgsMutation(field = "sendMessage")
    public Message sendMessage(@InputArgument("id") int id,@InputArgument MessageDto inputMsg) {

        if (!userDataFetcher.userMap().containsKey(id)) throw new UserNotFoundException("User Not Found");

        User user = new User();
        user.setUser_id(id);
        Message message = messageMapper.modelToDto(inputMsg);
        message.setSender(user);

        return messageService.sendMessage(message);
    }

}
