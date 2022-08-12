insert into AUTHORITY (AUTHORITY_NAME) values('ROLE_USER');
insert into AUTHORITY (AUTHORITY_NAME) values('ROLE_ADMIN');

insert into users(user_id, department, position, name, email, phone, birth_Date, password, andnum) values(90000, 'department_admin', 'position_admin', 'admin', 'admin', '01000000000', '000000',  '$2a$10$NZBLHb8/v1GjrriNGK/wx.MV/d7Hm07ggjd2Wj1IBITA1Hjoz85Da', 'andNum0');
insert into users(user_id, department, position, name, email, phone, birth_Date, password, andnum) values(90001, '개발팀', '부장', '정재훈', 'jjh@naver.com', '01020770880', '19970816', '$2a$10$UhIJYsHKFsfSx9dGZL7thOnEzfAZcsJZkMOiKEWF1YNA5Xwx2BDjW', 'andNum1');
insert into users(user_id, department, position, name, email, phone, birth_Date, password, andnum) values(90002, '인사팀', '사장', '김은비', 'keb@naver.com', '01026207411', '20011208', '$2a$10$UhIJYsHKFsfSx9dGZL7thOnEzfAZcsJZkMOiKEWF1YNA5Xwx2BDjW', 'andNum2');
insert into users(user_id, department, position, name, email, phone, birth_Date, password, andnum) values(90003, '디자인팀', '사원', '김현빈', 'khb@naver.com', '0101111111165597556', '20012345', '$2a$10$NZBLHb8/v1GjrriNGK/wx.MV/d7Hm07ggjd2Wj1IBITA1Hjoz85Da', 'andNum3');

insert into USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values(90000, 'ROLE_ADMIN');
insert into USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values(90001, 'ROLE_USER');
insert into USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values(90002, 'ROLE_USER');
insert into USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values(90003, 'ROLE_USER');




