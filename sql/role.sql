-- 需要在postgres数据库中执行
CREATE ROLE patient PASSWORD 'Gauss123' LOGIN;
CREATE ROLE doctor PASSWORD 'Gauss123' LOGIN;
CREATE ROLE nurse PASSWORD 'Gauss123' LOGIN;

-- TODO: 设置角色权限