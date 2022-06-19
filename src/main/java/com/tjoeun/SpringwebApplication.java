package com.tjoeun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
public class SpringwebApplication {

   public static void main(String[] args) {
      SpringApplication.run(SpringwebApplication.class, args);
   }
   //@Profile({"dev", "test"})  //  배포시에는 주석제거
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}