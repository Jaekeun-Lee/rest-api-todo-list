# Todo List Rest API



### 사용 기술 
* Spring Boot 2.4.2
* Gradle 6.8  
* JPA
* H2 Database
* Junit5 + Mockito
* Intellij IDE

　

### 커밋메시지 가이드
#### [git-styleguide](https://udacity.github.io/git-styleguide/) - Udacity git commit message style guide

* feat: A new feature
* fix: A bug fix
* docs: Changes to documentation
* style: Formatting, missing semi colons, etc; no code change
* refactor: Refactoring production code
* test: Adding tests, refactoring test; no production code change
* chore: Updating build tasks, package manager configs, etc; no production code change

　

### 필요기능 :seedling:

```
1. todo 리스트 목록에 아이템을 추가  
2. todo 리스트 목록 중 특정 아이템을 조회  
3. todo 리스트 전체 목록을 조회  
4. todo 리스트 목록 중 특정 아이템을 수정  
5. todo 리스트 목록 중 특정 아이템을 삭제  
6. todo 리스트 전체 목록을 삭제  
```
 

　


　

## 프로젝트 실행 :bookmark:

**[Todo Application Front Page](https://todobackend.com/client/index.html?http://localhost:8080/)** 로 접속하여 Todo Application을 사용할 수 있습니다.  
추가된 데이터는 **[H2 Database Console](http://localhost:8080/h2-console)** 에서 확인할 수 있습니다.  
프론트 서버를 활용한 테스트는 **[Test Code Run](https://todobackend.com/specs/index.html?http://localhost:8080/)** 을 통해 데이터 CRUD 테스트를 진행할 수 있습니다.



### 1. CLI 실행


1. 원하는 폴더에 클론 후 이동
```
git clone https://github.com/Jaekeun-Lee/rest-api-todo-list.git
cd rest-api-todo-list
```
2. 스프링 부트 프로젝트 실행
```
.\gradlew bootRun
```



### 2. IDE 실행


* [H2 database 설치](https://www.h2database.com/html/download.html)

* intellij lombok 플러그인 설치  
  `Preferences` -> `Plugins` -> `Browse repositories...` -> `search lombok` -> `Install "IntelliJ Lombok plugin"`

* Enable annotation processing  
  `Preferences` -> `Annotation Procesors` -> `Enable annotation processing check`  



　

　


## APIs

|Method|Path|Description|
|------|---|-----|
|Post|/|Todo 추가|
|Get|/|Todo 리스트 조회|
|Get|/{:id}|Todo 조회|
|Patch|/{:id}|Todo 수정|
|Delete|/|Todo 전체 삭제|
|Delete|/{:id}|Todo 삭제|
