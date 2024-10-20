package com.reinaldyrafli.code.orangecatcoffee.services

import com.reinaldyrafli.code.orangecatcoffee.primitives.Catalog
import com.reinaldyrafli.code.orangecatcoffee.primitives.Inventory
import com.reinaldyrafli.code.orangecatcoffee.primitives.Product
import com.reinaldyrafli.code.orangecatcoffee.primitives.Store
import com.reinaldyrafli.code.orangecatcoffee.repositories.CatalogsRepository
import com.reinaldyrafli.code.orangecatcoffee.repositories.ProductRepository
import com.reinaldyrafli.code.orangecatcoffee.repositories.StoresProductsRepository
import com.reinaldyrafli.code.orangecatcoffee.repositories.StoresRepository
import org.springframework.stereotype.Service

@Service
class InventoryService(
    private val productRepository: ProductRepository,
    private val storesRepository: StoresRepository,
    private val storesProductsRepository: StoresProductsRepository,
    private val catalogsRepository: CatalogsRepository,
) {
    fun getInventoryByStoreId(storeId: Int): Inventory {
        val store = storesRepository.findById(storeId).get()
        val storesProducts = storesProductsRepository.findByStoreId(storeId).toList()
        val products = storesProducts.map { storeProduct -> productRepository.findById(storeProduct.productId).get() }

        return Inventory(
            store = Store(storeId, store.name, store.description, store.image, store.address, store.coordinatesLatitude, store.coordinatesLongitude, store.disabled),
            products = products.map { product -> Product(product.id!!, product.name, product.price, product.description, product.image, product.maximumOrder, product.disabled, product.catalogId) },
        )
    }

    fun getCatalogs(): List<Catalog> {
        return catalogsRepository.findAll().map { catalog -> Catalog(catalog.id!!, catalog.name, catalog.description, catalog.image, catalog.disabled) }
    }
}
