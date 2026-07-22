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

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.function.Consumer;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.base.builder.IBuilder;
import com.helger.base.log.ConditionalLogger;
import com.helger.base.string.StringHelper;
import com.helger.datetime.helper.PDTFactory;
import com.helger.datetime.xml.XMLOffsetTime;
import com.helger.peppol.om.tdd.codelist.EOMTDDDocumentScope;
import com.helger.peppol.om.tdd.codelist.EOMTDDDocumentTypeCode;
import com.helger.peppol.om.tdd.codelist.EOMTDDReporterRole;
import com.helger.peppol.om.tdd.v10.ReportedTransactionType;
import com.helger.peppol.om.tdd.v10.TaxDataDocumentReporterRoleType;
import com.helger.peppol.om.tdd.v10.TaxDataDocumentScopeType;
import com.helger.peppol.om.tdd.v10.TaxDataDocumentTypeCodeType;
import com.helger.peppol.om.tdd.v10.TaxDataType;
import com.helger.peppolid.IParticipantIdentifier;
import com.helger.peppolid.factory.IIdentifierFactory;
import com.helger.peppolid.factory.PeppolIdentifierFactory;

import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_21.PartyIdentificationType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_21.PartyType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_21.CustomizationIDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_21.IssueDateType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_21.IssueTimeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_21.ProfileIDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_21.UUIDType;

/**
 * Builder for Peppol OM TDD 1.0 document.
 *
 * @author Philip Helger
 */
public class PeppolOMTDD10Builder implements IBuilder <TaxDataType>
{
  public static final String DEFAULT_CUSTOMIZATION_ID = "urn:peppol:taxdata:om-1";
  public static final String DEFAULT_PROFILE_ID = "urn:peppol:taxreporting";

  private static final Logger LOGGER = LoggerFactory.getLogger (PeppolOMTDD10Builder.class);

  private String m_sCustomizationID;
  private String m_sProfileID;
  private String m_sUUID;
  private LocalDate m_aIssueDate;
  private OffsetTime m_aIssueTime;
  private EOMTDDDocumentTypeCode m_eDocumentTypeCode;
  private EOMTDDDocumentScope m_eDocumentScope;
  private EOMTDDReporterRole m_eReporterRole;
  private IParticipantIdentifier m_aReportingParty;
  private IParticipantIdentifier m_aReceivingParty;
  private IParticipantIdentifier m_aReportersRepresentative;
  private ReportedTransactionType m_aReportedTransaction;

  public PeppolOMTDD10Builder ()
  {
    customizationID (DEFAULT_CUSTOMIZATION_ID);
    profileID (DEFAULT_PROFILE_ID);
    issueDateTimeNow ();
  }

  @Nullable
  public String customizationID ()
  {
    return m_sCustomizationID;
  }

  @NonNull
  public PeppolOMTDD10Builder customizationID (@Nullable final String s)
  {
    m_sCustomizationID = s;
    return this;
  }

  @Nullable
  public String profileID ()
  {
    return m_sProfileID;
  }

  @NonNull
  public PeppolOMTDD10Builder profileID (@Nullable final String s)
  {
    m_sProfileID = s;
    return this;
  }

  @Nullable
  public String uuid ()
  {
    return m_sUUID;
  }

  @NonNull
  public PeppolOMTDD10Builder uuid (@Nullable final String s)
  {
    m_sUUID = s;
    return this;
  }

  @NonNull
  public PeppolOMTDD10Builder randomUUID ()
  {
    return uuid (java.util.UUID.randomUUID ().toString ());
  }

  @Nullable
  public LocalDate issueDate ()
  {
    return m_aIssueDate;
  }

  @NonNull
  public PeppolOMTDD10Builder issueDateNow ()
  {
    return issueDate (PDTFactory.getCurrentLocalDate ());
  }

  @NonNull
  public PeppolOMTDD10Builder issueDate (@Nullable final LocalDate a)
  {
    m_aIssueDate = a;
    return this;
  }

