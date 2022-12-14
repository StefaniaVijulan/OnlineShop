package com.example.onlineshop.security.config;

import com.example.onlineshop.security.config.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public WebSecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/register",
                        "/login", "/designer/registerDesigner",
                        "/designer/loginDesigner","/designer/all",
                        "/product/all","/product/save","/product/oneProduct/{id}",
                        "/product/deleteProduct/{id}", "/shopCart/addProductInCart",
                        "/shopCart/checkProductInShopCart/{productId}/{clientId}",
                        "/shopCart/allProducts/{clientId}", "/shopCart/deleteProductInCart/{id}",
                        "/shopCart/updateQuantity/{newQuantity}",
                        "/shopCart/checkIfProductIsSaved/{productId}/{clientId}",
                        "/shopCart/savedProducts/{clientId}","/comments/addComments",
                        "/comments/getAllComments/{productId}","/comments/updateComment/{newComment}",
                        "/comments/deleteComment/{commentId}",
                        "/billingData/save","/orderProduct/save",
                        "/orderProduct/getProductsClient/{clientId}","/orderProduct/getProductsDesigner/{designerId}",
                        "/orderProduct/finalizeOrder/{finalized}").permitAll().anyRequest().authenticated()
                .and().cors().and().csrf().disable()
                .exceptionHandling()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
