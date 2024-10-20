package com.reinaldyrafli.code.orangecatcoffee.Services

import com.reinaldyrafli.code.orangecatcoffee.Controllers.DTO.Inventory
import com.reinaldyrafli.code.orangecatcoffee.Controllers.DTO.Product
import com.reinaldyrafli.code.orangecatcoffee.Controllers.DTO.Store
import com.reinaldyrafli.code.orangecatcoffee.Repositories.ProductRepository
import com.reinaldyrafli.code.orangecatcoffee.Repositories.StoresProductsRepository
import com.reinaldyrafli.code.orangecatcoffee.Repositories.StoresRepository
import org.springframework.stereotype.Service

@Service
class InventoryService(
    private val productRepository: ProductRepository,
    private val storesRepository: StoresRepository,
    private val storesProductsRepository: StoresProductsRepository,
) {
    fun getInventoryByStoreId(storeId: Int): Inventory {
        val store = storesRepository.findById(storeId).get()
        val storesProducts = storesProductsRepository.findByStoreId(storeId).toList()
        val products = storesProducts.map { storeProduct -> productRepository.findById(storeProduct.productId).get() }

        return Inventory(
            store = Store(store.id, store.name, store.description, store.image, store.address, store.coordinatesLatitude, store.coordinatesLongitude, store.disabled),
            products = products.map { product -> Product(product.id, product.name, product.price, product.description, product.image, null, product.maximumOrder) },
        )
    }
}
