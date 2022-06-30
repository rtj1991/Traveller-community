package com.travellers.community.dataFetcher;

import com.travellers.community.model.Role;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TokenGenerator {
    String build(Object id, List<Role> role);
}
