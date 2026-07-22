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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.collection.commons.CommonsArrayList;
import com.helger.io.resource.IReadableResource;
import com.helger.peppol.om.tdd.jaxb.PeppolOMTDD10Marshaller;
import com.helger.peppol.om.tdd.testfiles.PeppolOMTestFiles;
import com.helger.peppol.om.tdd.v10.TaxDataType;
import com.helger.schematron.ISchematronResource;
import com.helger.schematron.svrl.SVRLHelper;
import com.helger.schematron.svrl.SVRLMarshaller;
import com.helger.schematron.svrl.jaxb.SchematronOutputType;

/**
 * Test class for class {@link PeppolOMTDDValidator}.
 *
 * @author Philip Helger
 */
public final class PeppolOMTDDValidatorTest
{

  private static final Logger LOGGER = LoggerFactory.getLogger (PeppolOMTDDValidatorTest.class);

  @Test
  public void testReadTDD10Good () throws Exception
  {
    final ISchematronResource aSCHRes = PeppolOMTDDValidator.getSchematronOM_TDD_10 ();
    assertNotNull (aSCHRes);

    final PeppolOMTDD10Marshaller aMarshaller = new PeppolOMTDD10Marshaller ();

    for (final IReadableResource aRes : PeppolOMTestFiles.getAllGoodTDD10Files ())
    {
      LOGGER.info ("Reading " + aRes.getPath ());
      final TaxDataType tdd = aMarshaller.read (aRes);
      assertNotNull (tdd);

      final SchematronOutputType aSVRL = aSCHRes.applySchematronValidationToSVRL (aRes);
      assertNotNull (aSVRL);

      if (false)
        LOGGER.info (new SVRLMarshaller ().setFormattedOutput (true).getAsString (aSVRL));

      assertEquals (new CommonsArrayList <> (), SVRLHelper.getAllFailedAssertions (aSVRL));
    }
  }
}
