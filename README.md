# Transportation-System
Implementation of a theme for training classes, generics, enums, abstract classes in Java

## Project Structure

**models** - folder with system models:

- **Cargo** - class for cargo
- **CargoType** - enumeration of cargo types (DANGEROUS, SOLID, LIQUID, GASEOUS, PERISHABLE)
- **Transport** - base class for transport
- **Plane, Train, Ship** - transport subclasses
- **Transportation** - class for individual transportation
- **TransportationStatus** - enumeration of transportation statuses (PENDING, IN_PROGRESS, DELIVERED, CANCELLED)

**repositories** - folder with repositories for storing objects:

- **Repository<E>** - generic repository
- **TransportationRepository** - specialized repository for Transportation objects

---

## Entities

### Cargo

- Fields: `description`, `goodsWeight`, `goodsCount`, `type`
- compact constructor:
    - `Cargo` - checks the input values

### Transport

- Fields: model, modelNumber, maxWeight, kmCost
- Methods:
    - `transportInformation()` - prints transport information
    - `getTripCost(weight, distance)` - calculates transportation cost
    - `getType()` - returns transport type

### Plane, Train, Ship

- Subclasses of Transport, possibly with additional fields

### Transportation

- Fields: `id`, `cargo`, `transport`, `distance`, `transportationCost`, `status`
- Methods:
    - `startProcess()` - starts the transportation
    - `checkAvailableTransportation()` - checks if transportation is possible
    - `endProcess()` - ends the transportation
    - `getTransportationCost()`
    - `getStatus()`
    - `getId()`
  
### TransportationStatus

- CREATED
- PROCESSING
- COMPLETED

---

## Repositories

### Repository<E>

- Generic repository for storing any objects
- Methods:
    - `addItem(E item)`
    - `getItem(int index)`
    - `size()`

### TransportationRepository

- Repository for storing Transportation objects
- Methods:
    - `addTransportation(T transportation)`
    - `RegisterTransportation()`
    - `getTransportation(int index)`
    - `getSize()`
    - `showTotalIncome()` - returns total revenue from all delivered transports
    - `countCompletedTransportations()` - returns count completed transportations

---

## Main / Demonstration

- Create transport objects (Plane, Train, Ship)
- Create cargo objects (Cargo)
- Create transportation objects (Transportation)
- Add transportation objects to TransportationRepository
- Start and end transportation processes (`startProcess`, `endProcess`)
- Print transport and transportation information
- Print total income (`showTotalIncome`)

