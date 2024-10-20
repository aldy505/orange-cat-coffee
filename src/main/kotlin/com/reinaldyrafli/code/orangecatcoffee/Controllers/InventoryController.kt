package com.reinaldyrafli.code.orangecatcoffee.Controllers

import com.reinaldyrafli.code.orangecatcoffee.Controllers.DTO.Inventory
import com.reinaldyrafli.code.orangecatcoffee.Services.InventoryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/inventory")
class InventoryController(private val inventoryService: InventoryService) {
    @GetMapping
    fun getInventory(@RequestParam("store_id") storeId: Int): Inventory {
        return inventoryService.getInventoryByStoreId(storeId)
    }
}