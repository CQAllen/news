package com.allenliu.news.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allenliu.news.domain.busi.NewsInfo;
import com.allenliu.news.domain.busi.page.NewsInfoPage;
import com.allenliu.news.framework.ApiResult;
import com.allenliu.news.service.busi.impl.NewsInfoServiceImpl;
import com.allenliu.news.service.es.ElasticSearchResult;
import com.allenliu.news.service.es.impl.ElasticSearchServiceImpl;
import io.searchbox.core.SearchResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
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
    int count = newsInfoService.save(newsInfo);
    //成功新增新闻，刷新es
    if(count > 0){
      JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(newsInfo));
      elasticSearchService.createIndex(jsonObject,"newsinfo","newsinfo",newsInfo.getId().toString());
    }
    return ApiResult.buildSuccess();
  }

  @PostMapping("edit")
  @ApiOperation("编辑新闻")
  public ApiResult updateNews(@RequestBody NewsInfo newsInfo){
    int count = newsInfoService.update(newsInfo);
    //成功更新且更新不是删除新闻，刷新es
    if(count > 0 && newsInfo.getDeleted() == 0){
      JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(newsInfo));
      elasticSearchService.updateDocument(jsonObject,"newsinfo","newsinfo",String.valueOf(newsInfo.getId()));
    }
    return ApiResult.buildSuccess();
  }

  @PostMapping("remove")
  @ApiOperation("删除新闻")
  public ApiResult removeNews(@RequestBody NewsInfo newsInfo){
    int count = newsInfoService.deleteById(newsInfo.getId());
    if(count > 0){
      elasticSearchService.deleteDocument("newsinfo","newsinfo", String.valueOf(newsInfo.getId()));
    }
    return ApiResult.buildSuccess();
  }

  @PostMapping("list")
  @ApiOperation("从es中查询新闻列表")
  public ApiResult list(@RequestBody NewsInfoPage newsInfo){
    Map<String,String> map = new HashMap<>(2);
    String newsTitle = newsInfo.getNewsTitle();
    if(StringUtils.isNotEmpty(newsTitle)){
      map.put("newsTitle",newsTitle);
    }
    String newsContent = newsInfo.getNewsContent();
    if(StringUtils.isNotEmpty(newsContent)){
      map.put("newsContent",newsContent);
    }
    ElasticSearchResult result = elasticSearchService.createSearch(map, "newsinfo", new NewsInfo(), newsInfo.getPageSize(), newsInfo.getPageSize()*(newsInfo.getPageNum()-1), "newsTitle","newsContent");
    List<SearchResult.Hit<NewsInfo, Void>> list = result.getData();
    List<NewsInfo> newsInfos = new ArrayList<>();
    list.forEach(item -> newsInfos.add(item.source));
    return ApiResult.buildSuccess(newsInfos);
  }

}
