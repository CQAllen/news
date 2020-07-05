package com.allenliu.news.web;

import com.allenliu.news.domain.mdm.MdmUser;
import com.allenliu.news.domain.mdm.page.MdmUserPage;
import com.allenliu.news.framework.ApiResult;
import com.allenliu.news.service.mdm.impl.MdmUserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Allen Liu
 * @Date: 2020/7/4 5:33 下午
 */
@RestController
@RequestMapping("user")
@Api(tags = "用户")
public class UserController {
  @Resource
  private MdmUserServiceImpl mdmUserService;
  @Resource
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @PostMapping(value = "findByPage")
  @ApiOperation("分页查询用户")
  public Object getUser(@RequestBody MdmUserPage userPage) {
    return mdmUserService.findByPage(userPage);
  }

  @PostMapping("register")
  @ApiOperation("用户注册")
  public ApiResult add(@RequestBody MdmUser mdmUser){
    String password = bCryptPasswordEncoder.encode(mdmUser.getPassword());
    bCryptPasswordEncoder.matches(mdmUser.getPassword(),password);
    mdmUser.setPassword(password);
    mdmUserService.save(mdmUser);
    return ApiResult.buildSuccess();
  }

  @PostMapping(value = "login")
  @ApiOperation("登陆")
  public ApiResult getUser(@RequestBody MdmUser mdmUser) {
    return ApiResult.buildSuccess();
  }

}
