-- Create the Employee table
CREATE TABLE Employee (
    id INT IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    department VARCHAR(255) NOT NULL
);

-- Create the Task table
CREATE TABLE Task (
    id INT IDENTITY PRIMARY KEY, -- 'id' is the ID column, auto-generated
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    status VARCHAR(50) NOT NULL CHECK (status IN ('PENDING', 'IN_PROGRESS', 'COMPLETED')), -- ENUM equivalent
    assignedTo INT NULL, -- Foreign key to Employee, nullable
    FOREIGN KEY (assignedTo) REFERENCES Employee(id)
);


-- Insert 5 records into the Employee table
INSERT INTO Employee (name, email, department) VALUES ('Alice Smith', 'test1@example.com', 'Engineering');
INSERT INTO Employee (name, email, department) VALUES ('Bob Johnson', 'test2@gmail.com', 'Sales');
INSERT INTO Employee (name, email, department) VALUES ('Charlie Brown', 'test3@gmail.com', 'Engineering');
INSERT INTO Employee (name, email, department) VALUES ('David Williams', 'cams@example.com', 'Marketing');
INSERT INTO Employee (name, email, department) VALUES ('Eve Jones', 'test5@example.com', 'Sales');

-- Assuming the Employee IDs start from 1 and increment sequentially
INSERT INTO task (title, description, status)VALUES ('Complete Project Report', 'Finalize the Q3 report and submit to management.', 'PENDING');

