package com.ethnicthv.webprojectcnpmrestful.data.service;

import com.ethnicthv.webprojectcnpmrestful.data.entity.Product;
import com.ethnicthv.webprojectcnpmrestful.data.entity.SellerProductList;
import com.ethnicthv.webprojectcnpmrestful.data.entity.io.SellerProcutListIO;
import com.ethnicthv.webprojectcnpmrestful.data.repository.SellerDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SellerService {

    private final SellerDataRepository repository;
    private final ProductService productService;

    @Autowired
    public SellerService(SellerDataRepository repository, ProductService productService) {
        this.repository = repository;
        this.productService = productService;
    }

    public SellerProductList save(SellerProductList sellerProductList) {
        SellerProcutListIO sellerProcutListIO = new SellerProcutListIO(sellerProductList);
        SellerProcutListIO savedList = repository.save(sellerProcutListIO);
        return convertToSellerProductList(savedList);
    }

    public Optional<SellerProductList> findById(String id) {
        Optional<SellerProcutListIO> optionalList = repository.findById(id);
        return optionalList.map(this::convertToSellerProductList);
    }

    public List<SellerProductList> findAll() {
        List<SellerProcutListIO> sellerProcutListIOs = repository.findAll();
        return sellerProcutListIOs.stream()
                .map(this::convertToSellerProductList)
                .collect(Collectors.toList());
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public SellerProductList addProduct(String id, Long productId) {
        Optional<SellerProcutListIO> optionalList = repository.findById(id);
        if (optionalList.isPresent()) {
            SellerProcutListIO list = optionalList.get();
            List<Long> productIds = list.getProductIds();
            productIds.add(productId);
            list.setProductIds(productIds);
            SellerProcutListIO savedList = repository.save(list);
            return convertToSellerProductList(savedList);
        }
        return null;
    }

    private SellerProductList convertToSellerProductList( SellerProcutListIO sellerProcutListIO) {
        if (sellerProcutListIO == null) return null;
        List<Long> productIds = sellerProcutListIO.getProductIds();
        List<Product> products = productIds.stream()
                .map(productService::getProduct)
                .filter(Objects::isNull)
                .collect(Collectors.toList());
        return new SellerProductList(sellerProcutListIO.getUserId(), products);
    }

}