  @Nullable
  public OffsetTime issueTime ()
  {
    return m_aIssueTime;
  }

  @NonNull
  public PeppolOMTDD10Builder issueTimeNow ()
  {
    return issueTime (PDTFactory.getCurrentOffsetTime ());
  }

  @NonNull
  public PeppolOMTDD10Builder issueTime (@Nullable final OffsetTime a)
  {
    // XSD can only handle milliseconds
    m_aIssueTime = PDTFactory.getWithMillisOnly (a);
    return this;
  }

  @NonNull
  public PeppolOMTDD10Builder issueDateTime (@Nullable final OffsetDateTime a)
  {
    if (a == null)
      return issueDate (null).issueTime (null);
    return issueDate (a.toLocalDate ()).issueTime (a.toOffsetTime ());
  }

  @NonNull
  public PeppolOMTDD10Builder issueDateTimeNow ()
  {
    return issueDateTime (PDTFactory.getCurrentOffsetDateTime ());
  }

  @Nullable
  public EOMTDDDocumentTypeCode documentTypeCode ()
  {
    return m_eDocumentTypeCode;
  }

  @NonNull
  public PeppolOMTDD10Builder documentTypeCode (@Nullable final EOMTDDDocumentTypeCode e)
  {
    m_eDocumentTypeCode = e;
    return this;
  }

  @Nullable
  public EOMTDDDocumentScope documentScope ()
  {
    return m_eDocumentScope;
  }

  @NonNull
  public PeppolOMTDD10Builder documentScope (@Nullable final EOMTDDDocumentScope e)
  {
    m_eDocumentScope = e;
    return this;
  }

  @Nullable
  public EOMTDDReporterRole reporterRole ()
  {
    return m_eReporterRole;
  }

  @NonNull
  public PeppolOMTDD10Builder reporterRole (@Nullable final EOMTDDReporterRole e)
  {
    m_eReporterRole = e;
    return this;
  }

  @Nullable
  public IParticipantIdentifier reportingParty ()
  {
    return m_aReportingParty;
  }

  /**
   * @param a
   *        Peppol Participant ID of C1/C4 of the business document.
   * @return this for chaining
   */
  @NonNull
  public PeppolOMTDD10Builder reportingParty (@Nullable final IParticipantIdentifier a)
  {
    m_aReportingParty = a;
    return this;
  }

  @Nullable
  public IParticipantIdentifier receivingParty ()
  {
    return m_aReceivingParty;
  }

  /**
   * @param a
   *        Peppol Participant ID of C5 of the TDD.
   * @return this for chaining
   */
  @NonNull
  public PeppolOMTDD10Builder receivingParty (@Nullable final IParticipantIdentifier a)
  {
    m_aReceivingParty = a;
    return this;
  }

  @Nullable
  public IParticipantIdentifier reportersRepresentative ()
  {
    return m_aReportersRepresentative;
  }

  /**
   * @param a
   *        Peppol Participant ID of C2/C3 of the business document. Must use the SPIS scheme.
   * @return this for chaining
   */
  @NonNull
  public PeppolOMTDD10Builder reportersRepresentative (@Nullable final IParticipantIdentifier a)
  {
    m_aReportersRepresentative = a;
    return this;
  }

  @Nullable
  public ReportedTransactionType reportedTransaction ()
  {
    return m_aReportedTransaction;
  }

  @NonNull
  public PeppolOMTDD10Builder reportedTransaction (@NonNull final Consumer <PeppolOMTDD10ReportedTransactionBuilder> aBuilderConsumer)
  {
    final PeppolOMTDD10ReportedTransactionBuilder aBuilder = new PeppolOMTDD10ReportedTransactionBuilder ();
    aBuilderConsumer.accept (aBuilder);
    return reportedTransaction (aBuilder.build ());
  }

  @NonNull
  public PeppolOMTDD10Builder reportedTransaction (@Nullable final ReportedTransactionType a)
  {
    m_aReportedTransaction = a;
    return this;
  }

