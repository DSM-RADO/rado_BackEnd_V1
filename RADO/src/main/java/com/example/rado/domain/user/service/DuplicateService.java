package com.example.rado.domain.user.service;

import com.example.rado.domain.user.domain.repository.UserRepository;
import com.example.rado.domain.user.facade.UserFacade;
import com.example.rado.domain.user.presentation.dto.request.Duplicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DuplicateService {

    private final UserFacade userFacade;
    public boolean idDuplicate(Duplicate duplicate){
        return userFacade.existsByAccountId(duplicate.getAccountId());
    }
}
