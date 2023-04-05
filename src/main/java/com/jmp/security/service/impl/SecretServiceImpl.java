package com.jmp.security.service.impl;

import com.jmp.security.entity.Secret;
import com.jmp.security.exception.LinkNotFoundException;
import com.jmp.security.repository.SecretRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class SecretServiceImpl implements SecretService {

    private final SecretRepository secretRepository;

    public SecretServiceImpl(SecretRepository secretRepository) {
        this.secretRepository = secretRepository;
    }

    @Override
    @Transactional
    public Secret findAndDeleteByLink(String link) {
        Secret secret = secretRepository.findByLink(link)
                .orElseThrow(() -> new LinkNotFoundException("Link is not found"));
        secretRepository.delete(secret);
        return secret;
    }

    @Override
    @Transactional
    public String saveNewSecret(String secretValue) {
        Secret secret = new Secret();
        secret.setValue(secretValue);
        String link = UUID.randomUUID().toString();
        secret.setLink(link);
        secretRepository.save(secret);
        return link;
    }
}
