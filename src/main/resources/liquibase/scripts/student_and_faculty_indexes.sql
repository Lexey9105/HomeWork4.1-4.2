-- liquibase formatted sql

-- changeset lexey:students_name_index
create index students_name_index ON student (name);

-- changeset lexey:faculties_name_and_color_index
create index faculties_name_and_color_index ON faculty (name, color);