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
package com.mollom.client.rest;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BlacklistResponse extends RestResponse {

  @XmlElementWrapper(name = "list")
  @XmlElement(name = "entry")
  private List<? extends BlacklistEntry> entries;
  private int listOffset;
  private int listCount;
  private int listTotal;

  /**
   * @return the entries
   */
  public List<? extends BlacklistEntry> getEntries() {
    return entries;
  }

  /**
   * @param entries the entries to set
   */
  public void setEntries(List<? extends BlacklistEntry> entries) {
    this.entries = entries;
  }

  /**
   * @return the listOffset
   */
  public int getListOffset() {
    return listOffset;
  }

  /**
   * @param listOffset the listOffset to set
   */
  public void setListOffset(int listOffset) {
    this.listOffset = listOffset;
  }

  /**
   * @return the listCount
   */
  public int getListCount() {
    return listCount;
  }

  /**
   * @param listCount the listCount to set
   */
  public void setListCount(int listCount) {
    this.listCount = listCount;
  }

  /**
   * @return the listTotal
   */
  public int getListTotal() {
    return listTotal;
  }

  /**
   * @param listTotal the listTotal to set
   */
  public void setListTotal(int listTotal) {
    this.listTotal = listTotal;
  }
}
