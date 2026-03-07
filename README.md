# Record Synchronization Service

I've implemented a simplified **record-to-record synchronization service** between an internal system and esternal CRM providers.

My goal is propagate CRUD operations for a bi-directional record synchronization service between internal system and external systems.

For this assignment, the foucs is on implementing the core synchronization worker responsible for processing synchronization requests.

## Key Components 
### 1. Sync Worker
The sync worker is responsible for orchestrating the synchronization pipeline.

#### Responsibilities:
* Accept synchronization requests
* Perform CRUD operation

#### Execution Flow:
Internal System -> Sync Requests -> Sync Worker -> External CRM 

#### Assumptions:
* The internal system provides valid changee events.
* External CRO APIs are assumed to be reachable and authenticated.
* For this implementation, external API calls are mocked.

#### Tradeoffs:
* Only a minimal record schema is implemented to demonstrate the pipeline.