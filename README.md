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
 
   ```java
        String apikey = "f0b3264bc2414769bbc96b0eae207e9e";//机器人apikey
        //不加密
        OpenapiConfig openapiConfig = new OpenapiConfig(apikey);
        //加密
        String secret = "22c5daa34395b3cb";//机器人加密密匙
        OpenapiConfig openapiConfig = new OpenapiConfig(apikey, secret);
        
        OpenapiService openapiService = new OpenapiServiceImpl(openapiConfig);
        //请求api
        String userid = "123456";
        String info = "你好";
        Map<String, Object> result = openapiService.webapi(info, userid);
        //打印结果
        System.out.println(JsonUtils.toJson(result));
   ```
