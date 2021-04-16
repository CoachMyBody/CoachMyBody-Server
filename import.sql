select * from exercise;
insert into exercise values(1, 'LOWER_BODY', 'YOGA', '인대 조심', '유연성 강화에 도움', '이미지', '코브라 자세');
insert into exercise values(2, 'FULL_BODY', 'WEIGHT', '허리 조심하세요', '근력 강화', '이미지2', '데드리프트');
insert into exercise values(3, 'FULL_BODY', 'CARDIO', '무릎 조심', '지구력 강화', '이미지3', '달리기');
insert into exercise values(4, 'UPPER_BODY', 'WEIGHT', '그립 조심', '가슴 근육 빵빵', '이미지4', '벤치프레스');
insert into exercise values(5, 'UPPER_BODY', 'WEIGHT', '무리 노노', '직각 어깨', '이미지5', '숄더프레스');

select * from body_part_sub;
insert into body_part_sub values(1, 'TRAP');
insert into body_part_sub values(2, 'SHOULDERS');
insert into body_part_sub values(3, 'CHEST');
insert into body_part_sub values(4, 'BACK');
insert into body_part_sub values(5, 'ABS');
insert into body_part_sub values(6, 'BICEPS');
insert into body_part_sub values(7, 'CALVES');


select * from exercise_to_body_part_sub;
insert into exercise_to_body_part_sub values(1, 1);
insert into exercise_to_body_part_sub values(1, 2);
insert into exercise_to_body_part_sub values(2, 1);
insert into exercise_to_body_part_sub values(2, 3);
insert into exercise_to_body_part_sub values(2, 4);
insert into exercise_to_body_part_sub values(2, 5);
insert into exercise_to_body_part_sub values(3, 3);
insert into exercise_to_body_part_sub values(3, 5);
insert into exercise_to_body_part_sub values(4, 1);
insert into exercise_to_body_part_sub values(4, 2);
insert into exercise_to_body_part_sub values(5, 4);
insert into exercise_to_body_part_sub values(5, 5);
insert into exercise_to_body_part_sub values(5, 6);
insert into exercise_to_body_part_sub values(5, 7);