package com.example.junglebook.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
