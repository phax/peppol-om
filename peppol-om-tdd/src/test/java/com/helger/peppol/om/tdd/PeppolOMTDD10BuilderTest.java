/*
 * Copyright (C) 2026 Philip Helger
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.peppol.om.tdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.nio.charset.StandardCharsets;
import java.time.Month;
import java.time.ZoneOffset;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.base.numeric.BigHelper;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.datetime.helper.PDTFactory;
import com.helger.io.resource.inmemory.ReadableResourceString;
import com.helger.peppol.om.tdd.PeppolOMTDD10ReportedTransactionBuilder.CustomContent;
import com.helger.peppol.om.tdd.codelist.EOMTDDDocumentScope;
import com.helger.peppol.om.tdd.codelist.EOMTDDDocumentTypeCode;
import com.helger.peppol.om.tdd.codelist.EOMTDDReporterRole;
import com.helger.peppol.om.tdd.jaxb.PeppolOMTDD10Marshaller;
import com.helger.peppol.om.tdd.v10.TaxDataType;
import com.helger.peppol.om.tdd.validate.PeppolOMTDDValidator;
import com.helger.peppolid.factory.IIdentifierFactory;
import com.helger.peppolid.factory.PeppolIdentifierFactory;
import com.helger.schematron.ISchematronResource;
import com.helger.schematron.svrl.SVRLHelper;
import com.helger.schematron.svrl.jaxb.FiredRule;
import com.helger.schematron.svrl.jaxb.SchematronOutputType;
import com.helger.xml.serialize.read.DOMReader;

import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_21.CustomerPartyType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_21.PartyLegalEntityType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_21.PartyType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_21.SupplierPartyType;
import oasis.names.specification.ubl.schema.xsd.creditnote_21.CreditNoteType;
import oasis.names.specification.ubl.schema.xsd.invoice_21.InvoiceType;

/**
 * Test class for class {@link PeppolOMTDD10Builder}.
 *
 * @author Philip Helger
 */
public final class PeppolOMTDD10BuilderTest
{
  private static final Logger LOGGER = LoggerFactory.getLogger (PeppolOMTDD10BuilderTest.class);

  private static PartyType _createPartyWithName (final String sName)
  {
    final PartyLegalEntityType aPLE = new PartyLegalEntityType ();
    aPLE.setRegistrationName (sName);
    final PartyType ret = new PartyType ();
    ret.addPartyLegalEntity (aPLE);
    return ret;
  }

  @Test
  public void testInitFromInvoiceCopiesPartyNames ()
  {
    final InvoiceType aInvoice = new InvoiceType ();
    final SupplierPartyType aSupplier = new SupplierPartyType ();
    aSupplier.setParty (_createPartyWithName ("Seller name"));
    aInvoice.setAccountingSupplierParty (aSupplier);
    final CustomerPartyType aCustomer = new CustomerPartyType ();
    aCustomer.setParty (_createPartyWithName ("Buyer name"));
    aInvoice.setAccountingCustomerParty (aCustomer);

    final PeppolOMTDD10ReportedTransactionBuilder aBuilder = new PeppolOMTDD10ReportedTransactionBuilder ().initFromInvoice (aInvoice);
    assertEquals ("Seller name", aBuilder.sellerName ());
    assertEquals ("Buyer name", aBuilder.buyerName ());
  }

  @Test
  public void testInitFromCreditNoteCopiesPartyNames ()
  {
    final CreditNoteType aCreditNote = new CreditNoteType ();
    final SupplierPartyType aSupplier = new SupplierPartyType ();
    aSupplier.setParty (_createPartyWithName ("Seller name"));
    aCreditNote.setAccountingSupplierParty (aSupplier);
    final CustomerPartyType aCustomer = new CustomerPartyType ();
    aCustomer.setParty (_createPartyWithName ("Buyer name"));
    aCreditNote.setAccountingCustomerParty (aCustomer);

    final PeppolOMTDD10ReportedTransactionBuilder aBuilder = new PeppolOMTDD10ReportedTransactionBuilder ().initFromCreditNote (aCreditNote);
    assertEquals ("Seller name", aBuilder.sellerName ());
    assertEquals ("Buyer name", aBuilder.buyerName ());
  }

