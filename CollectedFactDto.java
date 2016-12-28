package com.neilsen.dto;

import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;

public class CollectedFactDto {
	private static final MultiMap<Integer, CollectedFactDto> barcodeMap = new MultiValueMap<Integer, CollectedFactDto>();
	
  int auditId;
  int collectionLevel;
  int entityId;


  public CollectedFactDto(final int auditId, final int collectionLevel, final int entityId) {
    super();
    this.auditId = auditId;
    this.collectionLevel = collectionLevel;
    this.entityId = entityId;
  }

  public int getAuditId() {
    return auditId;
  }

  public void setAuditId(final int auditId) {
    this.auditId = auditId;
  }

  public int getEntityId() {
    return entityId;
  }

  public void setEntityId(final int entityId) {
    this.entityId = entityId;
  }

  public int getCollectionLevel() {
    return collectionLevel;
  }

  public void setCollectionLevel(final int collectionLevel) {
    this.collectionLevel = collectionLevel;
  }


}
