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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.style.PresentForCodeCoverage;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.string.StringHelper;
import com.helger.base.string.StringImplode;
import com.helger.base.uuid.UUID5Helper;
import com.helger.peppol.om.tdd.jaxb.CPeppolOMTDD;

import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_21.DocumentReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_21.MonetaryTotalType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_21.PartyType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_21.SupplierPartyType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_21.TaxTotalType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_21.DocumentDescriptionType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_21.EndpointIDType;
import oasis.names.specification.ubl.schema.xsd.creditnote_21.CreditNoteType;
import oasis.names.specification.ubl.schema.xsd.invoice_21.InvoiceType;

/**
 * Helper class to calculate the Peppol OM Invoice UUID (BTOM-002) as a UUID version 5. The rules
 * are defined in the OM Solution Architecture v1.0.3, section 10.2.3.
 *
 * @author Philip Helger
 */
@Immutable
public final class PeppolOMInvoiceUUIDHelper
{
  /**
   * The DocumentTypeCode of the AdditionalDocumentReference that contains the Profit Margin "Total
   * amount due" (BTOM-020) in its DocumentDescription.
   */
  public static final String DOC_TYPE_CODE_PM_TOTAL = "PM_TOTAL";

  private static final Logger LOGGER = LoggerFactory.getLogger (PeppolOMInvoiceUUIDHelper.class);

  @PresentForCodeCoverage
  private static final PeppolOMInvoiceUUIDHelper INSTANCE = new PeppolOMInvoiceUUIDHelper ();

  private PeppolOMInvoiceUUIDHelper ()
  {}

  @Nullable
  private static String _trim (@Nullable final String s)
  {
    // String.trim removes all chars <= 0x20. In XML 1.0 the only valid chars in that range are
    // space, tab, carriage return and line feed - exactly the ones to be removed
    return s == null ? null : s.trim ();
  }

  /**
   * Get the input string ("name") for the Invoice UUID calculation. All values are trimmed and
   * separated by a single blank.
   *
   * @param sSellerEndpointIDSchemeID
   *        Seller electronic address scheme ID (IBT-034-1). May neither be <code>null</code> nor
   *        empty.
   * @param sSellerEndpointID
   *        Seller electronic address (IBT-034). May neither be <code>null</code> nor empty.
   * @param sDocumentTypeCode
   *        Invoice type code (IBT-003). May neither be <code>null</code> nor empty.
   * @param sID
   *        Invoice number (IBT-001). May neither be <code>null</code> nor empty.
   * @param aIssueDate
   *        Invoice issue date (IBT-002). May not be <code>null</code>.
   * @param aTaxTotalAmount
   *        Invoice total VAT amount (IBT-110). May not be <code>null</code>.
   * @param aTaxInclusiveAmount
   *        Invoice total amount with VAT (IBT-112). May not be <code>null</code>.
   * @param sPMTotalAmountDue
   *        Total amount due (BTOM-020). Only present for Profit Margin invoices. May be
   *        <code>null</code>.
   * @return The input string to be used for the UUID v5 calculation. Never <code>null</code>.
   */
  @NonNull
  @Nonempty
  public static String getInvoiceUUIDInput (@NonNull @Nonempty final String sSellerEndpointIDSchemeID,
                                            @NonNull @Nonempty final String sSellerEndpointID,
                                            @NonNull @Nonempty final String sDocumentTypeCode,
                                            @NonNull @Nonempty final String sID,
                                            @NonNull final LocalDate aIssueDate,
                                            @NonNull final BigDecimal aTaxTotalAmount,
                                            @NonNull final BigDecimal aTaxInclusiveAmount,
                                            @Nullable final String sPMTotalAmountDue)
  {
    ValueEnforcer.notEmpty (_trim (sSellerEndpointIDSchemeID), "SellerEndpointIDSchemeID");
    ValueEnforcer.notEmpty (_trim (sSellerEndpointID), "SellerEndpointID");
    ValueEnforcer.notEmpty (_trim (sDocumentTypeCode), "DocumentTypeCode");
    ValueEnforcer.notEmpty (_trim (sID), "ID");
    ValueEnforcer.notNull (aIssueDate, "IssueDate");
    ValueEnforcer.notNull (aTaxTotalAmount, "TaxTotalAmount");
    ValueEnforcer.notNull (aTaxInclusiveAmount, "TaxInclusiveAmount");

    return StringImplode.imploder ()
                        .filterNonEmpty ()
                        .separator (' ')
                        .source (_trim (sSellerEndpointIDSchemeID),
                                 _trim (sSellerEndpointID),
                                 _trim (sDocumentTypeCode),
                                 _trim (sID),
                                 DateTimeFormatter.ISO_LOCAL_DATE.format (aIssueDate),
                                 aTaxTotalAmount.toPlainString (),
                                 aTaxInclusiveAmount.toPlainString (),
                                 _trim (sPMTotalAmountDue))
                        .build ();
  }

