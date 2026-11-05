# Plan de calidad inicial (SUMQ)

**Responsable:** Heinner Vega García – Gerente de calidad y proceso
**Fecha:** noviembre de 2026 (semana 3)

## Proceso por módulo
Cada módulo sigue estas fases y registra sus defectos en [LOGD](../bitacoras/LOGD.md):

1. Requisitos
2. Diseño
3. Revisión del diseño
4. Código
5. Revisión del código (personal y luego de un compañero)
6. Pruebas
7. Registro de defectos

Ningún módulo se integra a `main` sin la revisión de un compañero (pull request aprobado).

## Metas de calidad
| Medida | Meta |
|--------|------|
| Revisión de código entre compañeros | 100 % de los módulos |
| Tiempo de revisión respecto al tiempo de codificación | Al menos 50 % |
| Defectos encontrados en pruebas de integración | Menos que los hallados en revisiones |
| Defectos abiertos al entregar | 5 o menos |

## Revisores asignados (según la distribución de módulos)
| Módulo | Autor | Revisor |
|--------|-------|---------|
| 1. Registro e inicio de sesión | Ronald | Heinner |
| 2. Búsqueda básica | Heinner | Isabel |
| 3. Búsqueda avanzada | Ronald | Isabel |
| 4. Artículos y características | Isabel | Ronald |
| 5. Compra por artículo | Heinner | Ronald |
| 6. Carrito de compras | Isabel | Heinner |

## Lista de chequeo para la revisión de código
- [ ] Compila y `./mvnw test` pasa.
- [ ] Cumple el requisito del módulo descrito en el documento de la Entrega 1.
- [ ] Respeta la arquitectura: el `domain` no importa Spring ni JPA, y los controladores solo usan puertos de entrada.
- [ ] Valida los datos de entrada (campos obligatorios, cantidades mayores que 0, stock).
- [ ] Los errores responden con el JSON del manejador global.
- [ ] No hay contraseñas ni datos sensibles en el código ni en los logs.
- [ ] Nombres claros y comentarios justos en español.
- [ ] Tiene al menos una prueba del servicio.

## Registro y seguimiento
- **LOGT** (tiempos) y **LOGD** (defectos): cada integrante los actualiza el mismo día que trabaja.
- El gerente de calidad revisa las bitácoras cada semana y reporta el resumen en [WEEK](../seguimiento/WEEK.md).
