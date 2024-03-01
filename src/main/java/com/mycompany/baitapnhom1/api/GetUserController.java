package com.mycompany.baitapnhom1.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.baitapnhom1.entity.UserEntity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GetUserController {

    private static final String URI = ResourceBundle.getBundle("database").getString("userApi");
    public static List<UserEntity> getUser() {
        try{
            URL url= new URI(URI).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json;charset=UTF-8");
            if(connection.getResponseCode()!=200){
                throw new IOException("An error occur when fetching users");
            }
            ObjectMapper objectMapper = new ObjectMapper();
            List<UserEntity> items = objectMapper.readValue(url,objectMapper.getTypeFactory().constructCollectionType(List.class, UserEntity.class));
            connection.disconnect();
            return items;
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException("An error occur when connecting to the server");
        }
    }
}