  /**
   * Calculate the Invoice UUID (BTOM-002) from the provided values.
   *
   * @param sSellerEndpointIDSchemeID
   *        Seller electronic address scheme ID (IBT-034-1). May neither be <code>null</code> nor
   *        empty.
   * @param sSellerEndpointID
   *        Seller electronic address (IBT-034). May neither be <code>null</code> nor empty.
   * @param sDocumentTypeCode
   *        Invoice type code (IBT-003). May neither be <code>null</code> nor empty.
   * @param sID
   *        Invoice number (IBT-001). May neither be <code>null</code> nor empty.
   * @param aIssueDate
   *        Invoice issue date (IBT-002). May not be <code>null</code>.
   * @param aTaxTotalAmount
   *        Invoice total VAT amount (IBT-110). May not be <code>null</code>.
   * @param aTaxInclusiveAmount
   *        Invoice total amount with VAT (IBT-112). May not be <code>null</code>.
   * @param sPMTotalAmountDue
   *        Total amount due (BTOM-020). Only present for Profit Margin invoices. May be
   *        <code>null</code>.
   * @return The UUID v5. Never <code>null</code>.
   * @see #getInvoiceUUIDInput(String, String, String, String, LocalDate, BigDecimal, BigDecimal,
   *      String)
   */
  @NonNull
  public static UUID getInvoiceUUID (@NonNull @Nonempty final String sSellerEndpointIDSchemeID,
                                     @NonNull @Nonempty final String sSellerEndpointID,
                                     @NonNull @Nonempty final String sDocumentTypeCode,
                                     @NonNull @Nonempty final String sID,
                                     @NonNull final LocalDate aIssueDate,
                                     @NonNull final BigDecimal aTaxTotalAmount,
                                     @NonNull final BigDecimal aTaxInclusiveAmount,
                                     @Nullable final String sPMTotalAmountDue)
  {
    final String sInput = getInvoiceUUIDInput (sSellerEndpointIDSchemeID,
                                               sSellerEndpointID,
                                               sDocumentTypeCode,
                                               sID,
                                               aIssueDate,
                                               aTaxTotalAmount,
                                               aTaxInclusiveAmount,
                                               sPMTotalAmountDue);
    return UUID5Helper.fromUTF8 (CPeppolOMTDD.PEPPOL_OM_NAMESPACE, sInput);
  }

