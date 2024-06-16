package com.example.finalproject.ui.residence_details.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.finalproject.R
import com.example.finalproject.retrofit.RetrofitClient
import com.example.finalproject.storage.AppReferences
import com.example.finalproject.storage.BaseActivity
import com.example.finalproject.ui.update_listing.factory.GetResidenceFactory
import com.example.finalproject.ui.update_listing.repository.GetResidenceRepository
import com.example.finalproject.ui.update_listing.viewModel.GetResidenceViewModel
import kotlinx.android.synthetic.main.fragment_description.*
import org.json.JSONException
import org.json.JSONObject

class DescriptionFragment : Fragment() {

    private lateinit var baseActivity: BaseActivity
    private lateinit var getResidenceViewModel: GetResidenceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivity = BaseActivity()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val getResidenceRepository = GetResidenceRepository(RetrofitClient.instance)
        val factoryGetResidence = GetResidenceFactory(getResidenceRepository)
        getResidenceViewModel = ViewModelProvider(
            this@DescriptionFragment, factoryGetResidence)[GetResidenceViewModel::class.java]
        getResidence()
    }

    private fun getResidence() {
        val token = AppReferences.getToken(requireContext())
        val residenceId = arguments?.getString("residenceId")

        if (residenceId == null) {
            Log.e("DescriptionFragment", "Residence ID is null")
            return
        }

        baseActivity.showProgressDialog(requireContext(), "please wait...")

        getResidenceViewModel.getResidence(token, residenceId)

        getResidenceViewModel.getResidenceResponseLiveData.observe(viewLifecycleOwner) { response ->
            baseActivity.hideProgressDialog()
            response?.let {
                val ownerId = response.residence.ownerId
                val image = ownerId?.image?.url

                if (image != null) {
                    val imageView: ImageView = requireView().findViewById(R.id.iv_agent_image)
                    Glide.with(this)
                        .load(image)
                        .into(imageView)
                } else {
                    Log.e("DescriptionFragment", "Image URL is null")
                }

                tv_agent_name.text = ownerId?.username ?: "N/A"

                apartment_location_description.text = response.residence.location.fullAddress ?: "N/A"
                textView_number_of_total_area.text = response.residence.totalarea ?: "N/A"
                textView_number_of_pool_area.text = response.residence.poolArea ?: "N/A"
                textView_number_of_age.text = response.residence.houseage ?: "N/A"
                textView_number_of_bedrooms.text = response.residence.bedroomAbvGr.toString()
                textView_number_of_kitchen.text = response.residence.KitchenAbvGr.toString()
                textView_number_of_bathrooms.text = response.residence.totalbaths.toString()

                if (!response.residence.hasBasement) {
                    cv_basement.visibility = View.GONE
                }

                if (!response.residence.hasGarage) {
                    cv_garage_cars.visibility = View.GONE
                    tv_garage_quality_details_1.visibility = View.GONE
                    tv_garage_quality_details_2.visibility = View.GONE
                } else {
                    textView_number_of_garage_cars.text = response.residence.garageCars.toString()
                    tv_garage_quality_details_2.text = response.residence.garageQual ?: "N/A"
                }

                if (!response.residence.hasFireplace) {
                    cv_fireplace.visibility = View.GONE
                    tv_fire_place_quality_details_1.visibility = View.GONE
                    tv_fire_place_quality_details_2.visibility = View.GONE
                } else {
                    textView_number_of_fireplace.text = response.residence.fireplaces.toString()
                    tv_fire_place_quality_details_2.text = response.residence.fireplaceQu ?: "N/A"
                }

                tv_msZoning_details_2.text = response.residence.mszoning ?: "N/A"
                tv_sale_condition_details_2.text = response.residence.saleCondition ?: "N/A"
                tv_month_of_sold_details_2.text = response.residence.moSold ?: "N/A"
                tv_central_air_details_2.text = response.residence.centralAir ?: "N/A"
                tv_foundation_details_2.text = response.residence.foundation ?: "N/A"
                tv_street_details_2.text = response.residence.street ?: "N/A"
                tv_roof_style_details_2.text = response.residence.roofStyle ?: "N/A"
                tv_roof_material_details_2.text = response.residence.roofMatl ?: "N/A"
                tv_building_type_details_2.text = response.residence.bldgType ?: "N/A"
                tv_lot_shape_details_2.text = response.residence.lotShape ?: "N/A"
                tv_house_style_details_2.text = response.residence.houseStyle ?: "N/A"
                tv_alley_details_2.text = response.residence.alley ?: "N/A"
                tv_masonry_veneer_type_details_2.text = response.residence.masVnrType ?: "N/A"
                tv_masonry_veneer_area_details_2.text = response.residence.masVnrArea.toString()
                tv_exterior_covering_on_house_details_2.text = response.residence.exterior1st ?: "N/A"
                tv_exterior_covering_on_house_details_4.text = response.residence.exterior2nd ?: "N/A"
                tv_condition_details_2.text = response.residence.condition1 ?: "N/A"
                tv_condition_details_4.text = response.residence.condition2 ?: "N/A"
                tv_sale_type_details_2.text = response.residence.saleType ?: "N/A"
                tv_heating_quality_details_2.text = response.residence.heatingQc ?: "N/A"
                tv_exterior_quality_details_2.text = response.residence.exterQual ?: "N/A"
                tv_exterior_condition_details_2.text = response.residence.exterCond ?: "N/A"
                tv_kitchen_quality_details_2.text = response.residence.kitchenQual ?: "N/A"
            }

            getResidenceViewModel.errorLiveData.observe(viewLifecycleOwner) { error ->
                baseActivity.hideProgressDialog()
                error?.let {
                    try {
                        val errorMessage = JSONObject(error).getString("message")
                        Toast.makeText(
                            requireContext(),
                            errorMessage,
                            Toast.LENGTH_LONG
                        ).show()

                        Log.e("DescriptionFragment", "Description Fragment Error: $errorMessage")

                    } catch (e: JSONException) {
                        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}