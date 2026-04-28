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
package com.helger.peppol.om.tdd.testfiles;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.Nonempty;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.io.resource.ClassPathResource;

/**
 * Sanity methods to get all Peppol OM test files
 *
 * @author Philip Helger
 */
public final class PeppolOMTestFiles
{
  private PeppolOMTestFiles ()
  {}

  @NonNull
  private static ClassLoader _getCL ()
  {
    return PeppolOMTestFiles.class.getClassLoader ();
  }

  @NonNull
  @ReturnsMutableCopy
  private static ICommonsList <@NonNull ClassPathResource> _getAll (@NonNull final String sPrefix,
                                                                    @NonNull final String @NonNull... aFilenames)
  {
    final ICommonsList <ClassPathResource> ret = new CommonsArrayList <> (aFilenames.length);
    for (final String s : aFilenames)
      ret.add (new ClassPathResource ("external/" + sPrefix + s, _getCL ()));
    return ret;
  }

  @NonNull
  @Nonempty
  @ReturnsMutableCopy
  public static ICommonsList <@NonNull ClassPathResource> getAllGoodTDD10Files ()
  {
    return _getAll ("tdd/10/good/",
                    "commercial-invoice-tdd.xml",
                    "example-tds-no-repdoc.xml",
                    "example-tds-with-repdoc.xml",
                    "simple.xml",
                    "standard-invoice-tdd.xml",
                    "tax-currency.xml");
  }
}
