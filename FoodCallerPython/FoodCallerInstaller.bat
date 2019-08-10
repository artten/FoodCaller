@echo off

:: BatchGotAdmin
:-------------------------------------
REM  --> Check for permissions
    IF "%PROCESSOR_ARCHITECTURE%" EQU "amd64" (
>nul 2>&1 "%SYSTEMROOT%\SysWOW64\cacls.exe" "%SYSTEMROOT%\SysWOW64\config\system"
) ELSE (
>nul 2>&1 "%SYSTEMROOT%\system32\cacls.exe" "%SYSTEMROOT%\system32\config\system"
)

REM --> If error flag set, we do not have admin.
if '%errorlevel%' NEQ '0' (
    echo Requesting administrative privileges...
    goto UACPrompt
) else ( goto gotAdmin )

:UACPrompt
    echo Set UAC = CreateObject^("Shell.Application"^) > "%temp%\getadmin.vbs"
    set params= %*
    echo UAC.ShellExecute "cmd.exe", "/c ""%~s0"" %params:"=""%", "", "runas", 1 >> "%temp%\getadmin.vbs"

    "%temp%\getadmin.vbs"
    del "%temp%\getadmin.vbs"
    exit /B

:gotAdmin
    pushd "%CD%"
    CD /D "%~dp0"

mkdir "C:\Program Files (x86)\FoodCaller"
copy %cd%\FoodCaller.vbs "C:\Program Files (x86)\FoodCaller"
copy %cd%\FoodCaller.bat "C:\Program Files (x86)\FoodCaller"
copy %cd%\Eat.txt "C:\Program Files (x86)\FoodCaller"
copy %cd%\ClientServer.py "C:\Program Files (x86)\FoodCaller"
copy "%cd%\WhatsApp Ptt 2019-04-26 at 2.34.39 PM.ogg" "C:\Program Files (x86)\FoodCaller"
copy %cd%\"FoodCaller.vbs - Shortcut.lnk" "c:\ProgramData\Microsoft\Windows\Start Menu\Programs\startup"
