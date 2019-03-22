package com.ws.controller;

import com.alibaba.fastjson.JSONObject;
import com.ws.bean.EsModel;
import com.ws.bean.Kecheng;
import com.ws.bean.Ketype;
import com.ws.service.KechengService;
import com.ws.utils.ElasticsearchUtil;
import com.ws.utils.FileUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class KechengController {

    @Autowired
    private KechengService kechengService;

    /**
     * 测试索引
     */
    private String indexName = "test_index";



    /**
     * 类型
     */
    private String esType = "external";

    /**
     * 插入记录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/insertKecheng")
    public String insertModel() {

        List<Kecheng> kecheng=  kechengService.querykechen4();

        for(int i = 0 ; i < kecheng.size() ; i++) {

            System.out.println(kecheng.get(i).getId());
            EsModel esModel = new EsModel();
            esModel.setId(kecheng.get(i).getId().toString());
            esModel.setKname(kecheng.get(i).getKname());
            esModel.setKss(kecheng.get(i).getKss());
            esModel.setLls(kecheng.get(i).getLls());
            esModel.setLteacher(kecheng.get(i).getLteacher());
            esModel.setOktime(kecheng.get(i).getOktime());
            esModel.setKtype(kecheng.get(i).getKtype());
            esModel.setKdesc(kecheng.get(i).getKdesc());
            esModel.setKurl(kecheng.get(i).getKurl());
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(esModel);
            String id = ElasticsearchUtil.addData(jsonObject, indexName, esType, jsonObject.getString("id"));

        }

        return "es添加成功";
    }

    /**
     * 通配符查询数据
     * 通配符查询 ?用来匹配1个任意字符，*用来匹配零个或者多个字符
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryWildcardData")
    public List<Map<String, Object>> queryWildcardData() {
        QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("lteacher.keyword", "未来老师");
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, esType, queryBuilder, 10, null, null, null);
        return list;
    }


    /**
     * 查询数据
     * 模糊查询
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryMatchData")
    public List<Map<String, Object>> queryMatchData(String name) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolean matchPhrase = false;
        if (matchPhrase == Boolean.TRUE) {
            //不进行分词搜索
            boolQuery.must(QueryBuilders.matchPhraseQuery("kname", "n"));
        } else {
            boolQuery.must(QueryBuilders.matchQuery("kname", name));
        }
        List<Map<String, Object>> list = ElasticsearchUtil.
                searchListData(indexName, esType, boolQuery, 10, null, null, "kname");


        return list;
    }



    //查询用户表
    @RequestMapping("querykechen2")
    @ResponseBody
    public HashMap<String,Object> queryProblem(int page, int rows, Kecheng kecheng){
        HashMap<String,Object> list = kechengService.querykechen(page,rows, kecheng);

        return list;
    }



    //查询审核后的
    @RequestMapping("querykecheneeee")
    @ResponseBody
    public  List<Kecheng> querykechen3(String ty,String type,String kname,String lteacher){


            return kechengService.querykechen3(ty,type,kname,lteacher);

    }

    //查询审核后的
    @RequestMapping("querykecheneee")
    @ResponseBody
    public  List<Kecheng> querykechen4(){
        return kechengService.querykechen4();

    }

    //查询审核后的
    @RequestMapping("querykechenfen")
    @ResponseBody
    public  List<Kecheng> querykechen6(int page,int rows){
        return kechengService.querykechen6(page,rows);

    }

    //查询审核前的
    @RequestMapping("querykechenId")
    @ResponseBody
    public List<Kecheng> querykechenId(){

        return kechengService.querykechenId();
    }

    //审核通过
    @RequestMapping("updatekechenId")
    @ResponseBody
    public String  updatekechenId(Integer id){
        kechengService.updatekechenId(id);
        return "审核通过";
    }

    //批量删除
    @RequestMapping("deleteKechenAll")
    @ResponseBody
    public  void  deleteAll(String id){

        kechengService.deleteAll(id);
    }

    //修改回显
   @RequestMapping("queryKechengById")
    @ResponseBody
    public Kecheng queryKechengById(Integer id){

       Kecheng kecheng   = kechengService.queryKechengById(id);
        return kecheng;
    }



    //上传图片
    @RequestMapping("uploadImg2")
    @ResponseBody
    public String upload(HttpServletRequest request, @RequestParam(value="picFile",required=false) MultipartFile picFile) {
        String path = null;
        try {
            path = FileUtil.FileUpload(picFile, request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(path.contains("null")) {
            return null;
        }else {
            System.out.println(path);
            return path;

        }
    }



    //修改 ： 新增
    @RequestMapping("addKechen")
    @ResponseBody
    public void  addAnswer(Kecheng kecheng){

        Integer id = kecheng.getId();
        if (id!=null){
            //修改

            kechengService.updateKecheng(kecheng);
        }else {
            //新增

            kecheng.setLls(0);
            kechengService.addKecheng(kecheng);
        }

    }




    //查询课程类型
    @RequestMapping("queryType1")
    @ResponseBody
    public List<Ketype> QuerykeType(){


        return kechengService.QuerykeType();
    }

    //新增课程类型
    @RequestMapping("addkeType1")
    @ResponseBody
    public void addkeType(Ketype ketype){
        kechengService.addkeType(ketype);
    }

}
