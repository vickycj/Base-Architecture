package com.vicky.apps.datapoints.ui.viewmodel

import com.google.gson.Gson
import com.vicky.apps.datapoints.common.SchedulerProvider
import com.vicky.apps.datapoints.data.remote.Repository
import com.vicky.apps.datapoints.ui.model.ResponseData

import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {


    @Mock
    lateinit var repository: Repository

    private val schedulerProvider = SchedulerProvider(Schedulers.trampoline(), Schedulers.trampoline())

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(repository, schedulerProvider)
    }

    @After
    fun tearDown() {
    }



    @Test
    fun getFirstQuarter(){
        assert(!viewModel.checkFirstQuarter("2005-Q1"))
    }




    fun getObject(): ResponseData{
        val jsonData ="{\"help\": \"https://data.gov.sg/api/3/action/help_show?name=datastore_search\", \"success\": true, \"result\": {\"resource_id\": \"a807b7ab-6cad-4aa6-87d0-e283a7353a0f\", \"fields\": [{\"type\": \"int4\", \"id\": \"_id\"}, {\"type\": \"text\", \"id\": \"quarter\"}, {\"type\": \"numeric\", \"id\": \"volume_of_mobile_data\"}], \"records\": [{\"volume_of_mobile_data\": \"0.000384\", \"quarter\": \"2004-Q3\", \"_id\": 1}, {\"volume_of_mobile_data\": \"0.000543\", \"quarter\": \"2004-Q4\", \"_id\": 2}], \"_links\": {\"start\": \"/api/action/datastore_search?limit=2&resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f\", \"next\": \"/api/action/datastore_search?offset=2&limit=2&resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f\"}, \"limit\": 2, \"total\": 56}}"
        val gson = Gson()
        return gson.fromJson(jsonData, ResponseData::class.java)

    }

    @Test
    fun testCheckSumValues(){

        val responseData = getObject()

        viewModel.checkAndSumTheValue("2004",responseData.result.records)

        assert(viewModel.getDataFields().size > 0)
    }

    @Test
    fun getDataFromRemote() {




        Mockito.`when`(repository.getDataFromApi(viewModel.createRequestParams())).thenReturn(Single.just(getObject()))

        val testObserver = TestObserver<ResponseData>()

        viewModel.generateApiCall()
            .subscribe(testObserver)

        testObserver.assertNoErrors()
        testObserver.assertValue { responseData -> responseData.success }
    }
}