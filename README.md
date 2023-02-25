## 导出配置文件

```shell
docker run --rm -it cassandra:4.1.0  cat /etc/cassandra/cassandra.yaml > ~/cassandra.yaml
```



## 创建容器

```shell
docker run --name cassandra410 -d \
  -p 9042:9042 \
  -v ~/cassandra.yaml:/etc/cassandra/cassandra.yaml \
  -v /data/docker_v/cassandra:/var/lib/cassandra \
  --restart always\
  cassandra:4.1.0
```

## 修改配置文件 vim cassandra.yaml
```shell
# Enables SASI index creation on this node.^M
# SASI indexes are considered experimental and are not recommended for production use.^M
sasi_indexes_enabled: true
```

## 重启cassandra服务
```shell
docker restart cassandra410
```

## 创建keyspace 和表 和 索引
```shell
docker exec -it cassandra410 cqlsh

CREATE KEYSPACE spring_cassandra WITH replication = {'class' : 'SimpleStrategy', 'replication_factor' : 1};

use spring_cassandra;

CREATE TABLE tutorial(
   id timeuuid PRIMARY KEY,
   title text,
   description text,
   published boolean
);


CREATE CUSTOM INDEX idx_title ON spring_cassandra.tutorial (title) 
USING 'org.apache.cassandra.index.sasi.SASIIndex' 
WITH OPTIONS = {
'mode': 'CONTAINS', 
'analyzer_class': 'org.apache.cassandra.index.sasi.analyzer.NonTokenizingAnalyzer', 
'case_sensitive': 'false'};

```


## 测试
### 新增数据

```shell
curl --location --request POST 'localhost:8080/api/tutorials' \
--header 'Content-Type: application/json' \
--data-raw '{
    "title":"Spring Data Tut#5",
    "description":"Tut#5 Description"
}'
```

### 查询数据
```shell
curl --location --request GET 'localhost:8080/api/tutorials' | jsonpp
```



