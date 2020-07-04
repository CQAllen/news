package com.allenliu.news.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allenliu.news.domain.busi.NewsInfo;
import com.allenliu.news.domain.busi.page.NewsInfoPage;
import com.allenliu.news.framework.ApiResult;
import com.allenliu.news.service.busi.impl.NewsInfoServiceImpl;
import com.allenliu.news.service.es.ElasticSearchResult;
import com.allenliu.news.service.es.impl.ElasticSearchServiceImpl;
import com.github.pagehelper.PageInfo;
import io.searchbox.core.SearchResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Allen Liu
 * @Date: 2020/7/4 9:48 下午
 */
@RestController
@RequestMapping("news")
@Api(tags = "新闻")
public class NewsInfoController {
  @Autowired
  private NewsInfoServiceImpl newsInfoService;
  @Autowired
  private ElasticSearchServiceImpl elasticSearchService;

  @PostMapping("add")
  @ApiOperation("发布新闻")
  public ApiResult createNews(@RequestBody NewsInfo newsInfo){
    newsInfoService.save(newsInfo);
    JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(newsInfo));
    elasticSearchService.createIndex(jsonObject,"newsinfo","newsinfo",newsInfo.getId().toString());
    return ApiResult.buildSuccess();
  }

  @PostMapping("list")
  @ApiOperation("从es中查询新闻列表")
  public ApiResult list(@RequestBody(required = false) NewsInfoPage newsInfo){
    Map<String,String> map = new HashMap<>(2);
    map.put("newsTitle",newsInfo.getNewsTitle());
    map.put("newsContent",newsInfo.getNewsContent());
    ElasticSearchResult result = elasticSearchService.createSearch(map, "newsinfo", new NewsInfo(), newsInfo.getPageSize(), newsInfo.getPageSize()*(newsInfo.getPageNum()-1), "newsTitle","newsContent");
    List<SearchResult.Hit<NewsInfo, Void>> list = result.getData();
    List<NewsInfo> newsInfos = new ArrayList<>();
    list.forEach(item -> newsInfos.add(item.source));
    return ApiResult.buildSuccess(newsInfos);
  }

}
