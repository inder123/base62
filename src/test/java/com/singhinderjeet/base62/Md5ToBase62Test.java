/*
 * Copyright (C) 2018 Inderjeet Singh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.singhinderjeet.base62;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Unit tests for {@link Base62}
 *
 * @author Inderjeet Singh
 */
public class Md5ToBase62Test {

    @Test
    public void testMd5ToBase62() {
        assertEquals("cbIKGiMVkLFTeenAa5kgO4", Md5ToBase62.toBase62("9e107d9d372bb6826bd81d3542a419d6"));
    }

    @Test
    public void testBase62ToMd5() {
        assertEquals("0000bdd3bb56865852a632deadbc62fc", Md5ToBase62.fromBase62("4KfZYA1udiGCjCEFC0l"));
        assertEquals(32, Md5ToBase62.fromBase62("4KfZYA1udiGCjCEFC0l").length());
    }

    @Test
    public void testMd5RoundTrip() {
        assertEquals("9e107d9d372bb6826bd81d3542a419d6", Md5ToBase62.fromBase62(Md5ToBase62.toBase62("9e107d9d372bb6826bd81d3542a419d6")));
        assertEquals("cbIKGiMVkLFTeenAa5kgO4", Md5ToBase62.toBase62(Md5ToBase62.fromBase62("cbIKGiMVkLFTeenAa5kgO4")));
    }

    @Test
    public void testMd5ToBase62RequiresValidInput() {
        try {
            Md5ToBase62.toBase62("hello world");
            fail();
        } catch (NumberFormatException expected) {}
    }

    @Test
    public void testBase62ToMd5RequiresValidInput() {
        try {
            Md5ToBase62.fromBase62("hello world");
            fail();
        } catch (NumberFormatException expected) {}
    }
}
