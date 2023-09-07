select student_table.name, student_table.age, student_table.faculty_id, faculty_table.name, faculty_table.color
from student_table inner join faculty_table ON student_table.faculty_id = faculty.id

SELECT * FROM student_table
JOIN avatar ON student_table.id = avatar.student_id