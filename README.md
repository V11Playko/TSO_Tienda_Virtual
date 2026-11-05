# Catálogo Virtual – Tienda de ropa

Base del proyecto "Construcción de una tienda virtual": API REST de un catálogo de ropa con registro de clientes, búsqueda básica y avanzada, artículos con sus características, compra por artículo y carrito de compras.

> **Estado (Entrega 1):** el repositorio tiene la estructura, la base de datos y el catálogo inicial de 20 prendas. Los módulos están como esqueleto y responden **501** hasta que se implementen en las semanas 4 a 7.

## 1. Contexto académico
- **Curso:** Desarrollo de software en equipos – Politécnico Grancolombiano
- **Proceso:** TSP (Team Software Process)
- **Grupo:** B 03 – Subgrupo 2
- **Docente:** Natalia Ramírez

## 2. Equipo y roles TSP
| Integrante | Rol TSP | Rol de respaldo |
|------------|---------|-----------------|
| Isabel Vega Vargas | Líder de equipo y gerente de planeación | Desarrollo |
| Ronald Fabian Cepeda | Gerente de desarrollo | Soporte |
| Heinner Vega García | Gerente de calidad y proceso, y gerente de soporte | Líder |

## 3. Tecnologías
Java 21 · Spring Boot 3 (Web, Data JPA, Validation) · PostgreSQL 16 · Flyway · Maven · Docker · JUnit 5

> ⚠️ El documento de la Entrega 1 (sección 5, STRAT) dice Node.js + Express + MySQL. El repositorio usa Java + Spring Boot + PostgreSQL, así que hay que **actualizar la sección 5 y registrar el cambio en un acta**.

## 4. Arquitectura hexagonal
Cada módulo se divide en capas:
- **domain:** modelos de negocio puros, sin Spring ni JPA.
- **application:** casos de uso (servicios) y puertos. `port/in` es lo que el módulo ofrece; `port/out` es lo que necesita (repositorios).
- **infrastructure:** adaptadores. `web` tiene los controladores REST y DTOs; `persistence` tiene las entidades JPA y la implementación de los puertos de salida.

Los controladores solo llaman a puertos de entrada y el dominio no depende de nada externo.

```
co.edu.poli.catalogo
├── CatalogoApplication.java
├── comun/                 excepciones compartidas y manejador global de errores (JSON)
├── usuario/               módulo 1: registro e inicio de sesión
├── busqueda/              módulos 2 y 3: búsqueda básica y avanzada
├── prenda/                módulo 4: artículos y características
├── compra/                módulo 5: compra por artículo
└── carrito/               módulo 6: carrito de compras

Dentro de cada módulo:
<modulo>/
├── domain/                modelos (records de Java)
├── application/           <Modulo>Service (esqueleto con TODO)
│   ├── port/in/           casos de uso
│   └── port/out/          repositorios
└── infrastructure/
    ├── web/               controlador REST y DTOs
    └── persistence/       TODO: entidades JPA y adaptador
```

## 5. Cómo ejecutar
Requisitos: Java 21 y Docker Desktop. No hace falta instalar Maven: se usa el wrapper `mvnw`.

```bash
docker compose up -d          # levanta PostgreSQL (base, usuario y clave: "catalogo")
./mvnw spring-boot:run        # inicia la API en http://localhost:8080; Flyway crea las tablas y carga las 20 prendas
./mvnw test                   # ejecuta las pruebas (no necesitan base de datos)
```
En PowerShell se usa `.\mvnw` y `curl.exe` en lugar de `./mvnw` y `curl`.

Las credenciales por defecto ya coinciden con `docker-compose.yml`. Para cambiarlas, se copia `.env.example` como `.env`.

Para ver las 20 prendas cargadas:
```bash
docker exec -it catalogo-postgres psql -U catalogo -d catalogo -c "SELECT id, nombre, precio, stock FROM prenda;"
```

### Endpoints (todos responden 501 por ahora)
| Método | Ruta | Módulo |
|--------|------|--------|
| GET | `/api/prendas` | 4. lista de prendas |
| GET | `/api/prendas/{id}` | 4. detalle |
| GET | `/api/busqueda?nombre=` | 2. búsqueda básica |
| GET | `/api/busqueda/avanzada` | 3. filtros opcionales: `categoria, genero, talla, color, marca, precioMin, precioMax` |
| POST | `/api/usuarios/registro` | 1. registro |
| POST | `/api/usuarios/login` | 1. inicio de sesión |
| GET / POST / PUT / DELETE | `/api/carrito`, `/api/carrito/items`, `/api/carrito/items/{itemId}` | 6. carrito |
| POST | `/api/compras` | 5. compra por artículo |

