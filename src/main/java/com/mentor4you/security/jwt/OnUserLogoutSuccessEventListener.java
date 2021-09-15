package com.mentor4you.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OnUserLogoutSuccessEventListener implements ApplicationListener<OnUserLogoutSuccessEvent> {

    @Autowired
    private final TokenCache tokenCache;

    public OnUserLogoutSuccessEventListener(TokenCache tokenCache) {
        this.tokenCache = tokenCache;
    }

    @Override
    public void onApplicationEvent(OnUserLogoutSuccessEvent event) {
        if(null!=event){
            tokenCache.markLogoutEventForToken(event);
        }
    }
}
