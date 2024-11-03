package com.reinaldyrafli.code.orangecatcoffee.controllers

import com.reinaldyrafli.code.orangecatcoffee.controllers.dto.inventory.CatalogProductResponse
import com.reinaldyrafli.code.orangecatcoffee.controllers.dto.inventory.InventoryListResponse
import com.reinaldyrafli.code.orangecatcoffee.services.InventoryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/inventory")
class InventoryController(private val inventoryService: InventoryService) {
    @GetMapping
    fun getInventory(@RequestParam("store_id") storeId: Int): InventoryListResponse {
        val inventory = inventoryService.getInventoryByStoreId(storeId)
        val catalogs = inventoryService.getCatalogs()

        val catalogProducts: MutableList<CatalogProductResponse> = mutableListOf()
        for (catalog in catalogs) {
            val products = inventory.products.filter { product -> product.categoryId == catalog.id }
            catalogProducts.add(CatalogProductResponse(catalog, products))
        }

        return InventoryListResponse(inventory.store, catalogProducts)
    }
}