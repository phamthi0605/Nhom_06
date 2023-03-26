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
	id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
	full_name VARCHAR(200) NOT NULL,
	position ENUM('manager','employee'),
	age INT UNSIGNED,
	phone VARCHAR(200) UNIQUE,
	email VARCHAR(200) UNIQUE,
	salary FLOAT,
	person_Income_Tax FLOAT,
	hire_date DATETIME,
	end_date DATETIME,
	department_id INT,
	is_manager ENUM('1'), -- nhân viên này có phải là trưởng phòng hay không
	UNIQUE (department_id, is_manager),			-- set cặp 1 phòng ban có nhiều nhân viên và trong 1 phòng ban chỉ có 1 manager
	FOREIGN KEY (department_id) REFERENCES department(id) -- 1 phòng ban có nhiều nhân viên
);


INSERT INTO employee(full_name, position, age, phone, email, hire_date, end_date, department_id,is_manager)
VALUES
		('Pham Thi Thi', 'manager', 24, '1', 'thipt1@gmail.com','2023-09-22 16:47:08', NULL, 1, 1),
		( 'Nguyen Thi Hoa', 'employee', 26, '2', 'hoant123@gmail.com', '2022-09-22 16:47:08', NULL, 1, null),  
		('Tran Thi Nga', 'employee', 25, '3', 'ngatran12@gmail.com','2021-09-22 16:47:08', NULL, 2, null),
		('Tran Van Tien', 'manager', 34, '4', 'tiennv1@gmail.com', '2009-09-22 16:47:08',null ,2,1),
		( 'Nguyen Van Viet', 'manager', 23, '5', 'vietnt14@gmail.com', '2022-09-22 16:47:08', NULL, 3,1),
		( 'Nguyen Thi Trang', 'employee', 26, '6', 'trang@gmail.com', '2020-09-22 16:47:08', NULL, 3,null);
	


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
		