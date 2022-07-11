# Menu

OS - AWS EC2 AML2

ssh key ppk 로 생성하여 putty로 연결.

1. DOCKER 설치

 - sudo amazon-linux-extras install docker
 - sudo service docker start
 - sudo usermod -a -G docker ec2-user
 - sudo curl \
-L "https://github.com/docker/compose/releases/download/1.26.2/docker-compose-$(uname -s)-$(uname -m)" \
-o /usr/local/bin/docker-compose

 2. Dockerfile 작성

 3. Github Action - Java with Maven 생성

  - on:
        push:
            branches: [ "main" ] : MAIN 브랜치가 PUSH 될 때마다 수행

  - name: Docker Build  메이븐 빌드 후 도커에 올림 
  - name: Deploy        올린거 받아와서 컨테이너 실행

 4. actions - setting - Secrets 에 actions 변수 저장

 5. H2 DB 컨테이너에 설치

  - docker run -d -p 1521:1521 -p 8081:81 -v /mnt/c/User/directory/h2:/opt/h2-data -e H2_OPTIONS="-ifNotExists" --name=h2 oscarfonts/h2

    설치한 h2의 컨테이너 접속
    - docker exec -it h2 /bin/bash
    h2 실행
    - cd /opt/h2/bin
    - java -cp h2-1.4.199.jar org.h2.tools.Shell
    사용할 테이블 생성
    - CREATE TABLE MENU(ID INT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(255), PARENT_ID INT, PARENT_NAME VARCHAR(255), PATH VARCHAR(255), LEVEL INT, INSERT_DATE VARCHAR(14), UPDATE_DATE VARCHAR(14));
    컬럼 설명
    - ID : 메뉴 ID
    - NAME : 메뉴 이름
    - PARENT_ID : 상위 메뉴의 ID (최상위 메뉴의 경우 빈값)
    - PARENT_NAME : 상위 메뉴의 이름 (최상위 메뉴의 경우 빈값)
    - PATH : 메뉴 클릭시 표출할 경로 (최상위 메뉴의 경우 배너 이미지 경로)
    - LEVEL : 메뉴의 깊이 레벨 (최상위 메뉴는 1)
    - INSERT_DATE : 등록일시
    - UPDATE_DATE : 수정일시

 6. SPRING BOOT 프로젝트의 JDBC URL
    - H2 컨테이너의 IP로 변경

 7. MAIN BRANCH PUSH 하여 배포

 8. 작성해둔 MenuAPI.yml 사용하여 api 테스트

