/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourgoat.resources;

import java.io.IOException;
import java.util.Properties;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.velocity.VelocityEngineFactory;

/**
 *
 * @author weynishetzula
 */
@Configuration
public class VelocityEngineTemplate{
     @Bean
    public VelocityEngine getVelocityEngine() throws  IOException{
        VelocityEngineFactory factory = new VelocityEngineFactory();
        Properties props = new Properties();
        props.put("resource.loader", "class");
        props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        factory.setVelocityProperties(props);
        return factory.createVelocityEngine();      
    }
    
}