  @Test
  public void testBasicMinimal () throws Exception
  {
    final IIdentifierFactory aIF = PeppolIdentifierFactory.INSTANCE;
    final ISchematronResource aSCHRes = PeppolOMTDDValidator.getSchematronOM_TDD_10 ();

    final TaxDataType aTDD = new PeppolOMTDD10Builder ().randomUUID ()
                                                        .documentTypeCode (EOMTDDDocumentTypeCode.SUBMIT)
                                                        .documentScope (EOMTDDDocumentScope.DOMESTIC)
                                                        .reporterRole (EOMTDDReporterRole.SENDER)
                                                        .reportingParty (aIF.createParticipantIdentifierWithDefaultScheme ("0235:c1id"))
                                                        .receivingParty (aIF.createParticipantIdentifierWithDefaultScheme ("0242:c5id"))
                                                        .reportersRepresentative (aIF.createParticipantIdentifierWithDefaultScheme ("0242:987654"))
                                                        // Provide all fields manually
                                                        .reportedTransaction (rt -> rt.transportHeaderID ("my-sbdh-uuid-12345678")
                                                                                      .receivedDate (PDTFactory.createLocalDate (2026,
                                                                                                                                 Month.APRIL,
                                                                                                                                 29))
                                                                                      .receivedTime (PDTFactory.createOffsetTime (9,
                                                                                                                                  0,
                                                                                                                                  0,
                                                                                                                                  ZoneOffset.UTC))
                                                                                      .customizationID ("urn:peppol:pint:billing-1@om-1")
                                                                                      .profileID ("urn:peppol:bis:billing")
                                                                                      .id ("invoice-1")
                                                                                      .uuid ("02de16f6-2395-59ba-89c4-6de86caf661a")
                                                                                      .issueDate (PDTFactory.createLocalDate (2026,
                                                                                                                              Month.APRIL,
                                                                                                                              28))
                                                                                      .documentTypeCode ("380")
                                                                                      .documentCurrencyCode ("OMR")
                                                                                      .sellerName ("Seller name")
                                                                                      .sellerTaxID ("123456789")
                                                                                      .sellerTaxSchemeID ("VAT")
                                                                                      .buyerName ("Buyer name")
                                                                                      .buyerID ("11223344")
                                                                                      .buyerIDSchemeID ("OM:TIN")
                                                                                      .buyerTaxID ("987654321")
                                                                                      .taxTotalAmountDocumentCurrency (BigHelper.toBigDecimal ("123.45"))
                                                                                      .taxExclusiveTotalAmount (BigHelper.toBigDecimal ("1200"))
                                                                                      .sourceDocument (DOMReader.readXMLDOM ("<Invoice xmlns='urn:oasis:names:specification:ubl:schema:xsd:Invoice-2'>" +
                                                                                                                             "\n... omitted for brevity ...\n" +
                                                                                                                             "</Invoice>")))
                                                        .build ();
    assertNotNull (aTDD);
    assertEquals ("Seller name",
                  aTDD.getReportedTransactionAtIndex (0)
                      .getReportedDocument ()
                      .getAccountingSupplierParty ()
                      .getParty ()
                      .getPartyLegalEntityAtIndex (0)
                      .getRegistrationNameValue ());
    assertEquals ("Buyer name",
                  aTDD.getReportedTransactionAtIndex (0)
                      .getReportedDocument ()
                      .getAccountingCustomerParty ()
                      .getParty ()
                      .getPartyLegalEntityAtIndex (0)
                      .getRegistrationNameValue ());

    // Serialize
    final String sXML = new PeppolOMTDD10Marshaller ().setFormattedOutput (true).getAsString (aTDD);
    assertNotNull (sXML);
    if (false)
      LOGGER.info (sXML);

    // Schematron validation
    final SchematronOutputType aSVRL = aSCHRes.applySchematronValidationToSVRL (new ReadableResourceString (sXML,
                                                                                                            StandardCharsets.UTF_8));
    assertNotNull (aSVRL);
    assertTrue (aSVRL.getActivePatternOrActiveGroupAndFiredRule ()
                     .stream ()
                     .filter (FiredRule.class::isInstance)
                     .map (FiredRule.class::cast)
                     .count () > 0);
    assertEquals (new CommonsArrayList <> (), SVRLHelper.getAllFailedAssertions (aSVRL));
  }

