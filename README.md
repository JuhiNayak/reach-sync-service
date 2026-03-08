# Record Synchronization Service

I've implemented a simplified **record-to-record synchronization service** between an internal system and esternal CRM providers.

My goal is propagate CRUD operations for a bi-directional record synchronization service between internal system and external systems.

For this assignment, the foucs is on implementing the core synchronization worker responsible for processing synchronization requests.

#### Execution Flow:
Internal System -> Sync Requests -> Sync Worker -> OperationHandler -> RecordTransformer -> External CRM

## Key Components 
### 1. Sync Worker
The sync worker is responsible for orchestrating the synchronization pipeline.

#### Responsibilities:
* Accept synchronization requests
* Perform CRUD operation

#### Assumptions:
* The internal system provides valid changee events.
* External CRO APIs are assumed to be reachable and authenticated.
* For this implementation, external API calls are mocked.

#### Tradeoffs:
* Only a minimal record schema is implemented to demonstrate the pipeline.

### 2. Schema Transformation

Different CRM providers may require different data formats.
So this system introduces a transformation layer that converts internal records into CRM specific schema.
Each CRM provider implements a dedicated transformer using the RecordTransformer interface.

#### Responsibilities:
* Converts internal record model to CRM specific payload
* Isolates schema differences between internal systema and external CRM

#### Assumptions:
* Each CRM provider requires a different schema format
* Internal record model remains consistent across system
* Only basic fields are transformed to demonstrate transformation pattern
* CRM payloads are represented using 'Map<String, Object>' for flexibility

#### Tradeoffs:
* Using 'Map<String, Object>' as output payload
  * To have flexible structure that supports different CRM payload format
  * Avoid creating many CRM specific classes
  * Although it may cause runtime errors if keys are mis-typed
* Separate transformer classes per CRM
  * To achieve clear sepration of logic, it causes more classes in the system

### 3. Error Handling
System uses centralized exception handling through '@RestControllerAdvice'.
Custom exceptions are thrown when unsupported operation or missing transfromers are detected.
This approach keeps business logic clean while ensuring consistent API responses.

### 4. Bi-Directional Synchorization
System support Bi-Directional Synchorization between internal system and external CRM providers

#### Execution Flow:
CRM Webhook -> ReverseSync Worker -> ReverseRecordTransformer -> Internal System

#### Responsibilities
* Recieving change events from external CRM providers via webhook
* PRocessing CRM events through a dedicated inbound worker pipeline
* Converting CRM specific payload into internal record model


#### Assumptions
* CRM provider supports webhook-based change notifications
* Incoming webhook payload contains sufficient information to reconstruct the internal record
* Internal system is responsble for resolving conflicts between inbound and outbound updates
* Reverse transformers exist for each supported CRM provider

#### Tadeoffs
* Separate inbound and outbound pipelines
  * Maintains separate processing pipelines
  * Clean separation of responsibilities
* Reverse transformation layer
  * Payloads are converted into the internla schema using dedicated reverse transformer implementation
  * Additional transformation logic must be maintained for each CRM