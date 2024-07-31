-- 插入假資料到 SeatingChart 表
INSERT INTO SeatingChart (FLOOR_NO, SEAT_NO) VALUES
                                                 ('1F', 1),
                                                 ('1F', 2),
                                                 ('1F', 3),
                                                 ('1F', 4),
                                                 ('2F', 1),
                                                 ('2F', 2),
                                                 ('2F', 3),
                                                 ('2F', 4),
                                                 ('3F', 1),
                                                 ('3F', 2),
                                                 ('3F', 3),
                                                 ('3F', 4),
                                                 ('4F', 1),
                                                 ('4F', 2),
                                                 ('4F', 3),
                                                 ('4F', 4);

-- 插入假資料到 Employee 表
INSERT INTO Employee (EMP_ID, NAME, EMAIL, FLOOR_SEAT_SEQ) VALUES
                                                               ('E0001', 'John Doe', 'john.doe@example.com', 1),
                                                               ('E0002', 'Jane Smith', 'jane.smith@example.com', 2),
                                                               ('E0003', 'Alice Johnson', 'alice.johnson@example.com', 3),
                                                               ('E0004', 'Bob Brown', 'bob.brown@example.com', 4),
                                                               ('E0005', 'Charlie Davis', 'charlie.davis@example.com', 5),
                                                               ('E0006', 'David Wilson', 'david.wilson@example.com', 6),
                                                               ('E0007', 'Eva White', 'eva.white@example.com', 7),
                                                               ('E0008', 'Frank Taylor', 'frank.taylor@example.com', 8),
                                                               ('E0009', 'Grace Harris', 'grace.harris@example.com', 9),
                                                               ('E0010', 'Henry Walker', 'henry.walker@example.com', 10);
