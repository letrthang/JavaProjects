package com.lecompany.app.security;

import com.lecompany.backend.data.entity.User;

@FunctionalInterface
public interface CurrentUser {

	User getUser();
}
