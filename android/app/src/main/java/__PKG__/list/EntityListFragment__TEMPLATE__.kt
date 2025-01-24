/*
 * Created by {{author}} on {{date_day}}/{{date_month}}/{{date_year}}.
 * {{company_header}}
 * Copyright (c) {{date_year}} {{author}}. All rights reserved.
 */

package {{package}}.list

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding 
import androidx.fragment.app.findFragment
import com.qmobile.qmobiledatasync.utils.CustomEntityListFragment
import com.qmobile.qmobileui.databinding.FragmentListBinding
import com.qmobile.qmobileui.list.EntityListFragment
import com.qmobile.qmobileui.ui.setupToolbarTitle

class EntityListFragment{{tableName}}(private val binding: ViewDataBinding) : CustomEntityListFragment {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       val fragment = view.findFragment<EntityListFragment>() // the caller
       var title = fragment.arguments?.getString("navbarTitle")?: ""
    
       val listData = (binding as? FragmentListBinding)?.viewModel?.dao?.getAll()
       listData?.observe(fragment.viewLifecycleOwner) {
           val count = it.count()
           fragment.activity?.setupToolbarTitle("$title $count")
       }
       val count = listData?.value?.count() ?: 0
       fragment.activity?.setupToolbarTitle("$title $count")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        onViewCreated(binding.root, savedInstanceState)
    }
}
