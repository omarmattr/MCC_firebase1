package com.omarmattr.mcc_firebase1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.omarmattr.mcc_firebase1.databinding.FragmentAddUserBinding
import com.omarmattr.mcc_firebase1.model.User
import com.omarmattr.mcc_firebase1.uitls.MyResult


class AddUserFragment : Fragment() {

    private val viewModel: ViewModel by activityViewModels()
    private lateinit var mBinding: FragmentAddUserBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentAddUserBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.addUserLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                MyResult.Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                MyResult.Status.SUCCESS -> {
                    Toast.makeText(requireContext(), "add SUCCESS", Toast.LENGTH_SHORT).show()
                    mBinding.txtName.text.clear()
                    mBinding.txtNum.text.clear()
                    mBinding.txtAddress.text.clear()
                    mBinding.txtNote.text.clear()


                }
                MyResult.Status.LOADING -> {
                }
                MyResult.Status.EMPTY -> {
                }

            }
        }
        mBinding.btnAdd.setOnClickListener {
            val name = mBinding.txtName.text
            val num = mBinding.txtNum.text
            val address = mBinding.txtAddress.text
            val note = mBinding.txtNote.text
            if (name.trim().isEmpty() || num.trim().isEmpty()
                || address.trim().isEmpty() || note.trim().isEmpty()
            ) {
                Toast.makeText(requireContext(), "تحقق من الحقول", Toast.LENGTH_SHORT).show()

            } else {
                viewModel.addUser(
                    User(
                        name.toString(), num.toString(), address.toString(), note.toString()
                    )
                )
            }
        }
    }

}