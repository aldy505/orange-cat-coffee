package com.reinaldyrafli.code.orangecatcoffee.controllers.dto

import com.reinaldyrafli.code.orangecatcoffee.primitives.Catalog
import com.reinaldyrafli.code.orangecatcoffee.primitives.Product
import com.reinaldyrafli.code.orangecatcoffee.primitives.Store

data class InventoryListResponse(
    val stores: Store,
    val products: List<CatalogProductResponse>
)

data class CatalogProductResponse(
    val catalog: Catalog,
    val products: List<Product>
)
