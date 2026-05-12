// Configuración de la API
const API_BASE_URL = '/api';

// Credenciales para autenticación básica (se envían automáticamente con las cookies de sesión)
// En este caso, usamos la sesión del navegador

// Inicialización - Se ejecuta cuando carga la página
document.addEventListener('DOMContentLoaded', () => {
    cargarEmpleados();
});

// --- 1. CARGAR EMPLEADOS (GET) ---
function cargarEmpleados() {
    console.log('Cargando empleados...');
    
    fetch(`${API_BASE_URL}/employees`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
        credentials: 'include' // Incluir cookies de sesión
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }
        return response.json();
    })
    .then(datos => {
        console.log('Empleados cargados:', datos);
        
        // Limpiar tabla anterior
        const tbody = document.getElementById('tablaEmpleados');
        tbody.innerHTML = '';

        // Si no hay datos
        if (!datos || datos.length === 0) {
            tbody.innerHTML = '<tr><td colspan="6" style="text-align: center;">No hay empleados registrados</td></tr>';
            return;
        }

        // Llenar tabla con los datos
        datos.forEach(emp => {
            const jobId = emp.job ? emp.job.id : 'SIN PUESTO';
            const salario = emp.salary ? parseFloat(emp.salary).toFixed(2) : '0.00';
            
            const fila = document.createElement('tr');
            fila.innerHTML = `
                <td>${emp.id}</td>
                <td>${emp.firstName || ''} ${emp.lastName || ''}</td>
                <td>${emp.email || ''}</td>
                <td>€${salario}</td>
                <td><span style="background:#eef; padding: 3px 8px; border-radius:4px;">${jobId}</span></td>
                <td style="text-align: center;">
                    <button class="btn-edit" onclick='cargarFormularioEdicion(${JSON.stringify(emp).replace(/'/g, "&apos;")})'>✏️ Editar</button>
                    <button class="btn-danger" onclick="eliminarEmpleado(${emp.id}, '${emp.firstName} ${emp.lastName}')">🗑️ Eliminar</button>
                </td>
            `;
            tbody.appendChild(fila);
        });
    })
    .catch(error => {
        console.error('Error al cargar empleados:', error);
        mostrarError('No se pudieron cargar los empleados. Verifica tu conexión.');
    });
}

