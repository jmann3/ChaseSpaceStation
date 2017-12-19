package com.example.mannj.chase_jrm;

import com.example.mannj.chase_jrm.mvvm.view.SinglePassViewHolder;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by mannj on 12/17/17.
 */

public class SinglePassViewHolderTests {

    @Test
    public void readableDateFromCurrentTimeIsAccurate() throws Exception {
        String stringDate = SinglePassViewHolder.Companion.readableDateFromCurrentTime(1513562457);
        assertEquals("12/17/17 21:00:57", stringDate);
    }
}
