package com.omarmattr.mcc_firebase1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.omarmattr.mcc_firebase1.adapter.UserAdapter
import com.omarmattr.mcc_firebase1.databinding.FragmentHomeBinding
import com.omarmattr.mcc_firebase1.databinding.RecycleUserBinding
import com.omarmattr.mcc_firebase1.model.User
import com.omarmattr.mcc_firebase1.uitls.MyResult


class HomeFragment : Fragment() {
private val viewModel:ViewModel by activityViewModels()
private lateinit var mBinding: FragmentHomeBinding
private val mAdapter=UserAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        mBinding=FragmentHomeBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllUsers()
        viewModel.getUserLiveData.observe(viewLifecycleOwner){
            when(it.status){
                MyResult.Status.ERROR->{
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
                    Log.e("ooo",it.message.toString())
                }
                MyResult.Status.SUCCESS->{
                    val array=it.data as ArrayList<User>
                    mAdapter.arrayList.clear()
                    mAdapter.arrayList.addAll(array)
                    mAdapter.notifyDataSetChanged()

                }
                MyResult.Status.LOADING->{}
                MyResult.Status.EMPTY->{}

            }
        }
        mBinding.recycleView.apply {
            adapter=mAdapter
        }
        mBinding.fabAdd.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().
            replace(R.id.navHostFragment,AddUserFragment()).addToBackStack(null).commit()
        }
    }


}