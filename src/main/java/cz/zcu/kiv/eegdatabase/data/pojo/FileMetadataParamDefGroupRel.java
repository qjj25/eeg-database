/*******************************************************************************
 * This file is part of the EEG-database project
 * 
 *   ==========================================
 *  
 *   Copyright (C) 2013 by University of West Bohemia (http://www.zcu.cz/en/)
 *  
 *  ***********************************************************************************************************************
 *  
 *   Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 *   the License. You may obtain a copy of the License at
 *  
 *       http://www.apache.org/licenses/LICENSE-2.0
 *  
 *   Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 *   an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 *   specific language governing permissions and limitations under the License.
 *  
 *  ***********************************************************************************************************************
 *  
 *   FileMetadataParamDefGroupRel.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.data.pojo;

// Generated 2.12.2013 0:56:28 by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * FileMetadataParamDefGroupRel generated by hbm2java
 */
@Entity
@Table(name = "FILE_METADATA_PARAM_GROUP_REL")
public class FileMetadataParamDefGroupRel implements java.io.Serializable {

	private FileMetadataParamDefGroupRelId id;
	private ResearchGroup researchGroup;
	private FileMetadataParamDef fileMetadataParamDef;

	public FileMetadataParamDefGroupRel() {
	}

	public FileMetadataParamDefGroupRel(FileMetadataParamDefGroupRelId id,
			ResearchGroup researchGroup,
			FileMetadataParamDef fileMetadataParamDef) {
		this.id = id;
		this.researchGroup = researchGroup;
		this.fileMetadataParamDef = fileMetadataParamDef;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "fileMetadataParamDefId", column = @Column(name = "FILE_METADATA_PARAM_DEF_ID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "researchGroupId", column = @Column(name = "RESEARCH_GROUP_ID", nullable = false, precision = 22, scale = 0)) })
	public FileMetadataParamDefGroupRelId getId() {
		return this.id;
	}

	public void setId(FileMetadataParamDefGroupRelId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESEARCH_GROUP_ID", nullable = false, insertable = false, updatable = false)
	public ResearchGroup getResearchGroup() {
		return this.researchGroup;
	}

	public void setResearchGroup(ResearchGroup researchGroup) {
		this.researchGroup = researchGroup;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FILE_METADATA_PARAM_DEF_ID", nullable = false, insertable = false, updatable = false)
	public FileMetadataParamDef getFileMetadataParamDef() {
		return this.fileMetadataParamDef;
	}

	public void setFileMetadataParamDef(
			FileMetadataParamDef fileMetadataParamDef) {
		this.fileMetadataParamDef = fileMetadataParamDef;
	}

}
