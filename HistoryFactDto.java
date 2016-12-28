package com.neilsen.dto;

public class HistoryFactDto {
	int auditId;
	int exhibitionId;
	int instanceId;
	int positionId;
	int locationId;
	int factId;
	int collectionLevel;
	int entityId;
	int previousYearValue;
	int twoVisitAgoValue;

	public HistoryFactDto(final int auditId, final int collectionLevel,
			final int entityId) {
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

	public int getExhibitionId() {
		return exhibitionId;
	}

	public void setExhibitionId(final int exhibitionId) {
		this.exhibitionId = exhibitionId;
	}

	public int getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(final int instanceId) {
		this.instanceId = instanceId;
	}

	public int getPositionId() {
		return positionId;
	}

	public void setPositionId(final int positionId) {
		this.positionId = positionId;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(final int locationId) {
		this.locationId = locationId;
	}

	public int getFactId() {
		return factId;
	}

	public void setFactId(final int factId) {
		this.factId = factId;
	}

	public int getCollectionLevel() {
		return collectionLevel;
	}

	public void setCollectionLevel(final int collectionLevel) {
		this.collectionLevel = collectionLevel;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(final int entityId) {
		this.entityId = entityId;
	}

	public int getPreviousYearValue() {
		return previousYearValue;
	}

	public void setPreviousYearValue(final int previousYearValue) {
		this.previousYearValue = previousYearValue;
	}

	public int getTwoVisitAgoValue() {
		return twoVisitAgoValue;
	}

	public void setTwoVisitAgoValue(final int twoVisitAgoValue) {
		this.twoVisitAgoValue = twoVisitAgoValue;
	}

	@Override
	public String toString() {
		return "HistoryFactDto [auditId=" + auditId + ", factId=" + factId
				+ ", collectionLevel=" + collectionLevel + ", entityId="
				+ entityId + "]";
	}

}
