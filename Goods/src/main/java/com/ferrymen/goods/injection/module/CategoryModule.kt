package com.ferrymen.goods.injection.module

import com.ferrymen.goods.service.CategoryService
import com.ferrymen.goods.service.impl.CategoryServiceImpl
import dagger.Module
import dagger.Provides

@Module
class CategoryModule {
    @Provides
    fun providersCategoryService(categoryService: CategoryServiceImpl): CategoryService {
        return categoryService
    }
}