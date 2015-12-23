package com.spring.el;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SpringDataJpaEclipselinkApplication extends JpaBaseConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaEclipselinkApplication.class, args);
    }

    @Override
    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        return new EclipseLinkJpaVendorAdapter();
    }

    @Override
    protected Map<String, Object> getVendorProperties() {
        return new HashMap<>(0);
    }
}
