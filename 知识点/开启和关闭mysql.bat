view plain
@echo off 
for /f "skip=3 tokens=4" %%i in ('sc query mysql') do set "zt=%%i" &goto :next 
 
:next 
if /i "%zt%"=="RUNNING" ( 
echo �Ѿ����ָ÷��������У������Ѿ��رշ��� 
net stop mysql 
) else ( 
echo �÷������ڴ���ֹͣ״̬���������ڿ������� 
net start mysql 
) 
exit 
pause 