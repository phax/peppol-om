# peppol-om

<!-- ph-badge-start -->
[![Sonatype Central](https://maven-badges.sml.io/sonatype-central/com.helger.peppol/peppol-om-parent-pom/badge.svg)](https://maven-badges.sml.io/sonatype-central/com.helger.peppol/peppol-om-parent-pom/)
[![javadoc](https://javadoc.io/badge2/com.helger.peppol/peppol-om-testfiles/javadoc.svg)](https://javadoc.io/doc/com.helger.peppol/peppol-om-testfiles)

> If this project saved you some time or made your day a little easier, a star would mean a lot — it helps others find it too.
<!-- ph-badge-end -->

Peppol specific stuff for Oman (OM)

peppol-om is part of my Peppol solution stack. See https://github.com/phax/peppol for other components and libraries in that area.

This contains a set of Java libraries.
They are licensed under the Apache 2.0 license.
The minimum requirement is Java 17.

The backing specifications are:
* TDD OM: https://docs.peppol.eu/poac/om/om-tdd/
* PINT OM: https://docs.peppol.eu/poac/om/pint-om/

# Submodules

This project consists of the following submodules (in alphabetic order)

* `peppol-om-tdd` - contains the main logic to create OM TDD documents based on PINT OM documents as well as documentation
    * Main class to build a complete TDD from scratch is `PeppolOMTDD10Builder`
    * To run the Schematron validation, use class `PeppolOMTDDValidator`
* `peppol-om-tdd-datatypes` - contains the JAXB generated OM TDD data model
    * Main class to read and write TDD XML is `PeppolOMTDD10Marshaller`
* `peppol-om-testfiles` - contains Peppol OM specific test files as a reusable component
    * Main class is `PeppolOMTestFiles`

# Maven usage

Add the following to your `pom.xml` to use this artifact, replacing `x.y.z` with the real version number.

```xml
<dependency>
  <groupId>com.helger.peppol</groupId>
  <artifactId>peppol-om-tdd</artifactId>
  <version>x.y.z</version>
</dependency>
```

# Building

This project requires Apache Maven 3.x and Java 17 for building.
Simply run
```
mvn clean install
```
to build the solution.

# News and noteworthy

v1.1.0 - 2026-07-17
* Updated to ph-schematron v10.x

v1.0.0 - 2026-06-26
* Synchronized XSD, Schematron and example files with the final OM TDD v1.0.0 release (2026-06-23)

v0.9.0 - 2026-06-09
* Updated to the latest XSD + Schematron

v0.8.0 - 2026-04-28
* Initial version
* Namespace URI is `urn:peppol:schema:om-taxdata:1.0`
* Expected `CustomizationID` is `urn:peppol:taxdata:om-1`
* Expected `ProfileID` is `urn:peppol:taxreporting`

---

My personal [Coding Styleguide](https://github.com/phax/meta/blob/master/CodingStyleguide.md) |
It is appreciated if you star the GitHub project if you like it.
