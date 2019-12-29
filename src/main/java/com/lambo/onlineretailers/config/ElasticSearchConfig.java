package com.lambo.onlineretailers.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * @author lambo
 */
@Configuration
public class ElasticSearchConfig {

//    @Bean
//    public TransportClient client(){
//        InetSocketAddress node =new InetSocketAddress(
//                InetSocketAddress.createUnresolved("",9300);
//        )
//        TransportAddress transportAddress =new TransportAddress(InetSocketAddress.createUnresolved(
//                "127.0.0.1",9300
//        ));
//
//        Settings settings =Settings.builder()
//                .build();
//        TransportClient client = new PreBuiltTransportClient(settings);
//        client.addTransportAddress(transportAddress);
//
//        return client;
//    }
//    @Bean
//    public ElasticsearchTemplate elasticsearchTemplate(TransportClient client){
//        ElasticsearchTemplate elasticsearchTemplate =new ElasticsearchTemplate(client);
//        return elasticsearchTemplate;
//    }
}
