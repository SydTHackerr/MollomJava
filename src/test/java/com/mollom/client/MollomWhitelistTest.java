/**
 * Copyright (c) 2010-2012 Mollom. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *   * Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.mollom.client;

import static com.mollom.client.BaseMollomTest.PRIVATE_KEY;
import static com.mollom.client.BaseMollomTest.PUBLIC_KEY;
import com.mollom.client.rest.WhitelistEntry;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Thomas Meire
 */
public class MollomWhitelistTest extends BaseMollomTest {

  @Test
  public void testCRUD () throws Exception {
    MollomWhitelist whitelist = new MollomWhitelist(PUBLIC_KEY, PRIVATE_KEY);

    WhitelistEntry original = new WhitelistEntry();
    original.setValue("85.234.217.77");
    original.setContext("authorIp");
    original.setStatus(1);
    original.setNote("Whitelist Mollom headquarters.");

    WhitelistEntry added = whitelist.add(original);
    assertNotNull(added);
    assertNotNull(added.getId());
    assertTrue(added.getCreated() != 0);
    assertEquals(original.getValue(),   added.getValue());
    assertEquals(original.getContext(), added.getContext());
    assertEquals(original.getStatus(),  added.getStatus());
    assertEquals(original.getNote(),    added.getNote());

    added.setNote("Whitelist Mollom BE headquarters.");

    WhitelistEntry updated = whitelist.update(added);
    assertNotNull(updated);
    assertEquals(added.getId(), updated.getId());
    assertEquals(added.getCreated(),    updated.getCreated());
    assertEquals(added.getValue(),   updated.getValue());
    assertEquals(added.getContext(), updated.getContext());
    assertEquals(added.getStatus(),  updated.getStatus());
    assertEquals(added.getNote(),    updated.getNote());

    WhitelistEntry fetched = whitelist.get(added.getId());
    assertNotNull(fetched);
    assertEquals(added.getId(),      fetched.getId());
    assertEquals(added.getCreated(), fetched.getCreated());
    assertEquals(added.getValue(),   fetched.getValue());
    assertEquals(added.getContext(), fetched.getContext());
    assertEquals(added.getStatus(),  fetched.getStatus());
    assertEquals(added.getNote(),    fetched.getNote());
    
    whitelist.delete(fetched);
  }
}
