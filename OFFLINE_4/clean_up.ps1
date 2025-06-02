Write-Host "Cleaning old .class files..."
Get-ChildItem -Recurse -Filter *.class | Remove-Item -Force