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
package com.helger.peppol.om.tdd.jaxb;

import java.util.UUID;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.style.PresentForCodeCoverage;
import com.helger.io.resource.ClassPathResource;

/**
 * Contains all the constants for Peppol OM TDD handling.
 *
 * @author Philip Helger
 */
@Immutable
public final class CPeppolOMTDD
{
  @NonNull
  private static ClassLoader _getCL ()
  {
    return CPeppolOMTDD.class.getClassLoader ();
  }

  /**
   * XML Schema resources for Peppol OM TDD XSD 1.0
   */
  public static final String TDD_XSD_1_0_PATH = "/external/schemas/peppol-om-tdd-1.0.1.xsd";

  /**
   * XML Schema resources for Peppol OM TDD XSD 1.0
   */
  public static final ClassPathResource TDD_XSD_1_0 = new ClassPathResource (TDD_XSD_1_0_PATH, _getCL ());

  /** Namespace URI for Peppol OM TDD XSD 1.0 */
  public static final String TDD_XSD_1_0_NS = "urn:peppol:schema:om-taxdata:1.0";

  /**
   * The fixed UUID v5 namespace to be used for the calculation of the Invoice UUID (BTOM-002) and
   * the Seller UUID (BTOM-004). See OM Solution Architecture v1.0.3, section 10.2.3.
   */
  public static final UUID PEPPOL_OM_NAMESPACE = UUID.fromString ("e0bc4ac8-b025-46e5-a76d-0c893fc3027e");

  @PresentForCodeCoverage
  private static final CPeppolOMTDD INSTANCE = new CPeppolOMTDD ();

  private CPeppolOMTDD ()
  {}
}
