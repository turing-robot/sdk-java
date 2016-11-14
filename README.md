# sdk-java
> 图灵机器人SDK java版


## 下载
  * [jar](https://github.com/turing-robot/sdk-java/releases)
  
## 依赖包
  * maven项目
    * 见pom.xml
  * 普通项目
    * commons-lang-2.6
    * commons-codec-1.10
    * jackson-databind-2.8.4
## 使用说明
 * 示例代码
 
   ```java
        String apikey = "f0b3264bc2414769bbc96b0eae207e9e";//机器人apikey
        //不加密
        OpenapiConfig openapiConfig = new OpenapiConfig(apikey);
        //加密
        String secret = "22c5daa34395b3cb";//机器人加密密匙
        OpenapiConfig openapiConfig = new OpenapiConfig(apikey, secret);
        
        OpenapiService openapiService = new OpenapiServiceImpl(openapiConfig);
        //请求api
        Map<String, Object> result = openapiService.webapi("你好", "123213");
        //打印结果
        System.out.println(JsonUtils.toJson(result));
   ```
