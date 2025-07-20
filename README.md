# 🛠️ No-Code Spring Boot CRUD Generator

A full-stack code generation platform that lets you build complete Spring Boot CRUD modules — including Entity, Repository, Service, and Controller — by simply providing input through a REST API or UI. It also supports One-to-Many and Many-to-One entity relationships.

---

## 🚀 Features

- ✅ REST + UI-based input for entity and attributes
- ✅ Auto-generates:
  - Entity classes with annotations
  - JPA Repositories
  - Service classes
  - REST Controllers
- ✅ Supports relationships:
  - `@OneToMany`, `@ManyToOne`
- ✅ Dynamic file writing to target project (`baseProjectTest`)
- ✅ UI available for simple entity input
- ✅ JSON-based API for automation via Postman

---

## 🧱 Tech Stack

| Layer      | Tech                         |
|------------|------------------------------|
| Backend    | Spring Boot, Java 17         |
| Frontend   | HTML + Bootstrap (simple UI) |
| API Client | Postman                      |
| Build Tool | Maven                        |

---


---

## 🧾 Sample JSON Input (via API)

```json
[
  {
    "entityName": "School",
    "attributes": [
      { "name": "name", "type": "String" }
    ],
    "relations": [
      {
        "type": "OneToMany",
        "targetEntity": "Student",
        "mappedBy": "school"
      }
    ]
  },
  {
    "entityName": "Student",
    "attributes": [
      { "name": "firstName", "type": "String" },
      { "name": "lastName", "type": "String" }
    ],
    "relations": [
      {
        "type": "ManyToOne",
        "targetEntity": "School",
        "joinColumn": "school_id"
      }
    ]
  }
]
```

## 🔮 Future Enhancements / Roadmap

-  Support for **Many-to-Many** relationships
- Built-in **.zip download** for generated project
- UI improvement with **field type dropdowns** and **validations**
- **Swagger/OpenAPI** documentation for all endpoints
- Ability to **edit/update generated entities** via UI
- **Role-based login** support for multi-user platform

