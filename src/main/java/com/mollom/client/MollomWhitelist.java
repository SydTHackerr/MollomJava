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

import com.mollom.client.rest.RestResponse;
import com.mollom.client.rest.WhitelistEntry;
import com.mollom.client.rest.WhitelistEntryResponse;
import com.mollom.client.rest.WhitelistResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.util.List;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 */
public class MollomWhitelist extends Mollom {

  public MollomWhitelist (String publicKey, String privateKey) {
    super(publicKey, privateKey, false);
  }

  public MollomWhitelist (String publicKey, String privateKey, boolean testing) {
    super(publicKey, privateKey, testing);
  }

  /**
   * Create a new entry.
   *
   * @param entry
   * @return the new entry with some additional fields.
	 * @throws Exception when something goes wrong while contacting Mollom
   */
  public WhitelistEntry add (WhitelistEntry entry) throws Exception {
    if (entry.getValue() == null) {
      throw new IllegalArgumentException("Value is mandatory.");
    }
    if (entry.getContext() == null) {
      throw new IllegalArgumentException("Context is mandatory.");
    }

    MultivaluedMap<String, String> params = new MultivaluedMapImpl();
    add(params, "value", entry.getValue());
    add(params, "status", entry.getStatus());
    add(params, "context", entry.getContext());
    add(params, "note", entry.getNote());

    WhitelistEntryResponse response = invoke("POST", "/whitelist/" + publicKey, params, WhitelistEntryResponse.class);
    if (response.getCode() != 200) {
      throw new Exception(response.getMessage());
    }
    return response.getEntry();
  }

  /**
   * Get the entry identified by the provided {@code id}.
   *
   * @param id the id of the entry
   * @return the entry
   * @throws IllegalArgumentException when the id is null or empty
	 * @throws Exception when something goes wrong while contacting Mollom
   */
  public WhitelistEntry get (String id) throws Exception {
    if (id == null || id.isEmpty()) {
      throw new IllegalArgumentException("Entry id should not be null or empty.");
    }

    WhitelistEntryResponse response = invoke("GET", "/whitelist/" + publicKey + "/" + id, new MultivaluedMapImpl(), WhitelistEntryResponse.class);
    if (response.getCode() != 200) {
      throw new Exception(response.getMessage());
    }
    return response.getEntry();
  }

  /**
   * Update the values of the provided entry.
   *
   * @param entry the entry that needs to be updated
   * @return an updated version of the entry
   * @throws IllegalArgumentException if the entry or the entry id is null
	 * @throws Exception when something goes wrong while contacting Mollom
   */
  public WhitelistEntry update (WhitelistEntry entry) throws Exception {
    if (entry == null || entry.getId() == null) {
      throw new IllegalArgumentException("Entry id should not be null.");
    }
    if (entry.getValue() == null) {
      throw new IllegalArgumentException("Value is mandatory.");
    }
    if (entry.getContext() == null) {
      throw new IllegalArgumentException("Context is mandatory.");
    }

    MultivaluedMap<String, String> params = new MultivaluedMapImpl();
    add(params, "value", entry.getValue());
    add(params, "status", entry.getStatus());
    add(params, "context", entry.getContext());
    add(params, "note", entry.getNote());

    WhitelistEntryResponse response = invoke("POST", "/whitelist/" + publicKey + "/" + entry.getId(), params, WhitelistEntryResponse.class);
    if (response.getCode() != 200) {
      throw new Exception(response.getMessage());
    }
    return response.getEntry();
  }

  /**
   * Delete the provided entry.
   *
   * @param entry the entry that needs to be deleted
	 * @throws Exception when something goes wrong while contacting Mollom
   */
  public void delete (WhitelistEntry entry) throws Exception {
    if (entry == null) {
      throw new IllegalArgumentException("Entry id should not be null or empty.");
    }
    delete (entry.getId());
  }

  /**
   * Delete the entry with the provided id.
   *
   * @param id the id of the entry that needs to be deleted
	 * @throws Exception when something goes wrong while contacting Mollom
   */
  public void delete (String id) throws Exception {
    if (id == null || id.isEmpty()) {
      throw new IllegalArgumentException("Entry id should not be null or empty.");
    }

    RestResponse response = invoke("POST", "/whitelist/" + publicKey + "/" + id + "/delete", new MultivaluedMapImpl(), RestResponse.class);
    if (response.getCode() != 200) {
      throw new Exception(response.getMessage());
    }
  }

  /**
   * Get a list with all whitelist entries.
   * 
   * @return the list with all entries
	 * @throws Exception when something goes wrong while contacting Mollom
   */
  public List<WhitelistEntry> list () throws Exception {
    WhitelistResponse response = invoke("GET", "/whitelist/" + publicKey, new MultivaluedMapImpl(), WhitelistResponse.class);
    if (response.getCode() != 200) {
      throw new Exception(response.getMessage());
    }
    return response.getEntries();
  }

  /**
   * Get a list with {@code count} whitelist entries, starting with entry
   * {@code offset} (included). If {@code count} is null, all entries are
   * retrieved. If offset is null, it's assumed to be 0.
   *
   * @param offset the index of the first entry (default 0)
   * @param count the number of entries to retrieve
   * @return the list of entries
   * @throws IllegalArgumentException if offset or count are not null and negative.
	 * @throws Exception when something goes wrong while contacting Mollom
   */
  public List<WhitelistEntry> list (Integer offset, Integer count) throws Exception {
    if (offset != null && offset < 0) {
      throw new IllegalArgumentException("Offset should be 0 or larger when provided.");
    }
    if (count != null && count <= 0) {
      throw new IllegalArgumentException("Offset should be strictly positive when provided.");
    }

    MultivaluedMap<String, String> params = new MultivaluedMapImpl();
    add(params, "offset", offset);
    add(params, "count", count);

    WhitelistResponse response = invoke("GET", "/whitelist/" + publicKey, params, WhitelistResponse.class);
    if (response.getCode() != 200) {
      throw new Exception(response.getMessage());
    }
    return response.getEntries();
  }
}
