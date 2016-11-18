# sdk-java
> 图灵机器人SDK java版


## 下载
  * [jar](https://github.com/turing-robot/sdk-java/releases)
  
## 依赖包
  * maven项目
  
    ```xml
     <dependency>
         <groupId>commons-lang</groupId>
         <artifactId>commons-lang</artifactId>
         <version>2.6</version>
     </dependency>
     <dependency>
         <groupId>commons-codec</groupId>
         <artifactId>commons-codec</artifactId>
         <version>1.10</version>
     </dependency>
     <dependency>
         <groupId>com.fasterxml.jackson.core</groupId>
         <artifactId>jackson-databind</artifactId>
         <version>2.8.4</version>
     </dependency>
    ```
  * 普通项目
    * commons-lang-2.6
    * commons-codec-1.10
    * jackson-databind-2.8.4
    
## 使用说明
 * 示例代码
    * 聊天接口
    
       ```java
            //-----------------初始化--------------------
            String apikey = "f0b3264bc2414769bbc96b0eae207e9e";//机器人apikey
            //不加密
            TuringRobotSetting turingRobotSetting = new TuringRobotSetting(apikey);
            //加密
            String secret = "22c5daa34395b3cb";//机器人加密密匙
            TuringRobotSetting turingRobotSetting = new TuringRobotSetting(apikey, secret);
            
            OpenapiService openapiService = new OpenapiServiceImpl(turingRobotSetting);
            
            //----------------调用-------------------
            //请求api
            String userid = "123456";
            String info = "你好";
            Map<String, Object> result = openapiService.webapi(info, userid);
            //打印结果
            System.out.println(JsonUtils.toJson(result));
       ```
    * 知识库
    
         ```java
            //-----------------初始化--------------------
            TuringRobotSetting turingRobotSetting = new TuringRobotSetting("74c15b68baae4f1282395f04c2f4c764");
            KnowledgeBaseSetting knowledgeBaseSetting = turingRobotSetting.getKnowledgeBaseSetting();//获取知识库配置对象
            knowledgeBaseSetting.setSecretKey(SECRET_KEY);//设置知识库加密KEY
            knowledgeBaseSetting.setSecretSwitch(true);//设置知识库加密开关
            KnowledgeBaseService knowledgeBaseService = new KnowledgeBaseServiceImpl(turingRobotSetting);//生成接口调用类
    
            //----------------调用-------------------
            Map result;
            result = knowledgeBaseService.setMatch(50);//设置知识库匹配度
            result = knowledgeBaseService.setSecretSwitch(true);//设置知识库加密开关
            result = knowledgeBaseService.setSecretKey("D1AS5D61");//设置知识库加密KEY
            result = knowledgeBaseService.setSecretSwitchAndSecretKey(true, "D1AS5D61");//设置知识库加密开关和加密KEY
            result = knowledgeBaseService.add(new KnowledgeBase("a", "b"));//知识库添加
            result = knowledgeBaseService.findByPageable(new Pageable());//知识库查找
            result = knowledgeBaseService.findByPageable(new Pageable());//知识库查找
            result = knowledgeBaseService.update(new KnowledgeBase("a", "b", "1"));//知识库修改
            result = knowledgeBaseService.delete("123");//知识库删除
            result = knowledgeBaseService.clear();//知识库清空
         ```
------------         
注意：文档写的烂大家凑合看吧。。。源码里有注释，搭配使用味道更佳