  public boolean isEveryRequiredFieldSet (final boolean bDoLogOnError)
  {
    int nErrs = 0;
    final ConditionalLogger aCondLog = new ConditionalLogger (LOGGER, bDoLogOnError);
    final IIdentifierFactory aIF = PeppolIdentifierFactory.INSTANCE;
    final String sErrorPrefix = "Error in Peppol OM TDD 1.0 builder: ";

    if (StringHelper.isEmpty (m_sCustomizationID))
    {
      aCondLog.error (sErrorPrefix + "CustomizationID is missing");
      nErrs++;
    }
    if (StringHelper.isEmpty (m_sProfileID))
    {
      aCondLog.error (sErrorPrefix + "ProfileID is missing");
      nErrs++;
    }
    if (StringHelper.isEmpty (m_sUUID))
    {
      aCondLog.error (sErrorPrefix + "UUID is missing");
      nErrs++;
    }
    if (m_aIssueDate == null)
    {
      aCondLog.error (sErrorPrefix + "IssueDate is missing");
      nErrs++;
    }
    if (m_aIssueTime == null)
    {
      aCondLog.error (sErrorPrefix + "IssueTime is missing");
      nErrs++;
    }
    if (m_eDocumentTypeCode == null)
    {
      aCondLog.error (sErrorPrefix + "DocumentTypeCode is missing");
      nErrs++;
    }
    if (m_eDocumentScope == null)
    {
      aCondLog.error (sErrorPrefix + "DocumentScope is missing");
      nErrs++;
    }
    if (m_eReporterRole == null)
    {
      aCondLog.error (sErrorPrefix + "ReporterRole is missing");
      nErrs++;
    }

    if (m_aReportingParty == null)
    {
      aCondLog.error (sErrorPrefix + "ReportingParty is missing");
      nErrs++;
    }
    else
      if (!aIF.isParticipantIdentifierSchemeValid (m_aReportingParty.getScheme ()))
      {
        aCondLog.error (sErrorPrefix +
                        "ReportingParty identifier scheme '" +
                        m_aReportingParty.getScheme () +
                        "' is invalid");
        nErrs++;
      }
      else
        if (!aIF.isParticipantIdentifierValueValid (m_aReportingParty.getScheme (), m_aReportingParty.getValue ()))
        {
          aCondLog.error (sErrorPrefix +
                          "ReportingParty identifier value '" +
                          m_aReportingParty.getValue () +
                          "' is invalid for scheme '" +
                          m_aReportingParty.getScheme () +
                          "'");
          nErrs++;
        }

    if (m_aReceivingParty == null)
    {
      aCondLog.error (sErrorPrefix + "ReceivingParty is missing");
      nErrs++;
    }
    else
      if (!aIF.isParticipantIdentifierSchemeValid (m_aReceivingParty.getScheme ()))
      {
        aCondLog.error (sErrorPrefix +
                        "ReceivingParty identifier scheme '" +
                        m_aReceivingParty.getScheme () +
                        "' is invalid");
        nErrs++;
      }
      else
        if (!aIF.isParticipantIdentifierValueValid (m_aReceivingParty.getScheme (), m_aReceivingParty.getValue ()))
        {
          aCondLog.error (sErrorPrefix +
                          "ReceivingParty identifier value '" +
                          m_aReceivingParty.getValue () +
                          "' is invalid for scheme '" +
                          m_aReceivingParty.getScheme () +
                          "'");
          nErrs++;
        }
        else
        {
          final String [] aParts = StringHelper.getExplodedArray (':', m_aReceivingParty.getValue (), 2);
          if (!"0242".equals (aParts[0]))
          {
            aCondLog.error (sErrorPrefix +
                            "ReceivingParty identifier value '" +
                            m_aReceivingParty.getValue () +
                            "' must use the 0242 identifier scheme");
            nErrs++;
          }
        }

    if (m_aReportersRepresentative == null)
    {
      aCondLog.error (sErrorPrefix + "ReportersRepresentative is missing");
      nErrs++;
    }
    else
      if (!aIF.isParticipantIdentifierSchemeValid (m_aReportersRepresentative.getScheme ()))
      {
        aCondLog.error (sErrorPrefix +
                        "ReportersRepresentative identifier meta scheme '" +
                        m_aReportersRepresentative.getScheme () +
                        "' is invalid");
        nErrs++;
      }
      else
        if (!aIF.isParticipantIdentifierValueValid (m_aReportersRepresentative.getScheme (),
                                                    m_aReportersRepresentative.getValue ()))
        {
          aCondLog.error (sErrorPrefix +
                          "ReportersRepresentative identifier value '" +
                          m_aReportersRepresentative.getValue () +
                          "' is invalid for meta scheme '" +
                          m_aReportersRepresentative.getScheme () +
                          "'");
          nErrs++;
        }
        else
        {
          final String [] aParts = StringHelper.getExplodedArray (':', m_aReportersRepresentative.getValue (), 2);
          if (!"0242".equals (aParts[0]))
          {
            aCondLog.error (sErrorPrefix +
                            "ReportersRepresentative identifier value '" +
                            m_aReportersRepresentative.getValue () +
                            "' must use the 0242 identifier scheme");
            nErrs++;
          }
        }

    // OM must have exactly one reported transaction
    if (m_aReportedTransaction == null)
    {
      aCondLog.error (sErrorPrefix + "ReportedTransaction is missing");
      nErrs++;
    }

    return nErrs == 0;
  }

