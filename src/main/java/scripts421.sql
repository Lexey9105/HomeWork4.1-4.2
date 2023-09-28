alter table student_table
add constraint age_check check (age >= 15);

alter table student_table
add constraint name_not_null check (name IS NOT NULL),
add constraint name_unique UNIQUE (name);

alter table faculty_table
add constraint color_name_unique UNIQUE (name, color);

alter table student_table
alter column age set default 20;