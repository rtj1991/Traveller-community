package com.travellers.community.dataFetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import com.travellers.community.dto.MessageDto;
import com.travellers.community.model.Message;
import com.travellers.community.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

@DgsComponent
public class MessageDataFetcher {

    @Autowired
    private MessageService messageService;
    @PreAuthorize("hasAnyRole('ADMIN','PREMIUM')")
    @DgsMutation
    public Message sendMessage(@InputArgument("id") int id, MessageDto inputMsg) {
        System.out.println("inputMessage-> "+inputMsg);
        return messageService.sendMessage(id, inputMsg);
    }

}
