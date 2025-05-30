param (
    [Parameter(Mandatory = $true)]
    [string]$SubfolderName
)

# Ensure the folder exists
if (-Not (Test-Path $SubfolderName)) {
    Write-Host "Error: Folder '$SubfolderName' does not exist."
    exit 1
}

Write-Host "Cleaning old .class files in '$SubfolderName'..."
Get-ChildItem -Recurse -Filter *.class | Remove-Item -Force -ErrorAction SilentlyContinue

Write-Host "Compiling Java files in '$SubfolderName'..."
javac "$SubfolderName\*.java"

if ($LASTEXITCODE -ne 0) {
    Write-Host "Compilation failed. Exiting."
    exit 1
}

Write-Host "Running App from package '$SubfolderName'..."
java "$SubfolderName.App"

Write-Host "Cleaning up .class files..."
Get-ChildItem -Recurse -Filter *.class | Remove-Item -Force -ErrorAction SilentlyContinue
