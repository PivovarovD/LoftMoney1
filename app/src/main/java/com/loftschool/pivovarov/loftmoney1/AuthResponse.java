package com.loftschool.pivovarov.loftmoney1;

import com.google.gson.annotations.SerializedName;

class AuthResponse {

    private String status;

    private String id;

    @SerializedName("auth_token")
    private String authToken;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
