@ECHO OFF
CD /D "%~dp0"

:INIT
    ECHO.
    ECHO Chrome Portable 管理脚本
    ECHO.
    ECHO     作者: 黯然过客
    ECHO     网站: https://www.portablesoft.org/
    ECHO.
    ECHO 操作序号:
    ECHO.
    ECHO     1 设置 Chrome Portable 作为默认浏览器
    ECHO     2 不再使用 Chrome Portable 作为默认浏览器
    ECHO     3 彻底删除 Chrome Portable 相关数据
    ECHO     4 退出本脚本
    ECHO.
    SET /p ACTION="请输入操作序号:"
    IF "%ACTION%"=="1" GOTO MAKE_DEFAULT_BROWSER
    IF "%ACTION%"=="2" GOTO CLEAR_DEFAULT_BROWSER
    IF "%ACTION%"=="3" GOTO UNINSTALL
    IF "%ACTION%"=="4" GOTO END
    CLS
    GOTO INIT

:MAKE_DEFAULT_BROWSER
    "%~dp0ChromePortable.exe" --make-default-browser
    CLS
    GOTO INIT
   
:CLEAR_DEFAULT_BROWSER
    "%~dp0ChromePortable.exe" --clear-default-browser
    CLS
    GOTO INIT
    
:UNINSTALL
    "%~dp0ChromePortable.exe" --uninstall
    CLS
    GOTO INIT

:END
    EXIT