  @Nullable
  public TaxDataType build ()
  {
    if (!isEveryRequiredFieldSet (true))
    {
      LOGGER.error ("At least one mandatory field is not set and therefore the TDD cannot be build.");
      return null;
    }

    final TaxDataType ret = new TaxDataType ();
    ret.setCustomizationID (new CustomizationIDType (m_sCustomizationID));
    ret.setProfileID (new ProfileIDType (m_sProfileID));
    ret.setUUID (new UUIDType (m_sUUID));
    ret.setIssueDate (new IssueDateType (m_aIssueDate));
    ret.setIssueTime (new IssueTimeType (XMLOffsetTime.of (m_aIssueTime)));
    {
      final TaxDataDocumentTypeCodeType a = new TaxDataDocumentTypeCodeType ();
      a.setValue (m_eDocumentTypeCode.getID ());
      ret.setDocumentTypeCode (a);
    }
    {
      final TaxDataDocumentScopeType a = new TaxDataDocumentScopeType ();
      a.setValue (m_eDocumentScope.getID ());
      ret.setDocumentScope (a);
    }
    {
      final TaxDataDocumentReporterRoleType a = new TaxDataDocumentReporterRoleType ();
      a.setValue (m_eReporterRole.getID ());
      ret.setReporterRole (a);
    }
    {
      final String [] aParts = StringHelper.getExplodedArray (':', m_aReportingParty.getValue (), 2);
      final PartyType aParty = new PartyType ();
      aParty.setEndpointID (aParts[1]).setSchemeID (aParts[0]);
      ret.setReportingParty (aParty);
    }
    {
      final String [] aParts = StringHelper.getExplodedArray (':', m_aReceivingParty.getValue (), 2);
      final PartyType aParty = new PartyType ();
      aParty.setEndpointID (aParts[1]).setSchemeID (aParts[0]);
      ret.setReceivingParty (aParty);
    }
    {
      final String [] aParts = StringHelper.getExplodedArray (':', m_aReportersRepresentative.getValue (), 2);
      final PartyType aParty = new PartyType ();
      final PartyIdentificationType aPID = new PartyIdentificationType ();
      aPID.setID (aParts[1]).setSchemeID (aParts[0]);
      aParty.addPartyIdentification (aPID);
      ret.setReportersRepresentative (aParty);
    }
    ret.addReportedTransaction (m_aReportedTransaction);
    return ret;
  }
}
