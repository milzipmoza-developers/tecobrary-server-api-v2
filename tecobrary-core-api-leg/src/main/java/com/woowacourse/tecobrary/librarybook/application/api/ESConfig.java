//package com.woowacourse.tecobrary.librarybook.application.api;
//
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
//@Configuration
//public class ESConfig {
//
//    private String host;
//    private int port;
//
//    @Autowired
//    public ESConfig(@Value("${elasticsearch.host}") final String host,
//                    @Value("${elasticsearch.port}") final int port) {
//        this.host = host;
//        this.port = port;
//    }
//
//    @Bean
//    public ESRestClient client() throws UnknownHostException {
//        HttpHost httpHost = new HttpHost(InetAddress.getByName(host), port);
//        RestClientBuilder restClientBuilder = RestClient.builder(httpHost);
//        return new ESRestClient(restClientBuilder);
//    }
//}
