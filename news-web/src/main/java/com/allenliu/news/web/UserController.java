package com.allenliu.news.web;

import com.allenliu.news.domain.mdm.MdmUser;
import com.allenliu.news.domain.mdm.page.MdmUserPage;
import com.allenliu.news.framework.ApiResult;
import com.allenliu.news.service.mdm.MdmUserService;
import com.allenliu.news.service.mdm.impl.MdmUserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(tags = "/user")
public class UserController {
  @Resource
  private MdmUserServiceImpl mdmUserService;

  @PostMapping(value = "findByPage")
  @ApiOperation("分页查询用户")
  public Object getUser(@RequestBody(required = false) MdmUserPage userPage) {
    return mdmUserService.findByPage(userPage);
  }
  @PostMapping("register")
  @ApiOperation("用户注册")
  public ApiResult add(@RequestBody MdmUser mdmUser){
    BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
    mdmUser.setPassword(bcryptPasswordEncoder.encode(mdmUser.getPassword()));
    mdmUserService.save(mdmUser);
    return ApiResult.buildSuccess();
  }
}
