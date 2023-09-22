package com.example.bstest23.networkCommunication

import android.util.Log
import com.google.gson.JsonArray
import retrofit2.Response

data class ModelList (
    var title: String = "",
    var description: String = "",
    var imageThumbnail : String? = null,
    var details : MutableList<String> = mutableListOf()
){
    fun loadApi(callback: (Boolean, List<ModelList>?, String?) -> Unit) {
        ServerManager().getRequest(
            url = ApiConstants.API_ITEMS,
            listener = object : RequestListener{
                override fun onSuccess(response: Any?) {

                    Log.d("Response",response.toString())
                    val responseObject = (response as? Response<*>)
                    //val body = responseObject?.body() as JsonObject

                    val productsJsonArray = responseObject?.body() as JsonArray

                    val productList : MutableList<ModelList> = mutableListOf()

                    for (index in 0 until productsJsonArray.size()){
                        if (productsJsonArray.get(index) != null) {
                            val productJsonObject = productsJsonArray.get(index).asJsonObject
                            val item = ModelList()

//                            if(productJsonObject.get("id")!= null){
//                                //item.
//                            }

                            if(productJsonObject.get("title")!= null){
                                item.title = productJsonObject.get("title").asString
                            }

                            if(productJsonObject.get("description")!= null){
                                item.description = productJsonObject.get("description").asString
                            }

                            if (productJsonObject.get("ingredients")!=null){
                                val ingredientsJsonArray = productJsonObject.get("ingredients").asJsonArray
                                for (i in 0 until ingredientsJsonArray.size()){

                                    if (ingredientsJsonArray.get(i) != null) {
                                        val ingredient = ingredientsJsonArray.get(i).asString
                                        item.details.add(ingredient)
                                    }
                                }

                            }

                            Log.d("id->",productJsonObject.get("id").asString)
                            Log.d("title->",productJsonObject.get("title").asString)
                            Log.d("description->",productJsonObject.get("description").asString)
//                            Log.d("thumbnail->",productJsonObject.get("thumbnail").asString)

                            //val item = CustomModel(productJsonObject.get("title").asString,productsJsonArray.get(index).asJsonObject.get("description").asString )
                            productList.add(item)
                        }
                    }

                    callback(true, productList, null)
                }

                override fun onError(error: String) {
//                    Timber.e("getFollowingTagApi: error $error")
                    Log.e("Error",error)
                    callback(false, null, error)
                }

            }
        )
    }
}
