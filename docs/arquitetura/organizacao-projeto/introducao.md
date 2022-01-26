# Introdução

SISALI

- Aplication
    - Configurations (ContextAccessor, AuthorizeAttribute)
    - Controllers
    - Mapper
    - Middleware
    - Formatters
    - Startup.cs
- Domain
    - Entities
    - Enums
    - Constants
    - Services + Services Interfaces
    - Repositories Interfaces
    - SeedWork
    - IUnitOfWork + IAggregateRoot
    - Queries (poco)
    - Commands
- Infrastructure
    - Queries
    - DataContext
    - Repositories
    - Migrations


https://docs.microsoft.com/pt-br/dotnet/architecture/microservices/microservice-ddd-cqrs-patterns/ddd-oriented-microservice

- Aplication
    - Web API
    - Network acces to microservice
    - Commands and command handlers
    - Queries (CQS)
        - Micro ORM como Dapper
- Domain
    - Domain entity model
    - POCO entity classes
    - Domain entities
    - DDD patterns
        - Domain entity, aggregate
        - Aggregate root, VO
        - Repositories Interfaces
- Infrastructure
    - Data persistence infrastructure
    - ORMs
    - Logs, cryptograph


Final

- Aplication
    - Controle de acesso
    - Controllers
    - Mapper
    - Middleware
    - Formatters
    - Startup.cs
    - Commands and command handlers
    - Queries (CQS)
        - Micro ORM como Dapper

- Domain entity model
    - POCO entity classes
    - Enums
    - Constants
    - Services + Services Interfaces
    - Repositories Interfaces
    - VO
    - Aggregate

- Infrastructure
    - Repositories (entrada e saída de dados sao entidades)
    - Acesso a dados de APIs
    - Migrations
    - Logs
    - Criptografia
