<#
run.ps1 - helper to compile and run the AADMySQL sample safely from PowerShell

Usage examples:
  # Compile (optional) and run interactively, prompting for password if not in env
  .\run.ps1 -Compile

  # Run non-interactive and save output to run_output.txt (sends '.' to stdin to exit)
  .\run.ps1 -NonInteractive

  # Provide the DB password on the command line (not recommended for security)
  .\run.ps1 -DbPassword 'Ssbjmc0602!' -NonInteractive

Notes:
- The script prefers an explicit `-DbPassword` argument, then the environment variable
  `DB_PASSWORD`, then `config.properties` in `src/main/resources` if present.
- Prefer using environment variables or CI secrets over passing passwords on the command line.
#>

param(
    [switch]$Compile = $true,
    [switch]$NonInteractive = $false,
    [string]$DbPassword
)

try {
    Push-Location -ErrorAction Stop $PSScriptRoot
} catch {
    Push-Location -ErrorAction Stop (Split-Path -Path $MyInvocation.MyCommand.Definition -Parent)
}

Write-Host "Working dir: $(Get-Location)"

# Optional compile
if ($Compile) {
    Write-Host "Compiling Java sources..."
    $javaFiles = Get-ChildItem -Recurse -Filter '*.java' | ForEach-Object { $_.FullName }
    if ($javaFiles.Count -eq 0) {
        Write-Host "No Java sources found. Skipping compilation."
    } else {
        javac -encoding UTF-8 -d target/classes $javaFiles
        if ($LASTEXITCODE -ne 0) {
            Write-Error "Compilation failed (javac exit code $LASTEXITCODE). Aborting."
            Pop-Location
            exit $LASTEXITCODE
        }
    }
}

# Determine password (priority: argument -> env var)
if ($DbPassword) {
    $passwordArg = "-DDB_PASSWORD=$DbPassword"
    # also set env var for subprocesses
    $env:DB_PASSWORD = $DbPassword
} elseif ($env:DB_PASSWORD) {
    $passwordArg = "-DDB_PASSWORD=$($env:DB_PASSWORD)"
} else {
    $passwordArg = $null
}

$cp = "target/classes;lib/*"

$javaExec = "java"
$argsList = @()
if ($passwordArg) { $argsList += $passwordArg }
$argsList += "-cp"; $argsList += $cp; $argsList += "id.monterojorge.Main"

if ($NonInteractive) {
    Write-Host "Running non-interactive and saving output to run_output.txt"
    # Send '.' to stdin so the interactive navigator exits immediately
    "." | & $javaExec @argsList > run_output.txt 2>&1
    $code = $LASTEXITCODE
    Write-Host "Process finished with exit code $code. Output saved to run_output.txt"
} else {
    Write-Host "Running interactively. To run non-interactive use -NonInteractive"
    & $javaExec @argsList
    $code = $LASTEXITCODE
    Write-Host "Process finished with exit code $code"
}

Pop-Location

exit $code