  @Test
  public void testBasicMaximal () throws Exception
  {
    final IIdentifierFactory aIF = PeppolIdentifierFactory.INSTANCE;
    final ISchematronResource aSCHRes = PeppolOMTDDValidator.getSchematronOM_TDD_10 ();

    final TaxDataType aTDD = new PeppolOMTDD10Builder ().randomUUID ()
                                                        .documentTypeCode (EOMTDDDocumentTypeCode.SUBMIT)
                                                        .documentScope (EOMTDDDocumentScope.DOMESTIC)
                                                        .reporterRole (EOMTDDReporterRole.SENDER)
                                                        .reportingParty (aIF.createParticipantIdentifierWithDefaultScheme ("0235:c1id"))
                                                        .receivingParty (aIF.createParticipantIdentifierWithDefaultScheme ("0242:c5id"))
                                                        .reportersRepresentative (aIF.createParticipantIdentifierWithDefaultScheme ("0242:987654"))
                                                        // Provide all fields manually
                                                        .reportedTransaction (rt -> rt.transportHeaderID ("my-sbdh-uuid-12345678")
                                                                                      .receivedDate (PDTFactory.createLocalDate (2026,
                                                                                                                                 Month.APRIL,
                                                                                                                                 29))
                                                                                      .receivedTime (PDTFactory.createOffsetTime (9,
                                                                                                                                  0,
                                                                                                                                  0,
                                                                                                                                  ZoneOffset.UTC))
                                                                                      .customizationID ("urn:peppol:pint:billing-1@om-1")
                                                                                      .profileID ("urn:peppol:bis:billing")
                                                                                      .id ("invoice-1")
                                                                                      .uuid ("02de16f6-2395-59ba-89c4-6de86caf661a")
                                                                                      .issueDate (PDTFactory.createLocalDate (2026,
                                                                                                                              Month.APRIL,
                                                                                                                              28))
                                                                                      .issueTime (PDTFactory.createOffsetTime (20,
                                                                                                                               8,
                                                                                                                               0,
                                                                                                                               ZoneOffset.UTC))
                                                                                      .documentTypeCode ("380")
                                                                                      .documentCurrencyCode ("OMR")
                                                                                      .taxCurrencyCode ("EUR")
                                                                                      .sellerName ("Seller name")
                                                                                      .sellerTaxID ("123456789")
                                                                                      .sellerTaxSchemeID ("VAT")
                                                                                      .buyerName ("Buyer name")
                                                                                      .buyerID ("11223344")
                                                                                      .buyerIDSchemeID ("OM:TIN")
                                                                                      .buyerTaxID ("987654321")
                                                                                      .taxTotalAmountDocumentCurrency (BigHelper.toBigDecimal ("123.45"))
                                                                                      .taxTotalAmountTaxCurrency (BigHelper.toBigDecimal ("500"))
                                                                                      .taxExclusiveTotalAmount (BigHelper.toBigDecimal ("1200"))
                                                                                      .addCustomContent (new CustomContent ("ID1",
                                                                                                                            "val1"))
                                                                                      .sourceDocument (DOMReader.readXMLDOM ("<Invoice xmlns='urn:oasis:names:specification:ubl:schema:xsd:Invoice-2'>" +
                                                                                                                             "\n... omitted for brevity ...\n" +
                                                                                                                             "</Invoice>")))
                                                        .build ();
    assertNotNull (aTDD);

    // Serialize
    final String sXML = new PeppolOMTDD10Marshaller ().setFormattedOutput (true).getAsString (aTDD);
    assertNotNull (sXML);
    if (false)
      LOGGER.info (sXML);

    // Schematron validation
    final SchematronOutputType aSVRL = aSCHRes.applySchematronValidationToSVRL (new ReadableResourceString (sXML,
                                                                                                            StandardCharsets.UTF_8));
    assertNotNull (aSVRL);
    assertTrue (aSVRL.getActivePatternOrActiveGroupAndFiredRule ()
                     .stream ()
                     .filter (FiredRule.class::isInstance)
                     .map (FiredRule.class::cast)
                     .count () > 0);
    assertEquals (new CommonsArrayList <> (), SVRLHelper.getAllFailedAssertions (aSVRL));
  }
}
