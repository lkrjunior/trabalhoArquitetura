# Trabalho Arquitetura

## Sistema para upload de fotos - UpPhotos

[![Build Status](https://travis-ci.org/lkrjunior/trabalhoArquitetura.svg?branch=master)](https://travis-ci.org/lkrjunior/trabalhoArquitetura)
<!--![Heroku](https://heroku-badge.herokuapp.com/?app=upphotos-uniritter)-->
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=upphotos&metric=alert_status)](https://sonarcloud.io/dashboard?id=upphotos)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=upphotos&metric=ncloc)](https://sonarcloud.io/dashboard?id=upphotos)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=upphotos&metric=bugs)](https://sonarcloud.io/dashboard?id=upphotos)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=upphotos&metric=code_smells)](https://sonarcloud.io/dashboard?id=upphotos)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=upphotos&metric=coverage)](https://sonarcloud.io/dashboard?id=upphotos)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=upphotos&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=upphotos)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=upphotos&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=upphotos)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=upphotos&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=upphotos)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=upphotos&metric=security_rating)](https://sonarcloud.io/dashboard?id=upphotos)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=upphotos&metric=sqale_index)](https://sonarcloud.io/dashboard?id=upphotos)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=upphotos&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=upphotos)


#### Resumo

Sistema para upload de fotos para nuvem.<br>
Utiliza páginas HTML5 com bootstrap desenvolvidas em Thymeleaf.<br>
Banco de dados local H2 utilizando JpaRepository como interface de comunicação.<br>
Implementação do upload/download das fotos com o App Dropbox utilizando AccessToken.<br>
Continous Integration com o TravisCI e Deploy automatizado no Heroku.<br>
Monitoramento do site utilizando o Site24x7.<br>

**Recursos APIs**

- /Status
    - /GetVersion ```GET```
- /Person
    - / ```GET```
    - /Save ```POST```
    - /Delete/{id} ```DELETE```
    - /Edit/{id} ```GET```
- /Photo
    - /All/{id} ```GET```
    - /Save ```POST```
        
**Estrutura do projeto**

    BO - Camada de negócio
    Cloudstorage - Implementação da interface de comunicação para upload e download de arquivos na nuvem
    Controller - APIs REST
    Model - Modelagem dos dados
    Repository - Interfaces dos repositorios para comunicação com o banco de dados

**Dependências do projeto**
    
    SpringBoot Web
    SpringBoot Actuator
    SpringBoot DataJpa
    SpringBoot JDBC
    SpringBoot Thymeleaf
    SpringBoot Test
    H2Database
    Dropbox-core-jdk
    
**Monitoramento**

    Site24x7
    
**Continuous Deployment**
    
    TravisCI -> Heroku
    
**Diagramas**
    
* [Arquitetura](https://drive.google.com/file/d/1IhIeq6IcQQE3ersfGaXCiazv_3ee2YXm/view?usp=sharing)
* [Estrutura do projeto](https://drive.google.com/file/d/1ZiaCUibWYqGDFeAtTxXkTjSgxg375h1z/view?usp=sharing)