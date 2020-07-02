package com.allenliu.news.web;

// import common.entity.RestfulResult;
// import common.utils.CommUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// import sun.entity.ServiceInfo;


@RestController // 重要，如果用Controller会404
@RequestMapping(value = "service")
@Api(
    value = "ServiceController",
    tags = {"测试"})
public class ServiceController {
//  @Value("${news.test}")
//  private String test;
  //    @RequestMapping(value = "hello")
  //    public void login(HttpServletRequest request, HttpServletResponse response,
  //                      @RequestBody ServiceInfo serviceInfo) {
  //
  //        RestfulResult restfulResult = new RestfulResult();
  //
  //        try {
  //            restfulResult.setData("Service1:Welcome " + serviceInfo.getName() + "!");
  //
  //        } catch (Exception e) {
  //            e.printStackTrace();
  //        }
  //
  //        CommUtils.printDataJason(response, restfulResult);
  //    }

  @RequestMapping(value = "rest")
  @ApiOperation("rest")
  public String rest() {
    return "Service1:Welcome !" ;
  }
}
