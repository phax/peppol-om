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
import static org.junit.Assert.assertNull;

import java.time.Month;
import java.util.UUID;

import org.junit.Test;

import com.helger.base.numeric.BigHelper;
import com.helger.datetime.helper.PDTFactory;

import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_21.DocumentReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_21.MonetaryTotalType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_21.PartyType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_21.SupplierPartyType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_21.TaxTotalType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_21.DocumentDescriptionType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_21.EndpointIDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_21.TaxAmountType;
import oasis.names.specification.ubl.schema.xsd.invoice_21.InvoiceType;

/**
 * Test class for class {@link PeppolOMInvoiceUUIDHelper}.
 *
 * @author Philip Helger
 */
public final class PeppolOMInvoiceUUIDHelperTest
{
  @Test
  public void testSolutionArchitectureExample ()
  {
    // Example input from OM Solution Architecture v1.0.3, section 10.2.3
    // Note: the output string stated there (b3fc2859-f851-59e4-9ed5-a5dfb9515487) does not match
    // the
    // input string - the value below was cross-checked with Python uuid.uuid5
    assertEquals ("0248 5060012349998 380 33445566 2026-01-13 100.00 105.00",
                  PeppolOMInvoiceUUIDHelper.getInvoiceUUIDInput ("0248",
                                                                 "5060012349998",
                                                                 "380",
                                                                 "33445566",
                                                                 PDTFactory.createLocalDate (2026, Month.JANUARY, 13),
                                                                 BigHelper.toBigDecimal ("100.00"),
                                                                 BigHelper.toBigDecimal ("105.00"),
                                                                 null));
    assertEquals (UUID.fromString ("7b95376b-e63c-50e0-a0e9-62f2092288a6"),
                  PeppolOMInvoiceUUIDHelper.getInvoiceUUID (" 0248\t",
                                                            "\n5060012349998 ",
                                                            "380",
                                                            "33445566\r\n",
                                                            PDTFactory.createLocalDate (2026, Month.JANUARY, 13),
                                                            BigHelper.toBigDecimal ("100.00"),
                                                            BigHelper.toBigDecimal ("105.00"),
                                                            null));
  }

  @Test
  public void testProfitMargin ()
  {
    assertEquals ("0248 5060012349998 380 33445566 2026-01-13 100.00 105.00 1032.210",
                  PeppolOMInvoiceUUIDHelper.getInvoiceUUIDInput ("0248",
                                                                 "5060012349998",
                                                                 "380",
                                                                 "33445566",
                                                                 PDTFactory.createLocalDate (2026, Month.JANUARY, 13),
                                                                 BigHelper.toBigDecimal ("100.00"),
                                                                 BigHelper.toBigDecimal ("105.00"),
                                                                 " 1032.210 "));
  }

  @Test
  public void testFromInvoice ()
  {
    final InvoiceType aInv = new InvoiceType ();
    aInv.setID ("33445566");
    aInv.setIssueDate (PDTFactory.createLocalDate (2026, Month.JANUARY, 13));
    aInv.setInvoiceTypeCode ("380");
    aInv.setDocumentCurrencyCode ("OMR");
    {
      final PartyType aParty = new PartyType ();
      final EndpointIDType aEndpointID = new EndpointIDType ("5060012349998");
      aEndpointID.setSchemeID ("0248");
      aParty.setEndpointID (aEndpointID);
      final SupplierPartyType aSupplier = new SupplierPartyType ();
      aSupplier.setParty (aParty);
      aInv.setAccountingSupplierParty (aSupplier);
    }

    // TaxTotal and LegalMonetaryTotal are missing
    assertNull (PeppolOMInvoiceUUIDHelper.getInvoiceUUID (aInv));

    {
      final TaxTotalType aTaxTotal = new TaxTotalType ();
      final TaxAmountType aTaxAmount = new TaxAmountType (BigHelper.toBigDecimal ("100.00"));
      aTaxAmount.setCurrencyID ("OMR");
      aTaxTotal.setTaxAmount (aTaxAmount);
      aInv.addTaxTotal (aTaxTotal);
    }
    {
      final MonetaryTotalType aLMT = new MonetaryTotalType ();
      aLMT.setTaxInclusiveAmount (BigHelper.toBigDecimal ("105.00")).setCurrencyID ("OMR");
      aInv.setLegalMonetaryTotal (aLMT);
    }
    final UUID aUUID = PeppolOMInvoiceUUIDHelper.getInvoiceUUID (aInv);
    assertNotNull (aUUID);
    assertEquals (UUID.fromString ("7b95376b-e63c-50e0-a0e9-62f2092288a6"), aUUID);

    // Used by the builder, if no UUID is present
    assertEquals (aUUID.toString (), new PeppolOMTDD10ReportedTransactionBuilder ().initFromInvoice (aInv).uuid ());

    // Profit Margin invoice changes the UUID
    {
      final DocumentReferenceType aDocRef = new DocumentReferenceType ();
      aDocRef.setID ("pm");
      aDocRef.setDocumentTypeCode (PeppolOMInvoiceUUIDHelper.DOC_TYPE_CODE_PM_TOTAL);
      aDocRef.addDocumentDescription (new DocumentDescriptionType ("1032.210"));
      aInv.addAdditionalDocumentReference (aDocRef);
    }
    assertEquals (PeppolOMInvoiceUUIDHelper.getInvoiceUUID ("0248",
                                                            "5060012349998",
                                                            "380",
                                                            "33445566",
                                                            PDTFactory.createLocalDate (2026, Month.JANUARY, 13),
                                                            BigHelper.toBigDecimal ("100.00"),
                                                            BigHelper.toBigDecimal ("105.00"),
                                                            "1032.210"),
                  PeppolOMInvoiceUUIDHelper.getInvoiceUUID (aInv));

    // Check that provided UUID is not overwritten
    aInv.setUUID ("a6bdaeb8-ce06-46ea-8e53-805cc05f3dba");
    assertEquals ("a6bdaeb8-ce06-46ea-8e53-805cc05f3dba",
                  new PeppolOMTDD10ReportedTransactionBuilder ().initFromInvoice (aInv).uuid ());
  }
}
