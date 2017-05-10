set MySQL_HOME=E:\mysql5.7
set PATH=%MySQL_HOME%\bin;%PATH%
@echo off
for %%i in (./*.sql) do (
   echo excute %%i
    "C:\Java\mysql5.5\mysql-5.7.11-winx64\bin\mysql" --host=localhost --port=3306 --user=root --password=admin --default-character-set=utf8 jbp_dev < %%i
)
echo success

pause