```bash
curl -i http://localhost:8080/api/prendas
# HTTP/1.1 501  {"fecha":"...","estado":501,"error":"Not Implemented","mensaje":"Funcionalidad pendiente de implementar: artículos y características","ruta":"/api/prendas"}
```

## 6. Módulos
| # | Módulo | Estado | Responsable | Apoyo |
|---|--------|--------|-------------|-------|
| 1 | Registro e inicio de sesión | 🚧 pendiente | Ronald | Heinner |
| 2 | Búsqueda básica | 🚧 pendiente | Heinner | Isabel |
| 3 | Búsqueda avanzada | 🚧 pendiente | Ronald | Isabel |
| 4 | Artículos y características | 🚧 pendiente | Isabel | Ronald |
| 5 | Compra por artículo | 🚧 pendiente | Heinner | Ronald |
| 6 | Carrito de compras | 🚧 pendiente | Isabel | Heinner |
| 7 | Catálogo inicial (20 prendas) | ✅ datos cargados con Flyway (`V2__catalogo_inicial.sql`); faltan las imágenes reales | Todos | — |
| – | Esquema de base de datos | ✅ `V1__esquema.sql` | Ronald / Heinner | — |

## 7. Calendario de entregas (2026)
| Semana | Fechas | Hito |
|--------|--------|------|
| 3 | 2 al 7 de nov. | Lanzamiento, roles, plan y documento. **Entrega 1: 7 de noviembre** |
| 4 | 9 al 14 de nov. | Diseño de la base de datos y repositorio listo; inicio de los módulos 1 y 4 |
| 5 | 16 al 21 de nov. | Módulos 1, 4 y 2 terminados. **Entrega 2: 21 de noviembre** |
| 6 | 23 al 28 de nov. | Módulos 3 y 5 terminados; avance del módulo 6 |
| 7 | 30 de nov. al 5 de dic. | Módulo 6, carga de las 20 prendas con imagen, pruebas de integración y postmortem. **Entrega final: 5 de diciembre** |

## 8. Flujo de trabajo en Git
- `main` siempre debe compilar y pasar las pruebas.
- Una rama por módulo: `feature/<modulo>` (por ejemplo `feature/usuario` o `feature/busqueda-basica`).
- Antes de integrar a `main`, otro integrante revisa el código con un pull request, siguiendo la lista de chequeo de [docs/calidad/plan-calidad.md](docs/calidad/plan-calidad.md). Los defectos encontrados se anotan en `docs/bitacoras/LOGD.md`.
- Commits pequeños y en español, con prefijo: `feat:`, `fix:`, `docs:`, `test:` o `refactor:`.

## 9. Documentación TSP (`docs/`)
| Ruta | Qué va |
|------|--------|
| `docs/entrega-1/` | Documento consolidado, actas y formularios de la 1.ª entrega |
| `docs/entrega-2/` | Actas de lanzamiento, registros personales y reportes de avance |
| `docs/entrega-3/` | Consolidado final, reflexión, video y línea de tiempo |
| `docs/actas/` | Plantilla de acta y actas de cada reunión |
| `docs/calidad/` | Plan de calidad inicial (SUMQ) |
| `docs/bitacoras/` | LOGT (tiempos) y LOGD (defectos) |
| `docs/seguimiento/` | WEEK (estado semanal) |

Ver el índice en [docs/README.md](docs/README.md).

## Decisiones de simplicidad y notas
- **Imágenes:** las prendas usan URLs de ejemplo (`placehold.co`). **Deben reemplazarse por fotos propias o de bancos de imágenes libres** para evitar problemas de derechos de uso (riesgo 5 del documento).
- **Marcas, materiales y descripciones** del catálogo inicial son inventados y genéricos.
- **Talla:** se guarda como texto con el rango disponible (`S-XL`, `28-36`, `Única`).
- **Usuario sin sesión:** mientras no exista login, el carrito y la compra identifican al usuario con el encabezado `X-Usuario-Id`.
- **BCrypt:** se incluye solo `spring-security-crypto` (no Spring Security completo), así los endpoints no quedan bloqueados.
- **Paquete `comun`:** contiene las excepciones compartidas y el manejador global de errores; no es un módulo de negocio.
