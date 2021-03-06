/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2020 the original author or authors.
 */
package org.assertj.core.internal.urls;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.error.uri.ShouldHaveHost.shouldHaveHost;
import static org.assertj.core.util.AssertionsUtil.expectAssertionError;
import static org.assertj.core.util.FailureMessages.actualIsNull;

import java.net.MalformedURLException;
import java.net.URL;

import org.assertj.core.internal.UrlsBaseTest;
import org.junit.jupiter.api.Test;

class Urls_assertHasHost_Test extends UrlsBaseTest {

  @Test
  void should_fail_if_actual_is_null() {
    // GIVEN
    URL url = null;
    String expectedHost = "www.helloworld.org";
    // WHEN
    AssertionError assertionError = expectAssertionError(() -> urls.assertHasHost(info, url, expectedHost));
    // THEN
    then(assertionError).hasMessage(actualIsNull());
  }

  @Test
  void should_pass_if_actual_URL_has_the_given_host() throws MalformedURLException {
    // GIVEN
    URL url = new URL("http://www.helloworld.org");
    String expectedHost = "www.helloworld.org";
    // WHEN/THEN
    urls.assertHasHost(info, url, expectedHost);
  }

  @Test
  void should_pass_if_actual_URL_with_path_has_the_given_host() throws MalformedURLException {
    // GIVEN
    URL url = new URL("http://www.helloworld.org/pages");
    String expectedHost = "www.helloworld.org";
    // WHEN/THEN
    urls.assertHasHost(info, url, expectedHost);
  }

  @Test
  void should_fail_if_actual_URL_has_not_the_expected_host() throws MalformedURLException {
    // GIVEN
    URL url = new URL("http://example.com/pages/");
    String expectedHost = "example.org";
    // WHEN
    AssertionError assertionError = expectAssertionError(() -> urls.assertHasHost(info, url, expectedHost));
    // THEN
    then(assertionError).hasMessage(shouldHaveHost(url, expectedHost).create());
  }
}
