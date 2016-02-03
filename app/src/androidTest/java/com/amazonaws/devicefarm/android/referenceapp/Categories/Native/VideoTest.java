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

package com.amazonaws.devicefarm.android.referenceapp.Categories.Native;

import android.support.test.espresso.Espresso;
import android.support.test.filters.RequiresDevice;
import android.widget.VideoView;

import com.amazonaws.devicefarm.android.referenceapp.IdlingResources.VideoPlayerIdlingResource;
import com.amazonaws.devicefarm.android.referenceapp.R;

import org.junit.Test;

/**
 * Tests for video view
 */
public class VideoTest extends NativeBase {
    private VideoPlayerIdlingResource videoPlayerIdlingResource;

    /**
     * Registers a idling resource so it can wait for the video to be loaded/playing
     *
     * @throws Exception
     */
    @Override
    @RequiresDevice
    public void setUp() {
        super.setUp();
        videoPlayerIdlingResource = new VideoPlayerIdlingResource((VideoView)
                mActivityRule.getActivity().findViewById(R.id.native_video_player));

        // videoPlayerIdlingResource = new VideoPlayerIdlingResource((VideoView)
        //         mActivityRule.getActivity().findViewById(R.id.native_video_player));
    }

    /**
     * Unregisters the idling resource
     *
     * @throws Exception
     */
    @Override
    @RequiresDevice
    public void tearDown() throws Exception {
        Espresso.unregisterIdlingResources(videoPlayerIdlingResource);
        super.tearDown();
    }

    @Override
    protected int getPageIndex() {
        return 2;
    }

    @Override
    @RequiresDevice
    public void testSanity() {
        Espresso.registerIdlingResources(videoPlayerIdlingResource);

        checkIfIdDisplayed(R.id.native_video_player);
        Espresso.unregisterIdlingResources(videoPlayerIdlingResource);

    }

    /**
     * Checks if the video player has the content description indicating
     * that the video is playing.
     *
     * @throws InterruptedException
     */
    @Test
    @RequiresDevice
    public void testCheckVideoPlaying() throws InterruptedException {
        Espresso.registerIdlingResources(videoPlayerIdlingResource);

        checkIdWithContentDescription(R.id.native_video_player, R.string.videoView_playing);
        Espresso.unregisterIdlingResources(videoPlayerIdlingResource);

    }
}
