# Kubernetes-mirror

We intend to store all Kubernetes data in a relational database (e.g., Postgres):

This code is based on JDK 17 and Maven 3.8.3

- **Flexibility**. It can support all Kubernetes-based systems without extra development, such as [Openshift](https://www.redhat.com/en/technologies/cloud-computing/openshift), [istio](https://istio.io/), etc.
- **Usability**. Developers can query what they needs using SQL.

This project is based on the following softwares.

|               NAME            |   Website                       |      LICENSE              | 
|-------------------------------|---------------------------------|---------------------------|
|     client-java               |  https://github.com/kubesys/client-java          |  Apache License 2.0 |
|     hibernate-core            |  https://github.com/hibernate                    |  Apache License 2.0 |
|     postgresql                |  https://www.postgresql.org/                     |  Apache License 2.0 |
|     rabbitmq                  |  https://www.rabbitmq.com/                       |  Apache License 2.0 |
      

## Architecture

## Installation

1. deploy Postgres

see [install](https://github.com/kubesys/installer)

```shell
bash kubeinst init-addon postgres
```

go to http://IP:30307, and the parameters are:

|      Key            |   Value                       |  
|---------------------|-------------------------------|
|     System          |   Postgres                   | 
|     Server          |   127.0.0.1:5432 or IP:30306   | 
|     Username        |   postgres                   | 
|     Password        |   onceas                     |

Login and create a database 'kubestack'


2. deploy rabbitmq

see [install](https://github.com/kubesys/installer)

```shell
bash kubeinst init-addon rabbitmq

go to http://IP:30305, and the parameters are:

|      Key            |   Value                       |  
|---------------------|-------------------------------| 
|     Username        |   guest                   | 
|     Password        |   guest                     |

Login and create a database 'kubestack'

3. deploy Mirror

replace yamls/kube-mirror.yaml variables, for example 

```
        env:
        - name: jdbcUrl
          value: jdbc:postgresql://kube-database.kubesystem:5432/kubestack (defaultValue)
        - name: jdbcUser
          value: postgres  (defaultValue)
        - name: jdbcPwd
          value: onceas  (defaultValue)
        - name: jdbcDriver
          value: org.postgresql.Driver  (defaultValue)
        - name: kubeUrl
          value: https://10.96.0.1:443  (defaultValue)
        - name: kubeToken
          value: see getToken in https://github.com/kubesys/client-java
        - name: kubeRegion
          value: test
        - name: mqUrl
          value: amqp://kube-message.kube-system:5672  (defaultValue)
        - name: mqUser
          value: guest  (defaultValue)
        - name: mqPwd
          value: guest  (defaultValue)
        
```

```shell
kubectl apply -f yamls/kube-mirror.yaml
```

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>io.github.kubesys</groupId>
  <artifactId>mirror</artifactId>
  <version>0.2.1</version> 
</dependency>

<repositories>
   <repository>
       <id>pdos-repos</id>
       <name>PDOS Releases</name>
       <url>http://139.9.165.93:31016/repository/maven-public/</url>
    </repository>
</repositories>
```


## Roadmap

- 0.1.x：support postgres
- 0.2.x：support rabbitmq
- 0.3.x：handle exceptions

## Others

- show max_connections
- select count(1) from pg_stat_activity

## Applications

- Pod <--> Workloads
  - SELECT data -> 'metadata' ->> 'ownerReferences' AS value FROM pods

- Pod <--> PVC
- SELECT data -> 'spec' ->> 'volumes' AS value FROM pods

- Pod <--> ConfigMap
  - 