package com.capstoneproject.taskdata.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        check request header : OPTIONS-> skip filtering, header with bearer token : check the token : process the request
//        else throw exception
//        downcasting from servlet request to httpservletrequest
        HttpServletRequest request1 = (HttpServletRequest)request;
        HttpServletResponse response1=(HttpServletResponse) response;

        String authHeader =request1.getHeader("authorization");

        if("OPTIONS".equals(request1.getMethod())){
            response1.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(request1,response1);
        }
        else if(authHeader==null || !authHeader.startsWith("Bearer")){
        //if authheader not found / header found but token bearer type
        throw new ServletException("Missing or Invalid exception");
        }
//        authHeader found with proper bearer token
        String token = authHeader.substring(7); //Bearer abcdxyz ->abcdxyz
        Claims claims= Jwts.parser().setSigningKey("mysecurekey").parseClaimsJws(token).getBody();
        System.out.println("Claims in fiter : " +claims);
        request.setAttribute("claims",claims);
        chain.doFilter(request1,response);
    }

}
