//package com.example.customsecurity;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.ldap.core.LdapTemplate;
//import org.springframework.ldap.core.support.LdapContextSource;
//
//@Configuration
//public class LdapConfiguration {
//
//    @Autowired
//    private Environment env;
//
//    @Bean
//    public LdapContextSource contextSource () {
//        LdapContextSource contextSource= new LdapContextSource();
//        contextSource.setUrl(env.getRequiredProperty("data-source.ldap1.urls"));
//        contextSource.setBase(env.getRequiredProperty("data-source.ldap1.base"));
//        contextSource.setUserDn(env.getRequiredProperty("data-source.ldap1.username"));
//        contextSource.setPassword(env.getRequiredProperty("data-source.ldap1.password"));
//        return contextSource;
//    }
//
//    @Bean
//    public LdapTemplate ldapTemplate() {
//        return new LdapTemplate(contextSource());
//    }
//}
