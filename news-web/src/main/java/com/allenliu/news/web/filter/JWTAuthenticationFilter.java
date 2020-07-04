package com.allenliu.news.web.filter;

import com.allenliu.news.domain.mdm.MdmUser;
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

    // 从输入流中获取到登录的信息
    try {
      MdmUser loginUser = new ObjectMapper().readValue(request.getInputStream(), MdmUser.class);
      return authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              loginUser.getName(), loginUser.getPassword(), new ArrayList<>()));
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  // 成功验证后调用的方法
  // 如果验证成功，就生成token并返回
  @Override
  protected void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authResult)
      throws IOException, ServletException {

    MdmUser user = (MdmUser) authResult.getPrincipal();
    System.out.println("jwtUser:" + user.toString());
    String token = JwtTokenUtils.createToken(user.getName(), false);
    // 返回创建成功的token
    // 但是这里创建的token只是单纯的token
    // 按照jwt的规定，最后请求的格式应该是 `Bearer token`
    response.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token);
  }

  // 这是验证失败时候调用的方法
  @Override
  protected void unsuccessfulAuthentication(
      HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
      throws IOException, ServletException {
    response.getWriter().write("authentication failed, reason: " + failed.getMessage());
  }
}
