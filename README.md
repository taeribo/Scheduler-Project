API 명세서: 일정 관리 앱
1. 일정 생성
API 엔드포인트 : POST / schedules
Request 
Request Body   
application/json
{
"title" : "일정관리 과제"
"content" : "필수 과제 구현하기"
"author" : "박태헌"
"password" : "1234"
}

Response (201 Created): 생성 성공
Response Body
application/json
{
"id" : 1,
"title" : "일정관리 과제"
"content" : "필수 과제 구현하기"
"author" : "박태헌"
"createdAt" : "2026-06-29T10:30:00"
"modifiedAt" : "2026-06-29T10:30:00"
}
설명 
- 새로운 일정을 생성합니다.
- 작성일과 수정일을 설정합니다.
- 비밀번호는 응답에서 제외됩니다.

2. 일정 조회
API 엔드포인트 : GET /schedules?author=박태헌
Request Body
없음

Responses (200 OK) : 조회 성공
Response Body 
application/json
[
{
"id":1,
"title" : "일정관리 과제"
"content" : "필수 과제 구현하기"
"author" : "박태헌"
"createdAt" : "2026-06-29T10:30:00"
"modifiedAt" : "2026-06-29T10:30:00"
},
{
"id":2,
"title" : "TIL 관리"
"content" : "블로그 카테고리 정리하기"
"author" : "박태헌"
"createdAt" : "2026-06-29T11:30:00"
"modifiedAt" : "2026-06-29T11:30:00"
}
]
설명
- 작성자명 기준으로 등록된 일정을 전부 조회합니다.
- 수정일 기준 내림차순 정렬합니다.
- 비밀번호는 응답에서 제외됩니다.

3. 선택 일정 조회
API 엔드포인트 : GET /schedules/{id}
Request Body
없음
Responses (200 OK) : 조회 성공
Response Body
application/json
{
"id":1,
"title" : "일정관리 과제"
"content" : "필수 과제 구현하기"
"author" : "박태헌"
"createdAt" : "2026-06-29T10:30:00"
"modifiedAt" : "2026-06-29T10:30:00"
},
설명
- id에 해당하는 일정 하나를 조회합니다.
- 비밀번호는 응답에서 제외됩니다.

4.일정 수정
API 엔드포인트 : PUT /schedules/{id}
Request Body
application/json
{
"title" : "일정관리 과제 최종"
"author": "박태헌"
"password" : "1234"
}
Response (200 OK) : 수정 성공
application/json
{
"id" : "일정관리 과제 최종"
"content": "필수 과제 구현하기"
"createdAt" : "2026-06-29T11:30:00"
"modifiedAt" : "2026-06-29T14:30:00"
}
설명
- 제목과 작성자명만 수정가능
- 비밀번호가 일치해야만 수정 가능
- 창작일(createdAt)은 변경되지 않음
- 수정일(modifiedAt)은 수정 시점으로 변경되어야함.
- 비밀번호는 응답에서 제외됩니다.


5.일정 삭제
API 엔드포인트 : DELETE /schedules/{id}
Request Body
application/json
{
"password" : "1234"
}
Response (200 OK) : 삭제 성공
application/json
{
"message" : "일정이 삭제되었습니다."
}
설명
- id에 해당하는 일정을 삭제
- 비밀번호가 일치해야만 삭제 가능

ERD
Schedule 테이블

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 일정 고유 식별자 |
| title | VARCHAR(30) | NOT NULL | 일정 제목 |
| content | VARCHAR(200) | NOT NULL | 일정 내용 |
| author | VARCHAR(50) | NOT NULL | 작성자명 |
| password | VARCHAR(50) | NOT NULL | 비밀번호 |
| createdAt | DATETIME | NOT NULL | 작성일 (JPA Auditing) |
| modifiedAt | DATETIME | NOT NULL | 수정일 (JPA Auditing) |