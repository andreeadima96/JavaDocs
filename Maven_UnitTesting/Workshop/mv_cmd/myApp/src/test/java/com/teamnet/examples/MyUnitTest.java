package com.teamnet.examples;


import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Andreea.Dima on 7/10/2017.
 */
public class MyUnitTest {
    @Test
    public  void testConcatenate(){
        MyUnit myUnit = new MyUnit();

        String result = myUnit.concatenate("one","two");

        assertEquals("onetwo",result);
    }

    @Test
    public void testConcatenateNulls(){
        MyUnit myUnit = new MyUnit();

        String result = myUnit.concatenate("one",null);
        assertNotNull(result);
        assertEquals("one",result);
    }

    @Test
    public void testGetBoolean(){
        MyUnit myUnit = new MyUnit();

        assertNotNull(myUnit.getBoolean());
      //  assertTrue((Boolean)myUnit.getBoolean() instanceof Boolean);
        assertThat(123, is(123));
        assertThat("a", not(is("b")));
    }


}
