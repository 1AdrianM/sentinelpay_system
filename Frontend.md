Contexto:
Este es un proyecto Spring Boot + Thymeleaf, orientado a portfolio, donde el objetivo principal es mostrar la UI y los details pages, no la funcionalidad completa de escritura.

Actualmente todas las vistas contienen acciones (crear, editar, borrar), pero vamos a introducir un modo Read-Only sin duplicar vistas.

Reglas de Roles

ROLE_READ_ONLY

Todas las vistas deben renderizarse en modo read-only

No debe poder ejecutar acciones mutables (create/update/delete)

Inputs → disabled o readonly

Botones de acción → ocultos o deshabilitados

ROLE_TENANT_ADMIN

ROLE_SYSTEM_ADMIN

Acceso completo a las acciones

Las vistas se renderizan normalmente (no read-only)

Requisitos Técnicos

NO duplicar vistas

Usar renderizado condicional en Thymeleaf

Apoyarse en:

sec:authorize

o variables de modelo como isReadOnly

Mantener una sola versión de cada template

Thymeleaf – Ejemplos Esperados

Botones:

<button
th:disabled="${isReadOnly}"
sec:authorize="!hasRole('ROLE_READ_ONLY')">
Save
</button>


Inputs:

<input
th:value="${entity.name}"
th:attr="readonly=${isReadOnly}">


Acciones completas solo para admins:

<div sec:authorize="hasAnyRole('ROLE_TENANT_ADMIN','ROLE_SYSTEM_ADMIN')">
  <!-- acciones completas -->
</div>

Console View (Excepción)

La vista console debe permanecer 100% interactiva

Es el único punto donde se permite interacción

Funciona como un mock para crear una transacción

Todos los roles pueden acceder a esta vista

Objetivo Final

UI funcional y limpia

Experiencia clara de:

Read-only user

Tenant Admin

System Admin

Proyecto cerrado y presentable como portfolio

Implementa la solución más simple, mantenible y alineada con buenas prácticas Thymeleaf + Spring Security.