  @Nullable
  private static UUID _getInvoiceUUID (@Nullable final SupplierPartyType aSupplier,
                                       @Nullable final String sDocumentTypeCode,
                                       @Nullable final String sID,
                                       @Nullable final LocalDate aIssueDate,
                                       @Nullable final String sDocumentCurrencyCode,
                                       @NonNull final List <TaxTotalType> aTaxTotals,
                                       @Nullable final MonetaryTotalType aLegalMonetaryTotal,
                                       @NonNull final List <DocumentReferenceType> aAdditionalDocRefs)
  {
    // IBT-034 and IBT-034-1
    final PartyType aSupplierParty = aSupplier == null ? null : aSupplier.getParty ();
    final EndpointIDType aEndpointID = aSupplierParty == null ? null : aSupplierParty.getEndpointID ();
    final String sSellerEndpointIDSchemeID = aEndpointID == null ? null : _trim (aEndpointID.getSchemeID ());
    final String sSellerEndpointID = aEndpointID == null ? null : _trim (aEndpointID.getValue ());

    // IBT-110 - the TaxTotal in document currency
    BigDecimal aTaxTotalAmount = null;
    if (sDocumentCurrencyCode != null)
      aTaxTotalAmount = aTaxTotals.stream ()
                                  .filter (x -> x.getTaxAmount () != null &&
                                                sDocumentCurrencyCode.equals (x.getTaxAmount ().getCurrencyID ()))
                                  .map (TaxTotalType::getTaxAmountValue)
                                  .findFirst ()
                                  .orElse (null);

    // IBT-112
    final BigDecimal aTaxInclusiveAmount = aLegalMonetaryTotal == null ? null
                                                                       : aLegalMonetaryTotal.getTaxInclusiveAmountValue ();

    // BTOM-020 - Profit Margin invoices only
    final String sPMTotalAmountDue = aAdditionalDocRefs.stream ()
                                                       .filter (x -> DOC_TYPE_CODE_PM_TOTAL.equals (_trim (x.getDocumentTypeCodeValue ())))
                                                       .flatMap (x -> x.getDocumentDescription ().stream ())
                                                       .map (DocumentDescriptionType::getValue)
                                                       .findFirst ()
                                                       .orElse (null);

    if (StringHelper.isEmpty (sSellerEndpointIDSchemeID) ||
        StringHelper.isEmpty (sSellerEndpointID) ||
        StringHelper.isEmpty (_trim (sDocumentTypeCode)) ||
        StringHelper.isEmpty (_trim (sID)) ||
        aIssueDate == null ||
        aTaxTotalAmount == null ||
        aTaxInclusiveAmount == null)
    {
      LOGGER.warn ("Failed to calculate the Invoice UUID because at least one mandatory field is missing: SellerEndpointIDSchemeID='" +
                   sSellerEndpointIDSchemeID +
                   "'; SellerEndpointID='" +
                   sSellerEndpointID +
                   "'; DocumentTypeCode='" +
                   sDocumentTypeCode +
                   "'; ID='" +
                   sID +
                   "'; IssueDate=" +
                   aIssueDate +
                   "; TaxTotalAmount=" +
                   aTaxTotalAmount +
                   "; TaxInclusiveAmount=" +
                   aTaxInclusiveAmount);
      return null;
    }

    return getInvoiceUUID (sSellerEndpointIDSchemeID,
                           sSellerEndpointID,
                           sDocumentTypeCode,
                           sID,
                           aIssueDate,
                           aTaxTotalAmount,
                           aTaxInclusiveAmount,
                           sPMTotalAmountDue);
  }

  /**
   * Calculate the Invoice UUID (BTOM-002) from the provided UBL 2.1 Invoice.
   *
   * @param aInv
   *        The Invoice to read from. May not be <code>null</code>.
   * @return <code>null</code> if at least one of the mandatory fields is missing in the Invoice.
   */
  @Nullable
  public static UUID getInvoiceUUID (@NonNull final InvoiceType aInv)
  {
    ValueEnforcer.notNull (aInv, "Invoice");

    return _getInvoiceUUID (aInv.getAccountingSupplierParty (),
                            aInv.getInvoiceTypeCodeValue (),
                            aInv.getIDValue (),
                            aInv.getIssueDateValueLocal (),
                            _trim (aInv.getDocumentCurrencyCodeValue ()),
                            aInv.getTaxTotal (),
                            aInv.getLegalMonetaryTotal (),
                            aInv.getAdditionalDocumentReference ());
  }

  /**
   * Calculate the Invoice UUID (BTOM-002) from the provided UBL 2.1 CreditNote.
   *
   * @param aCN
   *        The CreditNote to read from. May not be <code>null</code>.
   * @return <code>null</code> if at least one of the mandatory fields is missing in the CreditNote.
   */
  @Nullable
  public static UUID getInvoiceUUID (@NonNull final CreditNoteType aCN)
  {
    ValueEnforcer.notNull (aCN, "CreditNote");

    return _getInvoiceUUID (aCN.getAccountingSupplierParty (),
                            aCN.getCreditNoteTypeCodeValue (),
                            aCN.getIDValue (),
                            aCN.getIssueDateValueLocal (),
                            _trim (aCN.getDocumentCurrencyCodeValue ()),
                            aCN.getTaxTotal (),
                            aCN.getLegalMonetaryTotal (),
                            aCN.getAdditionalDocumentReference ());
  }
}
