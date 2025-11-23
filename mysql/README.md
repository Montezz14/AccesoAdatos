# Proyecto AADMySQL — Instrucciones rápidas

Breve guía para ejecutar la aplicación desde PowerShell y usar variables de entorno para las credenciales de la base de datos.

## Resumen
- El proyecto usa `src/main/resources/config.properties` para valores por defecto (ej. `db.url`, `db.user`, `db.driver`).
- Por seguridad **no** debe dejarse la contraseña en el repositorio. La propiedad `db.password` se dejó vacía; la aplicación admite la variable de entorno `DB_PASSWORD`.

## Variables de entorno soportadas
- `DB_URL` — (opcional) URL JDBC completa. Si no se proporciona, se usa `db.url` en `config.properties`.
- `DB_USER` — (opcional) usuario DB; por defecto se toma `db.user`.
- `DB_PASSWORD` — (recomendado) contraseña. Si se establece, sobreescribe `db.password`.
- `DB_DRIVER` — (opcional) driver JDBC; por defecto `com.mysql.cj.jdbc.Driver`.

## Ejecutar desde PowerShell (temporal en la sesión)
1. Desde la raíz del proyecto (ej. `c:\Users\jorge\AADMySQL\mysql`), compila si hace falta:

```powershell
# Compilar (si no hay .class recientes)
javac -d target/classes (Get-ChildItem -Recurse -Filter "*.java" | ForEach-Object { $_.FullName })
```

2. Ejecuta la aplicación definiendo `DB_PASSWORD` en la sesión actual:

```powershell
$env:DB_PASSWORD='TuPasswordAqui'
java -cp "target/classes;lib/*" id.monterojorge.Main
```

Nota: `setx DB_PASSWORD "valor"` hace persistente la variable para nuevas ventanas de PowerShell (no afecta la ventana actual).

## Si tienes Maven instalado
```powershell
mvn -DskipTests package
# luego ejecutar el jar/artifact o usar mvn exec configurado
```

## Seguridad y buenas prácticas
- Nunca guardes contraseñas en el repositorio. Usa variables de entorno o un gestor de secretos.
- Para CI/CD, configura `DB_PASSWORD` como secret en la plataforma (GitHub Actions, Azure DevOps, etc.).

## Nota sobre `DatabaseConnection`
La clase `id.monterojorge.dao.DatabaseConnection` carga `config.properties` y permite sobreescritura mediante las variables de entorno listadas arriba. Si `db.driver` no está especificado, usa `com.mysql.cj.jdbc.Driver` por defecto.

Si quieres, puedo añadir un pequeño script PowerShell (`run.ps1`) que automatice la compilación y ejecución segura.