insert into notice(id, title, date, day) values(1, 'COCONET 홈페이지 점검 안내(8월 12일)', 'KrCERT 및 보호나라 홈페이지 점검 작업을 수행합니다.'||CHAR(13)||CHAR(10)||CHAR(13)||CHAR(10)||'이로 인하여 KrCERT 및 보호나라 홈페이지 접속이 원활하지 않을 수 있고 일부 파일의 다운로드가 실패할 수 있으니 참고하여 주시기 바랍니다.'||CHAR(13)||CHAR(10)||CHAR(13)||CHAR(10)|| '* 일시 : 10월 3일(일) 22:00 ~ 24:00'||CHAR(13)||CHAR(10)||' ※ 점검시간은 상황에 따라 변동될 수 있습니다.'||CHAR(13)||CHAR(10)||CHAR(13)||CHAR(10)||'문의사항은 국번없이 118 로 전화주시기 바랍니다.'||CHAR(13)||CHAR(10)||CHAR(13)||CHAR(10)||'감사합니다.', to_char(sysdate, 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(2, '4차 추경 지원금 문자안내 관련 스미싱 주의 안내', '공지사항 내용', to_char(sysdate, 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(3, '과기정통부 제2차 정보보호산업 진흥계획 발표', '공지사항 내용', to_char(sysdate, 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(4, '2022년 침해사고 재발방지 기술지원 관련 개인정보 처리 위탁 안내', '공지사항 내용', to_char(sysdate, 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(5, 'IoT 취약점 점검 서비스 실시 안내', '공지사항 내용', to_char(sysdate, 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(6, '코로나19 비상상황에 따른 상담인력 감소 안내', '공지사항 내용', to_char(sysdate, 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(7, 'PC 원격점검 접수 점검 안내', '공지사항 내용', to_char(sysdate, 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(8, '화이트햇 투게더 버그바운티 대회 안내', '공지사항 내용', to_char(sysdate, 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(9, '비대면서비스 사업자 보안 지원 사업 참여 기업 모집', '공지사항 내용', to_char(sysdate, 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(10, '2022년 중소기업 대상 정보보안 서비스 안내', '공지사항 내용', to_char(sysdate, 'YYYY.MM.DD"("dy")"'));

insert into notice(id, title, date, day) values(11, 'IoT 취약점 점검 서비스 실시 안내', '안녕하세요, 네이버 윅스입니다.'||CHAR(13)||CHAR(10)||'여기부터 엔터를 했는데 적용이 될까?', to_char('2021.07.05(금)', 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(12, '3차 추경 지원금 문자안내 관련 스미싱 주의 안내', '공지사항 내용', to_char('2021.07.05(금)', 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(13, '과기정통부 제2차 정보보호산업 진흥계획 발표', '공지사항 내용', to_char('2021.07.05(금)', 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(14, '2021년 침해사고 재발방지 기술지원 관련 개인정보 처리 위탁 안내', '공지사항 내용', to_char('2021.07.05(금)', 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(15, 'COCONET 홈페이지 점검 안내(7월 5일)', '공지사항 내용', to_char('2021.07.05(금)', 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(16, '코로나19 비상상황에 따른 상담인력 감소 안내', '공지사항 내용', to_char('2021.07.05(금)', 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(17, 'PC 원격점검 접수 점검 안내', '공지사항 내용', to_char('2021.07.05(금)', 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(18, '화이트햇 투게더 버그바운티 대회 안내', '공지사항 내용', to_char('2021.07.05(금)', 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(19, '비대면서비스 사업자 보안 지원 사업 참여 기업 모집', '공지사항 내용', to_char('2021.07.05(금)', 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(20, '2021년 중소기업 대상 정보보안 서비스 안내', '공지사항 내용', to_char('2021.07.05(금)', 'YYYY.MM.DD"("dy")"'));

insert into notice(id, title, date, day) values(21, 'IoT 취약점 점검 서비스 실시 안내', '안녕하세요, 네이버 윅스입니다.'||CHAR(13)||CHAR(10)||'여기부터 엔터를 했는데 적용이 될까?', to_char('2020.06.26(금)', 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(22, '2차 추경 지원금 문자안내 관련 스미싱 주의 안내', '공지사항 내용', to_char('2020.06.26(금)', 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(23, '과기정통부 제2차 정보보호산업 진흥계획 발표', '공지사항 내용', to_char('2020.06.26(금)', 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(24, '2020년 침해사고 재발방지 기술지원 관련 개인정보 처리 위탁 안내', '공지사항 내용', to_char('2020.06.26(금)', 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(25, 'COCONET 홈페이지 점검 안내(6월 26일)', '공지사항 내용', to_char('2020.06.26(금)', 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(26, '코로나19 비상상황에 따른 상담인력 감소 안내', '공지사항 내용', to_char('2020.06.26(금)', 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(27, 'PC 원격점검 접수 점검 안내', '공지사항 내용', to_char('2020.06.26(금)', 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(28, '화이트햇 투게더 버그바운티 대회 안내', '공지사항 내용', to_char('2020.06.26(금)', 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(29, '비대면서비스 사업자 보안 지원 사업 참여 기업 모집', '공지사항 내용', to_char('2020.06.26(금)', 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(30, '2020년 중소기업 대상 정보보안 서비스 안내', '공지사항 내용', to_char('2020.06.26(금)', 'YYYY.MM.DD"("dy")"'));

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

insert into todo_data(num, user_Num, user_Name, todo, todo_Check) values(1, 90003, '김현빈', '1회차 회의', 'false');
insert into todo_data(num, user_Num, user_Name, todo, todo_Check) values(2, 90003, '김현빈', '1차 JWT 토근 기능 구현', 'false');
insert into todo_data(num, user_Num, user_Name, todo, todo_Check) values(3, 90003, '김현빈', '1차 메인페이지 작업', 'false');
insert into todo_data(num, user_Num, user_Name, todo, todo_Check) values(4, 90003, '김현빈', '1차 코드 리펙토링', 'false');
insert into todo_data(num, user_Num, user_Name, todo, todo_Check) values(5, 90002, '김은비', '2회차 회의', 'false');
insert into todo_data(num, user_Num, user_Name, todo, todo_Check) values(6, 90002, '김은비', '2차 JWT 토근 기능 구현', 'false');
insert into todo_data(num, user_Num, user_Name, todo, todo_Check) values(7, 90002, '김은비', '2차 메인페이지 작업', 'false');
insert into todo_data(num, user_Num, user_Name, todo, todo_Check) values(8, 90002, '김은비', '2차 코드 리펙토링', 'false');
insert into todo_data(num, user_Num, user_Name, todo, todo_Check) values(9, 90001, '정재훈', '3회차 회의', 'false');
insert into todo_data(num, user_Num, user_Name, todo, todo_Check) values(10, 90001, '정재훈', '3차 JWT 토근 기능 구현', 'false');
insert into todo_data(num, user_Num, user_Name, todo, todo_Check) values(11, 90001, '정재훈', '3차메인페이지 작업', 'false');
insert into todo_data(num, user_Num, user_Name, todo, todo_Check) values(12, 90001, '정재훈', '3차코드 리펙토링', 'false');

insert into department(department_id, department) values(101, '인사팀');
insert into department(department_id, department) values(102, '회계팀');
insert into department(department_id, department) values(103, '영업팀');
insert into department(department_id, department) values(104, '개발팀');
insert into department(department_id, department) values(105, '디자인팀');

insert into position(position_id, department_id, position) values(201, 101, '사장');
insert into position(position_id, department_id, position) values(202, 101, '부장');
insert into position(position_id, department_id, position) values(203, 101, '대리');
insert into position(position_id, department_id, position) values(204, 101, '사원');
insert into position(position_id, department_id, position) values(205, 101, '인턴');

insert into position(position_id, department_id, position) values(206, 102, '사장');
insert into position(position_id, department_id, position) values(207, 102, '부장');
insert into position(position_id, department_id, position) values(208, 102, '대리');
insert into position(position_id, department_id, position) values(209, 102, '사원');
insert into position(position_id, department_id, position) values(210, 102, '인턴');

insert into position(position_id, department_id, position) values(211, 103, '사장');
insert into position(position_id, department_id, position) values(212, 103, '부장');
insert into position(position_id, department_id, position) values(213, 103, '대리');
insert into position(position_id, department_id, position) values(214, 103, '사원');
insert into position(position_id, department_id, position) values(215, 103, '인턴');

insert into position(position_id, department_id, position) values(216, 104, '사장');
insert into position(position_id, department_id, position) values(217, 104, '부장');
insert into position(position_id, department_id, position) values(218, 104, '대리');
insert into position(position_id, department_id, position) values(219, 104, '사원');
insert into position(position_id, department_id, position) values(220, 104, '인턴');

insert into position(position_id, department_id, position) values(221, 105, '사장');
insert into position(position_id, department_id, position) values(222, 105, '부장');
insert into position(position_id, department_id, position) values(223, 105, '대리');
insert into position(position_id, department_id, position) values(224, 105, '사원');
insert into position(position_id, department_id, position) values(225, 105, '인턴');

insert into user_status(user_id, status) values(90000, 15);
insert into user_status(user_id, status) values(90001, 15);
insert into user_status(user_id, status) values(90002, 15);
insert into user_status(user_id, status) values(90003, 15);

insert into Admin_Work_Time(id, title, value, state) values(1, '근무일', '월-금', 'false');
insert into Admin_Work_Time(id, title, value, state) values(2, '출근시간', '09:00', 'false');
insert into Admin_Work_Time(id, title, value, state) values(3, '점심시간', '12:00 - 13:00', 'false');
insert into Admin_Work_Time(id, title, value, state) values(4, '퇴근시간', '05:00', 'false');
insert into Admin_Work_Time(id, title, value, state) values(5, '심야 퇴근시간', '09:00', 'false');

insert into image_data(num, file_extension, file_name, file_path, file_size) values(90001, '.png', '90001_정재훈', 'D:\Users\JJH\Desktop\Coconet\Coconet-main\Coconet-main\src\main\resources\images\', 166261);
insert into image_data(num, file_extension, file_name, file_path, file_size) values(90002, '.png', '90002_김은비', 'D:\Users\JJH\Desktop\Coconet\Coconet-main\Coconet-main\src\main\resources\images\', 250136);
insert into image_data(num, file_extension, file_name, file_path, file_size) values(90003, '.jpg', '90003_김현빈', 'D:\Users\JJH\Desktop\Coconet\Coconet-main\Coconet-main\src\main\resources\images\', 31742);
