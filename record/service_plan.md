# 1.0.0 가입, 로그인, 게시글 추가

## 페이지
- 입장 페이지 GET /
  - google login
  - naver login
  - kako login
- 로그인 페이지 GET /login/{provider}  
- 메인 페이지(글 목록 페이지)  GET /posts 
  - 작성 순으로 글 목록 출력
  - 네비게이션
    - 내가 쓴 글, 로그아웃 
  - 열
    - 제목, 작성자, 작성일, 수정일, 조회수
- 글 쓰기 페이지 POST /posts
  - 글만 쓸 수 있음
- 내가 쓴 글 목록 페이지 GET /posts/{userId}
  - 작성 순으로 글 목록 출력
    - 제목, 작성일, 수정일, 조회수
- 글 읽기 페이지
  - 작성자만 수정, 삭제 버튼 있음


## table 설계
- Post
  - id
  - title
  - content
  - createdTime
  - modifiedTime
- User
  - id
  - nickname
  - OneToMany postList

## api 설계

|기능|method|url|응답|
|----|----|----|----|
|입장|GET|/|index.html|
|로그인|GET|/login/{provider}|redirect:/posts?page=1|
|메인|GET|/posts?page=1|home.html|
|글 쓰기|GET|/post/{userId}|write-post.html|
|글 쓰기|POST|/post/{userId}|redirect:/post/{postId}|
|글 읽기|GET|/post/{postId}|read-post.html|
|글 수정|GET|/post/{postId}/edit|update-post.html|
|글 수정|PUT|/post/{postId}/edit|redirect:read-post.html|
|글 삭제|DELETE|/post/{postId}|redirect:/mypost/{userId}?page=1|
|내가 쓴 글|GET|/mypost/{userId}?page=1|myposts.html|


# 1.1.0 리액션 기능 추가
- 댓글, 대댓글 기능
  - 작성자만 수정, 삭제 가능
- 좋아요


# 1.2.0 검색 기능 추가
- 작성자 단위 검색
- 글 제목 단위 검색

# 1.3.0 사진 첨부 글쓰기 기능 추가
