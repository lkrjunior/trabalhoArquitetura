# Trabalho Arquitetura

## Sistema para upload de fotos - UpPhotos

[![Build Status](https://travis-ci.org/lkrjunior/trabalhoArquitetura.svg?branch=master)](https://travis-ci.org/lkrjunior/trabalhoArquitetura)

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
