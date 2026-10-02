# Oman – Solution Reference Architecture

> Converted from `Oman - Solution Architecture v1.0.3.pdf` for easier processing. The PDF is authoritative.
>
> Conversion notes: figures are replaced by placeholders (Figure 22 additionally has a transcription of its XML content). Lines that were only wrapped because of the PDF page/box width (long identifiers, URLs, hex/Base64 strings, code lines) have been joined. Typos and inconsistencies of the original have been kept as-is.

**Oman – Solution Reference Architecture**

- Status: Final - Version: 1.0.3
- Last updated: 2026.07.27
- OpenPeppol AISBL, Rond-point Schuman 6, box 5, 1040 Brussels Belgium – info@peppol.eu – www.peppol.org
- Corporate identification number 0848.934.496 (Register of Legal Entities Brussels).

## Table of Contents

- [Document History](#document-history)
- [1 Introduction](#1-introduction)
  - [1.1 Overview and Context](#11-overview-and-context)
  - [1.2 Architecture Scope for Oman Implementation](#12-architecture-scope-for-oman-implementation)
  - [1.3 Brief Guide to this document](#13-brief-guide-to-this-document)
- [2 Peppol Logical Architecture View](#2-peppol-logical-architecture-view)
  - [2.1 Business Document Exchange](#21-business-document-exchange)
    - [2.1.1 Business Document Prerequisite](#211-business-document-prerequisite)
    - [2.1.2 Normal Business Document flow](#212-normal-business-document-flow)
  - [2.2 Overview of CTC Logical Architecture](#22-overview-of-ctc-logical-architecture)
  - [2.3 Message-Level Status (MLS) Exchange](#23-message-level-status-mls-exchange)
    - [2.3.1 MLS Prerequisite](#231-mls-prerequisite)
  - [2.4 Peppol Network Specifications](#24-peppol-network-specifications)
- [3 Portal](#3-portal)
  - [3.1 Service provider and Tax Payer setup](#31-service-provider-and-tax-payer-setup)
  - [3.2 Relationship management](#32-relationship-management)
  - [3.3 Testing environment](#33-testing-environment)
  - [3.4 Operational](#34-operational)
- [4 B2C and QR code](#4-b2c-and-qr-code)
  - [4.1 Oman B2C invoicing](#41-oman-b2c-invoicing)
  - [4.2 QR code](#42-qr-code)
  - [4.3 PINT for B2C](#43-pint-for-b2c)
- [5 C2 Capabilities](#5-c2-capabilities)
  - [5.1 C2 Prerequisites](#51-c2-prerequisites)
    - [5.1.1 Registration in the Dynamic Discovery](#511-registration-in-the-dynamic-discovery)
    - [5.1.2 Functional](#512-functional)
  - [5.2 C2 flow capabilities](#52-c2-flow-capabilities)
  - [5.3 C2 → C3 Flow](#53-c2--c3-flow)
    - [5.3.1 Exceptions (see section 5.6)](#531-exceptions-see-section-56)
  - [5.4 C2 → C5 Flow (same as C3 → C5 flow)](#54-c2--c5-flow-same-as-c3--c5-flow)
    - [5.4.1 Exceptions (see section 5.6)](#541-exceptions-see-section-56)
  - [5.5 C2 - non Peppol domestic](#55-c2---non-peppol-domestic)
  - [5.6 C2 Summary](#56-c2-summary)
- [6 C3 Capabilities](#6-c3-capabilities)
  - [6.1 C3 Prerequisites](#61-c3-prerequisites)
    - [6.1.1 Registration in the Dynamic Discovery](#611-registration-in-the-dynamic-discovery)
    - [6.1.2 Functional](#612-functional)
  - [6.2 C3 flow capabilities](#62-c3-flow-capabilities)
  - [6.3 C3 → C5 Flow (same as C2 → C5 flow)](#63-c3--c5-flow-same-as-c2--c5-flow)
    - [6.3.1 Exceptions (see section 6.6)](#631-exceptions-see-section-66)
  - [6.4 C3 → C2 Flow](#64-c3--c2-flow)
    - [6.4.1 Exception (see section 6.6)](#641-exception-see-section-66)
  - [6.5 C3/C4 – Peppol/non-Peppol International](#65-c3c4--peppolnon-peppol-international)
  - [6.6 C3 Summary](#66-c3-summary)
- [7 C5 Capabilities](#7-c5-capabilities)
  - [7.1 C5 Prerequisites](#71-c5-prerequisites)
    - [7.1.1 Registration in the Dynamic Discovery](#711-registration-in-the-dynamic-discovery)
    - [7.1.2 Functional](#712-functional)
  - [7.2 C5 flow capabilities](#72-c5-flow-capabilities)
  - [7.3 C5 → C2/C3 Flow](#73-c5--c2c3-flow)
    - [7.3.1 Exception (see section 7.4)](#731-exception-see-section-74)
  - [7.4 C5 Summary](#74-c5-summary)
- [8 C6 TDD submission statuses](#8-c6-tdd-submission-statuses)
    - [8.1.1 Normal](#811-normal)
    - [8.1.2 Exception](#812-exception)
- [9 Oman document flows](#9-oman-document-flows)
- [10 Identifiers and Specifications](#10-identifiers-and-specifications)
  - [10.1 Process Identifiers](#101-process-identifiers)
  - [10.2 Documents identifiers and Specifications](#102-documents-identifiers-and-specifications)
    - [10.2.1 Invoice Business Document Types](#1021-invoice-business-document-types)
    - [10.2.2 TDD Business Document Type](#1022-tdd-business-document-type)
    - [10.2.3 Business Document and Transmission instances](#1023-business-document-and-transmission-instances)
  - [10.3 Identification of Participants](#103-identification-of-participants)
    - [10.3.1 End-Users (C1, C4)](#1031-end-users-c1-c4)
    - [10.3.2 Tax Authorities (C6)](#1032-tax-authorities-c6)
    - [10.3.3 Service Providers / Access Points (C2, C3, C5)](#1033-service-providers--access-points-c2-c3-c5)
- [11 Transmission](#11-transmission)
  - [11.1 SBDH Setup](#111-sbdh-setup)
- [12 Security](#12-security)
  - [12.1 Peppol Trust Model](#121-peppol-trust-model)
  - [12.2 C5 segregation](#122-c5-segregation)
  - [12.3 SMP and Portal systems](#123-smp-and-portal-systems)
- [Appendix A References](#appendix-a-references)
- [Appendix B Terminology](#appendix-b-terminology)
- [Appendix C SLR/SLR Timings](#appendix-c-slrslr-timings)
  - [C.1 Retention policy](#c1-retention-policy)
- [Appendix D QR code implementation](#appendix-d-qr-code-implementation)
  - [D.1 Method to generate Seller UUID](#d1-method-to-generate-seller-uuid)
  - [D.2 Method to generate the QR Code](#d2-method-to-generate-the-qr-code)

## Document History

| Version | Date | Author | Description of change |
|---|---|---|---|
| 1.0RC | 2026-04-20 | Roger Young / Oman team | Release candidate<br>With OTA review comments |
| 1.0 | 2026.05.07 | Roger Young | Updated for Oman 1.0RC review comments<br>OpenPeppol Architect review<br>Final version, published on confluence |
| 1.0.1 | 2026.05.22 | Roger Young / Oman team | Update for QR code - with implementation information<br>Update process IDs |
| 1.0.2 | 2026.06.25 | Roger Young / Oman team | Update the QR code and seller UUID fields |
| 1.03 | 2026.07.27 | Roger Young | Update Transaction type to use 0’s instead of X’s<br>Various corrections after internal Peppol review |

## 1 Introduction

### 1.1 Overview and Context

The following solution has the Solution view of an Access Point (AP) acting as Oman accredited Service Provider (SP) both as sending AP and receiving AP.

### 1.2 Architecture Scope for Oman Implementation

This document outlines the solution architecture for the implementation of Peppol in Oman, designed in alignment with Peppol, using the Oman PINT Invoice, and the national OM Tax Data Document (TDD) specification - based on the Peppol TDD (with location variations).

The architecture adopts a decentralised 5/6-corner model (the SMP will be centralised by Oman Peppol Authority), enabling secure, standards-based exchange of structured invoice and tax data between economic operators, service providers, and the Oman tax authority.

The Oman Peppol ecosystem will leverage a dedicated portal - to facilitate service provider and end-user registration and Oman SMP management (through APIs).

The Oman Peppol solution will also support B2C transactions, for consumers who are not registered with Peppol.

### 1.3 Brief Guide to this document

This document serves as the definitive source of technical solution architecture for the Peppol eInvoicing solution in Oman. It builds upon the foundational principles and structures defined in the Peppol eDelivery framework. These references are used to avoid duplication and maintain alignment with Peppol.

The structure of this document follows a top-down progression, beginning with generic Peppol architecture elements and evolving toward the specific design and implementation of the Peppol Oman Solution Architecture.

Chapter 2 provides an overview of the architecture, highlighting the relationships and dependencies between components. This chapter also introduces the decentralised 5/6-corner model adopted in Oman, and describe the end-to-end message choreographies, including the use of the Tax Data Document (TDD) and PINT semantic structures.

Chapter 3 describes the Portal, end-user registration and SMP management.

Chapter 4 describes the B2C solution and the associated QR code.

Chapters 5 through 7 examine the major architecture roles - C2, C3, and C5 - from their respective operational perspectives:

- Chapter 5 focuses on C2 (access points)
- Chapter 6 on C3 (access points)
- Chapter 7 on C5 (tax authority endpoints and validation services).

Chapter 8 provides information on the C6 TDD consolidation and the TDD status

Chapter 9 provides a matrix of the relevant document (PINT, TDD and MLS) flows.

Chapter 10 presents the identifiers and specifications for the artefacts used within the solution, including message formats, transport profiles, and metadata structures.

Chapter 11 covers transmission protocols and routing logic.

Chapter 12 addresses security mechanisms.

Appendix A lists references used in developing this document

Appendix B defines key terms used throughout the document

Appendix C SLR/SLA timings

Appendix D QR code implementation

## 2 Peppol Logical Architecture View

As the generic foundation of this Solution Reference Architecture, the Conceptual and Logical Peppol Architecture follows below. This chapter presents the generic concepts used as a starting point in the subsequent chapters.

The foundational elements of the Peppol Network Conceptual Exchange Architecture is shown in Figure 1below:

> *[Figure 1: Peppol Network Conceptual Exchange Architecture]*

The exchange is divided into two phases:

1. Exchange Prerequisite: Capability and addressing registration (one-time setup per receiver)
2. Business Document Exchange: Choreography of Sending a Business Document from C1 via C2 and C3 to C4 using Dynamic Discovery.

The following sections discuss the application of this generic document exchange pattern to Business Documents (including invoices) and Message-Level Status (MLS) messages.

### 2.1 Business Document Exchange

In the context of a particular Business Document Exchange, the components shown in Figure 2 are used.

> *[Figure 2: Peppol Logical Exchange Architecture]*

The components shown in blue including C2 and C3 fall into the scope of standardisation within the Peppol network; interaction between C1/C2 and C3/C4 are bilaterally agreed between the respective parties. The standardised exchange architecture isolates the choices on sending and receiving side from each other, enabling optimization independent of each other while enabling competition between access points.

#### 2.1.1 Business Document Prerequisite

As a prerequisite for any document exchange, C3 has to register Business Document receiving capabilities of C4, but also the AP (C3) end-point and the public key of the C3 Certificate.

#### 2.1.2 Normal Business Document flow

The document flow for Oman PINT Invoices / Credit Notes over the Peppol network is shown in Figure 3 below.

> *[Figure 3: Normal Business Document flow]*

The model shows the 4-corner model, with the invoice data (in an agreed format) moving from the supplier C1 to the supplier’s AP C2. C2 then prepares and validates the Oman PINT invoice and sends to the buyer’s AP C3, C3 validates the invoice then sends the final invoice (in an agreed format) to the buyer (C4).

### 2.2 Overview of CTC Logical Architecture

The Oman solution will also include sending tax reports to the OTA. The Logical Architecture of this 5-corner model can be divided into three different exchanges:

1. Invoice exchange: C1 → **C2 → C3** → C4 transmitting an Invoice
2. Tax Data exchange: (C1 →) **C2 → C5** → C6 transmitting a TDD
3. Tax Data exchange: (C4 ←) **C3 → C5** → C6 transmitting a TDD

Where each exchange above can be seen as normal Peppol eDelivery exchange of a Business Document in the middle of the standardised 4-corner model. This means that general requirements, trust model, dynamic discovery, transmission from the Peppol 4-corner model are reused.

The IT Architecture is shown in Figure 4 below:

> *[Figure 4: Full model with exchange from C1 to C6]*

Note: Sequencing of the C2->C3 invoice and C2->C5 TDD submission is in parallel.

### 2.3 Message-Level Status (MLS) Exchange

In the addition to direct synchronous feedback on the protocol layer, the Peppol network standardised message handling requires communication on its result between the participating access points (AP). This is distinct from any business-level follow-up exchanged between the End Users as the business process continues.

The main motivation is to share information within the standardised exchanges to avoid manual research and operational interaction in case of compliance deviations.

> *[Figure 5: MLS placement in Response/Status hierarchy]*

As seen in Figure 5 above, the MLS (invoice) is a status between C3 and C2 covering the Receiver (C3) status of the compliance in relation to Peppol Business Document Specifications and onwards delivery towards C4.

While the Business Document flows from C1 to C4, the MLS (invoice) is from C3 to C2. It is transmitted using the same Dynamic Discovery and transmission method as a Business Document (note: the Corners from the Original Business Document exchange are being used i.e. here C3 as Sender and C2 as Receiver of MLS). Shown in Figure 6:

> *[Figure 6: Peppol MLS Exchange Architecture]*

The MLS (TDD) is also used by C5 to report status of the tax report, sent from C5 to the sending AP (C2 / C3). Shown in Figure 7.

> *[Figure 7: TDD MLS eDelivery]*

#### 2.3.1 MLS Prerequisite

As prerequisites for any MLS exchange:

- C2 / C3 has to register its MLS receiving capabilities and receiving endpoint, along with its certificate.
- C2 / C3 must request an MLS for every transmission by including an MLS_TYPE business scope element with the value ALWAYS_SEND in the SBDH Scope/InstanceIdentifier (see Peppol MLS Specification) when sending business documents.

The components C2 and C3 fall into the scope of standardisation within the Peppol network. In the case of the MLS exchanges, only standard components are involved:

The MLS (invoice) is sent from C3 to C2 to confirm the status of the invoice submission from C2 to C3.

The MLS (TDD) is sent from C5 to C2 / C3 to confirm the status of the TDD submission from the sending AP to C5.

The response codes for MLS are shown in the below table:

| Code | Name/Description ([Peppol Docs](https://docs.peppol.eu/edelivery/specs/mls/v1.0.0/mls/trn-mls/codelist/ResponseCode/)) | Meaning for 5 corner model (see note) |
|---|---|---|
| AP | Message delivered towards C4 with confirmation | Delivery succeeded towards the final recipient of the relevant exchange, either C4 for invoice or C6 for a TDD and confirmation was received. |
| AB | Message delivered towards C4 without confirmation | Delivery succeeded towards the final recipient of the relevant exchange, either C4 for invoice or C6 for a TDD, but no confirmation was received. |
| RE | Message rejected or delivery towards C4 failed | Delivery failed, or message was rejected at C3 or C5. |

Note: The 5/6-corner model is two 4-corner models, and the MLS codes support the 4 corner model so they can also be used by C3 or C5 (the receiving Access Point will know who sent the MLS):

1. Invoice exchange: C1 → **C2 → C3** → C4
2. Tax Data exchange: (C1 →) **C2 → C5** → C6
3. MLS (TDD) exchange: (C4 ←) **C3 ← C5** | C6

### 2.4 Peppol Network Specifications

The Peppol Post-Award Architecture is defined by these generic Peppol Specifications:

**Peppol eDelivery Network [eDN]**

- Peppol Discovery
- DNS/SML [SML]
- SMP [SMP]
- Peppol Transmission (AS4)
- Peppol MLS [MLS]

**Peppol Code Lists [CL]**

Peppol Code Lists [CL] provide the authoritative references for identifiers and values used within the Peppol eDelivery Network, ensuring consistency and compliance across Access Point implementations [APL]

**Peppol Policies and Specifications relevant for this context:**

- [eDN] Peppol eDelivery Network documentation: https://docs.peppol.eu/edelivery/
  - Peppol Discovery
    - SML
    - SMP
  - Peppol Transmission
    - AS4
    - MLS
  - Peppol Policy for use of Peppol Network (PPfuoPN)
  - Peppol Policy for Use of Identifiers (PPfuoI)
- [CL] Peppol eDelivery Code List: https://docs.peppol.eu/edelivery/codelists/
- [APL] List of sample AP products: https://peppol.org/tools-support/links-to-software/
- [PoA] Peppol Post Award Documentation: https://peppol.org/documentation/technical-documentation/post-award-documentation/
- [PINT] and [TDD] Oman Specifications for PINT:<br>https://test-docs.peppol.eu/pint/pint-om/2026-Q2-v1.0.1/

## 3 Portal

Oman’s eInvoicing framework includes a portal responsible for managing service provider accreditation related processes, and processes for managing the C1/C4 and service provider relationships. Oman will also maintain its own centralised SMP [SMP], which will be used for all domestic participant records.

> *[Figure 8: Oman SMP / SML participant lookup]*

### 3.1 Service provider and Tax Payer setup

Access is given to the Fawtara Portal once a business obtains commercial registration (any legal type) in Oman. If commercial registration is not obtained then the service provider must use a local distributer (with an Oman commercial registration) to submit the application. They can then submit an application to express interest to operate as an access point.

Upon receiving full accreditation, the service provider is listed on the Fawtara Portal as an accredited service provider / access point for Oman. When the C1/C4 accesses the Fawtara Portal, also via Single Sign-On (SSO) using their Tax Authority portal credentials, they can select an accredited service provider to act as their Access Point for the Peppol eDelivery network. This relationship is initiated by the TP sending a connection request to the AP via the portal.

### 3.2 Relationship management

The Oman Peppol Authority has developed mechanisms to manage taxpayer-service provider relationships on the Fawtara Portal and Oman SMP, which include the portal:

- Providing a trust mechanism linking taxpayers to accredited service providers.
- Allowing taxpayers to connect, disconnect from and reconnect with service providers.
- Enabling service providers to effectively manage their connections with taxpayers.
- Facilitating participant management with the Oman SMP system through the provision of SMP management APIs.

Note: Service providers (C2/C3) must be accredited by the Oman Peppol Authority before they can receive portal link requests from taxpayers (C1/C4).

<u>Steps for Tax Payer / Portal / SMP / SML registration:</u>

1. **Step 1**
   - a. Service Provider (**SP**) accesses Portal to register interest in accreditation
   - b. OTA and **SP** complete the accreditation processes
   - c. SP is listed in Portal as an accredited access point
2. **Step 2**
   - a. The Tax Payer (TP) C1/C4 accesses the Fawtara Portal and selects a **SP**
   - b. The **SP** receives a request from the **Portal** to accept the TP request
   - c. The **SP** accesses the portal to accept the request
   - d. The **SP** then contacts the TP for onboarding (outside Portal) to agree:
      - i. receiving capabilities (PINT Invoice / Credit Note, Self-Billing Invoice / Credit Note etc.) \*
      - ii. TP’s Tax authority (see section 10.3.2)
      - iii. C1-C2 Bi-lateral data submission format (outside Peppol)
      - iv. C3-C4 Bi-lateral data submission format (outside Peppol)
3. **Step 3**
   - a. The SP registers the TP Participant ID (PID) and Service Group (SG) on the SMP
      - i. This step is validated by the portal (see Figure 9)
   - b. The SP updates the SMP SG with TP receiving capabilities (PINT INV / CN, SB INV / CN etc.)
      - i. This step is validated by the portal (see Figure 9)
   - c. **SMP** tells the SML to create the PID lookup record (see box below Figure 9)

      (SML verifies uniqueness of the PID)

\* If the SP and the TP do not reach an agreement before the SP accepts the request then the TP can withdraw the request. Once the SP accepts the request then a disconnection request need to be initiated in the portal by the TP.

> *[Figure 9: Oman Portal / SMP architectural overview]*

> **SML participant lookup record**
>
> After updating the Taxpayer’s metadata (PID and SG) in the OM SMP, the OM SMP then sends a SOAP request to the SML’s management interface:
>
> - createBusinessIdentifier
> - deleteBusinessIdentifier
> - updateBusinessIdentifier
>
> This request contains only:
>
> - The Participant ID
> - The Oman SMP’s domain name (that hosts the metadata)
>
> The SML authenticates the request using the SP’s Authorisation Certificate.
>
> The SML creates or updates the DNS entry:
>
> - CNAME record pointing the Participant ID to the SMP’s hostname.

> *[Figure 10: Oman Portal / SMP sequence diagram]*

### 3.3 Testing environment

The below Figure 10 shows the configuration of the OTA / OpenPeppol testing environment.

> *[Figure 11: Oman Testing environment]*

### 3.4 Operational

The Accredited Service Provider must add the C1/C4 to the central SMP within 3 working days of establishment of a relationship on the Fawtara Portal.

When relationship on the Fawtara Portal ceases to exist/is terminated, the Accredited Service Provider has 1 working day to deregister the C1/C4 from the central SMP.

When C1/C4 decides to switch from one Accredited Service Provider to another, the existing Accredited Service Provider must deregister C1/C4 from the central SMP within 1 working day and the new Accredited Service Provider must add C1/C4 to the central SMP within 3 working days.

## 4 B2C and QR code

### 4.1 Oman B2C invoicing

The Oman eInvoicing solution includes a B2C component, this will usually be buyer receiving a human readable invoice, sent outside of Peppol, plus the submission of a TDD sent by the seller’s AP, with an embedded Simplified OM PINT Invoice (see section 10.2.1).

To comply with OM PINT mandatory fields fixed values will be used for the buyer, who will not be registered (or identified as registered) in Peppol. Notes:

1. Typically, B2C sales are low value transactions.
2. Some of these transactions could be B2B registered Peppol buyers, but will still be treated as a B2C transaction. For example, a company purchasing stationary at a store.
3. For certain sales - usually involving higher valued items, more information will be collected by the seller (even for B2C), including details of the buyer. For example, a mobile phone purchase where warranty may be applicable. In this case a different invoice type would apply.
4. Proft Margin – OM PINT (not simplified) invoice (see section 10.2.1)

> *[Figure 12: Oman B2C transaction diagram]*

### 4.2 QR code

In B2C transactions the seller will provide the consumer with a readable invoice (printed or via email), this invoice will include a QR code.

See invoice example:

> *[Figure 13: Oman B2C readable invoice]*

The QR code on a B2C invoice (full or simplified) must contain the following:

| Field | Value |
|---|---|
| QR Version | 1.1<br>[value is fixed but subject to be changed by OTA, new version may suggest new structure] |
| Invoice Type | 01<br>[Fixed value] |
| IBT-001 Invoice Number | Invoice Number<br>[Unique sequential number of the invoice] |
| IBT-027 (seller name) | Name of the Seller (C1) |
| IBT-031 (VATIN of the seller) | VATIN of the Seller (C1) |
| IBT-002 (invoice date) | Invoice issuance date<br>Date format YYYY-MM-DD |
| IBT-168 (time stamp) | Invoice issuance time<br>Time format hh:mm:ss |
| IBT-112 (invoice total amount – incl. VAT) | The invoice total amount with VAT |
| IBT-110 (VAT total) | VAT total of the invoice |
| BTOM-004: (Seller UUID) | Seller UUID generated by C1 based on all the above-mentioned fields<br>• using the namespace defined in section 10.2.3 |

The QR code on a Profit Margin invoice must contain the following:

| Field | Value |
|---|---|
| QR Version | 1.1<br>value is fixed but subject to be changed by OTA, new version may suggest new structure ] |
| Invoice Type | 02<br>[Fixed value] |
| IBT-001 Invoice Number | Invoice Number<br>[Unique sequential number of the invoice] |
| IBT-027 (seller name) | Name of the Seller (C1) |
| IBT-031 (VATIN of the seller) | VATIN of the Seller (C1) |
| IBT-002 (invoice date) | Invoice issuance date<br>Date format YYYY-MM-DD |
| IBT-168 (time stamp) | Invoice issuance time<br>Time format hh:mm:ss |
| BTOM-020 (Total amount due) | Total amount of the profit margin invoice including VAT |
| BTOM-004 (Seller UUID) | Seller UUID generated by C1 based on all the above mentioned fields<br>• using the namespace defined in section 10.2.3 |

See Appendix D for examples on how to generate the Seller UUID.

### 4.3 PINT for B2C

The B2C transactions will not use a different invoice; it will still be a PINT Invoice. The PINT has mandatory fields for the buyer, which will not be available to C1, so C2 will populate generic values for these fields so that PINT validation is successful.

B2C relevant fields:

| OM PINT ID | Business term | Description | Value |
|---|---|---|---|
| IBT-044 | Buyer name | The full name of the Buyer. | ‘General customer’ |
| IBT-055 | Buyer country code | A code that identifies the country. | ‘OM’ |
| IBT-001 | Invoice number | A unique identification of the Invoice. | Generated by Seller |
| BTOM-004 | Seller UUID | Code generated by the seller (C1) and passed to C2 as part of the invoice data, for inclusion on the Invoice / TDD (new field – see 10.2.3) | Generated by seller |
| IBT-049 | Buyer identifier | An identifier of the Buyer.<br>• Dummy participant ID (see 10.3.1)<br>Note: For profit margin invoices the IBT-046 will be the identifier and the dummy PID will still be used | 0248: 997770000099 |

The following business rules will need creating for B2C invoices.

| Rule name | Rule description |
|---|---|
| IBR-173-OM | If Buyer identifier (IBT-049) is '997770000099', Seller UUID (BTOM-004) MUST be present. |

The following Figure 14shows the B2C purchase through to TDD submission to the OTA.

> *[Figure 14: B2C sequence diagram]*

Note: the supplier should send the invoice to their AP within 24 hours.

## 5 C2 Capabilities

### 5.1 C2 Prerequisites

#### 5.1.1 Registration in the Dynamic Discovery

C2 **MUST** register receiving capabilities in the SMP for its own appropriate endpoint by its:

- Service Provider Identification (see section 10.3.3)

With the following Document Type:

- Message Level Status (MLS) [MLS] (see 2.3)

#### 5.1.2 Functional

C2 **MUST** be able to generate UUIDs for Invoice and TDD instances and Transmission Instances.

C2 **MUST** be able to create Invoice and Credit Note Business Documents based on Oman PINT Billing and exchange with C3.

C2 **MUST** be able to prepare an Invoice and Credit Note by enriching an Oman PINT invoice provided by C1 - by enriching the C1 invoice with UUID etc.

C2 **MAY** be able to create Self-billed Invoice and Self-billed Credit Note Business Documents based on Oman PINT Billing and exchange with C3.

C2 **MUST** be able to create an OM TDD from Invoice Business Documents and exchange with C5.

C2 **MUST** be able to prepare a TDD by enriching an Oman TDD provided by C1 - by enriching the C1 TDD with the Invoice UUID and the C1’s Tax Authority Participant ID (see section 10.3.2).

C2 **MUST** be able to handle incoming MLS for invoice and TDD.

C2 **MUST** be able to confirm any C1 provided TDDs are a correct representation of the matching Invoice.

### 5.2 C2 flow capabilities

C2 follows the flow depicted in the below sequence diagram:

> *[Figure 15: C2 CTC transmissions]*

1. C2 receives the Invoice in an internal (C1-C2) agreed format (one of the formats could be Oman PINT invoice / Oman TDD) and by internal means of communication and generates:
   - An Oman PINT Invoice
   - An Oman TDD
2. Exchanges in parallel:
   - An Oman PINT Invoice (C2 → C3 flow)
   - An Oman TDD (C2 → C5 flow)
3. Processes the integrated result of the exchanges based on the MLSes received
4. Make all of the PINTs / TDDs available to C1 for auditing and archiving.

Note: Invoice and tax data is stored in transit and then removed, this usage is covered by GDPR (subject to agreement between C1/C2).

### 5.3 C2 → C3 Flow

Follows the normal Peppol Business Document exchange with the following additions:

- C2 **MUST** generate UUID (see 10.2.3) for the Invoice Business Document (see section 10.2.2) to be included in the PINT Invoice and also the TDD Business Document as a reference to the PINT Invoice.
- C2 **MUST** request an MLS for every transmission by including an MLS_TYPE business scope element with the value ALWAYS_SEND in the SBDH of the Invoice transmission (see Peppol MLS Specification). C2 expects to receive a corresponding MLS in response to transmission of the Invoice Business Document.

The following Invoice Business Document types (see section 10.2.1) **MUST** be exchanged with C3 dependent on C3 registrations in the Dynamic Discovery:

- Oman PINT Billing; Invoice and Credit Note
- Oman PINT Self-Billing, Invoice and Credit Note.

#### 5.3.1 Exceptions (see section 5.6)

1. Negative MLS (Validation error)
   - C3 has (in the MLS) informed C2 that the Invoice Business Document did not validate correctly.
2. Negative MLS (C4 not reachable)
   - C3 has (in the MLS) informed C2 that it could not deliver the Invoice Business Document to C4.
3. MLS not received
   - C2 has not received an MLS from C3 within the timeout defined as SLR [PNP_SLR] in the applicable Operational Guidelines.

### 5.4 C2 → C5 Flow (same as C3 → C5 flow)

Follows the normal Peppol Business Document exchange with the following additions:

- C2 **MUST** generate UUID (see 10.2.3) and include in the TDD Business Document (see section 10.2.2).
- C2 **MUST** use the UUID determined for the Invoice Business document and include in the TDD Business Document as reference.
- C2 **MUST** request MLS `ALWAYS_SEND` in the SBDH of the transmission (see Peppol MLS Specification) i.e. expects to receive an MLS in response to transmission of the TDD Business Document.

The following TDD Business Document type (see section 10.2.210.2.1) **MUST** be exchanged with C5:

- Oman TDD

#### 5.4.1 Exceptions (see section 5.6)

1. Negative MLS (Validation error)
   - C5 has in the MLS informed C2 that the TDD Business Document did not validate correctly.
2. MLS not received
   - C2 has not received an MLS from C5 within the timeout defined as SLR [PNP_SLR] in the applicable Operational Guidelines.

### 5.5 C2 - non Peppol domestic

The C1 sender (Oman seller) Participant Identifier Scheme is 0248.

The C4 receiver (Oman buyer non-Peppol) Participant Identifier Scheme is 0248 and the submission will use a “Substitute participant” identifier (see section 10.3.1).

Follows the normal Peppol Invoicing exchange with the following additions/omissions:

- C2 **MUST** have MLS receiving capabilities
- C2 **MUST** generate UUID for the Invoice Business Document (see section 10.2.2)
- C2 **MUST** exchange an OM TDD with C5
- C2 **MUST** not exchange an Invoice Business Document with a C3.

> *[Figure 16 C2 to OM domestic (non Peppol) recipient]*

### 5.6 C2 Summary

The following is a summary of all C2 scenarios:

| C2 viewpoint | Exchange | Action | Exception |
|---|---|---|---|
| **Prerequisite** | To SMP | Register:<br>• MLS | |
| **Domestic (Peppol)** | Prerequisite to sending in parallel to C5 and C3 | • Generate UUID. | |
| | To C3 | • Use generated UUID.<br>• Exchange Invoice Business Document.<br>• Receive MLS. | **Negative MLS (Validation error)**<br>• C2 must resolve the exception.<br>• Exchange Invoice Business Document.<br>• Exchange “Disregard” TDD Business Document with C5.<br><br>**Negative MLS (C4 not reachable)**<br>• Exchange “Disregard” TDD Business Document with C5.<br>• C2 must resolve the exception.<br><br>**MLS not received within C3 SLA**<br>• C2 must resolve the exception.<br>• Retry Exchange Invoice Business Document. |
| | To C5 | • Use generated UUID.<br>• Exchange “Submit” TDD Business Document.<br>• Receive MLS. | **Negative MLS (Validation error)**<br>• C2 to resolve the exception.<br>• Exchange “Resubmit” TDD Business Document with C5.<br><br>**MLS not received within C5 SLA**<br>• C2 to resolve the exception.<br>• C2 check C5 status.<br>• Retry exchange “Submit” TDD Business Document until MLS is Received. |
| **Domestic (non Peppol)** | To C5 | • Generate UUID.<br>• Exchange TDD Business Document.<br>• Receive MLS. | **Negative MLS (Validation error)**<br>• C2 to resolve the exception.<br>• Exchange “Resubmit” TDD Business Document with C5.<br><br>**MLS not received within C5 SLA**<br>• C2 to resolve the exception.<br>• C2 check C5 status.<br>• Retry exchange “Submit” TDD Business Document until MLS is Received. |
| **International (Peppol)** | Prerequisite to sending in parallel to C5 and C3 | • Generate UUID. | |
| | To C3 | • Use generated UUID.<br>• Exchange International Invoice Business Document.<br>• Receive MLS. | **Negative MLS (Validation error)**<br>• C2 must resolve the exception.<br>• Exchange Invoice Business Document.<br>• Exchange “Disregard” TDD Business Document with C5.<br><br>**Negative MLS (C4 not reachable)**<br>• C2 must resolve the exception.<br>• Exchange “Disregard” TDD Business Document with C5.<br><br>**MLS not received within SLA**<br>• C2 must resolve the exception.<br>• Retry Exchange Invoice Business Document. |
| | To C5 | • Use generated UUID.<br>• Exchange TDD Business Document.<br>• Receive MLS. | **Negative MLS (Validation error)**<br>• C2 to resolve the exception.<br>Exchange “Resubmit” TDD Business Document with C5.<br><br>**MLS not received within C5 SLA**<br>• C2 to resolve the exception.<br>• C2 check C5 status.<br>• Retry exchange “Submit” TDD Business Document until MLS is Received. |
| **International (non-Peppol)** | Prerequisite to sending in parallel to C5 and C3 | • Generate UUID. | |
| | To C3 | • “Substitute participant” identifier used for Receiver (C4)<br>• No exchange. | N/A |
| | To C5 | • Use generate UUID.<br>• Exchange “Submit” TDD Business Document.<br>• Receive MLS. | **Negative MLS (Validation error)**<br>• C2 to resolve the exception.<br>• Exchange “Resubmit” TDD Business Document.<br><br>**MLS not received**<br>• C2 to resolve the exception.<br>• C2 check C5 status.<br>• Retry Exchange “Disregard” TDD Business Document until MLS is Received. |

## 6 C3 Capabilities

### 6.1 C3 Prerequisites

#### 6.1.1 Registration in the Dynamic Discovery

C3 **MUST** be able to support the following Invoice Document Types and register receiving capabilities in the SMP for its C4 identified by:

- Participant Identifier Scheme and Participant Identifier (see section 10.3.1)

With the following Document Types:

- Oman PINT (see section 10.2.1) Invoice – Mandatory receiving capability for Oman (domestic) invoicing organisations
- Oman PINT (see section 10.2.1) Credit Note – Mandatory receiving capability for participating Oman (domestic) invoicing organisations

C3 **MAY** be able to support the following Invoice Document Types and register receiving capabilities in the chosen SMP for its C4 identified by:

- Participant Identifier Scheme and Participant Identifier (see section 10.3.1)

With the following Document Types:

- Oman PINT (see section 10.2.1) Self-billed Invoice – Optional receiving capability for participating invoicing organisations
- Oman PINT (see section 10.2.1) Self-billed Credit Note – Optional receiving capability for participating invoicing organisations

C3 **MUST** register receiving capabilities in the SMP for its own appropriate endpoint by its:

- Service Provider Identification (see section 10.3.3)

With the following Document Type:

- Message Level Status (MLS) [MLS] (see 2.3)

#### 6.1.2 Functional

C3 **MUST** be able to validate (using the Schematrons) the Invoice Business Documents, that it has registered in the Dynamic Discovery on behalf of its End-Users.

C3 **MUST** be able to create an OM TDD from Invoice Business Documents, that it has registered in the Dynamic Discovery on behalf of its End-Users, and exchange with C5.

C3 **MUST** be able to create an MLS from the result of validation of the Invoice Business Documents, that it has registered in the Dynamic Discovery on behalf of its End-Users, and exchange with C2.

C3 **MUST** be able to handle incoming MLS for TDD.

### 6.2 C3 flow capabilities

C3 follows the flow depicted in the sequence diagram:

> *[Figure 17: C3 CTC transmissions]*

The flow depicted in the sequence diagram comprises these steps:

1. C3 receives Oman PINT Invoice in the exchange with C2.
2. C3 Validates the Oman PINT Invoice and generates:
   - An Oman TDD (see note)
   - internal (C3-C4) format
3. Exchanges in parallel:
   - Invoice in internal (C3-C4) format to C4 by internal means of communication
   - An Oman TDD (C3 → C5 flow) (see note)
   - Generate and send Peppol MLS with (C3 → C2 flow)
4. Process the result of the parallel exchanges
5. Make all of the Invoices (the original from C2) / TDDs and the Invoice / TDD MLSs available to C4 for auditing and archiving

Note: Invoice and tax data is stored in transit and then removed, this usage is covered by GDPR (subject to agreement between C4/C3).

### 6.3 C3 → C5 Flow (same as C2 → C5 flow)

Follows the normal Peppol Business Document exchange with the following additions:

- C3 **MUST** generate UUID (see 10.2.3) and include in the TDD Business Document (see section 10.2.2).
- C3 **MUST** use UUID from the Invoice Business Document and include in the TDD Business Document as reference (see section 10.2.2).
- C3 **MUST** request MLS `ALWAYS_SEND` in the SBDH of the transmission (see Peppol MLS Specification) i.e. expects to receive an MLS in response to transmission of the TDD Business Document.

The following TDD Business Document type (see section 10.2.2) **MUST** be exchanged with C5:

- Oman TDD

#### 6.3.1 Exceptions (see section 6.6)

1. Negative MLS (Validation error)
   - C5 has in the MLS informed C3 that the TDD Business Document did not validate correctly.
2. MLS not received
   - C3 has not received an MLS from C5 within the timeout defined as SLR [PNP_SLR] in the applicable Operational Guidelines.

### 6.4 C3 → C2 Flow

Follows the normal Peppol MLS exchange.

#### 6.4.1 Exception (see section 6.6)

1. Negative Validation of Invoice Business Document
   - C3 validation of the Invoice Business Document did not complete correctly.

### 6.5 C3/C4 – Peppol/non-Peppol International

The scenario concerns the exchange of invoices where the sending organisation is outside Oman and the receiving organisation is within Oman. It covers both Peppol exchange and using other means of exchange e.g. mail.

> *[Figure 18: C3 Peppol/non-Peppol International]*

The import invoice (Peppol or non-Peppol) cannot be used to produce a TDD by Oman Invoice Receivers, because it cannot be guaranteed that the Invoice can provide all required data necessary for creating an OM TDD. However, in this case the Buyer’s C3 will produce a self-billed PINT Invoice to be able to generate a TDD for tax reporting purposes.

Note: this SB PINT invoice will not be sent to the original seller, it is only used to generate the TDD.

### 6.6 C3 Summary

The following is a summary of C3 scenarios:

| C3 viewpoint | Exchange | Action | Exception |
|---|---|---|---|
| **Prerequisite** | To SMP | Register:<br>• Invoice Business Documents<br>• MLS | |
| **Domestic** | From/To C2 | • Receive Invoice Business Document.<br>• Validate exchanged Invoice Business Document.<br>• Exchange MLS. | **Validation error**<br>• Exchange negative MLS with C2.<br>• Note: No TDD is exchanged with C5<br><br>**C4 not reachable**<br>• Exchange negative MLS with C2<br>• Note: No TDD is exchanged with C5 |
| | To C5 | • Exchange “Submit” TDD Business Document.<br>• Receive MLS. | **Negative MLS (Validation error)**<br>• C3 to resolve the exception.<br>• Exchange “Resubmit” TDD Business Document with C5.<br><br>**MLS not received within C5 SLA**<br>• C3 to resolve the exception.<br>• C3 check C5 status.<br>• Retry exchange “Submit” TDD Business Document until MLS is Received. |
| **Domestic (non Peppol)** | To C5 | • Exchange “Submit” TDD Business Document.<br>• Receive MLS. | **Negative MLS (Validation error)**<br>• C3 to resolve the exception.<br>• Exchange “Resubmit” TDD Business Document with C5.<br><br>**MLS not received within C5 SLA**<br>• C3 to resolve the exception.<br>• C3 check C5 status.<br>• Retry exchange “Submit” TDD Business Document until MLS is Received. |
| **International (Peppol)** | From/To C2 | • Receive Invoice Business Document.<br>• Validate exchanged Invoice Business Document.<br>• Exchange MLS. | N/A |
| | To C5 | • No Exchange. | N/A |
| **International (non-Peppol)** | To C5 | • No Exchange. | N/A |
| | To C2 | • No exchange. | N/A |

## 7 C5 Capabilities

### 7.1 C5 Prerequisites

#### 7.1.1 Registration in the Dynamic Discovery

C5 **MUST** register receiving capabilities in the SMP for the OTA (C6) identified by:

- OTA (C6) Identifier (see section 10.3.2)

With the following Document Types:

- Oman Tax Data Document (see section 10.2.1)

Note: C5 must be registered in the Oman SMP as an AP with one customer (C6).

#### 7.1.2 Functional

C5 **MUST** be able to validate the Oman TDD Business Document (using the Oman TDD Schematron - and not for Tax compliance etc.).

C5 **MUST** be able to create an MLS from the result of validation of the TDD Business Document and exchange with C2 or C3.

### 7.2 C5 flow capabilities

> *[Figure 19: C5 CTC transmissions]*

C5 follows the flow depicted in the sequence diagram:

1. C5 receives Oman TDD in the exchange with C2 or C3.
2. C5 Validates the Oman TDD and transmit to C6 the Tax Data in internal (C5-C6) format and by internal means of communication, and generates:
   - a. A Peppol MLS
3. Exchanges:
   - a. A Peppol MLS (C5 → C2/C3 flow)

### 7.3 C5 → C2/C3 Flow

Follows the normal Peppol MLS exchange.

#### 7.3.1 Exception (see section 7.4)

1. Negative Validation of TDD Business Document
   - The validation of the TDD Business Document did not validate correctly.

### 7.4 C5 Summary

The following is a summary of C5 scenarios:

| C5 viewpoint | Exchange | Action | Exception |
|---|---|---|---|
| **Prerequisite** | To SMP | Register:<br>• TDD Business Document | |
| **Domestic**<br>**International (Peppol)**<br>**International (Non-Peppol)** | From/To C2 | • Receive TDD Business Document.<br>• Validate exchanged TDD Business Document.<br>• Exchange MLS. | **Validation error**<br>Exchange negative MLS with C2 or C3.<br>Abort Processing. |
| | From/To C3 | • Receive TDD Business Document.<br>• Validate exchanged C3 to C5 TDD Business Document.<br>• Exchange MLS. | **Validation error**<br>Exchange negative MLS with C3.<br>Abort Processing. |

## 8 C6 TDD submission statuses

The following model outlines the Tax Data Document (TDD) status transitions applicable to the OTA (C6).

> *[Figure 20: C6 TDD submission notifications]*

#### 8.1.1 Normal

*Table 1 C6 Normal consolidation table*

| TDT006 received | Situation | action |
|---|---|---|
| Submit | No other TDD has been received on this Invoice | Use TDD |
| Resubmit | A previous TDD has been received on this TDD.<br>i.e. a new TDD has been received to replace an old TDD | Mark old (previously received) TDD as “Disregard” and use new TDD as current instead |
| Disregard | A previous TDD has been received on this TDD.<br>i.e. an older TDD has been received against an existing newer TDD (potentially due to a delay) | Mark the older (but most recently received) TDD as “Disregard” |

#### 8.1.2 Exception

*Table 2 C6 Exception consolidation table*

| TDT006 received | Situation | action |
|---|---|---|
| Submit | A previous TDD has been received on this TDD | Replace old TDD with new TDD (Treat as “Resubmit”) |
| Resubmit | No other TDD has been received on this Invoice<br>(Exception of C2/C3) | No previous TDD to replace (Treat as “Submit”) |
| Disregard | A previous TDD has not been received on this TDD<br>(Exception of C2/C3) | No action |

## 9 Oman document flows

The flow of all documents in the Peppol network are shown for Oman:

- Domestic B2B / Domestic B2C
- import / export
- Profit margin

*Table 3 OM Peppol document flows*

| ID | Jurisdiction | Scenario | document flows |
|---|---|---|---|
| 1 | OM domestic B2B | Seller and Buyer both VAT registered and part of the eInvoicing network in Oman | C2->C3 Invoice<br>C2->C5 TDD-OM<br>C3->C5 TDD-OM<br>C3->C2 MLS<br>C5->C2 MLS<br>C5->C3 MLS |
| 2 | OM domestic B2B | Only seller is VAT registered. Buyer is not VAT registered hence cannot be part of the eInvoicing network | C2->C5 TDD-OM<br>C5->C2 MLS |
| 3 | OM domestic B2B | Only buyer is VAT registered (for example in case of Profit margin self-invoice) | C3->C5 TDD-OM<br>C5->C3 MLS |
| 4 | OM domestic B2B | Only seller is registered in Oman SMP and part of the eInvoicing network currently. The buyer (although VAT registered) is not part of the eInvoicing implementation roll out and hence not on the Oman SMP | C2->C5 TDD-OM<br>C5->C2 MLS |
| 5 | OM domestic B2B | C4 not reachable (but registered in Oman SMP) | C2->C3 Invoice<br>C2->C5 TDD-OM<br>C5->C2 MLS<br>C3>C2 MLS (negative)<br>C2->C5 TDD-OM(D) |
| 6 | Profit margin | Only seller is VAT registered OR both seller and buyer are VAT registered | C2->C5 TDD-OM<br>C5->C2 MLS |
| 7 | OM domestic B2B – Self Billed | Seller and Buyer both VAT registered and part of the eInvoicing network in Oman.<br>For self billing the buyer (C1 in this case) is sending the invoice (via C2) to the seller (C4) via (C3) | C2->C3 Invoice<br>C2->C5 TDD-OM<br>C3->C5 TDD-OM<br>C3->C2 MLS<br>C5->C2 MLS<br>C5->C3 MLS |
| 8 | OM domestic B2B – Third Party | Seller and buyer are both VAT registered however the seller appoints a VAT registered third party to issue invoices on behalf of the seller.<br>In this case the third party is considered to be C1. Exchange of invoices between the seller and third party must be managed between the seller and the third party. | C2->C3 Invoice<br>C2->C5 TDD-OM<br>C3->C5 TDD-OM<br>C3->C2 MLS<br>C5->C2 MLS<br>C5->C3 MLS |
| 9 | OM domestic B2C | Only seller is VAT registered | C2->C5 TDD-OM<br>C5->C2 MLS |
| 10 | OM domestic B2C – Third Party | Only seller is VAT registered however the seller appoints a VAT registered third party third party to issue invoices on behalf of the seller. In this case the third party is considered to be C1. Exchange of invoices between the seller and third party must be managed between the seller and the third party. | C2->C5 TDD-OM<br>C5->C2 MLS |
| 11 | Export | outbound invoice (Seller is VAT registered in Oman and Buyer is outside Oman) | C2->C5 TDD-OM<br>C5->C2 MLS |
| 12 | Import | inbound invoice (Buyer is VAT registered in Oman and Seller is outside Oman)<br>Note: a SB PINT invoice will be created by C3 to generate the TDD, this will not be sent to the original seller | C3->C5 TDD-OM<br>C5->C3 MLS |

## 10 Identifiers and Specifications

### 10.1 Process Identifiers

Process is the sequence of Document Types being exchanged between stakeholders and therefore define the Choreography. In OM Peppol Invoicing Process the exchange choreographies are:

- C1 → C2 → C3 → C4 (Invoice)
- (C1) → C2 → C5 → OTA (TDD)
- (C4) → C3 → C5 → OTA (TDD)

“Peppol Policy for use of Identifiers” section 6 describes the policies for Process Identifiers [eDN].

Peppol Invoice / TDD Process Identifier Scheme: `cenbii-procid-ubl`

Peppol Invoice Process Identifier Value:

- `urn:peppol:bis:billing`
- `urn:peppol:bis:selfbilling`

Peppol TDD Process Identifier Value:

- `urn:peppol:taxreporting`

All Process Identifications can be found at: https://docs.peppol.eu/edelivery/codelists/ (Processes).

### 10.2 Documents identifiers and Specifications

#### 10.2.1 Invoice Business Document Types

> **Peppol Invoice Business Document Type**
>
> The allowed Invoice Business Documents are:
>
> - Oman PINT – includes document types for Invoice and Credit Note
> - Oman PINT – includes document types for Self-Billed Invoice and Credit Note
>
> Peppol Document Type Identifier Scheme: `peppol-doctype-wildcard`
>
> Peppol Document Type Identifiers (proposed):
>
> - `urn:oasis:names:specification:ubl:schema:xsd:Invoice-2::Invoice##urn:peppol:pint:billing-1@om-1::2.1`
> - `urn:oasis:names:specification:ubl:schema:xsd:CreditNote-2::CreditNote##urn:peppol:pint:billing-1@om-1::2.1`
> - `urn:oasis:names:specification:ubl:schema:xsd:Invoice-2::Invoice##urn:peppol:pint:selfbilling-1@om-1::2.1`
> - `urn:oasis:names:specification:ubl:schema:xsd:CreditNote-2::CreditNote##urn:peppol:pint:selfbilling-1@om-1::2.1`
>
> Specification: **Peppol OM PINT Document:**
>
> `https://test-docs.peppol.eu/pint/pint-om/`

#### 10.2.2 TDD Business Document Type

> **Peppol Tax Data Document**
>
> Peppol Document Type Identifier Scheme: `busdox-docid-qns`
>
> Peppol Document Type Identifier: `urn:peppol:schema:om-taxdata:1.0::TaxData##urn:peppol:taxdata:om-1::1.0`
>
> Specification: **Peppol OM Tax Data Document:**
>
> `https://test-docs.peppol.eu/pint/pint-om/2026-Q2-v1.0.1/`

#### 10.2.3 Business Document and Transmission instances

For traceability and reconciliation of both Invoice and TDD Business Documents, a UUID **MUST** be used.

> **Invoice document unique identifier**
>
> Invoice documents are referenced by a UUID that uniquely identifies it. UUID version 5 [UUID][^1] is used. This means that for the same content (below fields) the invoice has the same invoice UUID, so if the content changes a new UUID is determined. The Invoice UUID is determined by C2 and C3 in the same way. Common programming languages (like Java, Python and C#) have mature libraries for generation of UUID Version 5.
>
> The Invoice UUID is calculated based on a “name” containing values of these invoice fields:
>
> - IBT-034-1 (attribute) scheme ID (mandatory)
> - IBT-034 seller identifier ID (mandatory)
> - IBT-003 invoice type code (mandatory)
> - IBT-001 invoice number (mandatory)
> - IBT-002 invoice date (mandatory)
> - IBT-110 invoice total tax amount (mandatory)
> - IBT-112 invoice total amount with TAX (mandatory)
> - BTOM-020 Total amount due (Profit Margin invoices only)
>
> The **Invoice UUID** (and **Seller UUID** – see section 4.2) MUST use this fixed namespace[^2]:
>
> `e0bc4ac8-b025-46e5-a76d-0c893fc3027e`
>
> Notes:
>
> - The above fields are all mandatory in the invoice (BTOM-020 only for PM invoices), so the invoice will have already been rejected if any of the above are missing, in this case no TDD / UUIDv5 will be created.
> - All leading and trailing whitespace should be removed from above values in the invoice
>   - White space consists of one or more of the following characters: space (#x20), tab (#x9), carriage return (#xD), or line feed (#xA).
> - In the input string each value is separated with a single blank character (' ', ASCII 32)
> - Date format YYYY-MM-DD
>   - The date value MUST be formatted according to the XML Schema date format rules and MUST NOT contain time zone information
> - The UUIDv5 'name' should use the UTF-8 character set.
>
> An example UUIDv5:
>
> - input string: `0248 5060012349998 380 33445566 2026-01-13 100.00 105.00`
> - output string: `b3fc2859-f851-59e4-9ed5-a5dfb9515487`

The Invoice UUID must be generated by the C2 and included in the PINT. C2 and C3 will also include it in the TDD, obtained from the PINT, but the Invoice UUID can also be determined by C3.

- For example; where C3 only submits an OM TDD, and no invoice was received from C2, C3 will be able to create the correct Invoice UUID when it is not provided in a PINT from C2.

> **Tax Data document unique identifier**
>
> TDD documents **MUST** contain a UUID that uniquely identifies it. UUID version 4 [UUID] is used and generated locally by C2 and C3. Common programming language (like Java, Python, C#) have mature libraries for generation of UUID Version 4.
>
> The TDD UUID is placed in the TDD Business Document (TDT-003, Unique Identifier Number).
>
> UUID MUST be represented in hexadecimal value e.g.
>
> `a6bdaeb8-ce06-46ea-8e53-805cc05f3dba`

Note that also the SBDH uses UUID (v4) in element `DocumentIdentification/InstanceIdentifier` to identify the transmission within the Peppol Network.

For traceability of Invoice Business Documents, it must be possible to reference both the Business Document and the Transmission.

- The Transmission is identified by the UUID in the SBDH (see 11.1)
- The Business Document is identified by the Invoice UUID of the Business Document
- The Tax data document is identified by the TDD UUID of the Business Document
- The B2C receipt is identified by the Seller UUID included in the QR code on the (readable) invoice

The following model shows the identification and references to identifications in the different Business Documents and MLS:

> *[Figure 21: Identification and references from other Business Documents]*

- B2C receipt (1a) is determined by the supplier.
- The transmission of the Invoice Business Document identifications (2a) is determined by C2.
- Both, the transmission and the Invoice Business Document are referenced in the TDDs from C2 and C3 (3a and 3b)
- An MLS references the transmission identification of the validated Business Document (3a.M, 2b.M, 3b.M).

UUID fields in the Invoice, TDD and BC2 receipt.

*Table 4 UUID and business documents table*

| UUID | UUID Version | Generated by | PINT Invoice | OM TDD | Receipt |
|---|---|---|---|---|---|
| Seller UUID (1a) | UUID Version 5 | Seller | BTOM-004 | BTOM-004 | In QR code |
| Invoice UUID (2b) | UUID Version 5 | C2 | BTOM-002<br>Also:<br>• BTOM-031<br>• BTOM-014<br>• BTOM-023 | BTOM-002 | No |
| TDD UUID(s) (3a, 3b) | UUID Version 4 | C2, C3 | No | TDT-003 | No |
| Transmission UUID (2b.M, 3a.M, 3b.M) | UUID Version 4 | C2, C3, C5 | No | No | No |

### 10.3 Identification of Participants

#### 10.3.1 End-Users (C1, C4)

The principal basis of identifying End-Users i.e. C1 (sender) and C4 (receiver) Oman participants (organisations):

The usage of the identifier is:

- Inside the Business Documents.
- In the SBDH.
- In the SMP.

Oman issues a VAT Identification Number (12 digits) to organisation that are:

- Oman VAT Registrants

For example; 0248:OM1100003554.

These can act as C1 or C4, with a Participant Identifier Scheme (0248) in the Peppol Participant Identifier Scheme code list [CL].

“Substitute participant ID” identifies domestic / international invoice receivers in non-Peppol Invoice exchanges that are not registered in Peppol (the actual identification of the receiver must be completed by other means).

| No. | Transaction type | Participant scheme ID | Participant Identification |
|---|---|---|---|
| 1 | When the invoice transaction type code is (00000001000000000000) Deemed supply (B2B transactions only) and if invoice must not be shared with the buyer<br>Note: If the invoice is a deemed supply for B2C transactions then fixed value for B2C transactions must be used | 0248 | 997770000096 |
| 2 | When the invoice transaction type code is (00000010000000000000) Exports (receiver not registered in Peppol) | 0248 | 997770000097 |
| 3 | When the Self Billing Invoice (import) is not subject to Oman eInvoicing regulations | 0248 | 997770000098 |
| 4 | Where the invoice relates to a B2C transaction (domestic) and/or relates to profit margin scheme (00000000010000000000) | 0248 | 997770000099 |
| 5 | Where the buyer (domestic) is not part of the Peppol network (excluding. B2C) during partial roll out | 0248 | 997770000095 |

#### 10.3.2 Tax Authorities (C6)

Identifying C6 (OTA)

For the purpose of identifying the OTA, use the Service Provider Identifier Scheme (SPIS) with the ICD code 0242. The Identifier is divided into three parts:

- Service Provider Identifier: the 6 digit “Seat Id” from the Peppol AP Certificate (CN)
  - For example (OM) – 001090
- Use Case: TaxAuthority
- Suffix: “OM”

Example: 001090-TaxAuthority.OM

Note: C1/C2 submits the TDD to C6 (OTA), via the C5 (C6’s AP), C5 registers the receiving capabilities of the C6 (see section 7). C5 then delivers the TDD to C6 in the agreed format, this final delivery is outside the Peppol eDelivery network (like C3 delivering invoice data to C4) – see Figure 4.

#### 10.3.3 Service Providers / Access Points (C2, C3, C5)

Service Provider Identifier Scheme (SPIS) with the ICD code 0242.

For example; 0242:123456 (where the SPIS is combined with the SP seat ID).

## 11 Transmission

### 11.1 SBDH Setup

The transmission of Business Document and MLS messages is performed using Peppol eDelivery AS4.

The Business Document is enveloped in a Peppol SBDH envelope (Standard Business Document Header), which holds the metadata of the transmission e.g. Sender Identifier (C1), Receiver Identifier (C4), Process Identifier, Document Type Identifier and the Country Code of the Sender [eDN].

> *[Figure 22: SBDH XML]*
>
> Transcription of the XML shown in the figure (the figure is headed by a "SBDH | XML Invoice" box; the numbered callouts 1–8 point to the lines marked `[n]` below and correspond to the numbered restrictions that follow; bold/italic placeholders of the figure are shown as plain text):

```xml
<StandardBusinessDocument
xmlns="http://www.unece.org/cefact/namespaces/StandardBusinessDocumentHeader">
  <StandardBusinessDocumentHeader>
    <HeaderVersion>1.0</HeaderVersion>
    <Sender>
      <Identifier Authority="iso6523-actorid-upis">C1_SenderId</Identifier>              [3]
    </Sender>
    <Receiver>
      <Identifier Authority="iso6523-actorid-upis">C2_ReceiverId</Identifier>            [4]
    </Receiver>
    <DocumentIdentification>
      <Standard>Namespace_of_document_type_standardization</Standard>
      <TypeVersion>document_type_version</TypeVersion>                                  [2]
      <InstanceIdentifier>4d386b06-f4a3-4d74-bfbb-31668bd153b7</InstanceIdentifier>     [1]
      <Type>Type_of_Document</Type>
      <CreationDateAndTime>YYYY-MM-DDTHH:MM:SSZ</CreationDateAndTime>
    </DocumentIdentification>
    <BusinessScope>
      <Scope>
        <Type>MLS_TO</Type>
        <InstanceIdentifier>C2_ReceiverId</InstanceIdentifier>
      </Scope>
      <Scope>
        <Type>MLS_TYPE</Type>
        <InstanceIdentifier>MLS_Handling_Code</InstanceIdentifier>                       [5]
      </Scope>
      <Scope>
        <Type>DOCUMENTID</Type>
          <InstanceIdentifier>
          Document_Type_Identifier                                                       [6]
          </InstanceIdentifier>
        <Identifier>busdox-docid-qns</Identifier>
      </Scope>
      <Scope>
        <Type>PROCESSID</Type>
          <InstanceIdentifier>
          Process_Identifier                                                             [7]
          </InstanceIdentifier>
        <Identifier>cenbii-procid-ubl</Identifier>
      </Scope>
      <Scope>
        <Type>COUNTRY_C1</Type>
        <InstanceIdentifier>Country_of_C1</InstanceIdentifier>                           [8]
      </Scope>
    </BusinessScope>
  </StandardBusinessDocumentHeader>
  <DocType
    xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2"
    xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2"
    xmlns="urn:oasis:names:specification:ubl:schema:xsd:Invoice-2">
        <!-- ("Invoice" or TDD Business Document) -->
  </Doctype>
<StandardBusinessDocument>
```

The following restrictions apply to the attributes in the Peppol SBDH specification for C2 to C3 transmission of Invoice Business Documents on behalf of a C1:

1. UUID version 4 generated by the Sender AP, uniquely identifying the transmission, see section 10.2.2
2. SBDH follows the general rules for Business Document payloads i.e. UBL 2.1 content is indicated by `<TypeVersion>2.1</TypeVersion>`
3. For a C1, Sender/Identifier MUST be of Participant Identifier Schemes associated with the taxable persons, see section 10.3.1
4. For a C4, Receiver/Identifier MUST be of Participant Identifier Schemes associated with the taxable person, see section 10.3.1
5. For MLS_TYPE, MUST have ALWAYS_SEND
6. For DOCUMENTID, the Document_Type_Identifier MUST be one of the identifier values stated in section 10.2
7. For PROCESSID, the Process_Identifier MUST be one of the values for the process identifiers as stated in section 10.1
8. For COUNTRY_C1, MUST have the value as appropriate for the sending End User as required for Peppol traffic reporting.

## 12 Security

### 12.1 Peppol Trust Model

The Peppol Trust model is used for all three different flows:

1. Invoice exchange: C1 → C2 → C3 → C4 involving an Invoice Business Documents
2. Tax Data exchange: (C1 →) C2 → C5 → C6 involving a Tax Data Document
3. Tax Data exchange: (C4 ←) C3 → C5 → C6 involving a Tax Data Document

In the Peppol Post-Award domain a Peppol Post-Award Certificate for the invoice exchange is used for both Authenticity and Authorization. This also applies to the two Tax Data exchanges.

### 12.2 C5 segregation

In the 5-corner/6-corner model, the C5 Access Point acts on behalf of the Tax Authority (C6). Therefore, the C5 service provider must either:

- Be organisationally separate from any C2/C3 service provider roles, or
- Logically segregate its C5 Access Point functions from any other Access Point services (e.g., for C2 or C3).

### 12.3 SMP and Portal systems

The Oman solution includes a centralised SMP and also a tax payer / SP portal, which will manage SMP updates. It is important that the SMP and the Portal system(s) have clearly defined segregation.

The portal should not update the SMP directly, but manage the process of update requests and approval.

## Appendix A References

The references are used throughout the document where applicable, often augmented with a specific reference within the quoted document like a legal article or a specific section or page.

| Reference | Full name and document version info |
|---|---|
| [EN16931] | European Norm on Electronic Invoicing in public procurement<br>Electronic Invoicing – Part 1: Semantic data model of the core elements of an electronic invoice<br>Ref. No. EN 16931-1:2017+A1:2019 |
| [TASQ-Sec xx] | Structured Questionnaire to Tax Administrations<br>V1.1 of 17.12.2024<br>(referencing specific sections by heading number) |
| [PIF] | Peppol Interoperability Framework<br>(see https://peppol.org/learn-more/peppol-interoperability-framework/)<br>Including all its constituent specifications in the “Peppol Architectural Framework” |
| [P-CTC-Ref] | Peppol CTC Reference Document<br>V1.0, Sept. 2021<br>https://peppol.org/wp-content/uploads/2023/02/Peppol-CTC-Reference-Document-v1.0.pdf |
| [P-CTC-RefAdd] | Peppol CTC Reference Document Addendum<br>V0.7, Oct. 2010<br>https://peppol.org/wp-content/uploads/2023/10/Peppol-CTC-Reference-Document-Addendum-v1.07.pdf |
| [DCTCE] | A Next-Generation Model for Electronic Tax Reporting and Invoicing - DCTCE<br>GENA, v2.0 of 3.8.2022<br>https://www.gena.net/l/library/download/urn:uuid:c643584f-5fed-4038-9d51-52aeb1e4b19a/next+generation+model+-+decentralised+ctc+and+exchange+v2.0.pdf |
| [UUID] | RFC 9562 – Universally Unique Identifiers (UUIDs)<br>IETF, May 2024 (obsoletes RFC 4122)<br>https://www.rfc-editor.org/rfc/rfc9562 |
| [PeNCA] | Peppol eDelivery Network Conceptual Architecture<br>[https://openpeppol.atlassian.net/wiki/](https://openpeppol.atlassian.net/wiki/spaces/PKB/pages/4717805586/Peppol+eDelivery+Network+Conceptual+Architecture) |
| [PeNLA] | Peppol eDelivery Network Logical Architecture<br>[https://openpeppol.atlassian.net/wiki/](https://openpeppol.atlassian.net/wiki/spaces/PKB/pages/4712595469/Peppol+eDelivery+Network+Logical+Architecture) |
| [MLS] | Peppol Message Level Status Specification<br>https://docs.peppol.eu/edelivery/specs/mls/v1.0.0/ |
| [PNP_SLR] | Peppol Network Policy 1.0.0 2026-07-02<br>(includes SLR for MLS)<br>[https://docs.peppol.eu/edelivery/policies/PeppolNetworkPolicy1.0.0_2026-07-02.pdf](https://docs.peppol.eu/edelivery/policies/Peppol%20Network%20Policy%201.0.0%202026-07-02.pdf) |
| [SMP] | Peppol Service Metadata Publishing<br>https://docs.peppol.eu/edelivery/smp/Peppol-EDN-Service-Metadata-Publishing-1.4.0-2025-02-06.pdf |
| [SML] | Service Metadata Locator (SML)<br>https://docs.peppol.eu/edelivery/sml/Peppol-EDN-Service-Metadata-Locator-1.3.0-2025-02-06.pdf |

> Conversion note: for [PeNCA], [PeNLA] and [PNP_SLR] the displayed URL text in the PDF differs from the actual hyperlink target; the displayed text is kept and linked to the PDF's hyperlink target.

## Appendix B Terminology

To ease wording in the body of this document, it is useful to define a few concepts as “fixed terms” to reduce ambiguity and increase conciseness of the content. Wherever possible, existing terminology is used and imported by (brief explanatory) reference.

To indicate intentional use of fixed terms, they are spelled using **U**pper **C**ase notation.

| Term | Description |
|---|---|
| **Cross-Border Invoicing** | denotes invoicing transactions where the issuer and recipient fall under 2 different jurisdictions. This includes all cases irrespective of whether the one or both jurisdictions are an EU member state, i.e. intra-community and 3<sup>rd</sup>-country import/export scenarios. |
| **Peppol Interoperability Framework (PIF)** | The set of artifacts (i.e., agreements, policies, procedures and technical specifications) which together ensure interoperability in the Peppol Network. It consists of the Peppol Architectural Framework and the Peppol Governance Framework and evolves according to the change management provisions set forth in the Internal Regulations and the Operational Procedures and the principles set out in this agreement. |
| **Access Point (AP)** | denotes the services including corresponding organisational units directly interfacing in the Peppol network with other Access Points. An Access Point is a specific part of a Service Provider, which may include more services additional to those directly related to Peppol network operations. |
| **Service Provider (SP)** | An organisation authorised to provide Peppol Services within one or more Peppol Service Domains pursuant to a Peppol Service Provider Agreement. |
| **End User** | An identified or identifiable entity that is responsible for the business content of the datasets that is exchanged (by sending and/or receiving) with another such entity using Peppol Services over the Peppol Network. |
| **SP Customer** | The business-level entity contracting a Service Provider to implement their business flows with their Trading Partners. In the context of B2B Interoperability, the “End User” is always an “SP Customer” or is aligned with the Service Provider via an intermediary. |
| **Continuous Transaction Controls (CTC)** | denote a tax reporting approach based on near-time, continuous collection of financial transaction data, as opposed to relying on summarised periodic reports. |
| **Tax Reporting** | is the process of extracting, submitting and evaluating tax data for the sake of fulfilling tax obligations. Generally, in the context of Tax reporting enabled countries, a real-time reporting process is assumed, as opposed to periodic/aggregated reporting. |
| **Real-Time Tax Reporting** | where required to distinguish from “periodic” tax reporting for clarity, explicitly spells out the “real-time” character of the tax reporting process in question. |
| **Tax Data Document (TDD)** | Electronic document carrying tax data for reporting to tax authorities from businesses. |
| **PASR** | Peppol Authority Specific Requirements. |

## Appendix C SLR/SLR Timings

The standard Peppol SLR timings for MLS are defined in the PNP [PNP_SLR]. Oman will align to these timings.

SLA times for Oman will be covered by local legislation

For testing invoice and TDD submission the below times will be configured in the testbed. This is for the purpose of testing within a limited timeframe, but allows SPs to demonstrate they can manage to comply with a set SLA time (which could vary in production).

Note: For production Oman will align to PNP SLRs for MLS, and for TDDs the SLAs are specified in the Oman PASR.

<u>**Oman Testing timings (only):**</u>

| Scenario | SLA time |
|---|---|
| The maximum time period within which an **MLS** message MUST be sent by C3 or C5 and received by C2.<br>The maximum time shall be calculated between the reference points of:<br>• The time when the original business document was sent by C2<br>• The time when the MLS has been received by C2<br>*Note: this MLS timing applies to testbed only, for production Oman will align with the MLS SLR from the [PNP_SLR]* | 20 mins |
| The maximum time period for C2 to send the **TDD** to the Peppol Authority of Oman (C5) for B2B/G2B transactions.<br>The maximum time shall be calculated between the following reference points:<br>• The time when the original business document is validated by C2<br>• The time when the TDD is transmitted to C5<br>*Note: for production timings see the PASR* | 15 mins |
| The maximum time period for C2 to send the **TDD** to the Peppol Authority of Oman (C5) for B2C/G2C transactions.<br>The maximum time shall be calculated between the following reference points:<br>• The time when the original business document is validated by C2<br>• The time when the TDD is transmitted to C5<br>*Note: for production timings see the PASR* | 30 mins |
| The maximum time period for C3 to send the **TDD** to the Peppol Authority of Oman (C5) for B2B/G2B transactions.<br>The maximum time shall be calculated between the following reference points:<br>• The time when the original business document is received by C3<br>• The time when the TDD is transmitted to C5<br>*Note: for production timings see the PASR* | 15 mins |

### C.1 Retention policy

| Scenario | Retention time |
|---|---|
| Accredited Service Providers shall retain transmission and transaction metadata fields related to Electronic Tax Invoices and Electronic Adjustment Notes processed through their systems, which shall include at a minimum:<br>• Sender and receiver identifiers<br>• Transmission timestamps<br>• Acknowledgment and delivery confirmations | See Oman PASR |

## Appendix D QR code implementation

### D.1 Method to generate Seller UUID

Generation of UUIDv5 must follow the steps as below:

1. Define the QR Version and Invoice Type as described above
2. Concatenate the values in a single String use the character "|", the resulting String value must follow the structure as below examples:
   - a. Examples for B2C Full and Simplified PINT
      - i. `"1.1|01|INVOICENUMBER|SELLERNAME|VATTIN|DATE|TIMESTAMP|INVOICETOTALAMOUNT|VATTOTAL"`
      - ii. `"1.1|01|INVOICE-01|بائع|OM1234567891|2026-05-13|18:31:00|302.340|14.520"`
   - b. Examples for Profit Margin PINT invoice
      - i. `"1.1|02|INVOICENUMBER|SELLERNAME|VATTIN|DATE|TIMESTAMP|TOTALAMOUNTDUE"`
      - ii. `"1.1|02|INVOICE-01|بائع|OM1234567892|2026-05-13|18:30:00|1032.210"`
3. Ensure all letters in the final string are UPPERCASE letters, this is important, UUIDv5 generation is CASE SENSITIVE
4. Generate the UUIDv5 using the FIXED namespace defined in Guideline and the resulted concatenated string

**Code Examples:**

**UUIDv5 Generation - Python Expand source**

```python
import uuid

# 1. Define the required inputs
FIXED_NAMESPACE = uuid.UUID('e0bc4ac8-b025-46e5-a76d-0c893fc3027e')
qr_version = "1.1"
invoice_type = "0"
invoice_number = "INVOICE-01"
seller_name = "بائع"
vattin = "OM1234567891"
date = "2026-05-13"
timestamp = "18:31:00"
total_amount = "302.340"
vat_total = "14.520"

# 2. Construct the string, ensuring it is uppercase
input_string = f"{qr_version}|{invoice_type}|{invoice_number}|{seller_name}|{vattin}|{date}|{timestamp}|{total_amount}|{vat_total}".upper()

# 3. Generate the UUIDv5
generated_uuid = uuid.uuid5(FIXED_NAMESPACE, input_string)
```

**UUIDv5 Generation - Javascript**

```javascript
import { v5 as uuidv5 } from 'uuid';

// 1. Define the required inputs
const FIXED_NAMESPACE = 'e0bc4ac8-b025-46e5-a76d-0c893fc3027e';
const qrVersion = "1.1";
const invoiceType = "01";
const invoiceNumber = "INVOICE-01";
const sellerName = "بائع";
const vattin = "OM1234567891";
const date = "2026-05-13";
const timestamp = "18:31:00";
const totalAmount = "302.340";
const vatTotal = "14.520";

// 2. Construct the string, ensuring it is uppercase
const inputString = `${qrVersion}|${invoiceType}|${invoiceNumber}|${sellerName}|${vattin}|${date}|${timestamp}|${totalAmount}|${vatTotal}`.toUpperCase();

// 3. Generate the UUIDv5
const generatedUuid = uuidv5(inputString, FIXED_NAMESPACE);
```

> Conversion note: in the PDF, the `input_string =` / `const inputString =` assignments and the long f-string / template literal are wrapped over several lines by the page width; they are joined here. `invoice_type = "0"` in the Python example is as printed in the PDF (the JavaScript example uses `"01"`).

### D.2 Method to generate the QR Code

Once the UUIDv5 is generated and all the values required for QR generation are available, the QR Code must be generated as described below:

To generate and print QR code encoded in Base64 format with up to 700 characters that must contain the fields specified in section 4 tables.

The QR code fields shall be encoded in Tag-Length-Value (TLV) format with the tag values specified in the “Tag” column of the adjacent table.

The TLV encoding shall be as follows:

- **Tag**: the tag value as mentioned above stored in one byte
- **Length**: the length of the byte array resulted from the UTF8 encoding of the field value. The length shall be stored in one byte.
- **Value**: the byte array resulting from the UTF8 encoding of the field value.

For every electronic tax invoice, the supplier must generate and print a QR code encoded in Base64.

- The Base64 string may not exceed 700 characters.
- The QR code content is defined in Tag–Length–Value (TLV)S format, as described below:

| Item | Requirement |
|---|---|
| **Tag** | One byte containing the numeric tag value from the Table below. |
| **Length** | One byte indicating the length, in bytes, of the UTF-8-encoded **Value** |
| **Value** | UTF-8 text |

| Tag (1 Byte) | Length (1 Byte) | Value (L number of Bytes) |
|---|---|---|

What is a TLV (Tag - Length - Value) file format and how is it constructed?

QR code is the base64 encoded TLV. TLV is an encoding scheme used in many communication protocols to encode data. A TLV-encoded message has a defined structure which consists of 3 sections/parts. Those are:

Code of the message type **(T)** - 1 Byte<br>
Message value length **(L)** - 1 Byte<br>
Message value itself **(V)** - Variable

The Tag/Type and Length are of fixed sizes of 1 bytes while the value has a variable size.

As the general idea behind encoding is to transform abstract data into a stream of bits, using TLV, there are different sets of encoding rules that can be used according to the Abstract Syntax Notation Version 1 (ASN.1).

We are using a simple version of Basic Encoding Rules (BER).

**TLV Field - Tag Mapping table**

**B2C Full and Simplified PINT**

| Field | Tag |
|---|---|
| QR Version | 1 |
| Invoice Type | 2 |
| Invoice Number | 3 |
| Seller Name | 4 |
| VATIN of the Seller | 5 |
| Date | 6 |
| Time stamp | 7 |
| Invoice total amount (with VAT) | 8 |
| VAT total | 9 |
| Seller UUID | 10 |

*Table 5 QR Code content TLV field definitions Simplified (and full) PINT*

**Profit Margin PINT Invoice**

| Field | Tag |
|---|---|
| QR Version | 1 |
| Invoice Type | 2 |
| Invoice Number | 3 |
| Seller Name | 4 |
| VATIN of the Seller | 5 |
| Date | 6 |
| Time stamp | 7 |
| Total amount due | 8 |
| Seller UUID | 9 |

*Table 6 QR Code content TLV field definitions Profit Margin PINT Invoice*

<u>**Tag-Length-Value (TLV) Creation**</u>

**Example TLV Building – B2C Full and Simplified PINT:**

**Step 1: Create the hexadecimal representation:**

| Field | Tag | Length | Example Value | Hexadecimal ("Value" is UTF-8 Encoded, So 1 = 31, where 31 is the UTF-8 of 1) |
|---|---|---|---|---|
| QR Version | 1 | 3 **(FIXED)** | 1.1 **(FIXED)** | `0103312e31` |
| Invoice Type | 2 | 2 **(FIXED)** | 01 | `02023031` |
| Invoice Number | 3 | 10 **(Variable)** | INVOICE-01 | `030a494e564f4943452d3031` |
| Seller Name | 4 | 7 **(VARIABLE)** | بائع | `0408d8a8d8a7d8a6d8b9` |
| VATTIN | 5 | 12 **(FIXED)** | OM1234567891 | `050c4f4d31323334353637383931` |
| Date | 6 | 10 **(FIXED)** | 2026-05-13 | `060a323032362d30352d3133` |
| Time Stamp | 7 | 8 **(FIXED)** | 18:31:00 | `070831383a33313a3030` |
| Invoice Total Amount (with VAT) | 8 | 7 **(VARIABLE)** | 302.340 | `08073330322e333430` |
| VAT Total | 9 | 6 **(VARIABLE)** | 14.520 | `090631342e353230` |
| Seller UUID | 10 | 36 **(FIXED)** | 454cb7ea-f1f7-5b69-9e0b-756f002efa52 | `0a2434353463623765612d663166372d356236392d396530622d373536663030326566613532` |

Final TLV Hex Representation:

```text
0103312e3102023031030230310408d8a8d8a7d8a6d8b9050c4f4d31323334353637383931060a323032362d30352d3133070831383a33313a303008073330322e333430090631342e3532300a2436333062623436372d653530332d356330372d393664392d356534636535626632643330
```

**Step 2: Convert to Base64 Representation:**

Resulted value: (Must not exceed 700 characters)

```text
AQMxLjECAjAxAwIwMQQI2KjYp9im2LkFDE9NMTIzNDU2Nzg5MQYKMjAyNi0wNS0xMwcIMTg6MzE6MDAIBzMwMi4zNDAJBjE0LjUyMAokNjMwYmI0NjctZTUwMy01YzA3LTk2ZDktNWU0Y2U1YmYyZDMw
```

**Example TLV Building - Profit Margin PINT Invoice:**

**Step 1: Create the hexadecimal representation:**

| Field | Tag | Length | Example Value | Hexadecimal ("Value" is UTF-8 Encoded, So 1 = 31, where 31 is the UTF-8 of 1) |
|---|---|---|---|---|
| QR Version | 1 | 3 **(FIXED)** | 1.1 **(FIXED)** | `0103312E30` |
| Invoice Type | 2 | 2 **(FIXED)** | 02 | `02023032` |
| Invoice Number | 3 | 10 **(Variable)** | INVOICE-01 | `030a494e564f4943452d3031` |
| Seller Name | 4 | 9 **(VARIABLE)** | ائع | `0408d8a8d8a7d8a6d8b9` |
| VATTIN | 5 | 12 **(FIXED)** | OM1234567892 | `050c4f4d31323334353637383932` |
| Date | 6 | 10 **(FIXED)** | 2026-05-13 | `060a323032362d30352d3133` |
| Time stamp | 7 | 8 **(FIXED)** | 18:30:00 | `070831383a33303a3030` |
| Total Amount Due | 8 | 8 **(VARIABLE)** | 1032.210 | `0808313033322e323130` |
| Seller UUID | 9 | 36 **(FIXED)** | 9724a780-2360-502a-b6a2-201bf0f068c6 | `092439373234613738302d323336302d353032612d623661322d323031626630663036386336` |

Final TLV Hex Representation:

```text
0103312e3102023032030a494e564f4943452d30310408d8a8d8a7d8a6d8b9050c4f4d31323334353637383932060a323032362d30352d3133070831383a33303a30300808313033322e323130092439373234613738302d323336302d353032612d623661322d323031626630663036386336
```

**Step 2: Convert to Base64 Representation:**

**Resulted value: (Must not exceed 700 characters)**

```text
AQMxLjECAjAyAwpJTlZPSUNFLTAxBAjYqNin2KbYuQUMT00xMjM0NTY3ODkyBgoyMDI2LTA1LTEzBwgxODozMDowMAgIMTAzMi4yMTAJJDk3MjRhNzgwLTIzNjAtNTAyYS1iNmEyLTIwMWJmMGYwNjhjNg==
```

**Considerations**

- The Tag and Length are binary values of exactly one byte, therefore represented as Hex, example: "1" is "01" and "12" is "0C"
- The Value must also be converted to binary before adding to the byte array
- There should be no padding or separators between the TLV sets in binary array, the binary bytes should follow each other concurrently, the decoding is done by reading the length of each Tag from the second byte in each TLV pair.
- In order to encode Arabic Text into binary it is important to use UTF-8 Encoding
- Ensure that the Length in TLV is the byte length of the UTF-8 value, Arabic characters for example could be of length 2 (2 bytes) for a singular character and other special characters may be of more bytes.

| Field | Tag | Length | Example Value | Hexadecimal |
|---|---|---|---|---|
| Seller Name | 3 | 9 | بائع | `0408d8a8d8a7d8a6d8b9` |

**Code Example**

The following code example illustrates how the base64 value can be retrieved, using the same values from above table describing the TLV for **Simplified and Full PINT**, note that the same logic can be followed in order to fulfil any other type of invoice or future types

```javascript
function getTLVForValue(tagNum, tagValue) {
  // T: TAG
  var tagBuf = Buffer.from([tagNum]);

  // L: LENGTH
  var tagValueLenBuf = Buffer.from([Buffer.byteLength(tagValue, 'utf8')])

  // V: VALUE
  var tagValueBuf = Buffer.from(tagValue, 'utf8');

  // TLV
  var bufsArray = [tagBuf, tagValueLenBuf, tagValueBuf]

  return Buffer.concat(bufsArray);
}

// 1. QR Version
var qrVersionBuf = getTLVForValue("1", "1.1");

// 2. Invoice Type
var invoiceTypeBuf = getTLVForValue("2", "01");

// 3. Invoice Number
var invoiceNumberBuf = getTLVForValue("3", " INVOICE-01");

// 4. Seller Name
var sellerNameBuf = getTLVForValue("4", "بائع");

// 5. VATTIN
var vattinBuf = getTLVForValue("5", "OM1234567891");

// 6. Date
var dateBuf = getTLVForValue("6", "2026-05-13");

// 7. Time Stamp
var timeStampBuf = getTLVForValue("7", "18:31:00");

// 8. Invoice Total Amount (with VAT)
var invoiceTotalBuf = getTLVForValue("8", "302.340");

// 9. VAT Total
var vatTotalBuf = getTLVForValue("9", "14.520");

// 10. Seller UUID
var sellerUuidBuf = getTLVForValue("10", "454cb7ea-f1f7-5b69-9e0b-756f002efa52");

 // --- Combine all TLV buffers and encode to Base64 ---

// (1) Create an array of all the generated buffers in order
var tagsBufsArray = [
  qrVersionBuf,
  invoiceTypeBuf,
  invoiceNumberBuf,
  sellerNameBuf,
  vattinBuf,
  dateBuf,
  timeStampBuf,
  invoiceTotalBuf,
  vatTotalBuf,
  sellerUuidBuf
];

// (2) Concatenate all buffers into a single buffer
var qrCodeBuf = Buffer.concat(tagsBufsArray);

// (3) Convert the final buffer to a Base64 string
var qrCodeB64 = qrCodeBuf.toString('base64');

// Generate QR Code from the B64 string..
```

[^1]: UUID v5 is a standard for a hash-based, deterministic identifier based on specified payload data (see https://www.rfc-editor.org/rfc/rfc9562#name-uuid-version-5)

[^2]: Generated UUIDv4 per the recommendation of RFC9562 https://www.rfc-editor.org/rfc/rfc9562#name-namespace-id-usage-and-allo