// --- 2. CREAR O ACTUALIZAR EMPLEADO (POST / PUT) ---
function guardarEmpleado() {
    // Recolectar datos del formulario
    const empId = document.getElementById('empId').value;
    const firstName = document.getElementById('firstName').value.trim();
    const lastName = document.getElementById('lastName').value.trim();
    const email = document.getElementById('email').value.trim();
    const phoneNumber = document.getElementById('phoneNumber').value.trim();
    const hireDate = document.getElementById('hireDate').value;
    const salary = document.getElementById('salary').value;
    const jobId = document.getElementById('jobId').value.trim();

    // Validar campos obligatorios
    if (!firstName || !lastName || !email || !hireDate || !salary || !jobId) {
        alert('⚠️ Por favor, completa todos los campos obligatorios');
        return;
    }

    // Construir objeto JSON para enviar
    // IMPORTANTE: Respetar la estructura de la entidad Employee
    const empleadoDTO = {
        firstName: firstName,
        lastName: lastName,
        email: email,
        phoneNumber: phoneNumber,
        hireDate: hireDate,
        salary: parseFloat(salary),
        job: {
            id: jobId
        }
    };

    // Decidir si es crear (POST) o actualizar (PUT)
    let url = `${API_BASE_URL}/employees`;
    let metodo = 'POST';
    let mensaje_exito = '✅ Nuevo empleado creado correctamente';

    if (empId) {
        // Es actualización
        url += `/${empId}`;
        metodo = 'PUT';
        mensaje_exito = '✅ Empleado actualizado correctamente';
        empleadoDTO.id = parseInt(empId);
    }

    // Enviar petición al servidor
    fetch(url, {
        method: metodo,
        headers: {
            'Content-Type': 'application/json'
        },
        credentials: 'include',
        body: JSON.stringify(empleadoDTO)
    })
    .then(response => {
        if (response.ok) {
            mostrarExito(mensaje_exito);
            limpiarFormulario();
            cargarEmpleados(); // Refrescar tabla
        } else if (response.status === 401) {
            mostrarError('⚠️ No autorizado. Por favor, inicia sesión.');
        } else {
            mostrarError('❌ Error al guardar el empleado. Verifica los datos.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        mostrarError('❌ Error de conexión al guardar');
    });
}

// --- 3. ELIMINAR EMPLEADO (DELETE) ---
function eliminarEmpleado(id, nombre) {
    if (!confirm(`¿Estás seguro de que quieres eliminar a ${nombre}?`)) {
        return;
    }

    fetch(`${API_BASE_URL}/employees/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
        credentials: 'include'
    })
    .then(response => {
        if (response.ok || response.status === 204) {
            mostrarExito('🗑️ Empleado eliminado correctamente');
            cargarEmpleados(); // Refrescar tabla
        } else if (response.status === 401) {
            mostrarError('⚠️ No autorizado.');
        } else {
            mostrarError('❌ No se pudo eliminar el empleado.');
        }
    })
    .catch(error => {
        console.error('Error al eliminar:', error);
        mostrarError('❌ Error de conexión al eliminar');
    });
}

// --- 4. FUNCIONES AUXILIARES ---

// Cargar datos en el formulario para editar
function cargarFormularioEdicion(empleado) {
    console.log('Cargando formulario de edición:', empleado);
    
    document.getElementById('formTitle').innerText = `✏️ Editando a ${empleado.firstName} ${empleado.lastName} (ID: ${empleado.id})`;
    document.getElementById('empId').value = empleado.id;
    document.getElementById('firstName').value = empleado.firstName || '';
    document.getElementById('lastName').value = empleado.lastName || '';
    document.getElementById('email').value = empleado.email || '';
    document.getElementById('phoneNumber').value = empleado.phoneNumber || '';
    document.getElementById('hireDate').value = empleado.hireDate || '';
    document.getElementById('salary').value = empleado.salary || '';

    if (empleado.job) {
        document.getElementById('jobId').value = empleado.job.id;
    } else {
        document.getElementById('jobId').value = '';
    }

    // Scroll suave al formulario
    document.querySelector('.form-container').scrollIntoView({ behavior: 'smooth' });
}

// Limpiar formulario y volver al modo "Crear"
function limpiarFormulario() {
    document.getElementById('formTitle').innerText = '➕ Nuevo Empleado';
    document.getElementById('empId').value = '';
    document.getElementById('firstName').value = '';
    document.getElementById('lastName').value = '';
    document.getElementById('email').value = '';
    document.getElementById('phoneNumber').value = '';
    document.getElementById('hireDate').value = '';
    document.getElementById('salary').value = '';
    document.getElementById('jobId').value = 'IT_PROG';
}

// Mostrar mensaje de éxito
function mostrarExito(mensaje) {
    const alerta = document.createElement('div');
    alerta.className = 'alert alert-success';
    alerta.textContent = mensaje;
    alerta.style.position = 'fixed';
    alerta.style.top = '80px';
    alerta.style.right = '20px';
    alerta.style.zIndex = '1000';
    alerta.style.minWidth = '300px';
    document.body.appendChild(alerta);

    setTimeout(() => alerta.remove(), 4000);
}

// Mostrar mensaje de error
function mostrarError(mensaje) {
    const alerta = document.createElement('div');
    alerta.className = 'alert alert-error';
    alerta.textContent = mensaje;
    alerta.style.position = 'fixed';
    alerta.style.top = '80px';
    alerta.style.right = '20px';
    alerta.style.zIndex = '1000';
    alerta.style.minWidth = '300px';
    document.body.appendChild(alerta);

    setTimeout(() => alerta.remove(), 5000);
}
