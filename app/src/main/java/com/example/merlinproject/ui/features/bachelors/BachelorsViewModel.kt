package com.example.merlinproject.ui.features.bachelors

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merlinproject.common.CampusFirestoreDocument.CASA_LIBERTAD_DOCUMENT_ID
import com.example.merlinproject.common.CampusFirestoreDocument.CENTRO_HISTORICO_DOCUMENT_ID
import com.example.merlinproject.common.CampusFirestoreDocument.CUAUTEPEC_DOCUMENT_ID
import com.example.merlinproject.common.CampusFirestoreDocument.DEL_VALLE_DOCUMENT_ID
import com.example.merlinproject.common.CampusFirestoreDocument.SAN_LORENZO_TEZONCO_DOCUMENT_ID
import com.example.merlinproject.common.Resource
import com.example.merlinproject.domain.model.campus.CampusModel
import com.example.merlinproject.domain.model.campus.DocumentModel
import com.example.merlinproject.domain.model.campus.OfertaAcademica
import com.example.merlinproject.domain.usescase.campus.CampusUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BachelorsViewModel @Inject constructor(
    private val campusUsesCase: CampusUsesCase
) : ViewModel() {

    var campusResponse by mutableStateOf<Resource<List<CampusModel>>?>(null)
    var ofertaResponse by mutableStateOf<Resource<List<OfertaAcademica>>?>(null)
    var documentResponse by mutableStateOf<Resource<DocumentModel?>?>(null)

    fun getCampus() = viewModelScope.launch {
        campusResponse = Resource.Loading
        campusUsesCase.getCampusByName().collect() { campusList ->
            campusResponse = campusList
        }
    }

    fun getDocumentDelValle() = viewModelScope.launch {
        documentResponse = Resource.Loading
        campusUsesCase.getDocument(DEL_VALLE_DOCUMENT_ID).collect() {
            documentResponse = it
        }
    }

    fun getDocumentCuautepec() = viewModelScope.launch {
        documentResponse = Resource.Loading
        campusUsesCase.getDocument(CUAUTEPEC_DOCUMENT_ID).collect() {
            documentResponse = it
        }
    }

    fun getDocumentSanLorenzo() = viewModelScope.launch {
        documentResponse = Resource.Loading
        campusUsesCase.getDocument(SAN_LORENZO_TEZONCO_DOCUMENT_ID).collect() {
            documentResponse = it
        }
    }

    fun getDocumentCentro() = viewModelScope.launch {
        documentResponse = Resource.Loading
        campusUsesCase.getDocument(CENTRO_HISTORICO_DOCUMENT_ID).collect() {
            documentResponse = it
        }
    }

    fun getDocumentCasaLibertad() = viewModelScope.launch {
        documentResponse = Resource.Loading
        campusUsesCase.getDocument(CASA_LIBERTAD_DOCUMENT_ID).collect() {
            documentResponse = it
        }
    }

    fun getOfertas() = viewModelScope.launch {
        ofertaResponse = Resource.Loading
        campusUsesCase.getOferta().collect() {
            ofertaResponse = it
        }
    }

    init {
        getCampus()
        getOfertas()
        getDocumentCuautepec()
    }
}