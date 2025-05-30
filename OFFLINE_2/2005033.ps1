# Navigate to the script's directory
Set-Location -Path $PSScriptRoot

Write-Host "Cleaning old .class files..."
Get-ChildItem -Recurse -Filter *.class | Remove-Item -Force

Write-Host "Compiling Java files..."
javac .\*.java .\Builder\*.java .\Director\*.java .\Shakes\*.java

if ($LASTEXITCODE -ne 0) {
    Write-Host "Compilation failed. Exiting."
    exit 1
}

Write-Host "Running App..."
java App

Write-Host "Cleaning up .class files to restore previous state..."
Get-ChildItem -Recurse -Filter *.class | Remove-Item -Force
