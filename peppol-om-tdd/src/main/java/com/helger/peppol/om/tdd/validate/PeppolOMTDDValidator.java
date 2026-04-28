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
package com.helger.peppol.om.tdd.validate;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.concurrent.Immutable;
import com.helger.base.exception.InitializationException;
import com.helger.schematron.ISchematronResource;
import com.helger.schematron.sch.SchematronResourceSCH;

/**
 * This class contains the Schematron resources for validating Peppol OM TDD documents.
 *
 * @author Philip Helger
 */
@Immutable
public final class PeppolOMTDDValidator
{
  public static final String SCH_OM_TDD_100_PATH = "external/schematron/peppol-om-tdd-1.0.0.sch";

  private static final ISchematronResource OM_TDD_100 = SchematronResourceSCH.fromClassPath (SCH_OM_TDD_100_PATH);

  static
  {
    for (final ISchematronResource aSch : new ISchematronResource [] { OM_TDD_100 })
      if (!aSch.isValidSchematron ())
        throw new InitializationException ("Schematron in " + aSch.getResource ().getPath () + " is invalid");
  }

  private PeppolOMTDDValidator ()
  {}

  /**
   * @return Schematron OM TDD v1.0.0
   */
  @NonNull
  public static ISchematronResource getSchematronOM_TDD_100 ()
  {
    return OM_TDD_100;
  }

  /**
   * @return Schematron OM TDD v1.0.x
   */
  @NonNull
  public static ISchematronResource getSchematronOM_TDD_10 ()
  {
    return getSchematronOM_TDD_100 ();
  }
}
