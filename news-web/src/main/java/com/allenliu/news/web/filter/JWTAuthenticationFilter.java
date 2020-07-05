package com.allenliu.news.web.filter;

import com.allenliu.news.domain.mdm.MdmUser;
import com.allenliu.news.domain.mdm.dto.JwtUser;
import com.allenliu.news.utils.JwtTokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
    super.setFilterProcessesUrl("/user/login");
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

    try {
      MdmUser loginUser = new ObjectMapper().readValue(request.getInputStream(), MdmUser.class);
      return authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>()));
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authResult)
      throws IOException, ServletException {

    JwtUser user = (JwtUser) authResult.getPrincipal();
    System.out.println("jwtUser:" + user.toString());
    String token = JwtTokenUtils.createToken(user.getUsername(), false);
    response.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token);
  }

  @Override
  protected void unsuccessfulAuthentication(
      HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
      throws IOException, ServletException {
    response.getWriter().write("authentication failed, reason: " + failed.getMessage());
  }
}
