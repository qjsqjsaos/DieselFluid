package com.example.dieselfluid.viewmodel.repository.api.data;

import com.example.dieselfluid.viewmodel.repository.api.data.securitydefinitions.ApiKeyAuth;
import com.example.dieselfluid.viewmodel.repository.api.data.securitydefinitions.ApiKeyAuth2;

public class SecurityDefinitions {
    private ApiKeyAuth2 ApiKeyAuth2;

    private ApiKeyAuth ApiKeyAuth;

    public ApiKeyAuth2 getApiKeyAuth2() {
        return ApiKeyAuth2;
    }

    public void setApiKeyAuth2(ApiKeyAuth2 apiKeyAuth2) {
        this.ApiKeyAuth2 = apiKeyAuth2;
    }

    public ApiKeyAuth getApiKeyAuth() {
        return ApiKeyAuth;
    }

    public void setApiKeyAuth(ApiKeyAuth apiKeyAuth) {
        this.ApiKeyAuth = apiKeyAuth;
    }

    @Override
    public String toString() {
        return "ClassPojo [ApiKeyAuth2 = " + ApiKeyAuth2 + ", ApiKeyAuth = " + ApiKeyAuth + "]";
    }
}

