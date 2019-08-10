Set oShell = CreateObject ("Wscript.Shell") 
Dim strArgs
strArgs = "cmd /c FoodCaller.bat"
oShell.Run strArgs, 0, false