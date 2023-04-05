package com.jmp.security.service.impl;

import com.jmp.security.entity.Secret;

public interface SecretService {

    Secret findAndDeleteByLink(String link);

    String saveNewSecret(String secretValue);
}
