insert into AUTHORITY (AUTHORITY_NAME) values('ROLE_USER');
insert into AUTHORITY (AUTHORITY_NAME) values('ROLE_ADMIN');

insert into users values(90001, 'andNum1', '970816', 'jjh@naver.com', '정재훈', '$2a$10$ujiRnEFVBvKpNPwjWtFWQu2CnaTQL3uZcnlwzKrzIGJICNBRI0c2q', '01012341234', 'team1', 'tier');
insert into users values(90002, 'andNum2', '011208', 'keb@naver.com', '김은비', '$2a$10$mBzomKLG/Hx2sOuRkToX2OrWNWhNbIn4lo..Y22Wl2W/W8t1puVjK', '01023452345', 'team2', 'tier');
insert into users values(90003, 'andNum3', '011208', 'khb@naver.com', '김현빈', '$2a$10$/n1LY2SrSTs22cI0cNoI7OgxsiOYMTUiGS/cpJ1nSEHAC8cjK7RUK', '01034563456', 'team3', 'tier');

insert into USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values(90001, 'ROLE_USER');
insert into USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values(90001, 'ROLE_ADMIN');
insert into USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values(90002, 'ROLE_USER');
insert into USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values(90003, 'ROLE_USER');

insert into notice(id, title, day) values(1, '공지사항 test1', to_char(sysdate, 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, day) values(2, '공지사항 test2', to_char(sysdate, 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, day) values(3, '공지사항 test3', to_char(sysdate, 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, day) values(4, '공지사항 test4', to_char(sysdate, 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, day) values(5, '공지사항 test5', to_char(sysdate, 'YYYY.MM.DD"("dy")"'));

insert into chart_data(num, title, value, color) values(1, '근무중', 60, '#2CB0D7');
insert into chart_data(num, title, value, color) values(2, '휴식', 7, '#F0D828');
insert into chart_data(num, title, value, color) values(3, '외근', 11, '#2C89DE');
insert into chart_data(num, title, value, color) values(4, '휴가', 4, '#5AB6B1');
insert into chart_data(num, title, value, color) values(5, '출장', 3, '#6571DC');
insert into chart_data(num, title, value, color) values(6, '출근전', 15, '#BFC8D2');

insert into approval_data(num, state, color, name, day,img) values(1, '휴가', '#5AB6B1', '정사원', to_char(sysdate, 'YYYY.MM.DD"("dy")"'), '프사 링크1');
insert into approval_data(num, state, color, name, day,img) values(2, '외근', '#2C89DE', '정사원', to_char(sysdate, 'YYYY.MM.DD"("dy")"'), '프사 링크2');
insert into approval_data(num, state, color, name, day,img) values(3, '출장', '#6571DC', '정사원', to_char(sysdate, 'YYYY.MM.DD"("dy")"'), '프사 링크3');


insert into log_data(num, state, color, name, position, time, img) values(1, '퇴근', '#86ca89', '김대리', '백엔드 개발', to_char(sysdate, 'PM HH:MI'), '프사 링크1');
insert into log_data(num, state, color, name, position, time, img) values(2, '퇴근', '#86ca89', '김대리', '백엔드 개발', to_char(sysdate, 'PM HH:MI'), '프사 링크1');
insert into log_data(num, state, color, name, position, time, img) values(3, '퇴근', '#86ca89', '김대리', '백엔드 개발', to_char(sysdate, 'PM HH:MI'), '프사 링크1');
insert into log_data(num, state, color, name, position, time, img) values(4, '퇴근', '#86ca89', '김대리', '백엔드 개발', to_char(sysdate, 'PM HH:MI'), '프사 링크1');
insert into log_data(num, state, color, name, position, time, img) values(5, '퇴근', '#86ca89', '김대리', '백엔드 개발', to_char(sysdate, 'PM HH:MI'), '프사 링크1');
insert into log_data(num, state, color, name, position, time, img) values(6, '퇴근', '#86ca89', '김대리', '백엔드 개발', to_char(sysdate, 'PM HH:MI'), '프사 링크1');
insert into log_data(num, state, color, name, position, time, img) values(7, '퇴근', '#86ca89', '김대리', '백엔드 개발', to_char(sysdate, 'PM HH:MI'), '프사 링크1');

insert into todo_data(num, name, todo) values(1, '김현빈', '3회차 회의');
insert into todo_data(num, name, todo) values(2, '김현빈', 'JWT 토근 기능 구현');
insert into todo_data(num, name, todo) values(3, '김현빈', '메인페이지 작업');
insert into todo_data(num, name, todo) values(4, '김현빈', '코드 리펙토링');






