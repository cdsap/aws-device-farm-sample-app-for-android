/*
 * Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.amazonaws.devicefarm.android.referenceapp.Categories;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.RecyclerViewActions;

import com.amazonaws.devicefarm.android.referenceapp.BaseADFTest;
import com.amazonaws.devicefarm.android.referenceapp.IdlingResources.WebViewIdlingResource;
import com.amazonaws.devicefarm.android.referenceapp.R;

import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Tests for a web view
 */
public class WebViewTests extends BaseADFTest {
    private WebViewIdlingResource webViewIdlingResource;

    /**
     * Registers the webview idling resources before the webview tests.
     *
     * @throws Exception
     */
    @Override
    public void setUp() {
        DrawerActions.openDrawer(R.id.drawer_layout);
        RecyclerViewActions.scrollTo(withText(getClassName()));
        onView(withId(R.id.drawerList)).perform(RecyclerViewActions.actionOnItem(hasDescendant(withText(getClassName())), click()));

     //   Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
     //   WebViewFragment fragment = (WebViewFragment) mActivityRule.getActivity().getSupportFragmentManager()

       //         .findFragmentById(R.id.container_body);
      //  mActivityRule.getActivity().runOnUiThread(new Runnable() {
       ///     @Override
        //    public void run() {
      //          webViewIdlingResource = new WebViewIdlingResource((WebView) mActivityRule.getActivity().findViewById(R.id.webView_browser));
       // (WebView) ((WebView) mActivityRule.getActivity().findViewById(R.id.webView_browser)).setWebChromeClient(new WebChromeClient());
        //        Espresso.registerIdlingResources(webViewIdlingResource);

        //    }
       // });
   //               onView(withId(R.id.webView_browser)));
    }

    /**
     * Unregister the idling resource
     *
     * @throws Exception
     */
    @Override
    public void tearDown() throws Exception {
        Espresso.unregisterIdlingResources(webViewIdlingResource);
        super.tearDown();
    }

    @Override
    protected String getClassName() {
        return "Web";
    }

    @Override
    public void testSanity() {
        checkIfIdDisplayed(R.id.website_input);
        checkIfIdDisplayed(R.id.webView_browser);
    }

    /**
     * Tests if the error toast is displayed when a bad url is entered in the url bar
     */
    @Test
    public void testInputInvalidUrl() {
        final String BAD_URL = "a bad url string";
        typeInWebBar(BAD_URL);
        verifyToastMessage(R.string.web_error_toast);
    }

    /**
     * Tests to see if google is loaded when navigated to Google.com
     */
   // @UiThreadTest
    @Test
    public void testNavigateToWebSite() {
        final String URL = "http://www.google.com/";
        typeInWebBar(URL);
        checkIdWithContentDescription(R.id.webView_browser, "www.google.com");
    }

    /**
     * Types and navigates to url
     *
     * @param url the inputted url
     */
    private void typeInWebBar(String url) {
        onView(withId(R.id.website_input)).perform(click()).perform(typeText(url + "\n"));
    }
}
