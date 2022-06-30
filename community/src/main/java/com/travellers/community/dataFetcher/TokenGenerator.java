package com.travellers.community.dataFetcher;

import com.travellers.community.model.Role;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TokenGenerator {
    String build(Object id, List<Role> role);
}
