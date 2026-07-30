# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

A Java library implementing the Peppol Oman (OM) Tax Data Document (TDD) specification. The backing specs are:
- TDD OM: https://docs.peppol.eu/poac/om/om-tdd/ (production releases; MR snapshots at https://test-docs.peppol.eu/pint/pint-om/om-tdd/)
- PINT OM: https://test-docs.peppol.eu/pint/pint-om/

The implementation mirrors the sibling `peppol-uae` and `peppol-sk` projects under `~/dev/git/`. When in doubt about conventions or patterns, consult those.

Minimum Java version: **17**.

## Build Commands

```bash
# Full build with tests
mvn clean install

# Build without tests
mvn clean install -DskipTests

# Run tests for a single module
mvn test -pl peppol-om-tdd

# Run a single test class / method
mvn test -pl peppol-om-tdd -Dtest=PeppolOMTDD10BuilderTest
mvn test -pl peppol-om-tdd -Dtest=PeppolOMTDD10BuilderTest#testBasicMinimal

# Regenerate JAXB sources after editing the XSD
mvn -pl peppol-om-tdd-datatypes generate-sources
```

## Module Structure

Three Maven modules with strict build order (each depends on the previous):

1. **`peppol-om-testfiles`** — Bundles official OM TDD example XML files as classpath resources under `external/tdd/10/good/`. `PeppolOMTestFiles` exposes them as `ICommonsList<ClassPathResource>`.

2. **`peppol-om-tdd-datatypes`** — JAXB-generated data model from `external/schemas/peppol-om-tdd-1.0.0.xsd`. Key classes: `CPeppolOMTDD` (schema constants/resource), `PeppolOMTDD10Marshaller` (XML reader/writer extending `GenericJAXBMarshaller`). The JAXB plugin generates into `com.helger.peppol.om.tdd.v100` per `src/main/jaxb/binding.xjb`. The catalog at `src/main/jaxb/catalog-tdd.txt` resolves UBL 2.1 imports to `ph-ubl21` jar entries — do **not** add absolute file paths to XSD imports; the catalog handles resolution.

3. **`peppol-om-tdd`** — Main business logic:
   - `PeppolOMTDD10Builder` — builds a top-level `TaxDataType` from scratch
   - `PeppolOMTDD10ReportedTransactionBuilder` — builds the inner `ReportedTransaction`; supports `initFromInvoice(InvoiceType)` / `initFromCreditNote(CreditNoteType)` to populate from a UBL 2.1 document
   - `PeppolOMTDDValidator` — Schematron validation against `external/schematron/peppol-om-tdd-1.0.0.sch`
   - `codelist/` — enums backing the OM TDD genericode codelists (`EOMTDDDocumentTypeCode` S/R/D, `EOMTDDDocumentScope` D/IP/INP, `EOMTDDReporterRole` 01/02)

## Architecture

```
UBL Invoice/CreditNote XML
        ↓
PeppolOMTDD10ReportedTransactionBuilder.initFromInvoice/CreditNote
        ↓
PeppolOMTDD10Builder.reportedTransaction(rt -> ...).build()
        ↓
TaxDataType (JAXB model)  ←→  PeppolOMTDD10Marshaller (XML)
        ↓
PeppolOMTDDValidator (Schematron, applied to the serialized XML)
```

Fixed values applied by the builder by default:
- `CustomizationID = urn:peppol:taxdata:om-1`
- `ProfileID = urn:peppol:taxreporting`
- Top-level `cbc:UUID` is **mandatory** by Schematron rule `ibr-tdd-58` — set via `.uuid(...)` or `.randomUUID()`
- C5 (`ReceivingParty`) and C2/C3 (`ReportersRepresentative`) participant IDs **must use scheme `0242`** — enforced in `isEveryRequiredFieldSet`

`build()` returns `null` (not an exception) when `isEveryRequiredFieldSet` fails; errors are logged.

## Specification Updates

When pulling a new release from the OpenPeppol docs site:
1. Download `resources.zip` (e.g. from `pint-om/2026-Q2-MR/om-tdd/`)
2. Replace `peppol-om-tdd/src/main/resources/external/schematron/peppol-om-tdd-1.0.0.sch` from `trn-tdd/schematron/peppol-om-tdd.sch`
3. Sync example XMLs under `peppol-om-testfiles/src/main/resources/external/tdd/10/good/` and update the file list in `PeppolOMTestFiles.getAllGoodTDD10Files`
4. Compare codelists in `trn-tdd/codelist/TDD-{dtc,ds,rr}.gc` against the enums in `peppol-om-tdd/.../codelist/`
5. Since the 1.0.1 release (2026-07-29) the OpenPeppol release ships an XSD at `trn-tdd/xsd/` (derived from this project's hand-maintained one, namespace `urn:peppol:schema:om-taxdata:1.0`). Compare it against `peppol-om-tdd-datatypes/src/main/resources/external/schemas/` — but keep the project's version, which uses catalog-based imports (no `schemaLocation` URLs) and richer documentation

## Code Conventions

Strict Helger / phax-style. The user's global rules under `~/.claude/rules/` are authoritative; some specifics relevant here:

- **Hungarian notation** for all variables (`s`/`n`/`b`/`a`/`e`/`m_`/`s_`); `ID` is always uppercase (`getDocTypeID`, never `getDocTypeId`)
- All parameters `final`, JSpecify `@NonNull`/`@Nullable`/`@Nonempty`
- Logging via SLF4J with **inline string concatenation**, not `{}` placeholders
- Space before `(` and `<` in calls / generics
- Underscore prefix on private methods (`_getCL`)
- Apache 2.0 license header on every Java file (Copyright Philip Helger)
- JAXB-generated classes under `*.tdd.v100` are **auto-generated** — edit the XSD, do not hand-edit the generated sources

## JAXB Generation Notes

The `jaxb-maven-plugin` runs in `generate-sources`. The `maven-antrun-plugin` then deletes `target/generated-sources/xjc/oasis` because UBL types are pulled in via episodes from `ph-ubl21` and would otherwise be regenerated as duplicates. The XJC arguments enable the ph-jaxb-plugin extensions (`-Xph-annotate`, `-Xph-equalshashcode`, `-Xph-cloneable2`, etc.) that produce the JSpecify-annotated, fluent-setter, ph-commons-flavoured Java classes used throughout.
