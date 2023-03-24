CREATE DATABASE if NOT EXISTS	hr_management;
USE hr_management;
-- department table
CREATE TABLE department(
	id INT PRIMARY KEY AUTO_INCREMENT,
	department_name VARCHAR(200),
	address VARCHAR(200)
			
);

INSERT INTO department(department_name, address)
VALUES
		('HR', '1 floor'),
		('Sale', '2 floor'),
		('Accounting', '3 floor'),
		('Dev', '4 floor');

		

-- employee table
CREATE TABLE employee(
	id INT PRIMARY KEY AUTO_INCREMENT,
	employee_id VARCHAR(200) NOT NULL UNIQUE,
	full_name VARCHAR(200) NOT NULL,
	position VARCHAR(200),
	age INT UNSIGNED,
	phone VARCHAR(200) UNIQUE,
	email VARCHAR(200) UNIQUE,
	salary FLOAT,
	person_Income_Tax FLOAT,
	hire_date DATETIME,
	end_date DATETIME,
	department_id INT,
	is_manager ENUM('1'), -- nhân viên này có phải là trưởng phòng hay o
	UNIQUE (department_id, is_manager),
	FOREIGN KEY (department_id) REFERENCES department(id)
);


INSERT INTO employee(employee_id, full_name, position, age, phone, email, salary, hire_date, end_date)
VALUES
		('1', 'Pham Thi Thi', 'developer', 24, '1', 'thipt1@gmail.com', 10000000, '2023-09-22 16:47:08', NULL),
		('2', 'Nguyen Thi Hoa', 'Kế toán', 26, '2', 'hoant123@gmail.com',8500000, '2022-09-22 16:47:08', NULL),  
		('3', 'Tran Thi Nga', 'tester', 25, '3', 'ngatran12@gmail.com',9000000, '2021-09-22 16:47:08', NULL),
		('4', 'Tran Van Tien', 'Kế toán trưởng', 34, '4', 'tiennv1@gmail.com',19000000, '2009-09-22 16:47:08', null),
		('5', 'Nguyen Van Viet', 'Kế toán', 23, '5', 'vietnt14@gmail.com',8500000, '2022-09-22 16:47:08', NULL),
		('6', 'Nguyen Thi Trang', 'Kế toán', 26, '6', 'trang@gmail.com',8500000, '2020-09-22 16:47:08', NULL);
	


-- bảng admin
CREATE TABLE admin(
	id INT PRIMARY KEY AUTO_INCREMENT,
	full_name VARCHAR(200),
	username VARCHAR(200),
	password VARCHAR(200)		
);

INSERT INTO admin(full_name, username, password)
VALUES
	('phamthi', 'thipt1', '1234');
